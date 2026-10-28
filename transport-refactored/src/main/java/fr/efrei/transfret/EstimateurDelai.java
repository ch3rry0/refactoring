package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KMH = 70;
    private static final double HEURES_CONDUITE_NORMALES = 9;
    private static final double HEURES_CONDUITE_URGENTES = 18;
    private static final double DISTANCE_PAR_JOUR_RAIL = 1440;
    private static final int JOURS_RAIL = 1;
    private static final double DISTANCE_PAR_JOUR_MER = 720;
    private static final int JOURS_MER = 2;
    private static final int SEUIL_LONG_COURRIER_AERIEN = 3000;
    private static final int DELAI_AIR = 1;
    private static final int DELAI_AIR_LONG_COURRIER = 2;
    private static final double DISTANCE_PAR_JOUR_FLUVIAL = 360;
    private static final int JOURS_FLUVIAL = 1;

    public int estimer(int distance, ModeTransport mode, boolean urgent) {
        int jours = 0;
        switch (mode) {
            case ROUTE:
                double heures = distance / VITESSE_ROUTE_KMH;
                if (urgent) {
                    jours = (int) Math.ceil(heures / HEURES_CONDUITE_URGENTES);
                } else {
                    jours = (int) Math.ceil(heures / HEURES_CONDUITE_NORMALES);
                }
                break;
            case RAIL:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_RAIL) + JOURS_RAIL;
                if (urgent) jours = jours - 1;
                break;
            case MER:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_MER) + JOURS_MER;
                break;
            case AIR:
                jours = DELAI_AIR;
                if (distance > SEUIL_LONG_COURRIER_AERIEN) jours = DELAI_AIR_LONG_COURRIER;
                if (urgent) jours = DELAI_AIR;
                break;
            case FLUVIAL:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_FLUVIAL) + JOURS_FLUVIAL;
                break;
        }
        return Math.max(jours, 1);
    }
}
