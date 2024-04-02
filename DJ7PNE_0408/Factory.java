package com.database;

import java.time.LocalDate;

public class Factory {
	
	private long FTaxnumber;
	private LocalDate FoundationDate;
	private String fName;
	private String Premise;
	
	public long getFTaxnumber() {
		return FTaxnumber;
	}
	public void setFTaxnumber(long fTaxnumber) {
		FTaxnumber = fTaxnumber;
	}
	public LocalDate getFoundationDate() {
		return FoundationDate;
	}
	public void setFoundationDate(LocalDate foundationDate) {
		FoundationDate = foundationDate;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getPremise() {
		return Premise;
	}
	public void setPremise(String premise) {
		this.Premise = premise;
	}

}
