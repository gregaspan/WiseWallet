package si.ris.ww.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.ris.ww.model.Kategorija;
import si.ris.ww.service.KategorijaService;


import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/kategorija")
@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<Kategorija> getKategorijaById(@PathVariable("id") Integer id) {
        Kategorija kategorija = kategorijaService.findById(id);
        if (kategorija == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kategorija);
    }

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf() throws MalformedURLException {
        List<Kategorija> categories = kategorijaService.getAllKategorija(); // Replace with actual service call
        String imagePath = "https://i.pinimg.com/564x/ef/58/c8/ef58c8edea9a92227e4f743975832957.jpg"; // You need to provide the path to your image

        byte[] pdfContent = pdfGenerationService.generateCategoryPdf(categories, imagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=categories.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }




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

        kategorijaService.deleteById(id);
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

    @DeleteMapping("/deleteByName/{ime}")
    public ResponseEntity<Void> deleteKategorijaByName(@PathVariable("ime") String ime) {
        boolean isDeleted = kategorijaService.deleteByName(ime);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateByName/{oldName}")
    public ResponseEntity<Kategorija> updateKategorijaByName(@PathVariable String oldName, @RequestBody Kategorija kategorijaDetails) {
        Kategorija existingKategorija = kategorijaService.findByIme(oldName);
        if (existingKategorija == null) {
            return ResponseEntity.notFound().build();
        }

        existingKategorija.setIme(kategorijaDetails.getIme());
        final Kategorija updatedKategorija = kategorijaService.save(existingKategorija);
        return ResponseEntity.ok(updatedKategorija);
    }


}
