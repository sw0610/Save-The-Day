package com.savetheday.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyTaskListRes {

    private List<DailyTaskRes> notStartedList;
    private List<DailyTaskRes> inProgressList;
    private List<DailyTaskRes> finishedList;

}
