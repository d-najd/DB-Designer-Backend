package com.umldesigner.schema.user_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.user_project.domain.UserProject;
import com.umldesigner.submodules.UmlDesignerShared.schema.user_project.UserProjectPojo;

@Service
public interface UserProjectService {
    
    public UserProject findByUuid(String uuid);

    public UserProjectPojo getByUuid(String uuid);

    public List<UserProjectPojo> getAll();

    public UserProjectPojo createUserProject(UserProjectPojo pojo);

    public UserProjectPojo updateUserProject(String uuid, UserProjectPojo pojo);

    public void removeUserProject(String uuid);
}
