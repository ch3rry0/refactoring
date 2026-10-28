package fr.efrei.transfret;

public class RemiseEntreprise implements PolitiqueRemise {

    private static final double SEUIL_REMISE_SUPERIEURE = 5000;
    private static final double COEFFICIENT_STANDARD = 0.90;
    private static final double COEFFICIENT_SUPERIEUR = 0.85;

    @Override
    public double appliquer(double prix) {
        return prix * (prix > SEUIL_REMISE_SUPERIEURE
                ? COEFFICIENT_SUPERIEUR
                : COEFFICIENT_STANDARD);
    }
}
