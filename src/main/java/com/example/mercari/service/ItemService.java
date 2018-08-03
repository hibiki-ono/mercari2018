package com.example.mercari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mercari.domain.Item;
import com.example.mercari.repository.ItemRepository;

/**
 * 商品情報を取得するメソッドを利用するサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	/**
	 * 商品情報の全件検索メソッド.
	 * 
	 * @param pageNum ページ番号
	 * @return
	 */
	public List<Item> findAll(long pageNum) {
		return repository.findAll(pageNum);
	}

	/**
	 * 商品詳細情報取得メソッド.
	 * 
	 * @param itemId 商品ID
	 * @return
	 */
	public Item loadByItemId(Integer itemId) {
		return repository.loadByItemId(itemId);
	}
}
