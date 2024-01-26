package si.ris.ww.service;

import si.ris.ww.model.Transakcija;

import java.util.List;

public interface TransakcijaService {
    Transakcija save(Transakcija transakcija);
    Transakcija findById(int id);
    List<Transakcija> findAll();
    void delete(int id);

    List<Transakcija> findByTransakcijaIDGreaterThanAndOpisStartingWith(int minId, String opisStart);


}
