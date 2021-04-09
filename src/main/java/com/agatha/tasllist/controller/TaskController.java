package com.agatha.tasllist.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agatha.tasllist.model.Task;
import com.agatha.tasllist.repository.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	@CrossOrigin
	public List<Task> listTask() {
		return taskRepository.findByStatus(false, Sort.by(Sort.Direction.ASC, "position"));
	}
	
	@GetMapping(path = {"/list/concluded"})
	@CrossOrigin
	public List<Task> listTaskConcluded() {
		return taskRepository.findByStatus(true, Sort.by(Sort.Direction.DESC, "concluded"));
	}
	
	@GetMapping(path = {"{id}"})
	@CrossOrigin
	public Optional<Task> show(@PathVariable long id) {
		return taskRepository.findById(id);
	}
	
	@PostMapping
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public Task insert (@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@PutMapping(value="{id}")
	@CrossOrigin
	public ResponseEntity<Task> update(@PathVariable("id") long id, @RequestBody Task task) {
		return taskRepository.findById(id)
					.map(record -> {
						record.setTitle(task.getTitle());
						record.setDescription(task.getDescription());
						record.setUpdated_at(new Date());
						Task updated = taskRepository.save(record);
						return ResponseEntity.ok().body(updated);
					}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="/concluded/{id}")
	@CrossOrigin
	public ResponseEntity<Task> concluded(@PathVariable("id") long id) {
		return taskRepository.findById(id)
					.map(record -> {
						record.setStatus(true);
						record.setUpdated_at(new Date());
						record.setConcluded(new Date());
						Task updated = taskRepository.save(record);
						return ResponseEntity.ok().body(updated);
					}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="/position/{id}")
	@CrossOrigin
	public ResponseEntity<Task> position(@PathVariable("id") long id,  @RequestBody Task task) {
		List<Task> tmpTask = taskRepository.fetchByPosition(task.getPosition());
		
		if(tmpTask.size() > 1){
		
			tmpTask.forEach(taskTmp -> {
				taskTmp.setPosition(taskTmp.getPosition()+1);
				taskTmp.setUpdated_at(new Date());
				taskRepository.save(taskTmp);
			});
			
			return taskRepository.findById(id)
						.map(record -> {
							record.setPosition(task.getPosition());
							record.setUpdated_at(new Date());
							Task updated = taskRepository.save(record);
							return ResponseEntity.ok().body(updated);
						}).orElse(ResponseEntity.notFound().build());
			
		} else {
			try {
				tmpTask.get(0).setPosition(tmpTask.get(0).getPosition()+2);
				tmpTask.get(0).setUpdated_at(new Date());
				taskRepository.save(tmpTask.get(0));
				
				taskRepository.findById(id).map(
						record -> {
							record.setPosition(task.getPosition());
							record.setUpdated_at(new Date());
							taskRepository.save(record);
							return null;
						});
				
				tmpTask.get(0).setPosition(tmpTask.get(0).getPosition()-1);
				tmpTask.get(0).setUpdated_at(new Date());
				taskRepository.save(tmpTask.get(0));
				
				return ResponseEntity.ok().build();
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
	}
	
	@PutMapping(value="/reverse/{id}")
	@CrossOrigin
	public ResponseEntity<Task> reverse(@PathVariable("id") long id) {
		return taskRepository.findById(id)
					.map(record -> {
						record.setStatus(false);
						record.setConcluded(null);
						Task updated = taskRepository.save(record);
						return ResponseEntity.ok().body(updated);
					}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path={"{id}"})
	@CrossOrigin
	public ResponseEntity <?> delete(@PathVariable long id) {
		return taskRepository.findById(id)
				.map(record -> {
					taskRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
}
