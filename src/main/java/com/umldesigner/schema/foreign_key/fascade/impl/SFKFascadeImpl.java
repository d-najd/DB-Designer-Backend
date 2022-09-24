package com.umldesigner.schema.foreign_key.fascade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.fascade.SFKFascade;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.service.SItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SFKFascadeImpl implements SFKFascade {

    @Autowired
    SItemService sItemService; // TODO find a way to get rid of this

    @Override
    public boolean isValid(String uuid, String refUuid, SFKPojo pojo) {
        log.debug("Execute isValid with parameters {}, {}, {}", uuid, refUuid, pojo);

        if (sameTableFKCheck(uuid, refUuid)) {
            log.error("Foreign key links to the same table with parameters {}. {}. {}", uuid, refUuid, pojo);
            throw new IllegalArgumentException("Foreign key links to the same table");
        }

        if (!validArgumentsCheck(pojo)) {
            log.error("Invalid Arguemnt Entered for OnDelete or OnUpdate with parameters {}", pojo);
            throw new IllegalArgumentException(
                    "Invalid Argument entered for OnDelete or OnUpdate, available arguments are: No Action, REstrict, Cascade, Set Null, Set Default");
        }

        return true;
    }

    @Override
    public boolean sameTableFKCheck(String uuid, String refUuid) {
        log.debug("Execute sameTableFKCheck with parameters {}, {}", uuid, refUuid);
        SItem firstItem = sItemService.findByUuid(uuid);
        SItem secondItem = sItemService.findByUuid(refUuid);

        return firstItem.getTable().equals(secondItem.getTable());
    }

    @Override
    public boolean validArgumentsCheck(SFKPojo pojo) {
        log.debug("Execute validArguemntsCheck with parameters {}", pojo);
        String[] arguments = { pojo.getOnDelete(), pojo.getOnUpdate() };

        for (String argument : arguments) {
            switch (argument) {
                case "na": // no action
                case "re": // restrict
                case "ca": // cascade
                case "sn": // set null
                case "sd": // set default
                    break;
                default: // invalid argument
                    return false;
            }
        }

        return true;
    }
}
