package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "apiCategoryMapper")
public interface TaskMapper {
    void insertTask(Task task);

    void deleteTaskById(int task_id);

    void updateTaskById(int task_id,Task task);

    Task selectTaskById(int task_id);

    List<Task> selectAll();

}
