package com.savetheday.backend.controller.Task;

import com.savetheday.backend.dto.request.DailyTaskReq;
import com.savetheday.backend.dto.response.DailyTaskListRes;
import com.savetheday.backend.dto.response.TaskDetailRes;
import com.savetheday.backend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    @GetMapping("/daily")
    public ResponseEntity<DailyTaskListRes> getDailyTaskList(HttpServletRequest request, @RequestParam LocalDate date){
//        Long id = getUserId();
        Long id = 1L;
        return ResponseEntity.ok(taskService.getDailyTaskList(id, date));
    }

    @PostMapping("/detail")
    public ResponseEntity<String> createTask(HttpServletRequest servletRequest, @RequestBody DailyTaskReq dailyTaskReq){
        Long userId = 1L;
        taskService.createTask(userId, dailyTaskReq);

        return ResponseEntity.ok("SUCCESS");
    }
    @GetMapping("/detail/{taskId}")
    public ResponseEntity<TaskDetailRes> getTaskDetail(HttpServletRequest request, @PathVariable Long taskId){
//        Long id = getUserId();
        Long id = 1L;
        return ResponseEntity.ok(taskService.getTaskDetail(taskId));
    }

    @PutMapping("/detail/{taskId}")
    public ResponseEntity<Long> updateTaskDetail(HttpServletRequest request, @PathVariable Long taskId, @RequestBody DailyTaskReq dailyTaskReq){

        taskService.updateTaskDetail(taskId, dailyTaskReq);

        return ResponseEntity.ok(taskId);
    }

    @DeleteMapping("/detail/{taskId}")
    public ResponseEntity<String> deleterTask(HttpServletRequest request, @PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("SUCCESS");
    }





}
