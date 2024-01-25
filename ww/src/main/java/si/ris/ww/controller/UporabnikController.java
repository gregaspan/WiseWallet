package si.ris.ww.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.ris.ww.model.Uporabnik;
import si.ris.ww.service.UporabnikService;

import java.util.List;

@RestController
@RequestMapping("/uporabnik")
public class UporabnikController {

    @Autowired
    private UporabnikService uporabnikService;

    // POST endpoint to create a new Uporabnik
    @PostMapping
    public Uporabnik createUporabnik(@RequestBody Uporabnik uporabnik) {
        return uporabnikService.save(uporabnik);
    }

    // GET endpoint to retrieve all Uporabniks
    @GetMapping
    public List<Uporabnik> getAllUporabniks() {
        return uporabnikService.findAll();
    }

    // GET endpoint to retrieve a single Uporabnik by ID
    @GetMapping("/{id}")
    public ResponseEntity<Uporabnik> getUporabnikById(@PathVariable("id") int id) {
        Uporabnik uporabnik = uporabnikService.findById(id);
        if (uporabnik == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(uporabnik);
    }

    // PUT endpoint to update an existing Uporabnik
    @PutMapping("/{id}")
    public ResponseEntity<Uporabnik> updateUporabnik(@PathVariable("id") int id, @RequestBody Uporabnik uporabnikDetails) {
        Uporabnik uporabnik = uporabnikService.findById(id);
        if (uporabnik == null) {
            return ResponseEntity.notFound().build();
        }

        uporabnik.setEmail(uporabnikDetails.getEmail());
        uporabnik.setUsername(uporabnikDetails.getUsername());
        uporabnik.setPassword(uporabnikDetails.getPassword());
        // Update other fields as necessary

        final Uporabnik updatedUporabnik = uporabnikService.save(uporabnik);
        return ResponseEntity.ok(updatedUporabnik);
    }

    // DELETE endpoint to delete an Uporabnik
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUporabnik(@PathVariable("id") int id) {
        Uporabnik uporabnik = uporabnikService.findById(id);
        if (uporabnik == null) {
            return ResponseEntity.notFound().build();
        }

        uporabnikService.delete(id);
        return ResponseEntity.ok().build();
    }


    // Endpoint for querying by email, username, and minimum ID
    @GetMapping("/search/minId")
    public List<Uporabnik> findByEmailAndUsernameAndMinId(@RequestParam String email,
                                                          @RequestParam String username,
                                                          @RequestParam int minId) {
        return uporabnikService.findByEmailAndUsernameAndUporabnikIDGreaterThan(email, username, minId);
    }

    // Endpoint for querying by email, username, and password
    @GetMapping("/search/credentials")
    public List<Uporabnik> findByEmailAndUsernameAndPassword(@RequestParam String email,
                                                             @RequestParam String username,
                                                             @RequestParam String password) {
        return uporabnikService.findByEmailAndUsernameAndPassword(email, username, password);
    }

    // Endpoint to get users with more than two transactions
    @GetMapping("/users-with-more-than-two-transactions")
    public List<Uporabnik> getUsersWithMoreThanTwoTransactions() {
        return uporabnikService.findUsersWithMoreThanTwoTransactions();
    }

}
