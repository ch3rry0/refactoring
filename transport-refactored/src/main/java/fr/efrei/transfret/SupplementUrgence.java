package fr.efrei.transfret;

public class SupplementUrgence extends DecorateurPrix {

    private static final double COEFFICIENT = 1.50;

    private final boolean urgent;

    public SupplementUrgence(ComposantPrix composant, boolean urgent) {
        super(composant);
        this.urgent = urgent;
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        return urgent ? prix * COEFFICIENT : prix;
    }
}
