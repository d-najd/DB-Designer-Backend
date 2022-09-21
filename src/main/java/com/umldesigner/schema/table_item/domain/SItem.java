package com.umldesigner.schema.table_item.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.infrastructure.domain.entities.BaseEntity;
import com.umldesigner.schema.item_info.domain.SItemInfo;
import com.umldesigner.schema.table.domain.STable;

import lombok.Getter;
import lombok.Setter;

/**
 * @implSpec first an item info has to be created before an item is created
 */

@Getter
@Setter
@Entity
@Table(name = "s_item")
/*
 * TODO NOTE find a way to cascade on delete, currently doing it from mysql code
 * and it will break in the future
 * 
 * Attemted stuff
 * any sort of annotation or field before class, inside the @SecondaryTable any
 * anything of the sort,
 * setting @OnDelete on the field as annotation (the is a warning on the
 * anotation info that it doesnt work so)
 */
// @SecondaryTable(
// name = "s_item_info",
// pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid")
// )
public class SItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(targetEntity = STable.class, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "tableUuid", referencedColumnName = "uuid", updatable = false)
    private STable table;

    @Column(name = "tableUuid", updatable = false, insertable = false)
    private String tableUuid_;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE, optional = true)
    @PrimaryKeyJoinColumn
    private SItemInfo itemInfo;

    @JsonIgnore
    @NonNull
    @Column(name = "position", insertable = false)
    private Integer position;

    @NonNull
    @Column(name = "type")
    private String type;

    @NonNull
    @Column(name = "value")
    private String value;

    @NonNull
    @Column(name = "size")
    private Integer size = 0;

}
