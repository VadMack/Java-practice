package com.elevators;

import com.elevators.Request.Status;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Elevator {

  private int currentFloor;
  private int currentCapacity;
  private Direction direction;
  // is there any request
  private boolean isBusy;
  private List<Request> requests;

  private Runnable move = () -> {
    // if there aren't requests - stop
    isBusy = !requests.isEmpty();
    if (!isBusy) {
      return;
    }

    // find request with the same direction
    boolean found = false;
    for (Request request :
        requests) {
      if ((request.getFloor() > currentFloor ||
          request.getTargetFloor() > currentFloor) &&
          direction == Direction.UP) {
        found = true;
        break;
      }

      if ((request.getFloor() < currentFloor ||
          request.getTargetFloor() < currentFloor) &&
          direction == Direction.DOWN) {
        found = true;
        break;
      }
    }

    // change direction
    if (!found){
      if (direction == Direction.UP) {
        this.direction = Direction.DOWN;
      } else {
        this.direction = Direction.UP;
      }
    }

    // move
    if (direction == Direction.UP) {
      currentFloor++;
    } else {
      currentFloor--;
    }

    Iterator<Request> iterator = requests.iterator();
    while (iterator.hasNext()) {
      Request request = iterator.next();
      // let people out
      if (request.getTargetFloor() == currentFloor && request.getStatus() == Status.IN) {
        currentCapacity += request.getNumOfPeople();
        iterator.remove();
      }

      // let people in
      if (request.getFloor() == currentFloor && request.getStatus() == Status.OUT) {
        if (request.getNumOfPeople() <= currentCapacity) {
          currentCapacity -= request.getNumOfPeople();
          request.setStatus(Status.IN);
        }
      }
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  };

  public Elevator() {
    this.currentFloor = 1;
    this.currentCapacity = 5;
    this.direction = Direction.UP;
    this.isBusy = false;
    requests = new LinkedList<>();
  }

  public enum Direction {
    UP,
    DOWN
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  public int getCurrentCapacity() {
    return currentCapacity;
  }

  public void setCurrentCapacity(int currentCapacity) {
    this.currentCapacity = currentCapacity;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public boolean isBusy() {
    return isBusy;
  }

  public void setBusy(boolean busy) {
    isBusy = busy;
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  public Runnable getMove() {
    return move;
  }

  public void setMove(Runnable move) {
    this.move = move;
  }

  /*public void move() throws InterruptedException {
    //if there aren't requests - stop
    isBusy = !requests.isEmpty();
    if (!isBusy) {
      return;
    }

    //find request with the same direction
    boolean found = false;
    for (Request request :
        requests) {
      if ((request.getFloor() > currentFloor ||
          request.getTargetFloor() > currentFloor) &&
          direction == Direction.UP) {
        found = true;
        break;
      }

      if ((request.getFloor() < currentFloor ||
          request.getTargetFloor() < currentFloor) &&
          direction == Direction.DOWN) {
        found = true;
        break;
      }
    }

    //change direction
    if (!found){
      if (direction == Direction.UP) {
        this.direction = Direction.DOWN;
      } else {
        this.direction = Direction.UP;
      }
    }

    if (direction == Direction.UP) {
      currentFloor++;
    } else {
      currentFloor--;
    }

    Iterator<Request> iterator = requests.iterator();
    while (iterator.hasNext()) {
      Request request = iterator.next();
      if (request.getTargetFloor() == currentFloor && request.getStatus() == Status.IN) {
        currentCapacity += request.getNumOfPeople();
        iterator.remove();
      }

      if (request.getFloor() == currentFloor && request.getStatus() == Status.OUT) {
        if (request.getNumOfPeople() <= currentCapacity) {
          currentCapacity -= request.getNumOfPeople();
          request.setStatus(Status.IN);
        }
      }
    }
    Thread.sleep(1000);
  }*/

  public void addRequest(Request request) {
    requests.add(request);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Elevator elevator = (Elevator) o;
    return currentFloor == elevator.currentFloor && currentCapacity == elevator.currentCapacity
        && isBusy == elevator.isBusy && direction == elevator.direction && Objects
        .equals(requests, elevator.requests) && Objects.equals(move, elevator.move);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentFloor, currentCapacity, direction, isBusy, requests, move);
  }

  @Override
  public String toString() {
    return "Elevator{" +
        "currentFloor=" + currentFloor +
        ", currentCapacity=" + currentCapacity +
        ", direction=" + direction +
        ", isBusy=" + isBusy +
        ", requests=" + requests +
        '}';
  }
}
