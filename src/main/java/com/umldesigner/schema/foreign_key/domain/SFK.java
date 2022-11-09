package com.umldesigner.schema.foreign_key.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.schema.item_info.domain.SItemInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_fk", uniqueConstraints = {
        @UniqueConstraint(name = "unique_uuid_referencedUuid", columnNames = { "uuid", "referencedUuid" })
})
public class SFK implements Serializable {
    private static final long serialVersionUID = 7L;

    public SFK() {
        this.onUpdate = "na";
        this.onDelete = "na";
    }

    @Column(name = "uuid", updatable = false, nullable = false)
    @Id
    private String uuid;

    @Column(name = "referencedUuid", nullable = false)
    private String referencedUuid;

    @NonNull
    @Column(name = "referencedTableUuid", updatable = false)
    private String referencedTableUuid;

    // TODO fix this
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "referencedTableUuid", referencedColumnName = "uuid",
    // insertable = false, updatable = false)
    // @JsonIgnore
    // private STable referencedTable;

    @NonNull
    @Column(name = "onDelete", length = 2, nullable = false)
    private String onDelete;

    @NonNull
    @Column(name = "onUpdate", length = 2, nullable = false)
    private String onUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "uuid")
    @JsonIgnore
    private SItemInfo itemInfo;

    @JsonIgnore
    @ManyToOne(targetEntity = SItemInfo.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "referencedUuid", referencedColumnName = "uuid", updatable = false, insertable = false)
    private SItemInfo referencedItemInfo;

}