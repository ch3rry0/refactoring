package fr.efrei.transfret;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CalculateurPrix {

    private static final double COEFFICIENT_FRAGILE = 1.15;
    private static final double COEFFICIENT_DANGEREUX = 1.30;
    private static final double COEFFICIENT_REFRIGERE = 1.20;
    private static final double COEFFICIENT_URGENCE = 1.50;
    private static final double TAUX_ASSURANCE = 0.02;
    private static final double MINIMUM_ASSURANCE = 15;

    private final Map<ModeTransport, TarifTransport> tarifs;
    private final Map<TypeClient, PolitiqueRemise> remises;

    public CalculateurPrix() {
        tarifs = new EnumMap<>(ModeTransport.class);
        tarifs.put(ModeTransport.ROUTE, new TarifRoute());
        tarifs.put(ModeTransport.RAIL, new TarifRail());
        tarifs.put(ModeTransport.MER, new TarifMer());
        tarifs.put(ModeTransport.AIR, new TarifAir());

        remises = new EnumMap<>(TypeClient.class);
        remises.put(TypeClient.PARTICULIER, new AucuneRemise());
        remises.put(TypeClient.ENTREPRISE, new RemiseEntreprise());
        remises.put(TypeClient.PREMIUM, new RemisePremium());
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent,
                                 boolean assurance, TypeClient typeClient) {
        double prix = tarif(mode).calculer(colis, distance);
        if (contient(colis, TypeMarchandise.FRAGILE)) prix *= COEFFICIENT_FRAGILE;
        if (contient(colis, TypeMarchandise.DANGEREUX)) prix *= COEFFICIENT_DANGEREUX;
        if (contient(colis, TypeMarchandise.REFRIGERE)) prix *= COEFFICIENT_REFRIGERE;
        if (urgent) prix *= COEFFICIENT_URGENCE;
        prix = remise(typeClient).appliquer(prix);
        if (assurance) {
            double montantAssurance = Math.max(totalValeur(colis) * TAUX_ASSURANCE, MINIMUM_ASSURANCE);
            prix += montantAssurance;
        }
        return Utils.arrondir(prix);
    }

    private TarifTransport tarif(ModeTransport mode) {
        TarifTransport tarif = tarifs.get(mode);
        if (tarif == null) {
            throw new IllegalArgumentException("Mode inconnu : " + mode);
        }
        return tarif;
    }

    private PolitiqueRemise remise(TypeClient typeClient) {
        PolitiqueRemise remise = remises.get(typeClient);
        if (remise == null) {
            throw new IllegalArgumentException("Type de client inconnu : " + typeClient);
        }
        return remise;
    }

    private boolean contient(List<Colis> colis, TypeMarchandise type) {
        return colis.stream().anyMatch(c -> c.getType() == type);
    }

    private double totalValeur(List<Colis> colis) {
        return colis.stream().mapToDouble(Colis::getValeurDeclaree).sum();
    }
}
