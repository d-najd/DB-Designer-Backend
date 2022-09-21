package com.umldesigner.schema.item_info.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", updatable = false)
    private String uuid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
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

    @NonNull
    @Column(name = "unsigned")
    private Boolean unsigned;

    // @NonNull
    // @Column(name = "foreignKey")
    // private SFK foreignKey;

}
