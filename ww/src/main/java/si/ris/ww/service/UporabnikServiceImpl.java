package si.ris.ww.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.ris.ww.model.Kategorija;
import si.ris.ww.model.Uporabnik;
import si.ris.ww.repository.UporabnikRepository;

import java.util.List;

@Service
public class UporabnikServiceImpl implements UporabnikService{

    @Autowired
    private UporabnikRepository uporabnikRepository;

    @Override
    public Uporabnik save(Uporabnik uporabnik) {
        return uporabnikRepository.save(uporabnik);
    }

    @Override
    public Uporabnik findById(int id) {
        return uporabnikRepository.findById(id).orElse(null);
    }

    @Override
    public List<Uporabnik> findAll() {
        return uporabnikRepository.findAll();
    }

    @Override
    public void delete(int id) {
        uporabnikRepository.deleteById(id);
    }

    // Implement the methods in UporabnikServiceImpl class

    @Override
    public List<Uporabnik> findByEmailAndUsernameAndUporabnikIDGreaterThan(String email, String username, int minId) {
        return uporabnikRepository.findByEmailAndUsernameAndUporabnikIDGreaterThan(email, username, minId);
    }

    @Override
    public List<Uporabnik> findByEmailAndUsernameAndPassword(String email, String username, String password) {
        return uporabnikRepository.findByEmailAndUsernameAndPassword(email, username, password);
    }
    @Override
    public List<Uporabnik> findUsersWithMoreThanTwoTransactions() {
        return uporabnikRepository.findUsersWithMoreThanTwoTransactions();
    }

    @Override
    public List<Uporabnik> getAllUporabnik() {
        return uporabnikRepository.findAll();
    }

}
