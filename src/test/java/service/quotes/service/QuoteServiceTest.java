package service.quotes.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import service.quotes.domain.InnerEnergyLevel;
import service.quotes.domain.Quote;
import service.quotes.repos.EnergyLevelRepo;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteServiceTest {
    @Autowired
    private QuoteService quoteService;
    private Quote quote;

    @MockBean
    private EnergyLevelRepo energyLevelRepo;

    @Before
    public void setUp() {
        quote = new Quote("RU00A0JX0J2", 100.2, 101.9);
        InnerEnergyLevel elvl = new InnerEnergyLevel(1, "RU00A0JX0J2",100.2);
        Mockito.when(energyLevelRepo.findByIsin(quote.getIsin()))
                .thenReturn(elvl);
    }

    @Test
    public void quoteServiceCalculateTest() {
        assertEquals (quoteService.calculateAndSaveElvl(quote).getIsin(), "RU00A0JX0J2");
        assertEquals (quoteService.calculateAndSaveElvl(quote).getElvl(), 100.2);
        assertEquals (quoteService.calculateAndSaveElvl(quote).getId(), new Integer(1));
    }

    @Test
    public void getElvlByIsinTest() {
        String isin = "RU00A0JX0J2";
        assertEquals (quoteService.getElvlByIsin(isin).getElvl(), 100.2);
    }

}
