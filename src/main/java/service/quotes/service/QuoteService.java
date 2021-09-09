package service.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.quotes.domain.EnergyLevel;
import service.quotes.domain.InnerEnergyLevel;
import service.quotes.domain.InnerQuote;
import service.quotes.domain.Quote;
import service.quotes.repos.EnergyLevelRepo;
import service.quotes.repos.QuoteRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuoteService {
    private QuoteRepo quoteRepo;
    private EnergyLevelRepo energyLevelRepo;
    private Map<String, Object> locks;

    @Autowired
    public QuoteService (QuoteRepo quoteRepo, EnergyLevelRepo energyLevelRepo) {
        this.quoteRepo = quoteRepo;
        this.energyLevelRepo = energyLevelRepo;
        locks = new ConcurrentHashMap<>();
    }

    public EnergyLevel getElvlByIsin (String isin) {
        InnerEnergyLevel energyLevel = energyLevelRepo.findByIsin(isin);
        if (energyLevel != null)  return new EnergyLevel(energyLevel.getIsin(), energyLevel.getElvl());
        else return null;
    }

    public List<EnergyLevel> getElvls () {
        List <InnerEnergyLevel> innerElvls = energyLevelRepo.findAll();
        List <EnergyLevel> elvls = new ArrayList<>();
        innerElvls.forEach(elvl -> elvls.add(new EnergyLevel(elvl.getIsin(), elvl.getElvl())));
        return elvls;
    }

    public boolean check (Quote quote) {
        if (quote.getAsk() == null) return false;
        if (quote.getIsin() != null && quote.getIsin().length() != 12) return false;
        if (quote.getBid() != null && quote.getBid() >= quote.getAsk()) return false;
        return true;
    }

    public InnerEnergyLevel calculateAndSaveElvl (Quote quote) {
        synchronized (locks.computeIfAbsent(quote.getIsin(), k -> new Object()))
        {
            InnerEnergyLevel energyLevel = calculateElvl(quote);
            energyLevelRepo.save(energyLevel);
            locks.remove(quote.getIsin());
            return energyLevel;
        }
    }

    public InnerQuote saveQuote (Quote quote) {
        return quoteRepo.save(new InnerQuote(quote.getIsin(), quote.getBid(), quote.getAsk()));
    }

    public InnerEnergyLevel calculateElvl (Quote quote){
        InnerEnergyLevel energyLevel = energyLevelRepo.findByIsin(quote.getIsin());
        if (energyLevel == null) {
            energyLevel = new InnerEnergyLevel(quote.getIsin(), quote.getBid() != null ? quote.getBid() : quote.getAsk());
        }
        else if (quote.getBid() != null && quote.getBid() > energyLevel.getElvl()){
            energyLevel.setElvl(quote.getBid());
        }
        else if (quote.getAsk() < energyLevel.getElvl()){
            energyLevel.setElvl(quote.getAsk());
        }
        return energyLevel;
    }
}
