package com.umldesigner.schema.item_info.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.item_info.domain.SItemInfo;

@Repository
public interface SItemInfoRepository extends JpaRepository<SItemInfo, String> {

    public Optional<SItemInfo> findByUuid(String uuid);

}