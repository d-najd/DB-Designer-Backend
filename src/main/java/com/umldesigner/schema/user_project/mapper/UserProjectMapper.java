package com.umldesigner.schema.user_project.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.user_project.domain.UserProject;
import com.umldesigner.submodules.UmlDesignerShared.schema.user_project.UserProjectPojo;

public interface UserProjectMapper extends GeneralMapper<UserProjectPojo, UserProject> {

	public void mapRequestedFieldForUpdate(UserProject entity, UserProjectPojo dto);

}