package si.ris.ww.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.ris.ww.model.Kategorija;
@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija,Integer> {
}
