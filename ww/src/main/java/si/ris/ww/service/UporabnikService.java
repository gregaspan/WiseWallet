package si.ris.ww.service;

import si.ris.ww.model.Uporabnik;

import java.util.List;

public interface UporabnikService {
    Uporabnik save(Uporabnik uporabnik);
    Uporabnik findById(int id);
    List<Uporabnik> findAll();
    void delete(int id);
    List<Uporabnik> findByEmailAndUsernameAndUporabnikIDGreaterThan(String email, String username, int minId);
    List<Uporabnik> findByEmailAndUsernameAndPassword(String email, String username, String password);

    List<Uporabnik> findUsersWithMoreThanTwoTransactions();


}
