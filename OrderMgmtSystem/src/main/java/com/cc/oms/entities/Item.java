package com.cc.oms.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the ITEM database table.
 * 
 */
@Entity
@Table(name = "ITEM")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemid;

	private String description;

	private String name;

	private BigDecimal price;

/*	//bi-directional many-to-one association to ItemCatalog
	@OneToMany(mappedBy = "item", cascade = { CascadeType.MERGE })
	@JsonIgnoreProperties("item")
	private List<ItemCatalog> itemCatalogs;*/

	public Item() {
	}

	public int getItemid() {
		return this.itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + itemid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemid != other.itemid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

/*	public List<ItemCatalog> getItemCatalogs() {
		return this.itemCatalogs;
	}

	public void setItemCatalogs(List<ItemCatalog> itemCatalogs) {
		this.itemCatalogs = itemCatalogs;
	}

	public ItemCatalog addItemCatalog(ItemCatalog itemCatalog) {
		getItemCatalogs().add(itemCatalog);
		itemCatalog.setItem(this);

		return itemCatalog;
	}

	public ItemCatalog removeItemCatalog(ItemCatalog itemCatalog) {
		getItemCatalogs().remove(itemCatalog);
		itemCatalog.setItem(null);

		return itemCatalog;
	}*/

}