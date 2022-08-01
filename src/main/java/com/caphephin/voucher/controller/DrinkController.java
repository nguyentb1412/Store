package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.Drink;
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
public class DrinkController {

	@Autowired
	Environment env;

	@GetMapping("/drinks")
	public ResponseEntity<List<Drink>> getAllFoods(@RequestHeader(name = "accept-language", required=false) String language) throws IOException {
		List<Drink> drinkList = new ArrayList<>();

		String imageUrl = env.getProperty("imageUrl");

		if(language !=null && language.toLowerCase().equals("vi")) {

			drinkList.add(new Drink("Cà phê phin sữa/ Cà phê phin đen","￥350 - ￥300","Cà phê truyền thống hương vị Việt",imageUrl + "drink/drink1.jpg"));
			drinkList.add(new Drink("Cà phê nến sữa/ Cà phê nến đen","￥350 - ￥400","Luôn nhóm lửa để giữ độ ấm nóng cho cà phê Việt.",imageUrl + "drink/drink2.jpg"));
			drinkList.add(new Drink("Bạc xỉu","￥350","Bạc xỉu, thức uống dành cho người nặng tình.",imageUrl + "drink/drink3.jpg"));
			drinkList.add(new Drink("Cà phê trứng","￥450","Một lớp kem trứng béo ngậy cùng với một lớp cà phê Việt đắng, chúng tạo ra một ly cà phê trứng thơm ngon, béo ngọt.",imageUrl + "drink/drink4.jpg"));
			drinkList.add(new Drink("Cà phê cốt dừa","￥500","Hương béo ngậy của dừa, vị ngọt của sữa pha vị đậm đà của cafe, khiến cho ly cà phê cốt dừa có một vị ngon ngọt không thể cưỡng lại.",imageUrl + "drink/drink5.jpg"));
			drinkList.add(new Drink("Sinh tố bơ","￥600","ベトナム人にとって、アボカドは有名な果物です。濃厚でとてもクリーミアボカドスムージーはベトナムには超人気がある。",imageUrl + "drink/drink6.jpg"));
			drinkList.add(new Drink("Sinh tố xoài","￥500","",imageUrl + "drink/drink7.jpg"));
			drinkList.add(new Drink("Sinh tố dâu","￥400","",imageUrl + "drink/drink8.jpg"));
			drinkList.add(new Drink("Cacao (nóng / lạnh)","￥380","",imageUrl + "drink/drink9.jpg"));
			drinkList.add(new Drink("Cacao cốt dừa (nóng)","￥420","",imageUrl + "drink/drink10.jpg"));
			drinkList.add(new Drink("Cacao cà phê (nóng/lạnh)","￥420","",imageUrl + "drink/drink11.jpg"));
			drinkList.add(new Drink("Trà sữa trân châu đường đen","￥550","",imageUrl + "drink/drink12.jpg"));
			drinkList.add(new Drink("Trà gừng nóng","￥350","",imageUrl + "drink/drink13.jpg"));
			drinkList.add(new Drink("Nước sấu","￥350","",imageUrl + "drink/drink14.jpg"));

			return new ResponseEntity<>(drinkList, HttpStatus.OK);
		}

		drinkList.add(new Drink("フィンミルクコーヒ/ フィンブラックコーヒー","￥350 - ￥300","フィンは、コーヒーを抽出するためのフィルタです。ベトナムコーヒーの濃い味わいは、この特殊なフィルターによって生み出されています。",imageUrl + "drink/drink1.jpg"));
		drinkList.add(new Drink("キャンドルミルクコーヒー/ キャンドルブラックコーヒー","￥350 - ￥400","コーヒーをずっと温めるためにカップの下に蝋燭をつけます。",imageUrl + "drink/drink2.jpg"));
		drinkList.add(new Drink("ミルクたっぷりコーヒー（バクシウ）","￥350","バク・シウは甘いもの好きな人にとって素晴らしい飲み物です。",imageUrl + "drink/drink3.jpg"));
		drinkList.add(new Drink(" ホットエッグコーヒ","￥450","魂を温めてくれる最高のコーヒー特に冬にはおすすめの飲み物。濃厚で、泡沫をたっぷり含んだ卵を、スプーンで優しく混ぜ合わせて、ゆったりと味わう。",imageUrl + "drink/drink4.jpg"));
		drinkList.add(new Drink("ココナッツコーヒー","￥500","ココナッツコーヒーはアイスクリームに比べて飲みやすい、体力回復やリラックス効果のある飲み物",imageUrl + "drink/drink5.jpg"));
		drinkList.add(new Drink("アボカドスムージー","￥600","ベトナム人にとって、アボカドは有名な果物です。濃厚でとてもクリーミアボカドスムージーはベトナムには超人気がある。",imageUrl + "drink/drink6.jpg"));
		drinkList.add(new Drink("マンゴースムージー","￥500","",imageUrl + "drink/drink7.jpg"));
		drinkList.add(new Drink("ストロベリースムージー","￥400","",imageUrl + "drink/drink8.jpg"));
		drinkList.add(new Drink("ホット・アイスココア","￥380","",imageUrl + "drink/drink9.jpg"));
		drinkList.add(new Drink("ホットココナッツココア","￥420","",imageUrl + "drink/drink10.jpg"));
		drinkList.add(new Drink("ホット・アイスコーヒーココア","￥420","",imageUrl + "drink/drink11.jpg"));
		drinkList.add(new Drink("タピオカ黒糖ミルクティー","￥550","",imageUrl + "drink/drink12.jpg"));
		drinkList.add(new Drink("ホットジンジャーティー","￥350","",imageUrl + "drink/drink13.jpg"));
		drinkList.add(new Drink("黒米ヨーグルト","￥350","",imageUrl + "drink/drink14.jpg"));
		return new ResponseEntity<>(drinkList, HttpStatus.OK);
	}
}
