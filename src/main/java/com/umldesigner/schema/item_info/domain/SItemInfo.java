package com.umldesigner.schema.item_info.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.schema.table_item.domain.SItem;

import lombok.Getter;
import lombok.Setter;

/**
 * @implSpec first an item info has to be created before an item can be created
 */

@Getter
@Setter
@Entity
@Table(name = "s_item_info")

public class SItemInfo implements Serializable {
    private static final long serialVersionUID = 5L;

    public SItemInfo() {
        this.primaryKey = false;
        this.allowNull = false;
        this.uniqueKey = false;
        this.autoIncrement = false;
        this.unsigned = false;
    }

    @Id
    @Column(name = "uuid", updatable = false)
    private String uuid;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "uuid", updatable = false)
    @JsonIgnore
    private SItem item;

    @NonNull
    @Column(name = "primaryKey")
    private Boolean primaryKey;

    @NonNull
    @Column(name = "allowNull")
    private Boolean allowNull;

    @NonNull
    @Column(name = "uniqueKey")
    private Boolean uniqueKey;

    @NonNull
    @Column(name = "autoIncrement")
    private Boolean autoIncrement;

    // naming the field "unsigned" causes errors so "unsinged_" is used
    @NonNull
    @Column(name = "unsigned_")
    private Boolean unsigned;

    @OneToOne(mappedBy = "itemInfo", cascade = CascadeType.ALL, optional = true)
    @PrimaryKeyJoinColumn
    private SFK foreignKey;

    @OneToMany(mappedBy = "referencedItemInfo", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SFK> referencedForeignKeys;

}
