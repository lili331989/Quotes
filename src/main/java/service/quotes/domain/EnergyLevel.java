package service.quotes.domain;

import java.io.Serializable;

public class EnergyLevel implements Serializable{
    private String isin;
    private Double elvl;

    public EnergyLevel(){
    }

    public EnergyLevel(String isin, Double elvl){
        this.isin = isin;
        this.elvl = elvl;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getElvl() {
        return elvl;
    }

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }
}
