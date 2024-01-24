package si.ris.ww.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // GET endpoint to retrieve a Kategorija by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Kategorija> getKategorijaById(@PathVariable("id") Integer id) {
        Kategorija kategorija = kategorijaService.findById(id);
        if (kategorija == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kategorija);
    }
    // PUT endpoint to update a Kategorija record
    @PutMapping("/{id}")
    public ResponseEntity<Kategorija> updateKategorija(@PathVariable("id") int id, @RequestBody Kategorija kategorijaDetails) {
        Kategorija kategorija = kategorijaService.findById(id);
        if (kategorija == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the fields of Kategorija
        kategorija.setIme(kategorijaDetails.getIme());

        final Kategorija updatedKategorija = kategorijaService.save(kategorija);
        return ResponseEntity.ok(updatedKategorija);
    }

    // DELETE endpoint to delete a Kategorija record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategorija(@PathVariable("id") int id) {
        Kategorija kategorija = kategorijaService.findById(id);
        if (kategorija == null) {
            return ResponseEntity.notFound().build();
        }

        kategorijaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Kategorija>> searchKategorija(@RequestParam(defaultValue = "A") String startingLetter,
                                                             @RequestParam(defaultValue = "5") int minId) {
        List<Kategorija> kategorijas = kategorijaService.findByStartingLetterAndMinId(startingLetter, minId);
        if (kategorijas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kategorijas);
    }


}
