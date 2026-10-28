package fr.efrei.transfret;

public class SupplementHorsGabarit extends DecorateurPrix {

    private static final double DIMENSION_MAXIMALE_CM = 200;
    private static final double COEFFICIENT = 1.10;

    public SupplementHorsGabarit(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        boolean horsGabarit = calcul.colis().stream().anyMatch(colis ->
                colis.getLongueur() > DIMENSION_MAXIMALE_CM
                        || colis.getLargeur() > DIMENSION_MAXIMALE_CM
                        || colis.getHauteur() > DIMENSION_MAXIMALE_CM);
        return horsGabarit ? prix * COEFFICIENT : prix;
    }
}
