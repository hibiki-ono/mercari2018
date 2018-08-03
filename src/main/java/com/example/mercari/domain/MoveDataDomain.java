package com.example.mercari.domain;

/**
 * データの振り分けに使用するドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class MoveDataDomain {

	private Integer id;
	private String name;
	private Integer parent;
	private String nameAll;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
}
