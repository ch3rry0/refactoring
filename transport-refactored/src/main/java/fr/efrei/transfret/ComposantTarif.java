package fr.efrei.transfret;

import java.util.List;

public class ComposantTarif implements ComposantPrix {

    private final TarifTransport tarif;

    public ComposantTarif(TarifTransport tarif) {
        this.tarif = tarif;
    }

    @Override
    public double calculer(CalculPrix calcul) {
        return tarif.calculer(calcul.colis(), calcul.distance());
    }
}
