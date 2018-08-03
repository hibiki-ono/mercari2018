package com.example.mercari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mercari.domain.Item;
import com.example.mercari.service.ItemService;

/**
 * 商品一覧を表示させるコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/itemList")
public class ItemListController {

	@Autowired
	private ItemService service;

	/**
	 * 商品一覧画面表示.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("pageNumber", 1);

		return "mercari/itemList";
	}

	/**
	 * ページング.
	 * 
	 * @param key   遷移するページ番号.
	 * @param model
	 * @return ajaxを使用しているため、配列に変換.
	 */
	@RequestMapping(value = "/pageChange")
	@ResponseBody
	public Item[] changePage(Integer key, Model model) {
		System.out.println("key: " + key);

		List<Item> itemList = service.findAll(key);
		Item[] itemLists = itemList.toArray(new Item[itemList.size()]);

		for (int i = 0; i < itemList.size(); i++) {
			String brand = itemLists[i].getBrand();
			boolean isNullBrand = (brand == null);

			if (isNullBrand) {
				itemLists[i].setBrand("");
			}
		}

		return itemLists;
	}
}
