package com.example.ict210_frontend;

public class Teacher {

    private Integer idCol;
    private String nameCol,sexCol;

    public Teacher(Integer idCol, String nameCol, String sexCol) {
        this.idCol = idCol;
        this.nameCol = nameCol;
        this.sexCol = sexCol;
    }

    public Integer getIdCol() {
        return idCol;
    }

    public String getNameCol() {
        return nameCol;
    }

    public String getSexCol() {
        return sexCol;
    }

    public void setIdCol(Integer idCol) {
        this.idCol = idCol;
    }

    public void setNameCol(String nameCol) {
        this.nameCol = nameCol;
    }

    public void setSexCol(String sexCol) {
        this.sexCol = sexCol;
    }
}
