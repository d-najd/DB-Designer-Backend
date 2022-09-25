package com.umldesigner.schema.item_info.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.foreign_key.service.SFKService;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.schema.item_info.mapper.SItemInfoMapper;
import com.umldesigner.schema.item_info.repository.SItemInfoRepository;
import com.umldesigner.schema.item_info.service.SItemInfoService;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional

public class SItemInfoServiceImpl implements SItemInfoService {

    @Autowired
    SItemInfoRepository itemInfoRepository;

    @Autowired
    SItemInfoMapper itemInfoMapper;

    @Override
    public SItemInfo findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);

        return itemInfoRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Resource Schema Item Info with uuid {} not found", uuid);
            return new ResourceNotFoundException("Resource Schema Item Info Not Found");
        });
    }

    @Override
    public List<SItemInfoPojo> getAll() {
        log.debug("Execute getAll");
        itemInfoRepository.findAll();

        log.debug("Executed rerere findAll");
        return itemInfoMapper.mapList(itemInfoRepository.findAll(), SItemInfoPojo.class);
    }

    @Override
    public SItemInfoPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return itemInfoMapper.entityToDto(findByUuid(uuid));
    }

    /**
     * @implSpec the current implementation uses hibernate for the insert statement,
     *           at the moment I don't know how to make the connection to wait
     *           executing and don't want
     *           to add any delays so I am returning null
     * @return null
     */
    @Override
    public SItemInfoPojo createSItemInfo(String uuid, SItemInfoPojo pojo) {
        log.debug("Execute createSItemInfo with parameters {}, {}", uuid, pojo);

        pojo.setUuid(uuid);
        SItemInfo entityItemInfo = itemInfoMapper.dtoToEntity(pojo);
        SItem item = new SItem();
        item.setUuid(uuid);
        entityItemInfo.setItem(item);
        SItemInfo persistedItemInfo = itemInfoRepository.save(entityItemInfo);

        return itemInfoMapper.entityToDto(persistedItemInfo);
    }

    @Override
    public SItemInfoPojo updateSItemInfo(String uuid, SItemInfoPojo pojo) {
        log.debug("Execute createSItemInfo with parameters {}, {}", uuid, pojo);

        return null;
    }

    @Override
    public void removeSItemInfo(String uuid) {
        log.debug("Execute removeSItemInfo with parameter {}", uuid);

        itemInfoRepository.delete(findByUuid(uuid));
    }

}