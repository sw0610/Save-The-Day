package com.savetheday.backend.dto.response;

import com.savetheday.backend.db.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailRes {
    private Long taskId;
    private String title;
    private LocalDate dueDate;
    private String priority;
    private String processStatus;
    private String content;
    private String emotion;

    public static TaskDetailRes toTaskDetailRes(Task task){
        return TaskDetailRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .processStatus(task.getProcessStatus())
                .content(task.getContent())
                .emotion(task.getEmotion())
                .build();
    }

}
