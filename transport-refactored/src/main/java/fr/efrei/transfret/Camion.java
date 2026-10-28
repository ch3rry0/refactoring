package fr.efrei.transfret;

import java.util.List;

public class Camion extends Vehicule {

    protected boolean adr; // certification matières dangereuses

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax);
        this.adr = adr;
    }

    @Override
    public boolean peutPrendre(ModeTransport mode, List<Colis> colis) {
        if (mode != ModeTransport.ROUTE || colis.stream().anyMatch(c -> c.getType() == TypeMarchandise.REFRIGERE)) {
            return false;
        }
        return !colis.stream().anyMatch(c -> c.getType() == TypeMarchandise.DANGEREUX) || adr;
    }

    @Override
    public void partir(int distance) {
        System.out.println("Le camion " + immatriculation + " roule sur " + distance + " km");
    }
}
