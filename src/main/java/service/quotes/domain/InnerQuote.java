package service.quotes.domain;

import javax.persistence.*;

@Entity
@Table(name="quotes")
public class InnerQuote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String isin;
    private Double bid;
    private Double ask;

    public InnerQuote() {
    }

    public InnerQuote(String isin, Double bid, Double ask) {
        this.id = id;
        this.bid = bid;
        this.ask = ask;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
