package com.agatha.tasllist.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agatha.tasllist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByStatus(Boolean status, Sort sort);
	
	@Query(nativeQuery = true, value = "SELECT t.* FROM task t WHERE t.position >= :position ORDER BY t.position DESC")
	List<Task> fetchByPosition(@Param("position") Integer position);
}
