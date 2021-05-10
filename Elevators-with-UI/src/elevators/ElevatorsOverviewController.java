package elevators;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ElevatorsOverviewController {

  @FXML
  private TableView<Elevator> elevatorsTable;
  @FXML
  private TableColumn<Elevator, Number> floorColumn;
  @FXML
  private TableColumn<Elevator, Number> capacityColumn;

  @FXML
  private TableView<Request> requestTable;
  @FXML
  private TableColumn<Request, Number> fromColumn;
  @FXML
  private TableColumn<Request, Number> toColumn;
  @FXML
  private TableColumn<Request, Number> numOfPeopleColumn;

  private MainApp mainApp;

  public ElevatorsOverviewController() {
  }

  @FXML
  private void initialize() {
    floorColumn.setCellValueFactory(
        cellData -> cellData.getValue().currentFloorProperty());
    capacityColumn.setCellValueFactory(
        cellData -> cellData.getValue().currentCapacityProperty());

    fromColumn.setCellValueFactory(
        cellData -> cellData.getValue().floorProperty());
    toColumn.setCellValueFactory(
        cellData -> cellData.getValue().targetFloorProperty());
    numOfPeopleColumn.setCellValueFactory(
        cellData -> cellData.getValue().numOfPeopleProperty());
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
    elevatorsTable.setItems(mainApp.getElevatorData());
    requestTable.setItems(mainApp.getRequestData());
  }

  @FXML
  private void handleStart() {
    final Random random = new Random();
    ElevatorsServ elevatorService = new ElevatorsServ(mainApp.getElevatorData(), mainApp.getRequestData());

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





