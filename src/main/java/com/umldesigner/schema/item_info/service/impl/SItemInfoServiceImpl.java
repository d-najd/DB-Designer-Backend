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
import com.umldesigner.schema.table_item.service.SItemService;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SItemInfoServiceImpl implements SItemInfoService {

    @Autowired
    SItemInfoRepository itemInfoRepository;

    @Autowired
    SItemService itemService;

    @Autowired
    SFKService sfkService;

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

        return itemInfoMapper.mapList(itemInfoRepository.findAll(), SItemInfoPojo.class);
    }

    @Override
    public SItemInfoPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return itemInfoMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public SItemInfoPojo createSItemInfo(String uuid, SItemInfoPojo pojo, String sfkUuid) {
        log.debug("Execute createSItemInfo with parameters {}, {}, {}", uuid, pojo, sfkUuid);

        SItem transientSItem = itemService.findByUuid(uuid);

        log.debug("Execute got item {} ", transientSItem.getId());

        SItemInfo transientSItemInfo = itemInfoMapper.dtoToEntity(pojo);
        log.debug("Execute trying to set id old id {} new id {}", transientSItemInfo.getId(), transientSItem.getId());
        transientSItemInfo.setId(transientSItem.getId());
        //transientSItemInfo.setItem(transientSItem);

        //log.debug("Execute test with par {}", itemInfoMapper.entityToDto(transientSItemInfo));

        SItemInfo persistedSItemInfo = itemInfoRepository.save(transientSItemInfo);

        return itemInfoMapper.entityToDto(persistedSItemInfo);
    }

    @Override
    public SItemInfoPojo updateSItemInfo(String uuid, SItemInfoPojo pojo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeSItemInfo(String uuid) {
        log.debug("Execute removeSItemInfo with parameter {}", uuid);

        itemInfoRepository.delete(findByUuid(uuid));
    }

}