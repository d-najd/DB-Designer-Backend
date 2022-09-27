package com.umldesigner.schema.user_project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.user_project.service.UserProjectService;
import com.umldesigner.submodules.UmlDesignerShared.schema.user_project.UserProjectPojo;

@RestController
@RequestMapping(Endpoints.PROJECT) 
public class UserProjectController {
    @Autowired
    UserProjectService userProjectService;

	@GetMapping("/{uuid}")
	public UserProjectPojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		return userProjectService.getByUuid(uuid);
	}

	@GetMapping
	public List<UserProjectPojo> getAll() {
		return userProjectService.getAll();
	}

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserProjectPojo createUserProject(@RequestBody UserProjectPojo pojo) {
        return userProjectService.createUserProject(pojo);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserProjectPojo updateUserProject(@PathVariable(value = "uuid") String uuid,
            @RequestBody UserProjectPojo pojo) {
        return userProjectService.updateUserProject(uuid, pojo);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeSItem(@PathVariable(value = "uuid") String uuid) {
        userProjectService.removeUserProject(uuid);
    }
}
