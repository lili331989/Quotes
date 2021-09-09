package service.quotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.quotes.domain.InnerQuote;
import java.util.List;

@Repository
public interface QuoteRepo extends JpaRepository<InnerQuote, Long> {
}
