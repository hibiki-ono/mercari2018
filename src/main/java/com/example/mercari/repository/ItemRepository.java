package com.example.mercari.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.mercari.domain.Item;

/**
 * 商品情報を扱うレポジトリクラス.
 * 
 * @author hibiki.ono
 *
 */
@Repository
public class ItemRepository {

	private final String TABLE_NAME = "items";

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final RowMapper<Item> ITEM_LIST_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setItemId(rs.getInt("item_id"));
		item.setItemName(rs.getString("item_name"));
		item.setCondition(rs.getInt("condition"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getDouble("price"));
		item.setNameAll(rs.getString("name_all"));

		String[] splitCategory = split(rs.getString("name_all"));
		item.setParentName(splitCategory[0]);
		item.setChildName(splitCategory[1]);
		item.setCategoryName(splitCategory[2]);

		return item;
	};

	private final RowMapper<Item> ITEM_DETAIL_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setItemId(rs.getInt("item_id"));
		item.setItemName(rs.getString("item_name"));
		item.setCondition(rs.getInt("condition"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getDouble("price"));
		item.setDescription(rs.getString("description"));
		item.setNameAll(rs.getString("name_all"));

		return item;
	};

	/**
	 * カテゴリー名を、親・子・孫に分割.
	 * 
	 * @param name_all
	 * @return
	 */
	private String[] split(String name_all) {
		if(name_all == null) {
			String[] empty = new String[3];
			empty[0] = "";
			empty[1] = "";
			empty[2] = "";
			
			return empty;
		}
		String[] splitCategory = name_all.split("/");

		return splitCategory;
	}

	/**
	 * 商品情報の全件検索.
	 * 
	 * @param pageNum ページ番号
	 * @return
	 */
	public List<Item> findAll(long pageNum) {
		String findAllSql = "SELECT i.id AS item_id, i.name AS item_name, condition, brand, price, name_all FROM "
				+ TABLE_NAME + " i LEFT OUTER JOIN category c ON(category=c.id) ORDER BY item_id LIMIT 30 OFFSET "
				+ (pageNum * 30 - 30);
		List<Item> itemList = template.query(findAllSql, ITEM_LIST_ROW_MAPPER);

		return itemList;
	}

	/**
	 * 商品詳細情報を取得.
	 * 
	 * @param itemId 商品ID
	 * @return
	 */
	public Item loadByItemId(Integer itemId) {
		String loadSql = "SELECT i.id AS item_id, i.name AS item_name, condition, brand, price, description, name_all FROM "
				+ TABLE_NAME + " i LEFT OUTER JOIN category c ON(category=c.id) WHERE i.id=:itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		Item itemDetail = template.queryForObject(loadSql, param, ITEM_DETAIL_ROW_MAPPER);

		return itemDetail;
	}
}
