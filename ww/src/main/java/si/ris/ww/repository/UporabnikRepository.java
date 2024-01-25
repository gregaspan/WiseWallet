package si.ris.ww.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import si.ris.ww.model.Uporabnik;

import java.util.List;

@Repository
public interface UporabnikRepository extends JpaRepository<Uporabnik,Integer> {
    // Find by Email, Username, and Minimum ID
    List<Uporabnik> findByEmailAndUsernameAndUporabnikIDGreaterThan(String email, String username, int minId);

    // Find by Email, Username, and Password
    List<Uporabnik> findByEmailAndUsernameAndPassword(String email, String username, String password);

    // Custom query to find users with more than two transactions
    @Query("SELECT u FROM Uporabnik u WHERE SIZE(u.transakcijas) > 2")
    List<Uporabnik> findUsersWithMoreThanTwoTransactions();
}
