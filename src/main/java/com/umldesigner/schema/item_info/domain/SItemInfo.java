package com.umldesigner.schema.item_info.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.umldesigner.infrastructure.domain.entities.BaseEntity;
import com.umldesigner.submodules.UmlDesignerShared.infrastructure.pojo.pojos.BasePojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_item_info")
public class SItemInfo extends BaseEntity {
    private static final long serialVersionUID = 5L;
    
    protected boolean primaryKey = false;
    
    protected boolean allowNull = false;

    protected boolean uniqueKey = false;

    protected boolean autoIncrement = false;

    protected boolean unsigned = false;

    protected boolean foreignKey = false;

}
