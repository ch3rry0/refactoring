package fr.efrei.transfret;

import java.util.List;

public interface TarifTransport {

    double calculer(List<Colis> colis, int distance);
}
