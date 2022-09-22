package com.umldesigner.schema.foreign_key.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMIdentity;
import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.schema.foreign_key.fascade.SFKFascade;
import com.umldesigner.schema.foreign_key.mapper.SFKMapper;
import com.umldesigner.schema.foreign_key.repository.SFKRepository;
import com.umldesigner.schema.foreign_key.service.SFKService;
import com.umldesigner.schema.table.api.STableController;
import com.umldesigner.schema.table_item.api.SItemController;
import com.umldesigner.schema.table_item.service.SItemService;
import com.umldesigner.submodules.UmlDesignerShared.infrastructure.pojo.identities.BaseMIdentityPojo;
import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SFKServiceImpl implements SFKService {

    @Autowired
    SFKRepository sfkRepository;

    @Autowired
    SItemService sItemService;

    @Autowired
    SFKMapper sfkMapper;

    @Autowired
    SFKFascade sfkFascade;

    @Override
    public SFK findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameters {}");

        SFK sfkEntity = sfkRepository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("Error: Resource Schema Foreign Key with identity {} not found",
                            uuid);
                    return new ResourceNotFoundException("Resource Schema Primary Key not found");
                });

        return sfkEntity;
    }

    public List<SFKPojo> getAll() {
        log.debug("Execute getAll");

        return sfkMapper.mapList(sfkRepository.findAll(), SFKPojo.class);
    }

    public SFKPojo getByUuid(String uuid) {
        log.debug("Execute getById with parameters {}", uuid);

        return sfkMapper.entityToDto(findByUuid(uuid));
    }

    // TODO whem implementing multiple projects make sure that the foreign keys
    // don't point across multiple projects and realities

    @Override
    public SFKPojo createForeignKey(String uuid, String refUuid, SFKPojo pojo) {
        log.debug("Execute createForeignKey with parameters {}. {}. {}", uuid, refUuid, pojo);

        pojo.setUuid(uuid);
        pojo.setReferencedUuid(refUuid);
        sfkFascade.isValid(uuid, refUuid, pojo);

        SFK persistedSfk = sfkRepository.save(sfkMapper.dtoToEntity(pojo));

        return sfkMapper.entityToDto(persistedSfk);
    }

    @Override
    public SFKPojo updateForeignKey(String uuid, SFKPojo pojo) {
        log.debug("Execute updateForeignKey with parameters {}, {}", uuid, pojo);

        pojo.setUuid(uuid);
        SFK persistedSFK = findByUuid(uuid);

        //just in case something changes in future, unnecessary check atm
        sfkFascade.isValid(uuid, persistedSFK.getReferencedUuid(), pojo);

        sfkMapper.mapRequestedFieldForUpdate(persistedSFK, pojo);

        return sfkMapper.entityToDto(sfkRepository.saveAndFlush(persistedSFK));
    }

    @Override
    public void removeForeignKey(String uuid){
        log.debug("Execute removeForeignKey with parameters {}", uuid);

        sfkRepository.deleteById(uuid);
    }


}