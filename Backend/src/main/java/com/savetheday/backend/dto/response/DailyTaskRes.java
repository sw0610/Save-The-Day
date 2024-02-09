package com.savetheday.backend.dto.response;

import com.savetheday.backend.db.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyTaskRes {
    private Long taskId;
    private String title;
    private String processStatus;
    private String priority;
    private String emotion;

    public static DailyTaskRes toDailyTaskRes(Task task){
        return DailyTaskRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .processStatus(task.getProcessStatus())
                .priority(task.getPriority())
                .emotion(task.getEmotion())
                .build();
    }

}
