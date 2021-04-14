package com.elevators;

public class Request {
  private int floor;
  private int targetFloor;
  private int numOfPeople;
  // people in elevator or not
  private Status status;

  public enum Status{
    IN,
    OUT
  }

  public Request(int floor, int targetFloor, int numOfPeople) {
    this.floor = floor;
    this.targetFloor = targetFloor;
    this.numOfPeople = numOfPeople;
    this.status = Status.OUT;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getNumOfPeople() {
    return numOfPeople;
  }

  public void setNumOfPeople(int numOfPeople) {
    this.numOfPeople = numOfPeople;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public int getTargetFloor() {
    return targetFloor;
  }

  public void setTargetFloor(int targetFloor) {
    this.targetFloor = targetFloor;
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
