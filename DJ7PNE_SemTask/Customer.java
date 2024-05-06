package com.database;

public class Customer {

	private long Taxnumber;
	private String Named;
	private String Origin;
	private int Postalcode;
	private String Street;
	
	public long getTaxnumber() {
		return Taxnumber;
	}
	public void setTaxnumber(long taxnumber) {
		Taxnumber = taxnumber;
	}
	public String getNamed() {
		return Named;
	}
	public void setNamed(String named) {
		Named = named;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public int getPostalcode() {
		return Postalcode;
	}
	public void setPostalcode(int postalcode) {
		Postalcode = postalcode;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	
}
