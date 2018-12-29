package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class TodoTableData {
    private SimpleBooleanProperty todoSelector;
    private SimpleIntegerProperty slNo;
    private SimpleStringProperty particulars;
    private SimpleStringProperty notes;
    private SimpleObjectProperty<Date> reminder;
    private SimpleObjectProperty<Date> pendingFrom;
    private SimpleBooleanProperty cleared;

    public TodoTableData() {
        this.todoSelector  = new SimpleBooleanProperty();
        this.slNo = new SimpleIntegerProperty();
        this.particulars = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
        this.reminder = new SimpleObjectProperty<Date>();
        this.pendingFrom = new SimpleObjectProperty<Date>();
        this.cleared = new SimpleBooleanProperty();
    }

    public int getSlNo() {
        return slNo.get();
    }

    public SimpleIntegerProperty slNoProperty() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo.set(slNo);
    }

    public String getParticulars() {
        return particulars.get();
    }

    public SimpleStringProperty particularsProperty() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars.set(particulars);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public Date getReminder() {
        return reminder.get();
    }

    public SimpleObjectProperty<Date> reminderProperty() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder.set(reminder);
    }

    public Date getPendingFrom() {
        return pendingFrom.get();
    }

    public SimpleObjectProperty<Date> pendingFromProperty() {
        return pendingFrom;
    }

    public void setPendingFrom(Date pendingFrom) {
        this.pendingFrom.set(pendingFrom);
    }

    public boolean getCleared() {
        return cleared.get();
    }

    public SimpleBooleanProperty clearedProperty() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared.set(cleared);
    }


    public boolean getTodoSelector() {
        return todoSelector.get();
    }

    public SimpleBooleanProperty todoSelectorProperty() {
        return todoSelector;
    }

    public void setTodoSelector(boolean todoSelector) {
        this.todoSelector.set(todoSelector);
    }
}
