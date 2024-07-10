package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Clase para representar los datos de los conductores
    public static class Driver {
        private String name;
        private int wins;
        private int totalPoints;
        private int rank;

        public Driver(String name, int wins, int totalPoints, int rank) {
            this.name = name;
            this.wins = wins;
            this.totalPoints = totalPoints;
            this.rank = rank;
        }

        public String getName() { return name; }
        public int getWins() { return wins; }
        public int getTotalPoints() { return totalPoints; }
        public int getRank() { return rank; }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drivers Stats");

        // ComboBox para seleccionar el año
        ComboBox<String> yearComboBox = new ComboBox<>();
        yearComboBox.setItems(FXCollections.observableArrayList("2016", "2017", "2018", "2019", "2020"));
        yearComboBox.setValue("2016");

        // TableView para mostrar los datos de los conductores
        TableView<Driver> tableView = new TableView<>();
        tableView.setEditable(true);

        // Columnas de la TableView
        TableColumn<Driver, String> nameColumn = new TableColumn<>("Driver Name");
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Driver, Integer> winsColumn = new TableColumn<>("Wins");
        winsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getWins()));

        TableColumn<Driver, Integer> pointsColumn = new TableColumn<>("Total Points");
        pointsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalPoints()));

        TableColumn<Driver, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getRank()));

        tableView.getColumns().addAll(nameColumn, winsColumn, pointsColumn, rankColumn);

        // Datos de ejemplo
        ObservableList<Driver> data = FXCollections.observableArrayList(
                new Driver("Paco Fernandez", 5, 10, 3),
                new Driver("Timo Rato", 3, 23, 2),
                new Driver("Jose Maria Perez", 1, 80, 3)
        );
        tableView.setItems(data);

        // Layout
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.getChildren().addAll(new Label("Año:"), yearComboBox, tableView);

        // Escena
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
