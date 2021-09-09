package service.quotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.quotes.domain.InnerQuote;

@Repository
public interface QuoteRepo extends JpaRepository<InnerQuote, Long> {
}
