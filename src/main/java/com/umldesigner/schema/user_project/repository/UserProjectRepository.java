package com.umldesigner.schema.user_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.user_project.domain.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, String> {

}