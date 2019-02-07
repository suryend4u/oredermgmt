package com.cc.oms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ITEM_CATALOG database table.
 * 
 */
@Entity
@Table(name="ITEM_CATALOG")
@NamedQuery(name="ItemCatalog.findAll", query="SELECT i FROM ItemCatalog i")
public class ItemCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int catalogid;

	private int totalqty;

/*	//bi-directional many-to-one association to Item
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="ITEMID")
	@JsonIgnoreProperties("itemCatalogs")
	private Item item;*/
	
	@Column(name = "ITEMID")
	private int itemId;
	
	public ItemCatalog() {
	}

	public int getCatalogid() {
		return this.catalogid;
	}

	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}

	public int getTotalqty() {
		return this.totalqty;
	}

	public void setTotalqty(int totalqty) {
		this.totalqty = totalqty;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

/*	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}*/

}