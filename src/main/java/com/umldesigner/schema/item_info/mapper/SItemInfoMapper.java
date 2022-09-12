package com.umldesigner.schema.item_info.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.submodules.UmlDesignerShared.schema.item_info.SItemInfoPojo;

public interface SItemInfoMapper extends GeneralMapper<SItemInfoPojo, SItemInfo> {
    public void mapRequestedFieldForUpdate(SItemInfoPojo entity, SItemInfo dto);
}
