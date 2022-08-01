package com.caphephin.voucher.service;

import com.caphephin.voucher.model.Voucher;
import com.caphephin.voucher.repository.VoucherRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void updateExpired(List<Voucher> voucherList) throws ParseException {
        for (Voucher voucher: voucherList) {
            if(voucher.getStatus().equals("Open"))
            {
                Date date = dateFormat.parse(voucher.getCreatedDate());
                Date nextMonthDate = DateUtils.addMonths(date, 1);
                if(nextMonthDate.before(new Date())) {
                    voucher.setStatus("Expired");
                    voucherRepository.save(voucher);
                }
            }
        }
    }

}