package com.aplikacja.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "zamowieniaWymiar")
public class zamowieniaWymiar  implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "idKlient ")
    private int idKlient;

    @Column (name = "idProjektant ")
    private int idProjektant;
    
    @Column (name = "dataZakupu ")
    private LocalDate dataZakupu;
    
    @Column (name = "dataRealizacji  ")
    private LocalDate dataRealizacji;
    
    @Column (name = "cena")
    private Double cena;

    public zamowieniaWymiar  () {};
    //Konstruktor stosowany do tworzenia nowych produktow w bazie
    public zamowieniaWymiar  (int idKlient, int idProjektant, LocalDate dataZakupu, LocalDate dataRealizacji, Double cena) {
        this.setIdKlient (idKlient);
        this.setIdProjektant (idProjektant);
        this.setDataZakupu (dataZakupu);
        this.setDataRealizacji (dataRealizacji);
        this.setCena (cena);
    }
    //Konstruktor stosowany do edytowania istniejących produktow w bazie
    public zamowieniaWymiar  (int id, int idKlient, int idProjektant, LocalDate dataZakupu, LocalDate dataRealizacji, Double cena) {
        this.setId(id);
        this.setIdKlient (idKlient);
        this.setIdProjektant (idProjektant);
        this.setDataZakupu (dataZakupu);
        this.setDataRealizacji (dataRealizacji);
        this.setCena (cena);
    }
    public int getId() {
        return id;
    }
    public void setId (int id) {
        this.id = id;
    }
    public int getIdKlient () {
        return idKlient;
    }
    public void setIdKlient (int idKlient) {
        this.idKlient = idKlient;
    }
    public int getIdProjektant () {
        return idProjektant;
    }
    public void setIdProjektant (int idProjektant) {
        this.idProjektant = idProjektant;
    }
    public LocalDate getDataZakupu () {
        return dataZakupu;
    }
    public void setDataZakupu (LocalDate dataZakupu) {
        this.dataZakupu = dataZakupu;
    }
    public LocalDate getDataRealizacji () {
        return dataRealizacji;
    }
    public void setDataRealizacji (LocalDate dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }
    public Double getCena() {
        return cena;
    }
    public void setCena (Double cena) {
        this.cena = cena;
    }
    
    @Override
    public String toString () {
        return "Produkt (" +
            "id=" + id +
            ", idKlient='" + idKlient + '\"' +
            ", idProjektant='" + idProjektant + '\"' +
            ", dataZakupu='" + dataZakupu + '\"' +
            ", dataRealizacji='" + dataRealizacji + '\"' +
            ", cena='" + cena + '\"' +
            ')';
    }
}
