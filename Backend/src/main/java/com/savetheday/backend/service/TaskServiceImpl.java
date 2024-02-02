package com.savetheday.backend.service;

import com.savetheday.backend.db.entity.Task;
import com.savetheday.backend.db.repository.TaskRepository;
import com.savetheday.backend.dto.request.DailyTaskReq;
import com.savetheday.backend.dto.response.DailyTaskListRes;
import com.savetheday.backend.dto.response.DailyTaskRes;
import com.savetheday.backend.dto.response.TaskDetailRes;
import com.savetheday.backend.exception.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public DailyTaskListRes getDailyTaskList(Long id, LocalDate date) {

        //사용자 찾고 그 안이서 날짜 찾아서 리스트 형태로 반환
        List<DailyTaskRes> notStartedList =
                taskRepository.findAllByMemberIdAndDueDateAndProcessStatusOrderByCreatedAt(id, date, "not started")
                .stream()
                .map(DailyTaskRes::toDailyTaskRes)
                .toList();

        List<DailyTaskRes> inProgressList =
                taskRepository.findAllByMemberIdAndDueDateAndProcessStatusOrderByCreatedAt(id, date, "in progress")
                .stream()
                .map(DailyTaskRes::toDailyTaskRes)
                .toList();

        List<DailyTaskRes> finishedList =
                taskRepository.findAllByMemberIdAndDueDateAndProcessStatusOrderByCreatedAt(id, date, "finished")
                .stream()
                .map(DailyTaskRes::toDailyTaskRes)
                .toList();

        DailyTaskListRes dailyTaskList =
                DailyTaskListRes.builder()
                        .notStartedList(notStartedList)
                        .inProgressList(inProgressList)
                        .finishedList(finishedList)
                        .build();

        return dailyTaskList;
    }

    @Override
    public TaskDetailRes getTaskDetail(Long taskId) {

        TaskDetailRes taskDetailRes = taskRepository.findById(taskId)
                .map(TaskDetailRes::toTaskDetailRes)
                .orElseThrow(()-> new BadRequestException("해당하는 task가 없습니다"));

        return taskDetailRes;
    }

    @Override
    public void createTask(Long memberId, DailyTaskReq dailyTaskReq) {
        taskRepository.save(Task.toTask(memberId, dailyTaskReq));
    }

    @Override
    public void updateTaskDetail(Long taskId, DailyTaskReq dailyTaskReq) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new BadRequestException("해당 task가 없습니다"));

        task.updateTask(dailyTaskReq);
    }

}
