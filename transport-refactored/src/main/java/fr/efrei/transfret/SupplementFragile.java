package fr.efrei.transfret;

public class SupplementFragile extends DecorateurPrix {

    private static final double COEFFICIENT = 1.15;

    public SupplementFragile(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        return calcul.contient(TypeMarchandise.FRAGILE) ? prix * COEFFICIENT : prix;
    }
}
