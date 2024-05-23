public class Lahev {
    private int objem;
    private String barva;
    private int cena;
    private int dph;

    public Lahev(int objem, String barva, int cena, int dph) {
        this.objem = objem;
        this.barva = barva;
        this.cena = cena;
        this.dph = dph;
    }

    public int getObjem() {
        return objem;
    }

    public void setObjem(int objem) {
        this.objem = objem;
    }

    public String getBarva() {
        return barva;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getDph() {
        return dph;
    }

    public void setDph(int dph) {
        this.dph = dph;
    }
}
