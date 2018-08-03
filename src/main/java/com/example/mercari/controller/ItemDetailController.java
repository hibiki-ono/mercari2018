package com.example.mercari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mercari.domain.Item;
import com.example.mercari.service.ItemService;

/**
 * 商品詳細を表示させるコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Controller
@Transactional
@RequestMapping(value="/itemDetail")
public class ItemDetailController {

	@Autowired
	private ItemService service;
	
	/**
	 * 商品詳細画面表示.
	 * 
	 * @param itemId 商品ID.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Integer itemId, Model model) {
		System.out.println("itemID: " + itemId);
		
		Item itemDetail = service.loadByItemId(itemId);
		
		model.addAttribute("itemDetail", itemDetail);
		
		return "mercari/itemDetail";
	}
}
