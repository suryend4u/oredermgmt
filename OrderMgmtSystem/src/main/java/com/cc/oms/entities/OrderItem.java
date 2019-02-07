package com.cc.oms.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the ORDER_ITEMS database table.
 * 
 */
@Entity
@Table(name="ORDER_ITEMS")

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_ITEM_ID")
	private int orderItemId;

	private int quantity;

	//bi-directional many-to-one association to CustOrder
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "ORDER_ID", nullable = false, updatable = false, insertable = true)
	@JsonIgnoreProperties("orderItems")
	private CustOrder custOrder;

	// bi-directional many-to-one association to Item
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ITEMID")
	private Item item;

	public OrderItem() {
	}

	public int getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CustOrder getCustOrder() {
		return this.custOrder;
	}

	public void setCustOrder(CustOrder custOrder) {
		this.custOrder = custOrder;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}