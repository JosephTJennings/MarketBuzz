package _cw_6.marketbuzz.repository;

import _cw_6.marketbuzz.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
