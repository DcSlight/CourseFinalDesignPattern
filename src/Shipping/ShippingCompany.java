package Shipping;


import java.util.Objects;

import Components.Contact;

public abstract class ShippingCompany{
	private int id;
	private static int counter=0;
	private Contact contact;
	private double shipFee;
	private int importTax;
	
	public ShippingCompany(Contact contact,double shipFee,int importTax) {
		this.contact=contact;
		this.shipFee=shipFee;
		this.importTax=importTax;
		counter++;
		this.id=counter;
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public double getShipFee() {
		return shipFee;
	}

	public void setShipFee(double shipFee) {
		this.shipFee = shipFee;
	}

	public int getImportTax() {
		return importTax;
	}

	public void setImportTax(int importTax) {
		this.importTax = importTax;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingCompany other = (ShippingCompany) obj;
		return contact.equals(other.contact) && importTax == other.importTax && shipFee == shipFee;
	}

	@Override
	public String toString() {
		return "ShippingCompany [id=" + id + ", contact=" + contact + ", shipFee=" + shipFee + ", importTax="
				+ importTax + "]";
	}
	
	
	
	
}
