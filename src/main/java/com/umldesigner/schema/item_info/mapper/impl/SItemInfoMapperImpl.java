package com.umldesigner.schema.item_info.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.schema.item_info.mapper.SItemInfoMapper;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;

@Component
public class SItemInfoMapperImpl extends AbstractGeneralMapper implements SItemInfoMapper {

    @Autowired
    public SItemInfoMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SItemInfoPojo entityToDto(SItemInfo entity) {
        return this.modelMapper.map(entity, SItemInfoPojo.class);
    }

    @Override
    public SItemInfo dtoToEntity(SItemInfoPojo pojo) {
        return this.modelMapper.map(pojo, SItemInfo.class);
    }

    public void mapRequestedFieldForUpdate(SItemInfo entity, SItemInfoPojo dto) {
        entity.setAllowNull(dto.getAllowNull());
        entity.setAutoIncrement(dto.getAutoIncrement());
        // entity.setForeignKey(dto.getForeignKey()); this should be set somewhere else
        entity.setPrimaryKey(dto.getPrimaryKey());
        entity.setUniqueKey(dto.getUniqueKey());
        entity.setUnsigned(dto.getUnsigned());
    }
}