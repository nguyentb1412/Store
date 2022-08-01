package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.Food;
import com.caphephin.voucher.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {

	@Autowired
	Environment env;

	@GetMapping("/foods")
	public ResponseEntity<List<Food>> getAllFoods(@RequestHeader(name = "accept-language", required=false) String language) throws IOException {
		List<Food> foodList = new ArrayList<>();

		String imageUrl = env.getProperty("imageUrl");

		if(language !=null && language.toLowerCase().equals("vi")) {

			foodList.add(new Food("Bánh mì thịt xá xíu và trứng chiên ","￥660","",imageUrl + "food/food1.jpg"));
			foodList.add(new Food("Bánh mì thịt bò nướng, sốt cà chua","￥690","",imageUrl + "food/food2.jpg"));
			foodList.add(new Food("Xôi thịt xá xíu và trứng ốp la","￥640","",imageUrl + "food/food3.jpg"));
			foodList.add(new Food("Xôi thịt gà xé cay","￥660","",imageUrl + "food/food4.jpg"));

			return new ResponseEntity<>(foodList, HttpStatus.OK);
		}

		foodList.add(new Food("チャーシュー＆焼き卵バインミー","￥660","ベトナムの定番バインミーです。",imageUrl + "food/food1.jpg"));
		foodList.add(new Food("牛焼肉＆自家製トマトソースバインミー","￥690","",imageUrl + "food/food2.jpg"));
		foodList.add(new Food("チャーシュー＆目玉卵おこわ","￥640","",imageUrl + "food/food3.jpg"));
		foodList.add(new Food("ピリ辛チキンおこわ","￥660","",imageUrl + "food/food4.jpg"));

		return new ResponseEntity<>(foodList, HttpStatus.OK);
	}
}
