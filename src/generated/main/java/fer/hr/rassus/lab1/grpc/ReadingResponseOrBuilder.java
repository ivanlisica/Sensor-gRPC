// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sensor.proto

package fer.hr.rassus.lab1.grpc;

public interface ReadingResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:sensor.ReadingResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>double temperature = 1;</code>
   * @return The temperature.
   */
  double getTemperature();

  /**
   * <code>double pressure = 2;</code>
   * @return The pressure.
   */
  double getPressure();

  /**
   * <code>double humidity = 3;</code>
   * @return The humidity.
   */
  double getHumidity();

  /**
   * <code>double co = 4;</code>
   * @return The co.
   */
  double getCo();

  /**
   * <code>double no2 = 5;</code>
   * @return The no2.
   */
  double getNo2();

  /**
   * <code>double so2 = 6;</code>
   * @return The so2.
   */
  double getSo2();
}
