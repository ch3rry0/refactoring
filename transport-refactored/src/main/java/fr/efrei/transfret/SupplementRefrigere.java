package fr.efrei.transfret;

public class SupplementRefrigere extends DecorateurPrix {

    private static final double COEFFICIENT = 1.20;

    public SupplementRefrigere(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(CalculPrix calcul) {
        double prix = composant.calculer(calcul);
        return calcul.contient(TypeMarchandise.REFRIGERE) ? prix * COEFFICIENT : prix;
    }
}
