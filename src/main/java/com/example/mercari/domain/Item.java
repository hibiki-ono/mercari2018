package com.example.mercari.domain;

/**
 * 商品情報を扱うドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class Item {

	/**　商品ID*/
	private Integer itemId;
	/**　商品名*/
	private String itemName;
	/**　商品状態*/
	private Integer condition;
	/**　ブランド*/
	private String brand;
	/**　商品価格*/
	private double price;
	/**　発送状況*/
	private Integer shipping;
	/**　商品説明*/
	private String description;
	/**　カテゴリーID*/
	private Integer category;
	/**　親カテゴリーID*/
	private Integer parent;
	/**　孫カテゴリー名*/
	private String categoryName;
	/**　カテゴリー名*/
	private String nameAll;
	/**　親カテゴリー名*/
	private String parentName;
	/**　子カテゴリー名*/
	private String childName;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

}
