package fr.efrei.transfret;

public class Assurance extends DecorateurPrix {

    private static final double TAUX = 0.02;
    private static final double MINIMUM = 15;
    private final boolean activee;

    public Assurance(ComposantPrix composant, boolean activee) {
        super(composant);
        this.activee = activee;
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        if (!activee) {
            return prix;
        }
        return prix + Math.max(calcul.valeurDeclareeTotale() * TAUX, MINIMUM);
    }
}
