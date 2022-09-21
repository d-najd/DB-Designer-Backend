package com.umldesigner.schema.foreign_key.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;

@Service
public interface SFKService {
    public SFK findByUuid(String uuid);

    public List<SFKPojo> getAll();

    public SFKPojo getByUuid(String fUuid);

    /**
     * creates foreign key between s_item values
     * 
     * @param fUUid uuid of the first item
     * @param sUuid uuid of the second item
     * @param pojo  the identity from the pojo is not used
     * @return pojo of the created foreign key
     */
    public SFKPojo createForeignKey(String uuid, String itemUuid, SFKPojo pojo);

    public SFKPojo updateForeignKey(String uuid, String itemUuid, SFKPojo pojo);

    public void removeForeignKey(String uuid);

}
