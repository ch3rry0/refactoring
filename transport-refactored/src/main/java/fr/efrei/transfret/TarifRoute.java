package fr.efrei.transfret;

import java.util.List;

public class TarifRoute implements TarifTransport {

    private static final double PRIX_PAR_KILOMETRE = 1.20;
    private static final double PRIX_PAR_KILOGRAMME = 0.10;

    @Override
    public double calculer(List<Colis> colis, int distance) {
        return distance * PRIX_PAR_KILOMETRE + poidsTotal(colis) * PRIX_PAR_KILOGRAMME;
    }

    private double poidsTotal(List<Colis> colis) {
        return colis.stream().mapToDouble(Colis::getPoids).sum();
    }
}
