package si.ris.ww.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.ris.ww.model.Kategorija;
import si.ris.ww.repository.KategorijaRepository;

import java.util.List;

@Service
public class KategorijaServiceImpl implements KategorijaService{

    @Autowired
    private KategorijaRepository kategorijaRepository;

    @Override
    public Kategorija saveKategorija(Kategorija kategorija) {
        return kategorijaRepository.save(kategorija);
    }

    @Override
    public List<Kategorija> getAllKategorija() {
        return kategorijaRepository.findAll();
    }

    // Implementation of findById method
    @Override
    public Kategorija findById(Integer id) {
        return kategorijaRepository.findById(id).orElse(null);
    }

    @Override
    public Kategorija save(Kategorija kategorija) {
        return kategorijaRepository.save(kategorija);
    }

    @Override
    public void delete(int id) {
        kategorijaRepository.deleteById(id);
    }

    @Override
    public List<Kategorija> findByStartingLetterAndMinId(String startingLetter, int minId) {
        return kategorijaRepository.findByImeStartingWithAndKategorijaIDGreaterThan(startingLetter, minId);
    }

    @Override
    public void deleteById(int id) {
        kategorijaRepository.deleteById(id);
    }

    @Override
    public boolean deleteByName(String ime) {
        Kategorija kategorija = kategorijaRepository.findByIme(ime);
        if (kategorija != null) {
            kategorijaRepository.delete(kategorija);
            return true;
        }
        return false;
    }

    @Override
    public Kategorija findByIme(String ime) {
        return kategorijaRepository.findByIme(ime);
    }


}
