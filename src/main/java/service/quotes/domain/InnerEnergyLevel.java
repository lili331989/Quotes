package service.quotes.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="energy_level")
public class InnerEnergyLevel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String isin;
    private Double elvl;

    public InnerEnergyLevel(String isin, Double elvl){
        this.isin = isin;
        this.elvl = elvl;
    }
}
