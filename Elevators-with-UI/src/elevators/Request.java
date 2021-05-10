package elevators;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Request {
  private IntegerProperty floor;
  private IntegerProperty targetFloor;
  private IntegerProperty numOfPeople;
  // people in elevator or not
  private Status status;

  public enum Status{
    IN,
    OUT
  }

  public Request(int floor, int targetFloor, int numOfPeople) {
    this.floor = new SimpleIntegerProperty(floor);
    this.targetFloor = new SimpleIntegerProperty(targetFloor);
    this.numOfPeople = new SimpleIntegerProperty(numOfPeople);
    this.status = Status.OUT;
  }



  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public int getFloor() {
    return floor.get();
  }

  public IntegerProperty floorProperty() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor.set(floor);
  }

  public int getTargetFloor() {
    return targetFloor.get();
  }

  public IntegerProperty targetFloorProperty() {
    return targetFloor;
  }

  public void setTargetFloor(int targetFloor) {
    this.targetFloor.set(targetFloor);
  }

  public int getNumOfPeople() {
    return numOfPeople.get();
  }

  public IntegerProperty numOfPeopleProperty() {
    return numOfPeople;
  }

  public void setNumOfPeople(int numOfPeople) {
    this.numOfPeople.set(numOfPeople);
  }

  @Override
  public String toString() {
    return "Request{" +
        "floor=" + floor +
        ", targetFloor=" + targetFloor +
        ", numOfPeople=" + numOfPeople +
        ", status=" + status +
        '}';
  }
}
