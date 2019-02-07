package com.cc.oms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;

	private String name;

	private String phone;

	// bi-directional many-to-one association to Address
	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE })
	@JsonIgnoreProperties("user")
	private List<Address> addresses;

/*	//bi-directional many-to-one association to CustOrder
	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE })
	@JsonIgnoreProperties("user")
	private List<CustOrder> custOrders;*/

	public User() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setUser(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setUser(null);

		return address;
	}

/*	public List<CustOrder> getCustOrders() {
		return this.custOrders;
	}

	public void setCustOrders(List<CustOrder> custOrders) {
		this.custOrders = custOrders;
	}
*/
/*	public CustOrder addCustOrder(CustOrder custOrder) {
		getCustOrders().add(custOrder);
		custOrder.setUser(this);

		return custOrder;
	}

	public CustOrder removeCustOrder(CustOrder custOrder) {
		getCustOrders().remove(custOrder);
		custOrder.setUser(null);

		return custOrder;
	}*/

}