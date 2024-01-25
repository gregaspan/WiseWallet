package si.ris.ww.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.ris.ww.model.Transakcija;
import si.ris.ww.service.TransakcijaService;

import java.util.List;

@RestController
@RequestMapping("/transakcija")
public class TransakcijaController {

    @Autowired
    private TransakcijaService transakcijaService;

    // GET endpoint to retrieve all Transakcijas
    @GetMapping("/getAll")
    public List<Transakcija> getAllTransakcijas() {
        return transakcijaService.findAll();
    }

    // GET endpoint to retrieve a Transakcija by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transakcija> getTransakcijaById(@PathVariable("id") int id) {
        Transakcija transakcija = transakcijaService.findById(id);
        if (transakcija == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transakcija);
    }

    // POST endpoint to create a new Transakcija
    @PostMapping
    public Transakcija createTransakcija(@RequestBody Transakcija transakcija) {
        return transakcijaService.save(transakcija);
    }

    // PUT endpoint to update an existing Transakcija
    @PutMapping("/{id}")
    public ResponseEntity<Transakcija> updateTransakcija(@PathVariable("id") int id, @RequestBody Transakcija transakcijaDetails) {
        Transakcija transakcija = transakcijaService.findById(id);
        if (transakcija == null) {
            return ResponseEntity.notFound().build();
        }

        // Update Transakcija fields
        transakcija.setZnesek(transakcijaDetails.getZnesek());
        transakcija.setDatum(transakcijaDetails.getDatum());
        transakcija.setOpis(transakcijaDetails.getOpis());
        transakcija.setKategorija(transakcijaDetails.getKategorija());

        final Transakcija updatedTransakcija = transakcijaService.save(transakcija);
        return ResponseEntity.ok(updatedTransakcija);
    }

    // DELETE endpoint to delete a Transakcija
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransakcija(@PathVariable("id") int id) {
        Transakcija transakcija = transakcijaService.findById(id);
        if (transakcija == null) {
            return ResponseEntity.notFound().build();
        }

        transakcijaService.delete(id);
        return ResponseEntity.ok().build();
    }

    // GET endpoint for querying Transakcija with specific criteria
    @GetMapping("/search")
    public ResponseEntity<List<Transakcija>> searchTransakcija(@RequestParam(defaultValue = "5") int minId,
                                                               @RequestParam(defaultValue = "A") String opisStart) {
        List<Transakcija> transakcijas = transakcijaService.findByTransakcijaIDGreaterThanAndOpisStartingWith(minId, opisStart);
        if (transakcijas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transakcijas);
    }

}
