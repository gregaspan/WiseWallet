package si.ris.ww.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.ris.ww.model.Kategorija;
import si.ris.ww.service.KategorijaService;

import java.util.List;

@RestController
@RequestMapping("/kategorija")
public class KategorijaController {
    @Autowired
    private KategorijaService kategorijaService;

    @PostMapping("/add")
    public String add(@RequestBody Kategorija kategorija) {
        kategorijaService.saveKategorija(kategorija);
        return "Nova kategorija dodana";
    }

    @GetMapping("/getAll")
    public List<Kategorija> getAllKategorija() {
        return kategorijaService.getAllKategorija();
    }
}
