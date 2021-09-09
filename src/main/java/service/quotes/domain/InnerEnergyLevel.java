package service.quotes.domain;
import javax.persistence.*;

@Entity
@Table(name="energy_level")
public class InnerEnergyLevel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String isin;
    private Double elvl;

    public InnerEnergyLevel(){
    }

    public InnerEnergyLevel(String isin, Double elvl){
        this.isin = isin;
        this.elvl = elvl;
    }
    public InnerEnergyLevel(Integer id, String isin, Double elvl){
        this.id = id;
        this.isin = isin;
        this.elvl = elvl;
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

    public Double getElvl() {
        return elvl;
    }

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }
}
