import java.util.ArrayList;
import java.util.List;

public class Evidence {
    private List<Lahev> polozky = new ArrayList<>();

    public void pridatPolozku(Lahev p){
        polozky.add(p);
    }

    public List<Lahev> getPolozky() {
        return polozky;
    }

    public void smazPolozku() {
        polozky.remove(polozky.size()-1);
    }
}
