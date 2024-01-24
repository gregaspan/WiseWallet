package si.ris.ww.service;

import si.ris.ww.model.Kategorija;

import java.util.List;

public interface KategorijaService {
    public Kategorija saveKategorija(Kategorija kategorija);
    public List<Kategorija> getAllKategorija();
}
