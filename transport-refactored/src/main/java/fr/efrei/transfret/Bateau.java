package fr.efrei.transfret;

import java.util.List;

public class Bateau extends Vehicule {

    private static final double KM_PAR_MILLE_NAUTIQUE = 1.852;
    private final boolean conteneursRefrigeres;

    public Bateau(String immatriculation, double capaciteMax, boolean conteneursRefrigeres) {
        super(immatriculation, capaciteMax);
        this.conteneursRefrigeres = conteneursRefrigeres;
    }

    @Override
    public boolean peutPrendre(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.MER
                && (!colis.stream().anyMatch(c -> c.getType() == TypeMarchandise.REFRIGERE)
                || conteneursRefrigeres);
    }

    @Override
    public void partir(int distance) {
        int milesNautiques = (int) (distance / KM_PAR_MILLE_NAUTIQUE);
        if (conteneursRefrigeres) {
            System.out.println("Conteneurs frigorifiques réglés à 4°C");
        }
        System.out.println("Le navire " + immatriculation + " navigue sur " + milesNautiques + " milles nautiques");
    }
}
