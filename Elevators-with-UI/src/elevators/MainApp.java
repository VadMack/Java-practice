package elevators;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private ObservableList<Elevator> elevatorData = FXCollections.observableArrayList();
    private ObservableList<Request> requestData = FXCollections.observableArrayList();

    private Stage primaryStage;
    private BorderPane rootLayout;

    public MainApp(){
        elevatorData.add(new Elevator());
        elevatorData.add(new Elevator());
    }

    public ObservableList<Elevator> getElevatorData() {
        return elevatorData;
    }

    public void setElevatorData(ObservableList<Elevator> elevatorData) {
        this.elevatorData = elevatorData;
    }

    public ObservableList<Request> getRequestData() {
        return requestData;
    }

    public void setRequestData(ObservableList<Request> requestData) {
        this.requestData = requestData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ElevatorsApp");

        initRootLayout();

        showElevatorsOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("res/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showElevatorsOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("res/ElevatorsOverview.fxml"));
            AnchorPane elevatorsOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(elevatorsOverview);
            ElevatorsOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
