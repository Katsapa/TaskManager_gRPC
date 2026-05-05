package katsapa.service.taskmanager_grpc.server.api;

import com.katsapa.taskmanager.grpc.TaskProto;
import katsapa.service.taskmanager_grpc.server.database.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskGrpcMapper {
    public TaskProto.TaskResponse toResponse(Task task) {
        return TaskProto.TaskResponse.newBuilder()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setStatus(TaskProto.TaskStatus.valueOf(task.getStatus().name()))
                .build();
    }

    public Task toEntity(TaskProto.CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskProto.TaskStatus.DURING);
        return task;
    }
}
