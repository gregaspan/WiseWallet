package si.ris.ww.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;  // Import List from java.util


@Entity
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uporabnikID;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "uporabnik")
    private List<Transakcija> transakcijas;

    public Uporabnik() {
    }

    public int getUporabnikID() {
        return uporabnikID;
    }

    public void setUporabnikID(int uporabnikID) {
        this.uporabnikID = uporabnikID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transakcija> getTransakcijas() {
        return transakcijas;
    }

    public void setTransakcijas(List<Transakcija> transakcijas) {
        this.transakcijas = transakcijas;
    }
}
