package com.example.mercari.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.mercari.domain.MoveDataDomain;

/**
 * データの振り分けに使用するレポジトリクラス.
 * 
 * @author hibiki.ono
 *
 */
@Repository
public class MoveDataRepository {

	private final String ORIGINAL_TABLE = "original";
	private final String CATEGORY_TABLE = "category";
	private final String ITEMS_TABLE = "items";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * カテゴリー名を保存するRowMapper.
	 */
	private final RowMapper<MoveDataDomain> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		MoveDataDomain categoryName = new MoveDataDomain();
		categoryName.setNameAll(rs.getString("category_name"));

		return categoryName;
	};

	/**
	 * カテゴリーテーブルの要素を保存するRowMapper.
	 */
	private final RowMapper<MoveDataDomain> CATEGORY_ROW_MAPPER = (rs, i) -> {
		MoveDataDomain category = new MoveDataDomain();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setParent(rs.getInt("parent"));

		return category;
	};

	/**
	 * originalテーブルからcategory_nameリストを取得.
	 * 
	 * @return
	 */
	public List<MoveDataDomain> findOriginalName() {
		String findSql = "SELECT category_name FROM " + ORIGINAL_TABLE + " WHERE category_name IS NOT NULL";
		List<MoveDataDomain> categoryNameList = template.query(findSql, CATEGORY_NAME_ROW_MAPPER);

		return categoryNameList;
	}

	/**
	 * 親カテゴリーの検索.
	 * 
	 * @param parentCategoryName
	 * @return
	 */
	public MoveDataDomain loadByParentCategory(String parentCategoryName) {
		try {
			String loadSql = "SELECT id, name, parent FROM " + CATEGORY_TABLE + " WHERE name=:name AND parent IS NULL";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", parentCategoryName);
			MoveDataDomain parentCategory = template.queryForObject(loadSql, param, CATEGORY_ROW_MAPPER);

			return parentCategory;
		} catch (Exception e) {
			System.err.println("親カテゴリーが見つかりませんでした");

			return null;
		}
	}

	/**
	 * 子カテゴリーと孫カテゴリーの検索.
	 * 
	 * @param categoryName
	 * @param parentId
	 * @return
	 */
	public MoveDataDomain loadCategoryName(String categoryName, Integer parentId) {
		try {
			String loadSql = "SELECT id, name, parent FROM " + CATEGORY_TABLE + " WHERE name=:name AND parent=:parent";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", categoryName).addValue("parent",
					parentId);
			MoveDataDomain category_name = template.queryForObject(loadSql, param, CATEGORY_ROW_MAPPER);

			return category_name;
		} catch (Exception e) {
			System.err.println("子カテゴリーも孫カテゴリーも見つかりませんでした");

			return null;
		}
	}

	/**
	 * categoryテーブルへの登録.
	 * 
	 * @param nameAll
	 * @param parentId
	 * @param name
	 */
	public void insertCategoryInfo(String nameAll, Integer parentId, String name) {
		String insertSql = "INSERT INTO " + CATEGORY_TABLE
				+ " (name_all, parent, name) VALUES (:nameAll, :parentId, :categoryName)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll)
				.addValue("parentId", parentId).addValue("categoryName", name);
		template.update(insertSql, param);
	}

	/**
	 * itemsテーブル　categoryカラムにidを登録.
	 * 
	 * @param id
	 */
	public void insertItemsCategory(Integer id) {
		String insertSql = "INSERT INTO " + ITEMS_TABLE + " (category) VALUES (:category)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("category", id);
		template.update(insertSql, param);
	}
}
