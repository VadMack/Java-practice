package com.names;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NameHandler {

  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthDate;

  public NameHandler() {

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Enter from the keyboard
   */
  public void typeInfo() {

    Scanner in = new Scanner(System.in);
    lastName = in.next();
    firstName = in.next();
    middleName = in.next();
    String birthDateStr = in.next();

    String[] date = birthDateStr.split("\\.");
    List<Integer> list = Arrays.stream(date).map(Integer::parseInt).collect(Collectors.toList());

    try {
      birthDate = LocalDate.of(list.get(2), list.get(1), list.get(0));
    } catch (DateTimeException e) {
      System.out.println("Date is invalid");
    }

  }

  /**
   * Displays the information
   */
  public void printInfo() {
    char gender;
    if (middleName.charAt(middleName.length() - 1) == 'a') {
      gender = 'W';
    } else {
      gender = 'M';
    }
    System.out.println(
        lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ". " + gender + " "
            + calculateAge());

  }

  /**
   * Calculates the gap between birth date and current date in years
   * @return Age difference
   */
  public int calculateAge() {
    if (birthDate != null) {
      return Period.between(birthDate, LocalDate.now()).getYears();
    } else {
      return 0;
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
    NameHandler that = (NameHandler) o;
    return Objects.equals(firstName, that.firstName) && Objects
        .equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName)
        && Objects.equals(birthDate, that.birthDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName, birthDate);
  }

  @Override
  public String toString() {
    return "NameHandler{" +
        "firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthDate='" + birthDate + '\'' +
        '}';
  }

}
