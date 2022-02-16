package DetaliiInchiriere;

public class InchiriereNoua {

    String nume,cnp,telefon,masina_inchiriata,an_fabricatie,perioada_start,perioada_retur,nr_km,pret;
    int imagine;

    public InchiriereNoua(){
    }

    public InchiriereNoua(int imagine,String nume, String cnp , String telefon, String masina_inchiriata,
                          String an_fabricatie,String nr_km,String perioada_start,String perioada_retur,String pret){
        this.imagine = imagine;
        this.nume = nume;
        this.cnp = cnp;
        this.telefon = telefon;
        this.masina_inchiriata = masina_inchiriata;
        this.an_fabricatie = an_fabricatie;
        this.perioada_start = perioada_start;
        this.perioada_retur = perioada_retur;
        this.pret = pret;
        this.nr_km = nr_km;
    }

    public int getImagine() {
        return imagine;
    }

    public void setImagine(int imagine) {
        this.imagine = imagine;
    }

    public String getNr_km() {
        return nr_km;
    }

    public void setNr_km(String nr_km) {
        this.nr_km = nr_km;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMasina_inchiriata() {
        return masina_inchiriata;
    }

    public void setMasina_inchiriata(String masina_inchiriata) {
        this.masina_inchiriata = masina_inchiriata;
    }

    public String getAn_fabricatie() {
        return an_fabricatie;
    }

    public void setAn_fabricatie(String an_fabricatie) {
        this.an_fabricatie = an_fabricatie;
    }

    public String getPerioada_start() {
        return perioada_start;
    }

    public void setPerioada_start(String perioada_start) {
        this.perioada_start = perioada_start;
    }

    public String getPerioada_retur() {
        return perioada_retur;
    }

    public void setPerioada_retur(String perioada_retur) {
        this.perioada_retur = perioada_retur;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }
}
