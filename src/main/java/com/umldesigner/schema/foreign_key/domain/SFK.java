package com.umldesigner.schema.foreign_key.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.schema.item_info.domain.SItemInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_fk")
/**
 * @apiNote value1 and value2 in the identity refer to id's of SItems
 * 
 * @implSpec to use an sfk first an item has to be created
 */
public class SFK implements Serializable {
    private static final long serialVersionUID = 7L;

    public SFK() {
        this.onUpdate = "na";
        this.onDelete = "na";
    }

    @Column(name = "uuid", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    private String uuid;

    @Column(name = "referencedUuid", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String referencedUuid;

    @NonNull
    @Column(name = "onUpdate", updatable = false, length = 2)
    private String onUpdate;

    @NonNull
    @Column(name = "onDelete", updatable = false, length = 2)
    private String onDelete;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "uuid")
    @JsonIgnore
    private SItemInfo itemInfo;

}