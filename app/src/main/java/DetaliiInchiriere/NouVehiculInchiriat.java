package DetaliiInchiriere;

import android.graphics.drawable.Drawable;

public class NouVehiculInchiriat {

   public String brand, anFab,nrKm,perioadaStart,perioadaRetur;
   public Drawable imgDrawable;
    public NouVehiculInchiriat(Drawable imgDrawable,String brand, String anFab, String nrKm, String perioadaStart, String perioadaRetur) {
        this.imgDrawable = imgDrawable;
        this.brand = brand;
        this.anFab = anFab;
        this.nrKm = nrKm;
        this.perioadaStart = perioadaStart;
        this.perioadaRetur = perioadaRetur;
    }
}
