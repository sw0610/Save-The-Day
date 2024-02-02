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

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    @GetMapping("/daily/{date}")
    public ResponseEntity<DailyTaskListRes> getDailyTaskList(HttpServletRequest request, @PathVariable LocalDate date){
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
    public ResponseEntity<TaskDetailRes> getTaskDetail(HttpServletRequest request, @PathVariable String taskId){
//        Long id = getUserId();
        Long id = 1L;
        return ResponseEntity.ok(taskService.getTaskDetail(id));
    }


}
