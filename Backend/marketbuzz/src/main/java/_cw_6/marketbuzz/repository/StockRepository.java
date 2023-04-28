package _cw_6.marketbuzz.repository;

import _cw_6.marketbuzz.model.StaticStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StaticStock, Long> {
}
