package com.example.ict210_frontend;

import java.util.Date;

public class IPeriod {

    private Integer idPeriod,idSeance,idUe,idSalle;
    private Date jour;

    public IPeriod(Integer idPeriod, Integer idSeance, Integer idUe, Integer idSalle, Date jour) {
        this.idPeriod = idPeriod;
        this.idSeance = idSeance;
        this.idUe = idUe;
        this.idSalle = idSalle;
        this.jour = jour;
    }

    public Integer getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(Integer idPeriod) {
        this.idPeriod = idPeriod;
    }

    public Integer getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Integer idSeance) {
        this.idSeance = idSeance;
    }

    public Integer getIdUe() {
        return idUe;
    }

    public void setIdUe(Integer idUe) {
        this.idUe = idUe;
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }
}
