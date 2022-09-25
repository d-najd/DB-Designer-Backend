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
     * creates foreign key between s_item values from different tables
     * 
     * @param uuid id of the main item (the one that is doing the reference)
     * @param refUuid id of the secondary item (the one that is being referenced)
     */
    public SFKPojo createForeignKey(String uuid, String refUuid, SFKPojo pojo);

    public SFKPojo updateForeignKey(String uuid, SFKPojo pojo);

    public void removeForeignKey(String uuid);

}
