package fr.efrei.transfret;

import java.util.List;

public class Avion extends Vehicule {

    private static final int ALTITUDE_CROISIERE = 35000;

    public Avion(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    @Override
    public boolean peutPrendre(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.AIR;
    }

    @Override
    public void partir(int distance) {
        System.out.println("L'avion " + immatriculation + " roule sur la piste");
        System.out.println("L'avion " + immatriculation + " vole à " + ALTITUDE_CROISIERE + " pieds");
    }
}
