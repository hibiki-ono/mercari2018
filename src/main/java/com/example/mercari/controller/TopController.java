package com.example.mercari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Top画面を表示させるコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Controller
@Transactional
@RequestMapping(value="/top")
public class TopController {

	@RequestMapping(value="/index")
	public String index() {
		
		return "mercari/top";
	}
}
