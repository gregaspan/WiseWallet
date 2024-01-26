package si.ris.ww.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.ris.ww.model.Kategorija;
import java.util.List; // Add this import statement

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija,Integer> {
    List<Kategorija> findByImeStartingWithAndKategorijaIDGreaterThan(String ime, int kategorijaID);

    Kategorija findByIme(String ime);



}
