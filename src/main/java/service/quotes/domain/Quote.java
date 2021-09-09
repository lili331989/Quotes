package service.quotes.domain;

import java.io.Serializable;

public class Quote implements Serializable {
    private String isin;
    private Double bid;
    private Double ask;

    public Quote() {
    }

    public Quote (String isin, Double bid, Double ask){
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }
}
