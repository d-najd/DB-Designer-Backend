package com.umldesigner.schema.foreign_key.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

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

    @Column(name = "uuid", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    private String uuid;

    @Column(name = "referencedUuid", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String referencedUuid;

    @NonNull
    @Column(name = "onUpdate", updatable = false, length = 2)
    private String onUpdate = "ca";

    @NonNull
    @Column(name = "onDelete", updatable = false, length = 2)
    private String onDelete = "ca";

}