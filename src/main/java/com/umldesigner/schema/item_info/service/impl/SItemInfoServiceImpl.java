package com.umldesigner.schema.item_info.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.WebConfiguration;
import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.foreign_key.service.SFKService;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.schema.item_info.mapper.SItemInfoMapper;
import com.umldesigner.schema.item_info.repository.SItemInfoRepository;
import com.umldesigner.schema.item_info.service.SItemInfoService;
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

    /**
     * @implSpec the current implementation uses hibernate for the insert statement,
     * at the moment I don't know how to make the connection to wait executing and don't want
     * to add any delays so I am returning null
     * @return null
     */
    @Override
    public SItemInfoPojo createSItemInfo(String uuid, SItemInfoPojo pojo, String sfkUuid) {
        log.debug("Execute createSItemInfo with parameters {}, {}, {}", uuid, pojo, sfkUuid);

        final String query = "INSERT INTO s_item_info" +
                " VALUES(?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(WebConfiguration.dbLocation, WebConfiguration.dbUname,
                WebConfiguration.dbPass);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.closeOnCompletion();
            preparedStatement.setLong(1, itemService.findByUuid(uuid).getId());
            preparedStatement.setString(2, uuid);
            preparedStatement.setBoolean(3, pojo.getPrimaryKey());
            preparedStatement.setBoolean(4, pojo.getAllowNull());
            preparedStatement.setBoolean(5, pojo.getUniqueKey());
            preparedStatement.setBoolean(6, pojo.getAutoIncrement());
            preparedStatement.setBoolean(7, pojo.getUnsigned());
            preparedStatement.executeUpdate();
            return getByUuid(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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