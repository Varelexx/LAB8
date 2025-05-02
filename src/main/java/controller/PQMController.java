package controller;

import domain.Person;
import domain.queue.PriorityLinkedQueue;
import domain.queue.QueueException;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import util.Utility;

public class PQMController {

    @javafx.fxml.FXML
    private TableColumn<Person, String> tbc_priority;
    @javafx.fxml.FXML
    private TableView<Person> tbv_personData;
    @javafx.fxml.FXML
    private TableColumn<Person, Integer> tbc_atentionTime;
    @javafx.fxml.FXML
    private TextArea txa_attetntionprocess;
    @javafx.fxml.FXML
    private TextField txf_name;
    @javafx.fxml.FXML
    private TableColumn<Person, String> tbc_mood;
    @javafx.fxml.FXML
    private TableColumn<Person, String> tbc_name;
    @javafx.fxml.FXML
    private ComboBox<String> cbox_priority;
    @javafx.fxml.FXML
    private ComboBox<String> cbox_mood;

    private PriorityLinkedQueue priorityQueue;

    @javafx.fxml.FXML
    public void initialize() {
        priorityQueue = new PriorityLinkedQueue();

        tbc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbc_mood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        tbc_atentionTime.setCellValueFactory(new PropertyValueFactory<>("attentionTime"));

        tbc_priority.setCellValueFactory(cellData -> {
            int priority = cellData.getValue().getPriority();
            String priorityText = switch (priority) {
                case 0 -> "Low";
                case 1 -> "Medium";
                case 2 -> "High";
                default -> "Unknown";
            };
            return new SimpleStringProperty(priorityText);
        });


        cbox_priority.getItems().addAll("Low", "Medium", "High");
        cbox_mood.getItems().addAll("Happiness", "Sadness", "Anger", "Sickness", "Cheerful",
                "Reflective", "Gloomy", "Romantic", "Calm", "Hopeful", "Fearful", "Tense", "Lonely");


        }

    private void refreshTableView() {
        // Limpiar la tabla
        tbv_personData.getItems().clear();

        // Obtener el primer nodo de la cola
        domain.queue.Node current = priorityQueue.getFront();
        while (current != null) {
            // Agregar el dato del nodo (Person) directamente al TableView
            tbv_personData.getItems().add((Person) current.data);
            current = current.next;
        }
    }

    @javafx.fxml.FXML
    public void OnActionenqueue(ActionEvent actionEvent) {
        String name = txf_name.getText();
        String mood = cbox_mood.getValue();
        String priorityStr = cbox_priority.getValue(); // Obtener el valor como String ("Low", "Medium", "High")

        if (name != null && !name.isEmpty() && mood != null && priorityStr != null) {
            // Convertir "Low", "Medium", "High" a 0, 1, 2
            int priority;
            switch (priorityStr) {
                case "Low" -> priority = 0;
                case "Medium" -> priority = 1;
                case "High" -> priority = 2;
                default -> throw new IllegalArgumentException("Prioridad no válida: " + priorityStr);
            }

            int attentionTime = Utility.getAttentionTime();
            Person person = new Person(name, mood, attentionTime);
            person.setPriority(priority);

            try {
                priorityQueue.enQueue(person, priority);
                refreshTableView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(priorityQueue);
    }

    @javafx.fxml.FXML
    public void OnActionautoEnqueue(ActionEvent actionEvent) {
        for (int i = 0; i < 20; i++) {
            String name = Utility.getName();
            String mood = Utility.getMood();
            int priority = Utility.getRandom(3);
            int attentionTime = Utility.getAttentionTime();

            Person person = new Person(name, mood, attentionTime);

            try {
                if (!priorityQueue.contains(person)) {
                    priorityQueue.enQueue(person, priority);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        refreshTableView();
    }

    @javafx.fxml.FXML
    public void OnActionattentionProcess(ActionEvent actionEvent) {
        try {
            if (!priorityQueue.isEmpty()) {
                Person person = (Person) priorityQueue.deQueue();
                txa_attetntionprocess.appendText("Atendiendo a " + person.getName()
                        + " con estado de ánimo: " + person.getMood()
                        + " y tiempo de atención: " + person.getAttentionTime() + "ms\n");
                refreshTableView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void OnActionClear(ActionEvent actionEvent) {
        txf_name.clear();
        cbox_priority.setValue(null);
        cbox_mood.setValue(null);
        txa_attetntionprocess.clear();
        priorityQueue.clear();
        refreshTableView();
    }

    @javafx.fxml.FXML
    public void cbox_priority(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cbox_mood(ActionEvent actionEvent) {
    }
}
