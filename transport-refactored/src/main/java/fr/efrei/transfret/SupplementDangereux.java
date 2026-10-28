package fr.efrei.transfret;

public class SupplementDangereux extends DecorateurPrix {

    private static final double COEFFICIENT = 1.30;

    public SupplementDangereux(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        return calcul.contient(TypeMarchandise.DANGEREUX) ? prix * COEFFICIENT : prix;
    }
}
