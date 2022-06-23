package com.example.ict210_frontend;

public class IClass {
    private Integer idCol;
    private String nameCol;

    public IClass(int idCol, String nameCol) {
            this.idCol=idCol;
            this.nameCol=nameCol;
    }

    public Integer getIdCol() {
        return idCol;
    }

    public void setIdCol(Integer idCol) {
        this.idCol = idCol;
    }

    public String getNameCol() {
        return nameCol;
    }

    public void setNameCol(String nameCol) {
        this.nameCol = nameCol;
    }
}
