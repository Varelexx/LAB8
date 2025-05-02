package controller;

import domain.*;
import domain.Stack.LinkedStack;
import domain.Stack.StackException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class QueueToStackController
{
    @javafx.fxml.FXML
    private ChoiceBox cb_weather;
    @javafx.fxml.FXML
    private TableView tv_stack;
    @javafx.fxml.FXML
    private TextField tf_place;
    @javafx.fxml.FXML
    private TableView tv_queue;
    private LinkedStack climateStack = new LinkedStack();
    private LinkedQueue climateQueue = new LinkedQueue();
    private ObservableList<Climate> climateList = FXCollections.observableArrayList();
    private ObservableList<Climate> emptyClimateList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button btn_to;

    @javafx.fxml.FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "rainy", "thunderstorm", "sunny", "cloudy", "foggy"
        );
        cb_weather.setItems(options);
        btn_to.setDisable(true);

        // columnas para tv_queue
        TableColumn<Climate, String> placeColumnQueue = new TableColumn<>("Place");
        placeColumnQueue.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPlace().getName()));

        TableColumn<Climate, String> weatherColumnQueue = new TableColumn<>("Weather");
        weatherColumnQueue.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getWeather().getDescription()));

        tv_queue.getColumns().addAll(placeColumnQueue, weatherColumnQueue);
        tv_queue.setItems(climateList);

        // columnas para tv_stack
        TableColumn<Climate, String> placeColumnStack = new TableColumn<>("Place");
        placeColumnStack.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPlace().getName()));

        TableColumn<Climate, String> weatherColumnStack = new TableColumn<>("Weather");
        weatherColumnStack.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getWeather().getDescription()));

        tv_stack.getColumns().addAll(placeColumnStack, weatherColumnStack);
        tv_stack.setItems(emptyClimateList);
    }


    @javafx.fxml.FXML
    public void ClearOnAction(ActionEvent actionEvent) {
        cb_weather.setValue(null);
        tf_place.setText("");
    }

    @javafx.fxml.FXML
    public void EnQueueOnAction(ActionEvent actionEvent) {
        btn_to.setDisable(false);
        //validacion para no meter valores en la pila
        if (!climateStack.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You can only add in a Queue");
            alert.showAndWait();
            return;
        }
        String placeName = tf_place.getText();
        String weatherType = (String) cb_weather.getValue();

        //validacion para que no falten datos
        if (placeName == null || placeName.isEmpty() || weatherType == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Select a weather and enter a place");
            alert.showAndWait();
            return;
        }

        Place place = new Place(placeName);
        Weather weather = new Weather(weatherType);
        Climate climate = new Climate(place, weather);
        for (Climate c : climateList) {
            if (c.getPlace().getName().equalsIgnoreCase(placeName) &&
                    c.getWeather().getDescription().equalsIgnoreCase(weatherType)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("There is a place with that weather already");
                alert.showAndWait();
                return;
            }
        }
        try {
            climateQueue.enQueue(climate);
            climateList.add(climate); // actualiza el TableView
            tf_place.clear();
            cb_weather.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ToOnAction(ActionEvent actionEvent) throws StackException {
        climateList.clear();
        //de Queue a Stack
        if (!climateQueue.isEmpty()) {
            while (!climateQueue.isEmpty()) {
                climateStack.push(climateQueue.deQueue());
                climateList.add((Climate) climateStack.peek());
            }
            tv_queue.setItems(emptyClimateList);
            tv_stack.setItems(climateList);
        }else //de Stack a Queue
        {
            while (!climateStack.isEmpty()) {
                Climate temp = (Climate) climateStack.pop();
                climateQueue.enQueue(temp);
                climateList.add(temp);
            }
            tv_queue.setItems(climateList);
            tv_stack.setItems(emptyClimateList);
        }
    }

    @javafx.fxml.FXML
    public void AutoEnQueueOnAction(ActionEvent actionEvent) {
        btn_to.setDisable(false);
        //validacion para no meter valores en la pila
        if (!climateStack.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You can only add in a Queue");
            alert.showAndWait();
            return;
        }
        int count = 0;
        while (count < 20) {
            String placeName = util.Utility.getPlace();
            String weatherType = util.Utility.getWeather();
            // se verifica si ya existe ese climate
            boolean duplicated = false;
            for (Climate c : climateList) {
                if (c.getPlace().getName().equalsIgnoreCase(placeName) &&
                        c.getWeather().getDescription().equalsIgnoreCase(weatherType)) {
                    duplicated = true;
                    break;
                }
            }

            if (!duplicated) {
                Place place = new Place(placeName);
                Weather weather = new Weather(weatherType);
                Climate climate = new Climate(place, weather);

                try {
                    climateQueue.enQueue(climate);
                    climateList.add(climate);
                    count++; // se aumenta si no era duplicado
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        tf_place.clear();
        cb_weather.setValue(null);
    }
}