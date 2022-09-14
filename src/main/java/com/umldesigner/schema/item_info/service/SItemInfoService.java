package com.umldesigner.schema.item_info.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;


@Service
public interface SItemInfoService {
    
    public SItemInfo findByUuid(String uuid);
    
    public List<SItemInfoPojo> getAll();

    public SItemInfoPojo getByUuid(String uuid);

    public SItemInfoPojo createSItemInfo(String uuid, 
                                        SItemInfoPojo pojo,
                                        String sfkUuid);
                                        
    public SItemInfoPojo updateSItemInfo(String uuid,
                                        SItemInfoPojo pojo);

    public void removeSItemInfo(String uuid); 

}
