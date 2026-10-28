package fr.efrei.transfret;

import java.util.List;

public interface CanalNotification {

    void envoyer(String destinataire, String sujet, String corps);

    List<NotificationEnvoyee> notifications();
}
