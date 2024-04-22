package mdbgyak;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		MongoClient mongoClient = MongoClients.create("mongodb://localhost:2701");
        MongoDatabase db = mongoClient.getDatabase("DJ7PNE");
        com.mongodb.client.MongoCollection<Document> table = db.getCollection("factory");
        Document doc = new Document();
		System.out.println("Adatb�zis megnyit�sa �s collection megnyit�sa k�sz");
            
		System.out.println("Els� elem neve a collectionben");
        doc = table.find().first();
        String res = doc.getString("name");
        System.out.println(res);
        
		System.out.println("A collectionben l�v� elemek");
        for (Document doc2 :  table.find()) {
        	System.out.println(doc2.toJson());
        }

		System.out.println("Egy factory elem hozz�ad�sa");
		Document newFactory = new Document("_id", 8)
			    .append("name", "java inserted")
			    .append("place", "germany")
			    .append("foundation_date", "2022.06.30")
			    .append("customer_id", 222)
			    .append("allapot", "uj")
			    .append("price", 400000);
		
		table.insertOne(newFactory);
		
		System.out.println("T�bb factory elem hozz�ad�sa");
		Document newFactory2 = new Document("_id", 9)
			    .append("name", "java inserted2")
			    .append("place", "germany")
			    .append("foundation_date", "1999.12.10")
			    .append("customer_id", 444)
			    .append("allapot", "tul_regi")
			    .append("price", 232000);
		
		Document newFactory3 = new Document("_id", 10)
			    .append("name", "java inserted3")
			    .append("place", "germany")
			    .append("foundation_date", "2003.04.23")
			    .append("customer_id", 111)
			    .append("allapot", "tul_regi")
			    .append("price", 600000);
		
		ArrayList<Document> factories = new ArrayList<>();
		factories.add(newFactory2);
		factories.add(newFactory3);
		
		table.insertMany(factories);
		
		System.out.println("Azok az elemek, amelyeknek a felbecs�lt �ra kevesebb, mint 500000");
        MongoCursor<Document> cursor = (MongoCursor<Document>) table.find(lt("price", 500000)).iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
		
		System.out.println("A 'tul_regi' �llapot� gy�rak t�rl�se");
        MongoCursor<Document> cursor2 = table.find(eq("allapot", "tul_regi")).iterator();
        try {
            while(cursor2.hasNext()) {
                Document docDel = cursor2.next();
            	table.deleteOne(docDel);
            }
        } finally {
            cursor2.close();
        }
        
        System.out.println("A collectionben maradt elemek");
        for (Document doc2 :  table.find()) {
        	System.out.println(doc2.toJson());
        }
		
		mongoClient.close();

        
	}
	

}