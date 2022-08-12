package com.opw.financemessage.repository;

import com.opw.financemessage.entity.CardInfor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInfoRepository extends JpaRepository<CardInfor, Long> {
}
