package com.caphephin.voucher.repository;

import com.caphephin.voucher.model.Sticky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StickyRepository extends JpaRepository<Sticky, Long> {
    List<Sticky> findByPhone(String phone);
}
