package service.quotes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="quotes")
public class InnerQuote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String isin;
    private Double bid;
    private Double ask;

    public InnerQuote(String isin, Double bid, Double ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }
}
