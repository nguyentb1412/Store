package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.Voucher;
import com.caphephin.voucher.repository.VoucherRepository;
import com.caphephin.voucher.service.VoucherService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VoucherController {

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	VoucherService voucherService;

	public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@GetMapping("/voucher")
	public ResponseEntity<List<Voucher>> getAllVouchers(@RequestHeader(name = "accept-language", required=false) String language) {
		try {
			List<Voucher> vouchers = new ArrayList<Voucher>();

			voucherRepository.findAll().forEach(vouchers::add);

			voucherService.updateExpired(vouchers);

			if (vouchers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(vouchers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/voucher/{phone}")
	public ResponseEntity<List<Voucher>> getVoucherByPhone(@RequestHeader(name = "accept-language", required=false) String language, @PathVariable("phone") String phone) throws ParseException {
		List<Voucher> voucherData = voucherRepository.findByPhone(phone);

		voucherService.updateExpired(voucherData);

		if (voucherData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(voucherData, HttpStatus.OK);
	}

	@PutMapping("/voucher/{id}")
	public ResponseEntity<Voucher> activeVoucher(@RequestHeader(name = "accept-language", required=false) String language, @PathVariable("id") long id) {
		Optional<Voucher> voucherData = voucherRepository.findById(id);

		if (voucherData.isPresent() && voucherData.get().getStatus().equals("Open")) {
			Voucher voucher = voucherData.get();
			voucher.setStatus("Used");
			voucher.setUsedDate(dateFormat.format(new Date()));
			return new ResponseEntity<>(voucherRepository.save(voucher), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



}
