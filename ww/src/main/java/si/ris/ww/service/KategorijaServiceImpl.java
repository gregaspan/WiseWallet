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
}
