package service.quotes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyLevel implements Serializable{
    private String isin;
    private Double elvl;
}
