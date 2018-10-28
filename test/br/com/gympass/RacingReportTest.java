package br.com.gympass;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RacingReportTest {

    private static final String EMPTY_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n";
    private static final String ONE_PILOT_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n"
            + "1                 38              F.MASSA       4                         01:03.170  \n";
    private static final String TWO_PILOT_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n"
            + "1                 38              F.MASSA       4                         01:03.170  \n"
            + "2                 33              R.BARRICHELLO 4                         01:04.352  \n";
    private Pilot pilot1;
    private Pilot pilot2;

    @Before
    public void createPilots() {
        pilot1 = new Pilot();
        pilot1.setName("F.MASSA");
        pilot1.setFinishingPosition(1);
        pilot1.setCode(38);
        pilot1.setLastLap(4);
        pilot1.setTotalRacingTime(63170);

        Lap lap = new Lap();
        lap.setNumber(1);
        lap.setTime(62852);
        pilot1.put(lap);

        lap = new Lap();
        lap.setNumber(2);
        lap.setTime(63170);
        pilot1.put(lap);

        pilot2 = new Pilot();
        pilot2.setName("R.BARRICHELLO");
        pilot2.setFinishingPosition(2);
        pilot2.setCode(33);
        pilot2.setLastLap(4);
        pilot2.setTotalRacingTime(64352);

        lap = new Lap();
        lap.setNumber(1);
        lap.setTime(64352);
        pilot2.put(lap);

        lap = new Lap();
        lap.setNumber(2);
        lap.setTime(64002);
        pilot2.put(lap);
    }

    @Test
    public void testcreateReportTwoPilot() {
        final List<Pilot> pilots = new ArrayList<>();
        pilots.add(pilot1);
        pilots.add(pilot2);

        final RacingReport racingReport = new RacingReport();
        racingReport.setPilots(pilots);
        racingReport.createReport();
        final String report = racingReport.getReport();

        Assert.assertEquals(TWO_PILOT_REPORT, report);
    }

    @Test
    public void testcreateReportOnePilot() {
        final List<Pilot> pilots = new ArrayList<>();
        pilots.add(pilot1);

        final RacingReport racingReport = new RacingReport();
        racingReport.setPilots(pilots);
        racingReport.createReport();
        final String report = racingReport.getReport();

        Assert.assertEquals(ONE_PILOT_REPORT, report);
    }

    @Test
    public void testcreateReportEmpty() {
        final RacingReport racingReport = new RacingReport();
        racingReport.setPilots(new ArrayList<>());
        racingReport.createReport();
        final String report = racingReport.getReport();

        Assert.assertEquals(EMPTY_REPORT, report);
    }

}
