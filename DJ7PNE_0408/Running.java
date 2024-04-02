package com.database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Running {

	public static void main(String[] args) {

		AllDao dao = new AllDao();
		
        Scanner sc = new Scanner(System.in);
        
        System.out.println("----------------------------------------");
        System.out.print("Do you want to insert customer data? (y/n): ");
        
        String decision = sc.nextLine();
        
        if(decision.contains("y")) {
		
			//insert a customer
        	
        	System.out.println("Please provide the datas below: ");
            System.out.print("Taxnumber: ");
            long taxnumber = sc.nextLong();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Origin: ");
            String origin = sc.nextLine();

            System.out.print("Postalcode: ");
            int postalCode = sc.nextInt();
            sc.nextLine();

            System.out.print("Street: ");
            String street = sc.nextLine();
        	
			Customer customer = new Customer();
			
			customer.setTaxnumber(taxnumber);
			customer.setNamed(name);
			customer.setOrigin(origin);
			customer.setPostalcode(postalCode);
			customer.setStreet(street);
			dao.saveCustomer(customer);
			
        }
		
		//getAllCustomerData
		
        System.out.println("----------------------------------------");
        System.out.println("All customer data:\n");
		
		List<Customer> customers = dao.getAllCustomerData();
		for(Customer c : customers) {
			System.out.println("Taxnumber: " + c.getTaxnumber() + ", name: " + c.getNamed() +
					", origin: " + c.getOrigin() + ", postalcode: " + c.getPostalcode() + ", street: " + c.getStreet());
		}
		
        System.out.println("----------------------------------------");
        System.out.print("Do you want to insert factory data? (y/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("y")) {
		
			//insert a factory
        	
        	System.out.println("Please provide the datas below: ");
            System.out.print("Taxnumber: ");
            long taxnumber = sc.nextLong();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Foundation date: (yyyy-mm-dd)");
            String foundationDate = sc.nextLine();

            System.out.print("Premise: ");
            String premise = sc.nextLine();
        	
			Factory factory = new Factory();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(foundationDate, formatter);
			
			factory.setFTaxnumber(taxnumber);
			factory.setfName(name);
			factory.setFoundationDate(date);
			factory.setPremise(premise);
			dao.saveFactory(factory);
			
        }
        
        System.out.println("----------------------------------------");
        System.out.print("Do you want to connect a customer with a factory? (y/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("y")) {
        
            System.out.print("Which customer do you want to add connection to? (taxnumber): ");
            
            long taxnumber = sc.nextLong();
            sc.nextLine();
            
            //Customer selectedCustomer = dao.getCustomerById(taxnumber);
            
            System.out.print("Which factory do you want to add connection to? (taxnumber): ");
            
            long ftaxnumber = sc.nextLong();
            sc.nextLine();
            
            //Factory selectedFactory = dao.getFactoryById(ftaxnumber);
            
            CF cf = new CF();
            cf.setFTaxnumberCF(ftaxnumber);
            cf.setTaxnumberCF(taxnumber);
            
            dao.saveRelationship(cf);
        
        }
		
		//getAllFactoryData
		
        System.out.println("----------------------------------------");
        System.out.println("All factory data:\n");
		
		List<Factory> factories = dao.getAllFactoryData();
		for(Factory f : factories) {
			System.out.println("Taxnumber: " + f.getFTaxnumber() + ", name: " + f.getfName() +
					", foundationDate: " + f.getFoundationDate() + ", premise: " + f.getPremise());
		}
		
		//getAllRelationships
		
        System.out.println("----------------------------------------");
        System.out.println("All customer's connection to a factory:\n");
		
		List<CF> cfList = dao.findAllFactoryCustomerRelationships();
		for (CF cf : cfList) {
		    if (cf != null) {
		        System.out.println("Factory: " + cf.getFTaxnumberCF() + ", Customer: " + cf.getTaxnumberCF());
		    } else {
		        System.out.println("Encountered null CF object");
		    }
		}
		
		//all factories which have at least one connection
		
		System.out.println("----------------------------------------");
        System.out.println("All factories which have at least one connection:\n");
		
		List<Factory> factoryList = dao.findFactoriesWithCustomers();
		for (Factory f : factoryList) {
			System.out.println("Taxnumber: " + f.getFTaxnumber() + ", name: " + f.getfName() +
					", foundationDate: " + f.getFoundationDate() + ", premise: " + f.getPremise());
		}
		
		//delete a factory
		
		System.out.println("----------------------------------------");
        System.out.print("Do you want to delete a factory? (y/n): ");
        
        String delete = sc.nextLine();
        
        if(delete.contains("y")) {
        
	        System.out.println("----------------------------------------");
	        System.out.print("Please write down the taxnumber of the factory you want to delete: ");
	        long id = sc.nextLong();
	
	        dao.deleteFactoryRelationshipById(id);
	        dao.deleteFactoryById(id);
	
	        System.out.println("Factory deleted successfully.");
	        
	        sc.close();
	        
	        //getAllFactoryData
			
	        System.out.println("----------------------------------------");
	        System.out.println("All factory data:\n");
			
			factories = dao.getAllFactoryData();
			for(Factory f : factories) {
				System.out.println("Taxnumber: " + f.getFTaxnumber() + ", name: " + f.getfName() +
						", foundationDate: " + f.getFoundationDate() + ", premise: " + f.getPremise());
			}
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Program has finished running.");

	}

}
