package com.Flipr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Flipr.entity.Projects;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Integer> {

}
