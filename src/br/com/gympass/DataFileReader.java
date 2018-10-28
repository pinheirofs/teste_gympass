package br.com.gympass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsavel por ler o arquivo de dados
 */
public class DataFileReader {

    private static Logger LOGGER = new Logger("DataFileReader");

    private String filepath;
    private Map<String, Pilot> pilots = null;

    public DataFileReader() {
    }

    public void setFilePath(final String filepath) {
        this.filepath = filepath;
    }

    /**
     * Metodo Responsavel por ler o arquivo de dados informado
     */
    public void read() {
        pilots = null;

        final File file = new File(filepath);
        if (!file.exists()) {
            LOGGER.warn("Arquivo inexistente");
            return;
        } else if (file.isDirectory()) {
            LOGGER.warn("Arquivo informado eh um diretorio");
            return;
        } else if (!file.canRead()) {
            LOGGER.warn("Arquivo informado n");
            return;
        }

        pilots = new HashMap<>();

        BufferedReader bufferReader = null;
        try {
            final FileInputStream inputStreamn = new FileInputStream(file);
            final Reader reader = new InputStreamReader(inputStreamn);
            bufferReader = new BufferedReader(reader);

            String line;
            do {
                line = bufferReader.readLine();
                processLine(line);
            } while (line != null);
        } catch (final IOException e) {
            LOGGER.warn("Erro ao processa o arquivo " + filepath + ":  " + e.getMessage());
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (final IOException e) {
                    LOGGER.warn("Erro ao fechar o arquivo " + filepath);
                }
            }
        }
    }

    private void processLine(final String line) {
        if (line == null || line.isEmpty()) {
            return;
        }

        final LineSplitter splitter = new LineSplitter();
        splitter.setLine(line);
        splitter.split();

        if (!splitter.isValid()) {
            return;
        }

        final String pilotName = splitter.getPilotName();
        Pilot pilot;
        if (!pilots.containsKey(pilotName)) {
            pilot = new Pilot();
            pilot.setName(pilotName);
            pilot.setCode(splitter.getPilotCode());
            pilots.put(pilotName, pilot);
        }

        pilot = pilots.get(pilotName);

        final Lap lap = new Lap();
        lap.setAvarage(splitter.getAverageSpeed());
        lap.setNumber(splitter.getLapNumber());

        final int lapMilisecond = splitter.getLapMilisecond();
        final int lapSeconds = splitter.getLapSeconds();
        final int lapMinute = splitter.getLapMinute();
        final long time_ms = lapMilisecond + 1000 * (lapSeconds + 60 * lapMinute);
        lap.setTime(time_ms);

        final int hours = splitter.getHours();
        final int minutes = splitter.getMinutes();
        final int seconds = splitter.getSeconds();
        final int miliseconds = splitter.getMiliseconds();
        final long startTime = miliseconds + 1000 * (seconds + 60 * minutes + 3600 * hours);
        lap.setStartTime(startTime);

        pilot.put(lap);
    }

    public Map<String, Pilot> getPilots() {
        return new HashMap<>(pilots);
    }

}
