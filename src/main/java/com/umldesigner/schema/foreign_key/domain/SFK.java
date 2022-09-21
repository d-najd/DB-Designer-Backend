package com.umldesigner.schema.foreign_key.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.entities.BaseEntity;
import com.umldesigner.infrastructure.domain.entities.BaseMEntity;

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
public class SFK extends BaseEntity{
    private static final long serialVersionUID = 3L;

    @Column(name = "tableUuid", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String tableUuid;

    @NonNull
    @Column(name = "onUpdate", updatable = false, length = 2)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String onUpdate = "ca";

    @NonNull
    @Column(name = "onDelete", updatable = false, length = 2)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String onDelete = "ca";

}