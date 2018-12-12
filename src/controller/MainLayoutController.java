package controller;

import dao.TodoDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DateEditableCell;
import model.TodoTableData;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    public TableView<TodoTableData> todoTable;
    @FXML
    public Button createTodo;
    @FXML
    public Button updateTodo;

    final static TodoService service = new TodoService();
    
    @FXML
    public TableColumn<TodoTableData,String> particularsColumn;
    @FXML
    public TableColumn<TodoTableData,String>  notesColumn;
    @FXML
    public TableColumn<TodoTableData,Date>  reminderColumn;
    @FXML
    public TableColumn<TodoTableData,Date>  pendingFromColumn;
    @FXML
    public TableColumn<TodoTableData,Boolean>  clearedColumn;


    public void createTodo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CreateTodo.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Create Todo");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        todoTable.itemsProperty().bind(service.valueProperty());
        service.start();
        todoTable.setEditable(true);
        particularsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        particularsColumn.setOnEditCommit((TableColumn.CellEditEvent<TodoTableData,String> event)->{
            TablePosition<TodoTableData,String> pos = event.getTablePosition();
            String newParticulars = event.getNewValue();
            int row = pos.getRow();
            TodoTableData todo = event.getTableView().getItems().get(row);
            todo.setParticulars(newParticulars);
            TodoDAO.updateTodo("particulars",todo.getParticulars(),todo.getSlNo());
        });
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setOnEditCommit((TableColumn.CellEditEvent<TodoTableData,String> event)->{
            TablePosition<TodoTableData,String> pos = event.getTablePosition();
            String newNotes = event.getNewValue();
            int row = pos.getRow();
            TodoTableData todo = event.getTableView().getItems().get(row);
            todo.setNotes(newNotes);
            TodoDAO.updateTodo("notes",todo.getNotes(),todo.getSlNo());
        });
        Callback<TableColumn<TodoTableData, Date>, TableCell<TodoTableData, Date>> dateCellFactory
                = (TableColumn<TodoTableData, Date> param) -> new DateEditableCell();
        reminderColumn.setCellFactory(dateCellFactory);
        reminderColumn.setOnEditCommit((TableColumn.CellEditEvent<TodoTableData,Date> event) -> {
         TablePosition<TodoTableData,Date> pos = event.getTablePosition();
         Date newReminder = event.getNewValue();
         int row = pos.getRow();
         TodoTableData todo = event.getTableView().getItems().get(row);
         todo.setReminder(newReminder);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         TodoDAO.updateTodo("reminder",format.format(todo.getReminder()),todo.getSlNo());
        });
        pendingFromColumn.setCellFactory(dateCellFactory);
        pendingFromColumn.setOnEditCommit((TableColumn.CellEditEvent<TodoTableData,Date> event) -> {
            TablePosition<TodoTableData,Date> pos = event.getTablePosition();
            Date newPendingFrom = event.getNewValue();
            int row = pos.getRow();
            TodoTableData todo = event.getTableView().getItems().get(row);
            todo.setPendingFrom(newPendingFrom);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            TodoDAO.updateTodo("pending_from",format.format(todo.getPendingFrom()),todo.getSlNo());
        });
        clearedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(clearedColumn));
        clearedColumn.setOnEditCommit((TableColumn.CellEditEvent<TodoTableData,Boolean> event) -> {
            TablePosition<TodoTableData,Boolean> pos = event.getTablePosition();
            Boolean newCleared= event.getNewValue();
            int row = pos.getRow();
            TodoTableData todo = event.getTableView().getItems().get(row);
            todo.setCleared(newCleared);
            TodoDAO.updateTodo("cleared",Boolean.toString(todo.getCleared()),todo.getSlNo());
        });

        clearedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TodoTableData, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TodoTableData, Boolean> param) {
                TodoTableData todo = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(todo.getCleared());

                // When "Single?" column change.
                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        todo.setCleared(newValue);
                        TodoDAO.updateTodo("cleared",Boolean.toString(todo.getCleared()),todo.getSlNo());
                    }
                });
                return booleanProp;
            }
        });

        todoTable.refresh();

    }

}
