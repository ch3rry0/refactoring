package fr.efrei.transfret;

import java.util.List;

public class TarifMer implements TarifTransport {

    private static final double PRIX_PAR_KILOMETRE = 0.50;
    private static final double PRIX_PAR_KILOGRAMME = 0.03;
    private static final double MINIMUM_FACTURATION = 500;

    @Override
    public double calculer(List<Colis> colis, int distance) {
        return Math.max(MINIMUM_FACTURATION,
                distance * PRIX_PAR_KILOMETRE + poidsTotal(colis) * PRIX_PAR_KILOGRAMME);
    }

    private double poidsTotal(List<Colis> colis) {
        return colis.stream().mapToDouble(Colis::getPoids).sum();
    }
}
