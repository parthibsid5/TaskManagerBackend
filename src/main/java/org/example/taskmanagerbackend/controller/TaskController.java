package org.example.taskmanagerbackend.controller;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import org.example.taskmanagerbackend.model.Task;
import org.example.taskmanagerbackend.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // GET paginated tasks
    @GetMapping
    //    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }

    public ResponseEntity<Page<Task>> getTasks(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size)
    {
        Page<Task> task=taskRepository.findAll(PageRequest.of(page,size));
        return ResponseEntity.ok(task);
    }

    // POST new task
    @PostMapping
    //    public Task createTask(@RequestBody Task task) {
//        return taskRepository.save(task);
//    }

    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task saved = taskRepository.save(task);
        return ResponseEntity.status(201).body(saved);
    }


        // PUT update task
    @PutMapping("/{id}")

        public ResponseEntity<?> updateTask(
                @PathVariable Long id,
                @Valid @RequestBody Task updatedTask)
        {
            Optional<Task>  taskToEdit=taskRepository.findById(id);
            if(taskToEdit.isEmpty()) {
                return ResponseEntity.status(404).body("Task with id " + id + " not found");
            }
            else{
            Task taskToUpdate = taskToEdit.get();
            taskToUpdate.setTitle(updatedTask.getTitle());
            taskToUpdate.setDescription(updatedTask.getDescription());
            taskToUpdate.setCompleted(updatedTask.isCompleted());

            // Save the updated task to the repository and return a 200 OK response
            Task savedTask = taskRepository.save(taskToUpdate);
            return ResponseEntity.ok(savedTask);
            }
        }

    // DELETE task with check if task not found
    @DeleteMapping("/{id}")
    //    public void deleteTask(@PathVariable Long id) {
//        taskRepository.deleteById(id);
//    }
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("The task is not found!");
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
        }
    }
