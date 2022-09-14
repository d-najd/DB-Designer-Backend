package com.umldesigner.schema.item_info.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.item_info.service.SItemInfoService;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(Endpoints.ITEM_INFO)
public class SItemInfoController {

    @Autowired
    SItemInfoService service;

    @GetMapping
    public List<SItemInfoPojo> getAll(){
        return service.getAll();
    }

    @GetMapping("/{uuid}")
    public SItemInfoPojo getByUuid(
        @PathVariable(value = "uuid") String uuid) {
            return service.getByUuid(uuid);
    }

    @PostMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SItemInfoPojo createSchemaForeignKey(
            @RequestBody SItemInfoPojo requestSfkPojo,
            @PathVariable(value = "uuid") String uuid) {
        return service.createSItemInfo(uuid, requestSfkPojo, null);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeSItemInfo(
        @PathVariable(value = "uuid") String uuid){
            service.removeSItemInfo(uuid);
    }
}
