package com.umldesigner.schema.table.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.infrastructure.domain.entities.UmlObjectEntity;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.user_project.domain.UserProject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_table")
public class STable extends UmlObjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("position ASC")
    private List<SItem> items;

    @JsonIgnore
    @ManyToOne(targetEntity = UserProject.class, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userProject", referencedColumnName = "uuid", updatable = false)
    private UserProject userProject;

    @PrePersist
    public void init() {
        uuid = UUID.randomUUID().toString();
    }

}