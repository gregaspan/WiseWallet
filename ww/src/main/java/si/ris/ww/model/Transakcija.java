package si.ris.ww.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transakcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transakcijaID;

    @Column(nullable = false)
    private double znesek;

    @Column(nullable = false)
    private Date datum;

    private String opis;

    @ManyToOne
    @JoinColumn(name = "kategorija_id", nullable = false)
    private Kategorija kategorija;

    public Transakcija() {
    }

    public int getTransakcijaID() {
        return transakcijaID;
    }

    public void setTransakcijaID(int transakcijaID) {
        this.transakcijaID = transakcijaID;
    }

    public double getZnesek() {
        return znesek;
    }

    public void setZnesek(double znesek) {
        this.znesek = znesek;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}
