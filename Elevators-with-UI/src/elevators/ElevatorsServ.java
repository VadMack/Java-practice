package elevators;

import static com.elevators.ConcurrentUtils.stop;

import elevators.Elevator.Direction;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javafx.collections.ObservableList;

public class ElevatorsServ {

  ObservableList<Elevator> elevators;
  ObservableList<Request> queue;

  ReadWriteLock lock = new ReentrantReadWriteLock();

  public ElevatorsServ() {
  }

  public ElevatorsServ(ObservableList<Elevator> elevatorData,
      ObservableList<Request> requestsData) {
    this.elevators = elevatorData;
    this.queue = requestsData;
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
              request.getFloor() > elevator.currentFloorProperty().getValue() &&
              request.getNumOfPeople() <= elevator.currentCapacityProperty().getValue()) {
            elevator.addRequest(request);
            iterator.remove();
            break;
          }
        }

        ExecutorService executor = Executors.newFixedThreadPool(elevators.size());
        elevators.forEach(elevator -> executor.submit(elevator.getMove()));
        stop(executor);

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
    ElevatorsServ that = (ElevatorsServ) o;
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

