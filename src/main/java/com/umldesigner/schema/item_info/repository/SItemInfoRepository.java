package com.umldesigner.schema.item_info.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.item_info.domain.SItemInfo;

@Repository
public interface SItemInfoRepository extends JpaRepository<SItemInfo, String> {

    public Optional<SItemInfo> findByUuid(String uuid);

    // Attempt at writing hibernate.....
    // @Modifying
    // @Query("INSERT INTO s_item_info sif VALUES (sif.id, sif.uuid, sif.primaryKey,
    // sif.allowNull, sif.uniqueKey, sif.autoIncrement, sif.unsigned)")

    // @Query("INSERT INTO s_item_info VALUES (id, uuid, primaryKey, allowNull,
    // uniqueKey, autoIncrement, unsigned)")
    // @Query("INSERT INTO s_item_info(id, uuid, primaryKey, allowNull, uniqueKey,
    // autoIncrement, unsigned)" +
    // " VALUES (19, '9b59fb61-b2e1-40d4-8cba-e82dd3a1ecd0', 0, 0, 0, 0, 0)")
    // @Query("INSERT INTO s_item_info VALUES(19,
    // '9b59fb61-b2e1-40d4-8cba-e82dd3a1ecd0', 0, 0, 0, 0, 0)")

    // @Query("INSERT INTO s_item_info(id, uuid, primaryKey, allowNull, uniqueKey,
    // autoIncrement, unsigned)")
    // @SuppressWarnings("unchecked")
    // public SItemInfo save(SItemInfo itemInfo);

}