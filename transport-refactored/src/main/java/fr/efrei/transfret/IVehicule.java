package fr.efrei.transfret;

public interface IVehicule extends Transportable {

    String getImmatriculation();

    double getCapaciteMax();

    double getChargeActuelle();

    boolean estDisponible();

    void setDisponible(boolean disponible);

    void charger(Colis colis);

    void decharger(Colis colis);

    void partir(int distance);
}
