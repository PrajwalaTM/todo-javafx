package controller;

import dao.TodoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class TodoController {

    @FXML
    public DatePicker reminder;
    @FXML
    public TextField particulars;
    @FXML
    public TextArea notes;
    @FXML
    public DatePicker pendingFrom;
    @FXML
    public Button submitTodo;

    public void createNewTodo(ActionEvent actionEvent) {
        Date reminderDate = Date.valueOf(reminder.getValue());
        Date pendingFromDate = Date.valueOf(pendingFrom.getValue());
        TodoDAO.createTodo(particulars.getText(),notes.getText(),reminderDate,pendingFromDate);
        MainLayoutController.service.restart();
        Stage stage = (Stage) submitTodo.getScene().getWindow();
        // Close the create todo window on submit
        stage.close();
    }
}
