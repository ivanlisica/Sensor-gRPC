package fer.hr.rassus.lab1.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: sensor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SensorGrpcServiceGrpc {

  private SensorGrpcServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "sensor.SensorGrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fer.hr.rassus.lab1.grpc.ReadingRequest,
      fer.hr.rassus.lab1.grpc.ReadingResponse> getGetReadingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReading",
      requestType = fer.hr.rassus.lab1.grpc.ReadingRequest.class,
      responseType = fer.hr.rassus.lab1.grpc.ReadingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fer.hr.rassus.lab1.grpc.ReadingRequest,
      fer.hr.rassus.lab1.grpc.ReadingResponse> getGetReadingMethod() {
    io.grpc.MethodDescriptor<fer.hr.rassus.lab1.grpc.ReadingRequest, fer.hr.rassus.lab1.grpc.ReadingResponse> getGetReadingMethod;
    if ((getGetReadingMethod = SensorGrpcServiceGrpc.getGetReadingMethod) == null) {
      synchronized (SensorGrpcServiceGrpc.class) {
        if ((getGetReadingMethod = SensorGrpcServiceGrpc.getGetReadingMethod) == null) {
          SensorGrpcServiceGrpc.getGetReadingMethod = getGetReadingMethod =
              io.grpc.MethodDescriptor.<fer.hr.rassus.lab1.grpc.ReadingRequest, fer.hr.rassus.lab1.grpc.ReadingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReading"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fer.hr.rassus.lab1.grpc.ReadingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fer.hr.rassus.lab1.grpc.ReadingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SensorGrpcServiceMethodDescriptorSupplier("GetReading"))
              .build();
        }
      }
    }
    return getGetReadingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SensorGrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceStub>() {
        @java.lang.Override
        public SensorGrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorGrpcServiceStub(channel, callOptions);
        }
      };
    return SensorGrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SensorGrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceBlockingStub>() {
        @java.lang.Override
        public SensorGrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorGrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return SensorGrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SensorGrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorGrpcServiceFutureStub>() {
        @java.lang.Override
        public SensorGrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorGrpcServiceFutureStub(channel, callOptions);
        }
      };
    return SensorGrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getReading(fer.hr.rassus.lab1.grpc.ReadingRequest request,
        io.grpc.stub.StreamObserver<fer.hr.rassus.lab1.grpc.ReadingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReadingMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SensorGrpcService.
   */
  public static abstract class SensorGrpcServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SensorGrpcServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SensorGrpcService.
   */
  public static final class SensorGrpcServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SensorGrpcServiceStub> {
    private SensorGrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorGrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorGrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void getReading(fer.hr.rassus.lab1.grpc.ReadingRequest request,
        io.grpc.stub.StreamObserver<fer.hr.rassus.lab1.grpc.ReadingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReadingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SensorGrpcService.
   */
  public static final class SensorGrpcServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SensorGrpcServiceBlockingStub> {
    private SensorGrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorGrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorGrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public fer.hr.rassus.lab1.grpc.ReadingResponse getReading(fer.hr.rassus.lab1.grpc.ReadingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReadingMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SensorGrpcService.
   */
  public static final class SensorGrpcServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SensorGrpcServiceFutureStub> {
    private SensorGrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorGrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorGrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fer.hr.rassus.lab1.grpc.ReadingResponse> getReading(
        fer.hr.rassus.lab1.grpc.ReadingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReadingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_READING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_READING:
          serviceImpl.getReading((fer.hr.rassus.lab1.grpc.ReadingRequest) request,
              (io.grpc.stub.StreamObserver<fer.hr.rassus.lab1.grpc.ReadingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetReadingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              fer.hr.rassus.lab1.grpc.ReadingRequest,
              fer.hr.rassus.lab1.grpc.ReadingResponse>(
                service, METHODID_GET_READING)))
        .build();
  }

  private static abstract class SensorGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SensorGrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fer.hr.rassus.lab1.grpc.Sensor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SensorGrpcService");
    }
  }

  private static final class SensorGrpcServiceFileDescriptorSupplier
      extends SensorGrpcServiceBaseDescriptorSupplier {
    SensorGrpcServiceFileDescriptorSupplier() {}
  }

  private static final class SensorGrpcServiceMethodDescriptorSupplier
      extends SensorGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SensorGrpcServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SensorGrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SensorGrpcServiceFileDescriptorSupplier())
              .addMethod(getGetReadingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
