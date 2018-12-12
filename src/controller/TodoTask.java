package controller;

import dao.TodoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import model.TodoTableData;

public class TodoTask extends Task<ObservableList<TodoTableData>> {

    @Override
    protected ObservableList<TodoTableData> call() throws Exception {
        ObservableList<TodoTableData> data = FXCollections.observableArrayList();
        data.addAll(TodoDAO.getAllTodos());
        return data;
    }
}
