package com.caphephin.voucher.controller;

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
public class ProductController {

	@Autowired
	Environment env;

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct(@RequestHeader(name = "accept-language", required=false) String language) throws IOException {
		List<Product> productList = new ArrayList<>();

		String imageUrl = env.getProperty("imageUrl");

		if(language !=null && language.toLowerCase().equals("vi")) {
			productList.add(new Product("Bánh mì thịt xá xíu và trứng chiên ","￥660","",imageUrl + "food/food1.jpg","food",""));
			productList.add(new Product("Bánh mì thịt bò nướng, sốt cà chua","￥690","",imageUrl + "food/food2.jpg","food",""));
			productList.add(new Product("Bánh mì bơ sữa","￥450","",imageUrl + "food/foodbonus1.jpg","food",""));
			productList.add(new Product("Xôi thịt xá xíu và trứng ốp la","￥640","",imageUrl + "food/food3.jpg","food",""));
			productList.add(new Product("Xôi thịt gà xé cay","￥660","",imageUrl + "food/food4.jpg","food",""));
			productList.add(new Product("Bánh flan/ caramen","￥350","",imageUrl + "food/foodbonus2.jpg","food",""));
			productList.add(new Product("Bánh flan trân châu","￥380","",imageUrl + "food/foodbonus3.jpg","food",""));

			productList.add(new Product("Cà phê phin sữa/ Cà phê phin đen","￥350 - ￥300","Cà phê truyền thống hương vị Việt",imageUrl + "drink/drink1.jpg","drink",""));
			productList.add(new Product("Cà phê nến sữa/ Cà phê nến đen","￥350 - ￥400","Luôn nhóm lửa để giữ độ ấm nóng cho cà phê Việt.",imageUrl + "drink/drink2.jpg","drink",""));
			productList.add(new Product("Bạc xỉu","￥350","Bạc xỉu, thức uống dành cho người nặng tình.",imageUrl + "drink/drink3.jpg","drink",""));
			productList.add(new Product("Cà phê trứng","￥450","Một lớp kem trứng béo ngậy cùng với một lớp cà phê Việt đắng, chúng tạo ra một ly cà phê trứng thơm ngon, béo ngọt.",imageUrl + "drink/drink4.jpg","drink",""));
			productList.add(new Product("Cà phê cốt dừa","￥500","Hương béo ngậy của dừa, vị ngọt của sữa pha vị đậm đà của cafe, khiến cho ly cà phê cốt dừa có một vị ngon ngọt không thể cưỡng lại.",imageUrl + "drink/drink5.jpg","drink",""));
			productList.add(new Product("Cà phê kẹo dalgona","￥400","Nổi tiếng tại Hàn Quốc và được biến thể một chút tại Cà phê phin, cà phê kẹo dalgona mang lại vị ngọt của kẹo, vị béo của sữa và vị đậm đà hơi đắng của cà phê.",imageUrl + "drink/drink6.jpg","drink",""));
			productList.add(new Product("Sinh tố bơ","￥600","Thức uống nổi tiếng đối với giới trẻ Việt Nam. Vừa béo vừa thơm vừa đẹp da.",imageUrl + "drink/drink7.jpg","drink",""));
			productList.add(new Product("Sinh tố xoài","￥500","",imageUrl + "drink/drink8.jpg","drink",""));
			productList.add(new Product("Sinh tố dâu","￥400","",imageUrl + "drink/drink9.jpg","drink",""));
			productList.add(new Product("Cacao (nóng / lạnh)","￥380","",imageUrl + "drink/drink10.jpg","drink",""));
			productList.add(new Product("Cacao cốt dừa (nóng)","￥420","",imageUrl + "drink/drink11.jpg","drink",""));
			productList.add(new Product("Cacao cà phê (nóng/lạnh)","￥420","",imageUrl + "drink/drink12.jpg","drink",""));
			productList.add(new Product("Trà sữa trân châu đường đen","￥550","",imageUrl + "drink/drink13.jpg","drink",""));
			productList.add(new Product("Trà đào cam","￥490","",imageUrl + "drink/drinkbonus1.jpg","drink",""));
			productList.add(new Product("Trà gừng nóng","￥350","",imageUrl + "drink/drink14.jpg","drink",""));
			productList.add(new Product("Sữa chua nếp cẩm","￥450","",imageUrl + "drink/drink15.jpg","drink",""));
			productList.add(new Product("Nước sấu","￥350","",imageUrl + "drink/drink16.jpg","drink",""));
			return new ResponseEntity<>(productList, HttpStatus.OK);
		}

		productList.add(new Product("チャーシュー＆焼き卵バインミー","￥660","ベトナムの定番バインミーです。",imageUrl + "food/food1.jpg","food",""));
		productList.add(new Product("牛焼肉＆自家製トマトソースバインミー","￥690","",imageUrl + "food/food2.jpg","food",""));
		productList.add(new Product("バターピリ辛サテバインミー","￥450","",imageUrl + "food/foodbonus1.jpg","food",""));
		productList.add(new Product("チャーシュー＆目玉卵おこわ","￥640","",imageUrl + "food/food3.jpg","food",""));
		productList.add(new Product("ピリ辛チキンおこわ","￥660","",imageUrl + "food/food4.jpg","food",""));
		productList.add(new Product("バインフラン/ベトナムプリン","￥350","",imageUrl + "food/foodbonus2.jpg","food",""));
		productList.add(new Product("タピオカのせプリン","￥380","",imageUrl + "food/foodbonus3.jpg","food",""));

		productList.add(new Product("フィンミルクコーヒ/ フィンブラックコーヒー","￥350 - ￥300","フィンは、コーヒーを抽出するためのフィルタです。ベトナムコーヒーの濃い味わいは、この特殊なフィルターによって生み出されています。",imageUrl + "drink/drink1.jpg","drink",""));
		productList.add(new Product("キャンドルミルクコーヒー/ キャンドルブラックコーヒー","￥350 - ￥400","コーヒーをずっと温めるためにカップの下に蝋燭をつけます。",imageUrl + "drink/drink2.jpg","drink",""));
		productList.add(new Product("ミルクたっぷりコーヒー（バクシウ）","￥350","バク・シウは甘いもの好きな人にとって素晴らしい飲み物です。",imageUrl + "drink/drink3.jpg","drink",""));
		productList.add(new Product(" ホットエッグコーヒ","￥450","魂を温めてくれる最高のコーヒー特に冬にはおすすめの飲み物。濃厚で、泡沫をたっぷり含んだ卵を、スプーンで優しく混ぜ合わせて、ゆったりと味わう。",imageUrl + "drink/drink4.jpg","drink",""));
		productList.add(new Product("ココナッツコーヒー","￥500","ココナッツコーヒーはアイスクリームに比べて飲みやすい、体力回復やリラックス効果のある飲み物",imageUrl + "drink/drink5.jpg","drink",""));
		productList.add(new Product("ダルゴナキャンディーコーヒー","￥400","ダルゴナとはカラメル焼き。ダルゴナキャンディーはキャラメルキャンディーです。キャンディーは食べてもいいし、コーヒーに溶かして飲んでもいい。",imageUrl + "drink/drink6.jpg","drink",""));
		productList.add(new Product("アボカドスムージー","￥600","ベトナム人にとって、アボカドは有名な果物です。濃厚でとてもクリーミアボカドスムージーはベトナムには超人気がある。",imageUrl + "drink/drink7.jpg","drink",""));
		productList.add(new Product("マンゴースムージー","￥500","",imageUrl + "drink/drink8.jpg","drink",""));
		productList.add(new Product("ストロベリースムージー","￥400","",imageUrl + "drink/drink9.jpg","drink",""));
		productList.add(new Product("ホット・アイスココア","￥380","",imageUrl + "drink/drink10.jpg","drink",""));
		productList.add(new Product("ホットココナッツココア","￥420","",imageUrl + "drink/drink11.jpg","drink",""));
		productList.add(new Product("ホット・アイスコーヒーココア","￥420","",imageUrl + "drink/drink12.jpg","drink",""));
		productList.add(new Product("タピオカ黒糖ミルクティー","￥550","",imageUrl + "drink/drink13.jpg","drink",""));
		productList.add(new Product("ピーチオレンジティー","￥490","",imageUrl + "drink/drinkbonus1.jpg","drink",""));
		productList.add(new Product("ホットジンジャーティー","￥350","",imageUrl + "drink/drink14.jpg","drink",""));
		productList.add(new Product("黒米ヨーグルト","￥350","",imageUrl + "drink/drink15.jpg","drink",""));
		productList.add(new Product("ヌックサウ","￥350","",imageUrl + "drink/drink16.jpg","drink",""));


		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
}
