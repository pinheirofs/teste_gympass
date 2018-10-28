package br.com.gympass;

import java.util.List;

public class RacingReport {

    private static final Object TITLES_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n";
    private List<Pilot> pilots;
    private StringBuilder builder;
    private String report;

    public RacingReport() {
    }

    public void setPilots(final List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public void createReport() {
        builder = new StringBuilder();
        builder.append(TITLES_REPORT);

        for (final Pilot pilot : pilots) {
            format(pilot);
        }

        report = builder.toString();
    }

    private void format(final Pilot pilot) {
        // TODO Auto-generated method stub

    }

    public String getReport() {
        return report;
    }

}
