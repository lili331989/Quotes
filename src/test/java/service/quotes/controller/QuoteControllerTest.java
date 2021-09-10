package service.quotes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.quotes.domain.EnergyLevel;
import service.quotes.domain.InnerEnergyLevel;
import service.quotes.domain.InnerQuote;
import service.quotes.domain.Quote;
import service.quotes.service.QuoteService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class QuoteControllerTest {
    @Mock
    private QuoteService quoteService;
    private EnergyLevel energyLevel;
    private InnerEnergyLevel innerEnergyLevel;
    private Quote wrongQuote;
    private Quote quote;
    private InnerQuote innerQuote;
    private String isin = "RU00A0JX0J2";
    private List<EnergyLevel> energyLevelList;
    @InjectMocks
    private QuoteController quoteController;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        energyLevel = new EnergyLevel("RU00A0JX0J2",100.2);
        innerEnergyLevel = new InnerEnergyLevel("RU00A0JX0J2",100.2);
        wrongQuote = new Quote("RU00A0JX0J22", 102.2, 100.2);
        quote = new Quote("RU00A0JX0J22", 100.2, 101.9);
        innerQuote = new InnerQuote("RU00A0JX0J2", 100.2, 101.9);
        mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
    }

    @AfterEach
    void tearDown() {
        energyLevel = null;
    }

    @Test
    public void getElvlsTest() throws Exception {
        when(quoteService.getElvls()).thenReturn(energyLevelList);
        mockMvc.perform(MockMvcRequestBuilders.get("/quote/getElvls").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        verify(quoteService).getElvls();
        verify(quoteService,times(1)).getElvls();
    }

    @Test
    public void saveQuoteAndEnergyLevelTest() throws Exception{
        when(quoteService.calculateAndSaveElvl(any())).thenReturn(innerEnergyLevel);
        when(quoteService.saveQuote(any())).thenReturn(innerQuote);
        mockMvc.perform(MockMvcRequestBuilders.post("/quote/saveQuote").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(quote))).
                andExpect(status().isCreated());
        verify(quoteService,times(1)).calculateAndSaveElvl(any());
    }

    @Test
    public void getElvlTest() throws Exception {
        when(quoteService.getElvlByIsin(isin)).thenReturn(energyLevel);
        mockMvc.perform(MockMvcRequestBuilders.get("/quote/getElvl/RU00A0JX0J2").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(isin))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenInputIsInvalidThenReturnsStatus400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/quote/saveQuote").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(wrongQuote))).
                andExpect(status().isBadRequest());
    }

    public  static <T> String asJsonString(T energyLevel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(energyLevel);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
