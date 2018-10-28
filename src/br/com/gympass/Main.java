package br.com.gympass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger LOGGER = new Logger("Main");

    public static void main(final String[] args) {
        if (args.length != 1) {
            LOGGER.erro("Favor informar o arquivo de dados");
            return;
        }

        final DataFileReader reader = new DataFileReader();
        reader.setFilePath(args[0]);
        reader.read();

        final Map<String, Pilot> pilotsMap = reader.getPilots();
        final List<Pilot> pilots = new ArrayList<>(pilotsMap.values());
        final DataRacingProcess process = new DataRacingProcess(4);
        process.setPilots(pilots);
        process.process();

        final RacingReport racingReport = new RacingReport();
        racingReport.setPilots(pilots);
        racingReport.createReport();

        System.out.println(racingReport.getReport());
    }

}
