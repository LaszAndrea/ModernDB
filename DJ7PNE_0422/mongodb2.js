// metódusok törlése ha már léteznek
db.system.js.remove({ _id: "add_customer" });
db.system.js.remove({ _id: "add_factory" });

// új function, megadott adatokkal való factory létrehozásához
db.system.js.save(
    {
        _id: "add_factory",
        value: function (id, name, place, foundation_date, allapot){
            db.factory.insertOne({
                _id: id,
                name: name,
                place: place,
                foundation_date: foundation_date,
                allapot: allapot
            })
        }
    }
)

//új function, customer létrehozására, idegenkulcs megadásával is
db.system.js.save(
    {
        _id: "add_customer",
        value: function (id, name, origin, postalCode, street, factory_id){
            db.customers.insertOne({
                _id: id,
                name: name,
                origin: origin,
                place: {
                    postalcode: postalCode,
                    street: street
                },
                factory_id: factory_id
            })
        }
    }
)

//tárolt függvény arra, hogy megadott origin alapján kikeresse az adott customere-ket
db.system.js.save(
    {
        _id: "getCustomersByOrigin",
        value: function (origin){
            var q = db.customers.find({
                origin: origin
            });
            while(q.hasNext()){
                print(tojson(q.next()));
            }
        }
    }
)

//function, amely kiírja mely customerek tartoznak az egyes gyárhoz
function printCustomersForFactory(factoryId) {
    db.factory.aggregate([
        { $match: { "_id": factoryId } },
        { $lookup: {
            from: "customers",
            localField: "customer_id",
            foreignField: "_id",
            as: "customers"
        }},
        { $unwind: "$customers" },
        { $project: { "factory_name": "$name", "customer_names": "$customers.name" }}
    ]).forEach(function(doc) {
        print("A(z) " + doc.factory_name + " gyárhoz tartozó ügyfelek: " + doc.customer_names);
    });
}

//minden factoryhez adjunk hozzá egy mezőt "price" néven, ami a felbecsült értéket jelzi
db.factory.updateMany({}, { $set: { price: 500000 } });

//function, amely a "túl_régi" állapotú gyárak árát 25%-al csökkenti
db.factory.find().forEach(
    function(obj){
        if(obj.allapot == "tul_regi"){
            db.factory.update({
                _id: obj._id,
            }, {
                $mul : {price : 0.75}
            });
        }
    }
)

//számoljuk meg azon gyárak számát, mely ára kevesebb, mint 500000
db.factory.find({"$where": function(){
    if(this.price < 500000)
        return true;
    else
        return false;
}})

//írassuk ki place szerint az átlagárakat a factory collectionnél
db.factory.aggregate(
    {
        $group : {"_id": "$place", "avgprice" : {"$avg": "$price"}}
    }
)

//hány factory rendelkezik helyenként "tul_regi" státusszal
db.cars.aggregate([
  { $match: { status: "sérült" } }, // Csak a sérült autókat vesszük figyelembe
  { $group: { _id: "$type", count: { $sum: 1 } } }, // Csoportosítás típus szerint és számolás
  { $sort: { count: -1 } } // Csökkenő sorrendbe rendezés a számok alapján
])

db.factory.aggregate([
    { 
        $match: { "allapot": "tul_regi" } 
    },
    {
        $group: {"_id": "$place", "db": { $sum: 1 }}
    }, 
    {
        $sort: {"db": -1}
    }
])

//scriptek betöltése
db.loadServerScripts();

//functionek tesztelése
add_factory("555", "inserted_with_function", "hungary", "2022.02.02", "új");
add_customer("c111", "customer_name", "customer_origin", "12345", "Example Street", ["001", "002"]);
getCustomersByOrigin("hungary");
printCustomersForFactory("004");

