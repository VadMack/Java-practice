package com.elevators;

import static com.elevators.ConcurrentUtils.stop;

import com.elevators.Elevator.Direction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ElevatorService {

  List<Elevator> elevators;
  List<Request> queue;

  ReadWriteLock lock = new ReentrantReadWriteLock();

  public ElevatorService(){
    elevators = new ArrayList<>();
    queue = new LinkedList<>();
  }


  public ElevatorService(int numOfElevators) {
    elevators = new ArrayList<>();
    for (int i = 0; i < numOfElevators; i++) {
      elevators.add(new Elevator());
    }
    queue = new LinkedList<>();
  }

  public ElevatorService(List<Elevator> elevators) {
    this.elevators = elevators;
  }

  public void receiveRequest(Request request) {
    System.out.println(Thread.currentThread().getName() + " - receive");
    synchronized (this) {
      queue.add(request);
    }
  }

  public void processRequests() {
    System.out.println(Thread.currentThread().getName() + " - process");

    synchronized (this) {
      if (queue.isEmpty()) {
        return;
      }

      Iterator<Request> iterator = queue.iterator();
      while (iterator.hasNext()) {
        Request request = iterator.next();
        for (Elevator elevator :
            elevators) {

          //empty elevator
          if (!elevator.isBusy()) {
            elevator.addRequest(request);
            iterator.remove();
            break;
          }

          // same direction
          if (elevator.isBusy() &&
              elevator.getDirection() == Direction.UP &&
              request.getFloor() > elevator.getCurrentFloor() &&
              request.getNumOfPeople() <= elevator.getCurrentCapacity()) {
            elevator.addRequest(request);
            iterator.remove();
            break;
          }
        }

        ExecutorService executor = Executors.newFixedThreadPool(elevators.size());
        elevators.forEach(elevator -> executor.submit(elevator.getMove()));
        stop(executor);

        /*for (Elevator elevator :
            elevators) {
          elevator.move();
        }*/

        for (int i = 0; i < elevators.size(); i++) {
          System.out.println("Elevator " + i + " - " + elevators.get(i));
        }

      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ElevatorService that = (ElevatorService) o;
    return Objects.equals(elevators, that.elevators) && Objects
        .equals(queue, that.queue) && Objects.equals(lock, that.lock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elevators, queue, lock);
  }

  @Override
  public String toString() {
    return "ElevatorService{" +
        "elevators=" + elevators +
        ", queue=" + queue +
        ", lock=" + lock +
        '}';
  }
}

