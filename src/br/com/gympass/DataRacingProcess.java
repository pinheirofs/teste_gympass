package br.com.gympass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DataRacingProcess {

    private final int maxLaps;
    private List<Pilot> pilots;

    public DataRacingProcess(final int maxLaps) {
        this.maxLaps = maxLaps;
    }

    public void setPilots(final List<Pilot> pilots) {
        this.pilots = new ArrayList<>(pilots);
    }

    public void process() {
        for (final Pilot pilot : pilots) {
            calculatePilotData(pilot);
        }

        calculateRacinData();
    }

    private void calculateRacinData() {
        pilots.sort(new Comparator<Pilot>() {
            @Override
            public int compare(final Pilot o1, final Pilot o2) {
                final Integer lastLap1 = o1.getLastLap();
                final Integer lastLap2 = o2.getLastLap();
                final int c = lastLap1.compareTo(lastLap2);
                if (c != 0) {
                    return c;
                }

                final Long totalRacingTime1 = o1.getTotalRacingTime();
                final Long totalRacingTime2 = o2.getTotalRacingTime();

                return totalRacingTime1.compareTo(totalRacingTime2);
            }
        });

        for (int i = 0; i < pilots.size(); i++) {
            final Pilot pilot = pilots.get(i);
            pilot.setFinishingPosition(i + 1);
        }
    }

    private void calculatePilotData(final Pilot pilot) {
        pilot.setTotalRacingTime(0);

        final Map<Integer, Lap> laps = pilot.getLaps();
        int lapIndex;
        for (lapIndex = 1; lapIndex <= maxLaps && lapIndex <= laps.size(); ++lapIndex) {
            // Calculo do tempo de prova
            final Lap lap = laps.get(lapIndex);
            final long lapTime = lap.getTime();
            long totalRacingTime = pilot.getTotalRacingTime();
            totalRacingTime += lapTime;
            pilot.setTotalRacingTime(totalRacingTime);
        }

        pilot.setLastLap(lapIndex);
    }

}
