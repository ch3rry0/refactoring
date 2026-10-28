package fr.efrei.transfret;

import java.util.List;

public class TarifAir implements TarifTransport {

    private static final double PRIX_PAR_KILOMETRE = 0.60;
    private static final double PRIX_PAR_KILOGRAMME_TAXABLE = 4.50;
    private static final double COEFFICIENT_POIDS_VOLUMETRIQUE = 167;

    @Override
    public double calculer(List<Colis> colis, int distance) {
        double poids = colis.stream().mapToDouble(Colis::getPoids).sum();
        double volume = colis.stream().mapToDouble(Colis::getVolume).sum();
        double poidsTaxable = Math.max(poids, volume * COEFFICIENT_POIDS_VOLUMETRIQUE);
        return distance * PRIX_PAR_KILOMETRE + poidsTaxable * PRIX_PAR_KILOGRAMME_TAXABLE;
    }
}
