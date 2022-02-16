package Profil;

public class InregistrareClient {
    private String Nume,CNP,Telefon,Adresa,Numar;

    public InregistrareClient() {
    }

    public InregistrareClient(String nume, String CNP,String telefon,String adresa, String numar){
        this.Nume=nume;
        this.CNP=CNP;
        this.Telefon=telefon;
        this.Adresa=adresa;
        this.Numar=numar;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getNumar() {
        return Numar;
    }

    public void setNumar(String numar) {
        Numar = numar;
    }
}
