package com.example.mgfinan2.dominio.entidades;

import java.io.Serializable;

public class Despesa implements Serializable {

    public int id;
    public String obsDespesa;
    public String vlrDespesa;
    public String tpdespesa;
    public String getDateTime;

    public Despesa(){
        id = 0;
    }
}
