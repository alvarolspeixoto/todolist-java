package br.com.alvarolspeixoto.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var currentDate = LocalDateTime.now();
        var startsAt = taskModel.getStartsAt();
        var endsAt = taskModel.getEndsAt();

        if(currentDate.isAfter(startsAt) || currentDate.isAfter(endsAt)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("As datas de início e de fim devem ser posteriores à data atual");
        } else if (startsAt.isAfter(endsAt)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("A data de término deve ser posterior à data de início");
        }

        var task = this.taskRepository.save(taskModel);
        
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}