package model;

import java.util.Date;

public class Todo {
    private int slNo;
    private String particulars;
    private String notes;
    private Date reminder;
    private Date pendingFrom;
    private boolean cleared;

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public Date getPendingFrom() {
        return pendingFrom;
    }

    public void setPendingFrom(Date pendingFrom) {
        this.pendingFrom = pendingFrom;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

}
