package com.caphephin.voucher.repository;

import com.caphephin.voucher.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    List<Voucher> findByPhone(String phone);
}
