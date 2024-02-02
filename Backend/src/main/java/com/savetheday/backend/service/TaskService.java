package com.savetheday.backend.service;


import com.savetheday.backend.dto.request.DailyTaskReq;
import com.savetheday.backend.dto.response.DailyTaskListRes;
import com.savetheday.backend.dto.response.TaskDetailRes;

import java.time.LocalDate;

public interface TaskService {
    DailyTaskListRes getDailyTaskList(Long id, LocalDate date);

    TaskDetailRes getTaskDetail(Long taskId);

    void createTask(Long memberId, DailyTaskReq dailyTaskReq);
}
