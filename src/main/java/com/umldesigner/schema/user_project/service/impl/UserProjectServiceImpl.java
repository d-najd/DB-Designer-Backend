package com.umldesigner.schema.user_project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.user_project.domain.UserProject;
import com.umldesigner.schema.user_project.mapper.UserProjectMapper;
import com.umldesigner.schema.user_project.repository.UserProjectRepository;
import com.umldesigner.schema.user_project.service.UserProjectService;
import com.umldesigner.submodules.UmlDesignerShared.schema.user_project.UserProjectPojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    UserProjectRepository userProjectRepository;

    @Autowired
    UserProjectMapper userProjectMapper;

    @Override
    public UserProject findByUuid(String uuid) {
        log.debug("Execute findByUuid");

        return userProjectRepository.findById(uuid).orElseThrow(() -> {
            log.error("Resource User Project uuid {} not found", uuid);
            return new ResourceNotFoundException("Resource User Project Not Found");
        });
    }

    @Override
    public UserProjectPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return userProjectMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public List<UserProjectPojo> getAll() {
        log.debug("Execute getAll");

        return userProjectMapper.mapList(userProjectRepository.findAll(), UserProjectPojo.class);
    }

    @Override
    public UserProjectPojo createUserProject(UserProjectPojo pojo) {
        log.debug("Execute createUserProject with parameters {}", pojo);

        UserProject transientUserProject = userProjectMapper.dtoToEntity(pojo);
        UserProject persistedUserProject = userProjectRepository.save(transientUserProject);

        return userProjectMapper.entityToDto(persistedUserProject);
    }

    @Override
    public UserProjectPojo updateUserProject(String uuid, UserProjectPojo pojo) {
        log.debug("Execute updateUserProject with parameters {}, {}", uuid, pojo);

        UserProject persistedUserProject = findByUuid(uuid);
        userProjectMapper.mapRequestedFieldForUpdate(persistedUserProject, pojo);

        return userProjectMapper.entityToDto(userProjectRepository.saveAndFlush(persistedUserProject));
    }

    @Override
    public void removeUserProject(String uuid) {
        log.debug("Execute removeUserProject with parameter {}", uuid);

        userProjectRepository.deleteById(uuid);
    }

}
