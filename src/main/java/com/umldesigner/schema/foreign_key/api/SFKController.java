package com.umldesigner.schema.foreign_key.api;

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
import com.umldesigner.schema.foreign_key.service.SFKService;

import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(Endpoints.ITEM_FK)
public class SFKController {

    @Autowired
    SFKService sfkService;

    @GetMapping
    public List<SFKPojo> getAll() {
        return sfkService.getAll();
    }

    public SFKPojo getById(
            @PathVariable(value = "uuid") String uuid) {
        return sfkService.getByUuid(uuid);
    }

    @PostMapping("/{uuid}/{refUuid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SFKPojo createSchemaForeignKey(
            @RequestBody SFKPojo requestSfkPojo,
            @PathVariable(value = "uuid") String uuid,
            @PathVariable(value = "refUuid") String refUuid) {
        return sfkService.createForeignKey(uuid, refUuid, requestSfkPojo);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public SFKPojo updateSchemaForeignKey(
            @RequestBody SFKPojo requestSfkPojo,
            @PathVariable(value = "uuid") String uuid) {
        return sfkService.updateForeignKey(uuid, requestSfkPojo);
    }

    @DeleteMapping("/{fUuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeSchemaForeignKey(
            @PathVariable(value = "uuid") String uuid) {
                sfkService.removeForeignKey(uuid);
            }
}