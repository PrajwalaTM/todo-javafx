package controller;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.TodoTableData;


public class TodoService extends Service<ObservableList<TodoTableData>> {
    @Override
    protected Task<ObservableList<TodoTableData>> createTask() {
        return new TodoTask();
    }
}
