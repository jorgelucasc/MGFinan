package model;

import java.io.Serializable;

public class Despesas implements Serializable {

    private long id;
    private String obsDespesa;
    private String tipoDespesa;
    private int valordespesa;

    @Override
    public String toString() {
        return "Valor da Despesa = R$" + valordespesa +
                "Observação Despesa =" + obsDespesa
                ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObsDespesa() {
        return obsDespesa;
    }

    public void setObsDespesa(String obsDespesa) {
        this.obsDespesa = obsDespesa;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public int getValordespesa() {
        return valordespesa;
    }

    public void setValordespesa(int valordespesa) {
        this.valordespesa = valordespesa;
    }
}
