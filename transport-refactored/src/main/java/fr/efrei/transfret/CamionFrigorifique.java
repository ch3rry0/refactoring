package fr.efrei.transfret;

import java.util.List;

public class CamionFrigorifique extends Camion {

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    public boolean peutPrendre(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.ROUTE
                && !colis.isEmpty()
                && colis.stream().allMatch(c -> c.getType() == TypeMarchandise.REFRIGERE);
    }

    @Override
    public void partir(int distance) {
        System.out.println("Réfrigération activée à 4°C sur " + immatriculation);
        System.out.println("Le camion " + immatriculation + " roule sur " + distance + " km");
    }
}
