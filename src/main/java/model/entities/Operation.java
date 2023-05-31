package model.entities;

import java.util.Date;

public class Operation {
    private int idOperation;
    private String refOperation;
    private String natureOperation;
    private Date dateOperation;
    private String notes;

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public String getRefOperation() {
        return refOperation;
    }

    public void setRefOperation(String refOperation) {
        this.refOperation = refOperation;
    }

    public String getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(String natureOperation) {
        this.natureOperation = natureOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
