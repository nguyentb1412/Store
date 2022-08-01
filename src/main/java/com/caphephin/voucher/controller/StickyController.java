package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.Sticky;
import com.caphephin.voucher.model.Voucher;
import com.caphephin.voucher.repository.StickyRepository;
import com.caphephin.voucher.repository.VoucherRepository;
import com.caphephin.voucher.service.AESService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class StickyController {

	@Autowired
	StickyRepository stickyRepository;

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	AESService aesService;

	@Autowired
	Environment env;

	public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@GetMapping("/sticky")
	public ResponseEntity<List<Sticky>> getAllStickys(@RequestHeader(name = "accept-language", required=false) String language) {
		try {
			List<Sticky> stickys = new ArrayList<Sticky>();

			stickyRepository.findAll().forEach(stickys::add);

			if (stickys.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(stickys, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sticky/{phone}")
	public ResponseEntity<List<Sticky>> getStickyByPhone(@RequestHeader(name = "accept-language", required=false) String language, @PathVariable("phone") String phone) {
		List<Sticky> stickyData = stickyRepository.findByPhone(phone);

		if (stickyData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(stickyData, HttpStatus.OK);
	}

	@PostMapping("/sticky/add-sticky")
	@Transactional
	public ResponseEntity<Map<String, Boolean>> addSticky(@RequestHeader(name = "accept-language", required=false) String language, @RequestParam String phone, @RequestParam String qrCode) {
		try {

			ObjectMapper mapper = new ObjectMapper();

			String qrCodeDecrypt = aesService.decrypt(qrCode, env.getProperty("secretkey"));

			int newStick = 0;

			Boolean newVoucher = false;

			if (qrCodeDecrypt.equals("onestick")) {
				newStick = 1;
			} else if (qrCodeDecrypt.equals("twostickies")) {
				newStick = 2;
			} else if (qrCodeDecrypt.equals("threestickies")) {
				newStick = 3;
			} else if (qrCodeDecrypt.equals("fourstickies")) {
				newStick = 4;
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			List<Sticky> stickyData = stickyRepository.findByPhone(phone);
			Sticky sticky;
			List<String> StickList;
			int oldStick = 0;

			if (stickyData.isEmpty()) {
				sticky = new Sticky();
				StickList = new ArrayList<>();
			} else {
				sticky = stickyRepository.findByPhone(phone).get(0);
				StickList = Arrays.asList(mapper.readValue(sticky.getStickys(), String[].class));

				StickList = mapper.readValue(sticky.getStickys(), new TypeReference<List<String>>() {});

				oldStick = StickList.size();
			}

			int allStick = oldStick + newStick;

			if (allStick < 20) {
				for(int i = 0; i < newStick; i++) {
					StickList.add(dateFormat.format(new Date()));
				}
			} else {
				newVoucher = true;
				int addStickNo = allStick % 20;
				StickList = new ArrayList<>();
				for(int i = 0; i < addStickNo; i++) {
					StickList.add(dateFormat.format(new Date()));
				}
			}
			String stickStr = mapper.writeValueAsString(StickList);
			System.out.println("Stick Str: " +  stickStr);
			sticky.setStickys(stickStr);
			sticky.setPhone(phone);
			stickyRepository
					.save(sticky);

			int addVoucher = allStick / 20;

			List<Voucher> voucherList = new ArrayList<>();
			for(int i = 0; i < addVoucher; i++) {
				Voucher voucher = new Voucher();
				voucher.setCreatedDate(dateFormat.format(new Date()));
				voucher.setPhone(phone);
				voucher.setStatus("Open");
				voucherList.add(voucher);
			}
			voucherRepository.saveAll(voucherList);
			Map<String, Boolean> body = new HashMap<>();
			body.put("newVoucher", newVoucher);
			return new ResponseEntity<>(body, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
