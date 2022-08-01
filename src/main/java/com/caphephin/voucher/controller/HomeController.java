package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.User;
import com.caphephin.voucher.model.Sticky;
import com.caphephin.voucher.model.Voucher;
import com.caphephin.voucher.repository.StickyRepository;
import com.caphephin.voucher.repository.UserRepository;
import com.caphephin.voucher.repository.VoucherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	StickyRepository stickyRepository;

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	Environment env;

	@GetMapping("/home/{phone}")
	public ResponseEntity<Map<String, Object>> getHomeData(@RequestHeader(name = "accept-language", required=false) String language, @PathVariable("phone") String phone) {
		try {

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> homeData = new HashMap();

			String imageUrl = env.getProperty("imageUrl");

			User user =  userRepository.findByUsernameOrEmail(phone, phone)
					.orElseThrow(() ->
							new UsernameNotFoundException("User not found with username or email:" + phone));

			homeData.put("user", user);

			List<Voucher> voucherData = voucherRepository.findByPhone(phone);

			homeData.put("vouchers", voucherData);

			List<Sticky> stickyData = stickyRepository.findByPhone(phone);

			List <String > StickList = new ArrayList<>();

			if (!stickyData.isEmpty()) {
				System.out.println("stick json:" + stickyData.get(0).stickJson());
				homeData.put("stickJson", stickyData.get(0).stickJson());
				StickList = Arrays.asList(mapper.readValue(stickyData.get(0).getStickys(), String[].class));
			} else {
				homeData.put("stickJson", "");
			}

			homeData.put("openSticks" , StickList.size());

			int openVouchers = 0;
			for (Voucher voucher: voucherData) {
				if(voucher.getStatus().equals("Open")) {
					openVouchers++;
				}
			}

			homeData.put("openVouchers", openVouchers);

			return new ResponseEntity<>(homeData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
