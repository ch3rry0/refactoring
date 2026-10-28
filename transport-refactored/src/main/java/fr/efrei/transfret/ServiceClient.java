package fr.efrei.transfret;

public class ServiceClient {

    private final ClientRepository clients;
    private final CanalNotification email;

    public ServiceClient(ClientRepository clients, CanalNotification email) {
        this.clients = clients;
        this.email = email;
    }

    public Client creerClient(String nom, String emailAddress, String telephone, String adresse, TypeClient type, String siret) {
        if (nom == null || nom.trim().length() == 0) {
            System.out.println("ERREUR : nom obligatoire");
            return null;
        }
        if (emailAddress == null || !emailAddress.contains("@") || !emailAddress.contains(".")) {
            System.out.println("ERREUR : email invalide (" + emailAddress + ")");
            return null;
        }
        if (type == null) {
            System.out.println("ERREUR : type de client inconnu");
            return null;
        }
        if (type == TypeClient.ENTREPRISE && (siret == null || siret.length() != 14)) {
            System.out.println("ERREUR : SIRET obligatoire (14 chiffres) pour une entreprise");
            return null;
        }
        Client c = new Client(nom, emailAddress, telephone, adresse, type, siret);
        clients.sauvegarder(c);
        email.envoyer(c.getEmail(), "Bienvenue chez TransFret", "Bonjour " + c.getNom() + ", votre compte " + c.getType().name().toLowerCase() + " a été créé.");
        return c;
    }
}
