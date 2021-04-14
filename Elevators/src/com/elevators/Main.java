package com.elevators;

import java.util.Random;

public class Main {

  public static void main(String[] args) {

    final Random random = new Random();
    ElevatorService elevatorService = new ElevatorService(2);

    Runnable runReceiver = () -> {
      while (true) {
        try {
          Request request = new Request(random.nextInt(9) + 1,
              random.nextInt(9) + 1,
              random.nextInt(5) + 1);
          elevatorService.receiveRequest(request);
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Runnable runProcessor = () -> {
      while (true) {
        try {
          elevatorService.processRequests();
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    // thread for receiving new requests
    Thread receiver = new Thread(runReceiver, "Receiver");
    // thread for processing requests
    Thread processor = new Thread(runProcessor, "Processor");
    receiver.start();
    System.out.println("Receiver has started");
    processor.start();
    System.out.println("Processor has started");
  }
}
