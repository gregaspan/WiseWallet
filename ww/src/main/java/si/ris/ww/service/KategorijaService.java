package si.ris.ww.service;

import si.ris.ww.model.Kategorija;

import java.util.List;

public interface KategorijaService {
    public Kategorija saveKategorija(Kategorija kategorija);
    public List<Kategorija> getAllKategorija();
    Kategorija findById(Integer id);
    Kategorija save(Kategorija kategorija);
    void delete(int id);
    List<Kategorija> findByStartingLetterAndMinId(String startingLetter, int minId);


}
