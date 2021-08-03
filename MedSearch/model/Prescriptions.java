package model;

import java.util.Date;


public class Prescriptions {
	protected int prescriptionId;
	protected Customers customer;
	protected Drugs drug;
	protected Date fillDate;
	protected Doctors doctor;
	
	// all-fields constructor
	public Prescriptions(int prescriptionId, Customers customer, Drugs drug, Date fillDate, Doctors doctor) {
		this.prescriptionId = prescriptionId;
		this.customer = customer;
		this.drug = drug;
		this.fillDate = fillDate;
		this.doctor = doctor;
	}

	// PK-only constructor
	public Prescriptions(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	// no-PK constructor
	public Prescriptions(Customers customer, Drugs drug, Date fillDate, Doctors doctor) {
		this.customer = customer;
		this.drug = drug;
		this.fillDate = fillDate;
		this.doctor = doctor;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Drugs getDrug() {
		return drug;
	}

	public void setDrug(Drugs drug) {
		this.drug = drug;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public Doctors getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}
}