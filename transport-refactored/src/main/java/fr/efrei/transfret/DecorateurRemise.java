package fr.efrei.transfret;

public class DecorateurRemise extends DecorateurPrix {

    private final PolitiqueRemise remise;

    public DecorateurRemise(ComposantPrix composant, PolitiqueRemise remise) {
        super(composant);
        this.remise = remise;
    }

    @Override
    public double calculer(CalculPrix calcul) {
        return remise.appliquer(composant.calculer(calcul));
    }
}
