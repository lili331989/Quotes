package service.quotes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.quotes.validation.QuoteAnnotation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@QuoteAnnotation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote implements Serializable {
    @Size(min = 12, max = 12, message = "Size must be 12")
    @NotNull (message = "Isin must not be null")
    private String isin;

    @Valid
    private Double bid;

    @Valid
    @NotNull (message = "<ask> must not be null")
    private Double ask;
}
