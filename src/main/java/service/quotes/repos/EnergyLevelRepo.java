package service.quotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.quotes.domain.InnerEnergyLevel;
import java.util.List;

@Repository
public interface EnergyLevelRepo extends JpaRepository<InnerEnergyLevel, Integer> {
    InnerEnergyLevel findByIsin (String isin);
    List<InnerEnergyLevel> findAll ();
}
