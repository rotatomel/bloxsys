package ar.com.blox.bloxsys.service;

/**
 * Interfaz para los servicios de notificación que deben ejecutarse períodicamente.
 */
public interface PeriodicNotificationService {
    /**
     * Realiza las notificaciones correspondientes a los choferes que tienen su licencia de conducir próxima a expirar.
     */
    void notificarLicenciasPorExpirar();
}
