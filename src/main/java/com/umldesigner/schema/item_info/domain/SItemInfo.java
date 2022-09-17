package com.umldesigner.schema.item_info.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.infrastructure.domain.entities.BaseEntity;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.submodules.UmlDesignerShared.infrastructure.pojo.pojos.BasePojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @implSpec first an item info has to be created before an item can be created
 */

@Getter
@Setter
@Entity
@Table(name = "s_item_info")
public class SItemInfo extends BaseEntity {
    private static final long serialVersionUID = 5L;

    //@OneToOne
    //@JoinColumn(referencedColumnName = "uuid")
    //private SItemInfo item;
    
    @NonNull
    @Column(name = "primaryKey")
    private Boolean primaryKey = false;
    
    @NonNull
    @Column(name = "allowNull")
    private Boolean allowNull = false;

    @NonNull
    @Column(name = "uniqueKey")
    private Boolean uniqueKey = false;

    @NonNull
    @Column(name = "autoIncrement")
    private Boolean autoIncrement = false;

    @NonNull
    @Column(name = "unsigned")
    private Boolean unsigned = false;

    @NonNull
    @Column(name = "foreignKey")
    private SFK foreignKey;

}
