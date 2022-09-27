package com.umldesigner.schema.user_project.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.umldesigner.schema.table.domain.STable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_project")
public class UserProject implements Serializable {
    private static final long serialVersionUID = 9L;
    
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;
    
    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "userProject", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<STable> tables;

    @PrePersist
    public void init() {
        uuid = UUID.randomUUID().toString();
    }
}