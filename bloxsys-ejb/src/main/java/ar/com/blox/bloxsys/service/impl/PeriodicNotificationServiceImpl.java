package ar.com.blox.bloxsys.service.impl;

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.eao.ChoferesFacade;
import ar.com.blox.bloxsys.search.ChoferesSearchFilter;
import ar.com.blox.bloxsys.service.PeriodicNotificationService;
import ar.com.blox.bloxsys.utils.VelocityHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.tools.generic.DateTool;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class PeriodicNotificationServiceImpl implements PeriodicNotificationService {

    @EJB
    private MailService mailService;
    @EJB
    private ChoferesFacade choferesFacade;

    @Override
    @Schedule(minute = "0",
            hour = "1",
            dayOfWeek = "*",
            dayOfMonth = "*",
            persistent = false)
    public void notificarLicenciasPorExpirar() {
        ChoferesSearchFilter csf = new ChoferesSearchFilter();
        csf.setActivo(true);
        //Primero genero alerta de próximos a vencer
        csf.setDiasLicenciaProximaVencer(7);//Una semana
        csf.addSortField("supervisor", true);

        List<Chofer> choferesProximos = choferesFacade.findAllBySearchFilter(csf);
        if (CollectionUtils.isEmpty(choferesProximos)) {
            return;
        }
        //Agrupo los choferes por supervisor
        Map<Usuario, List<Chofer>> choferesAgrupados =
                choferesProximos.stream().collect(Collectors.groupingBy(w -> w.getSupervisor()));

        //Se envía el mail a cada supervisor, informado de los choferes con licencia próxima a vencer.
        choferesAgrupados.forEach(this::enviarMailAvisoProximoExpirar);
    }

    /**
     * Envia el mail al supervisor de cada chofer con licencia proxima a vencer.
     *
     * @param supervisor
     * @param choferes
     */
    private void enviarMailAvisoProximoExpirar(Usuario supervisor, List<Chofer> choferes) {

        Map<String, Object> params = new HashMap<>();

        params.put("supervisor", supervisor);
        params.put("choferes", choferes);
        params.put("dateTool", new DateTool());

        String text = VelocityHelper.merge(
                "mail/template/template-choferes-aviso-proximo-venc.vsl", params);

        mailService.sendMail(supervisor.getEmail(), "[BloxSys] Licencias próximas a vencer",
                text);

    }
}
