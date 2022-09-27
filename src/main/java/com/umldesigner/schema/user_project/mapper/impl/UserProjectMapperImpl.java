package com.umldesigner.schema.user_project.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.user_project.domain.UserProject;
import com.umldesigner.schema.user_project.mapper.UserProjectMapper;
import com.umldesigner.submodules.UmlDesignerShared.schema.user_project.UserProjectPojo;

@Component
public class UserProjectMapperImpl extends AbstractGeneralMapper implements UserProjectMapper {

    public UserProjectMapperImpl(ModelMapper modelMapper){
        super(modelMapper);
    }


    @Override
    public UserProjectPojo entityToDto(UserProject entity) {
        return this.modelMapper.map(entity, UserProjectPojo.class);
    }
    
    @Override
    public UserProject dtoToEntity(UserProjectPojo dto) {
        return this.modelMapper.map(dto, UserProject.class);
    }
    
    @Override
    public void mapRequestedFieldForUpdate(UserProject entity, UserProjectPojo dto) { 
        entity.setTitle(dto.getTitle());
    }
}