package clinicaveterinaria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaLog implements ObservadorAtendimento {

    private List<String> historicoLogs = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public List<String> getHistoricoLogs() {
        return historicoLogs;
    }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        String dataHora = LocalDateTime.now().format(formatter);
        String log = String.format("[LOG %s] Atendimento %s alterou status gerando evento: %s",
                dataHora, atendimento.getCodigo(), evento.name());
        historicoLogs.add(log);
    }

    public void imprimirLogs() {
        System.out.println("--- LOGS DE AUDITORIA ---");
        for (String log : historicoLogs) {
            System.out.println(log);
        }
    }
}