package katsapa.service.taskmanager_grpc.server.api;

import com.katsapa.taskmanager.grpc.TaskProto;
import com.katsapa.taskmanager.grpc.TaskServiceGrpc;
import io.grpc.stub.StreamObserver;
import katsapa.service.taskmanager_grpc.server.database.Task;
import katsapa.service.taskmanager_grpc.server.database.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TaskGrpcService extends TaskServiceGrpc.TaskServiceImplBase {

    private final TaskRepository repository;
    private final TaskGrpcMapper mapper;

    @Override
    public void createTask(
            TaskProto.CreateTaskRequest request,
            StreamObserver<TaskProto.TaskResponse> responseObserver
    ) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
        repository.save(task);

        TaskProto.TaskResponse response = TaskProto.TaskResponse.newBuilder()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setStatus(task.getStatus())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listTasks(
            TaskProto.ListTasksRequest request,
            StreamObserver<TaskProto.TaskResponse> responseObserver
    ) {
        repository.findAll().forEach(task -> {
            responseObserver.onNext(mapper.toResponse(task));
        });
        responseObserver.onCompleted();
    }
}
