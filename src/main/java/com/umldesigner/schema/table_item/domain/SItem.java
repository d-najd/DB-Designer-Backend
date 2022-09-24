package com.umldesigner.schema.table_item.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 */
@SecondaryTable(name = "s_item_info", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid"))
public class SItem implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "uuid", updatable = false)
    private String uuid;

    @JsonIgnore
    @ManyToOne(targetEntity = STable.class, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "tableUuid", referencedColumnName = "uuid", updatable = false)
    private STable table;

    @Column(name = "tableUuid", updatable = false, insertable = false)
    private String tableUuid_;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, optional = true)
    @PrimaryKeyJoinColumn
    private SItemInfo itemInfo;

    @JsonIgnore
    @NonNull
    @Column(name = "position")
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

    @PrePersist
    public void init() {
        uuid = UUID.randomUUID().toString();
    }
}
