package si.ris.ww.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.ris.ww.model.Kategorija;
import si.ris.ww.model.Transakcija;

import java.util.List;

@Repository
public interface TransakcijaRepository extends JpaRepository<Transakcija,Integer> {
    List<Transakcija> findByTransakcijaIDGreaterThanAndOpisStartingWith(int minId, String opisStart);

}
