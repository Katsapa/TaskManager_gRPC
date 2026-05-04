package katsapa.service.taskmanager_grpc.server;

import com.katsapa.taskmanager.grpc.TaskProto;
import com.katsapa.taskmanager.grpc.TaskServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class TaskGrpcService extends TaskServiceGrpc.TaskServiceImplBase {
    @Override
    public void createTask(
            TaskProto.CreateTaskRequest request,
            StreamObserver<TaskProto.TaskResponse> responseObserver
    ) {

    }
}
