package fr.efrei.transfret;

import java.util.List;

public class TarifFluvial implements TarifTransport {

    private static final double PRIX_PAR_KILOMETRE = 0.40;
    private static final double PRIX_PAR_KILOGRAMME = 0.02;
    private static final double MINIMUM_FACTURATION = 300;

    @Override
    public double calculer(List<Colis> colis, int distance) {
        return Math.max(MINIMUM_FACTURATION,
                distance * PRIX_PAR_KILOMETRE
                        + colis.stream().mapToDouble(Colis::getPoids).sum() * PRIX_PAR_KILOGRAMME);
    }
}
