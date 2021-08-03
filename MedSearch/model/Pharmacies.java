package model;

import java.util.Date;


public class Pharmacies extends Users {
	protected String pharmacyName;
	protected Date openTime;
	protected Date closeTime;
	
	// all-fields constructor
	public Pharmacies(String userName, String password, String phone, String street1, String street2, String city,
			String state, String zipcode, String pharmacyName, Date openTime, Date closeTime) {
		super(userName, password, phone, street1, street2, city, state, zipcode);
		this.pharmacyName = pharmacyName;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	// PK-only constructor
	public Pharmacies(String userName) {
		super(userName);
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
}