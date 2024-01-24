package si.ris.ww.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.ris.ww.model.Transakcija;
import si.ris.ww.repository.TransakcijaRepository;

import java.util.List;

@Service
public class TransakcijaServiceImpl implements TransakcijaService{

    @Autowired
    private TransakcijaRepository transakcijaRepository;

    @Override
    public Transakcija save(Transakcija transakcija) {
        return transakcijaRepository.save(transakcija);
    }

    @Override
    public Transakcija findById(int id) {
        return transakcijaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transakcija> findAll() {
        return transakcijaRepository.findAll();
    }

    @Override
    public void delete(int id) {
        transakcijaRepository.deleteById(id);
    }

    @Override
    public List<Transakcija> findByTransakcijaIDGreaterThanAndOpisStartingWith(int minId, String opisStart) {
        return transakcijaRepository.findByTransakcijaIDGreaterThanAndOpisStartingWith(minId, opisStart);
    }
}
