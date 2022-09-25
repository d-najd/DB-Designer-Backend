package com.umldesigner.schema.table.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.schema.item_info.mapper.SItemInfoMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.mapper.STableMapper;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.submodules.UmlDesignerShared.schema.table.dto.STablePojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class STableServiceImpl implements STableService {

    @Autowired
    STableRepository sTableRepository;

    @Autowired
    STableMapper sTableMapper;

    @Autowired
    SItemInfoMapper itemInfoMapper;

    @Override
    public STablePojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return sTableMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public STable findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);

        return sTableRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaTable with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Resource SchemaTable not found");
        });
    }

    @Override
    public List<STablePojo> getAll() {
        log.debug("Execute getAll");

        return sTableMapper.mapList(sTableRepository.findAll(), STablePojo.class);
    }

    @Override
    public STablePojo createSchemaTable(STablePojo sTablePojo) {
        log.debug("Execute createSchemaTable with parameters {}", sTablePojo);
        STable transientSTable = sTableMapper.dtoToEntity(sTablePojo);

        // getting a reference to the items
        List<SItem> items = transientSTable.getItems();

        for (int i = 0; i < items.size(); i++) {
            SItem curItem = items.get(i);
            curItem.setPosition(i);
            curItem.setTable(transientSTable);
            SItemInfo info = curItem.getItemInfo();
            if (info != null) {
                info.setItem(curItem);
            } else {
                // one liner doesnt work for some reason
                info = new SItemInfo();
                info.setItem(curItem);<tab>
                curItem.setItemInfo(info);
            }
            log.debug("attempting to save, {}", itemInfoMapper.entityToDto(info));
        }

        STable persistentSTable = sTableRepository.save(transientSTable);

        return sTableMapper.entityToDto(persistentSTable);
    }

    /**
     * @apiNote doesn't update the table items just the table itself
     */

    @Override
    public STablePojo updateSchemaTable(String uuid, STablePojo sTablePojo) {
        log.debug("Execute updateSchemaTable with parameters {}, {}", uuid, sTablePojo);
        STable persistentSTable = findByUuid(uuid);

        sTableMapper.mapRequestedFieldForUpdate(persistentSTable, sTablePojo);

        return sTableMapper.entityToDto(sTableRepository.saveAndFlush(persistentSTable));
    }

    @Override
    public void removeSchemaTable(String uuid) {
        log.debug("Execute removeSchemaTable with parameter {}", uuid);
        STable persistentSTable = findByUuid(uuid);
        sTableRepository.delete(persistentSTable);
    }
}