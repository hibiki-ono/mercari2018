package com.example.mercari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mercari.domain.MoveDataDomain;
import com.example.mercari.repository.MoveDataRepository;

/**
 * データの振り分けをするためのコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Controller
@RequestMapping(value = "/move")
public class MoveDataController {

	@Autowired
	private MoveDataRepository repository;

	@RequestMapping(value = "/index")
	public String index() {
		return "/move_data/moveData";
	}

	@RequestMapping(value = "/data")
	public String moveData() {
		int count = 1;
		List<MoveDataDomain> categoryNameList = repository.findOriginalName();
		
		for(int i = 0; i < categoryNameList.size(); i++) {
			System.out.println("実行回数: " + count + "回目");
			count++;
			
			String nameAll = categoryNameList.get(i).getNameAll();
			String[] categoryName = nameAll.split("/");
			System.out.println("categoryName: " + nameAll);
			
			MoveDataDomain category = repository.loadByParentCategory(categoryName[0]);
			if(category == null) {
				repository.insertCategoryInfo(null, null, categoryName[0]);
				System.out.println("親カテゴリー登録完了");
			}
			
			String childCategoryName = categoryName[1];
			Integer parentId = repository.loadByParentCategory(categoryName[0]).getId();
			MoveDataDomain findChild = repository.loadCategoryName(childCategoryName, parentId);
			if(findChild == null) {
				repository.insertCategoryInfo(null, parentId, childCategoryName);
				System.out.println("子カテゴリー登録完了");
			}
			
			String grandCategoryName = categoryName[2];
			Integer childId = repository.loadCategoryName(childCategoryName, parentId).getId();
			MoveDataDomain findGrand = repository.loadCategoryName(grandCategoryName, childId);
			if(findGrand == null) {
				repository.insertCategoryInfo(nameAll, childId, grandCategoryName);
				System.out.println("孫カテゴリー登録完了");
			}
			
			System.err.println("このカテゴリーパターンはすでに登録されています");
		}

		return "/move_data/finish";
	}
}
