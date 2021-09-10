package service.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.quotes.domain.EnergyLevel;
import service.quotes.domain.InnerEnergyLevel;
import service.quotes.domain.Quote;
import service.quotes.service.QuoteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping(value = "/saveQuote")
    public ResponseEntity<EnergyLevel> saveQuoteAndEnergyLevel(@Valid @RequestBody Quote quote) {
            quoteService.saveQuote(quote);
            InnerEnergyLevel innerEnergyLevel = quoteService.calculateAndSaveElvl(quote);
            EnergyLevel energyLevel = new EnergyLevel(innerEnergyLevel.getIsin(), innerEnergyLevel.getElvl());
            return new ResponseEntity<>(energyLevel, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getElvls")
    public ResponseEntity<List<EnergyLevel>> getElvls() {
        return new ResponseEntity<>(
                 quoteService.getElvls(), HttpStatus.OK);
    }

    @GetMapping(value = "/getElvl/{isin}")
    public ResponseEntity<EnergyLevel> getElvl(@PathVariable(value = "isin") String isin) {
        return new ResponseEntity<>(quoteService.getElvlByIsin(isin),HttpStatus.OK);
    }
}
