package service.quotes.repos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import service.quotes.domain.InnerEnergyLevel;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EnergyLevelRepoTest {
    @Autowired
    private EnergyLevelRepo energyLevelRepo;
    private InnerEnergyLevel energyLevel;

    @BeforeEach
    public void setUp() {
        energyLevel = new InnerEnergyLevel("RU00A0JX0J2",100.2);
    }
    @AfterEach
    public void tearDown() {
        energyLevelRepo.deleteAll();
        energyLevel = null;
    }
    @Test
    public void saveEnergyLevelTest() {
        energyLevelRepo.save(energyLevel);
        Optional<InnerEnergyLevel> fetchedEnergyLevel = energyLevelRepo.findById(energyLevel.getId());
        fetchedEnergyLevel.ifPresent(innerEnergyLevel -> assertEquals("RU00A0JX0J2", innerEnergyLevel.getIsin()));
        assertTrue(fetchedEnergyLevel.isPresent());
    }

    @Test
    public void findByIsinTest() {
        energyLevelRepo.save(energyLevel);
        InnerEnergyLevel fetchedEnergyLevel = energyLevelRepo.findByIsin(energyLevel.getIsin());
        assertEquals("RU00A0JX0J2", fetchedEnergyLevel.getIsin());
    }

    @Test
    public void getAllEnergyLevelsTest(){
        InnerEnergyLevel energyLevel1 = new InnerEnergyLevel(1, "RU00A0JX0J2",100.2);
        InnerEnergyLevel energyLevel2 = new InnerEnergyLevel(2, "RU00A0JX0J2",100.5);
        energyLevelRepo.save(energyLevel1);
        energyLevelRepo.save(energyLevel2);
        List<InnerEnergyLevel> energyLevelList = energyLevelRepo.findAll();
        assertEquals("RU00A0JX0J2", energyLevelList.get(1).getIsin());
    }
}
