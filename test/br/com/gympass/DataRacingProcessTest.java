package br.com.gympass;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class DataRacingProcessTest {

    private List<Pilot> pilots;

    @Before
    public void createPilots() {
        pilots = new ArrayList<>();

        // Piloto 1
        Pilot pilot = new Pilot();
        pilots.add(pilot);
        pilot.setName("1");

        Lap lap = new Lap();
        lap.setNumber(1);
        lap.setTime(62852);
        pilot.put(lap);

        lap = new Lap();
        lap.setNumber(2);
        lap.setTime(63170);
        pilot.put(lap);

        // Piloto 2
        pilot = new Pilot();
        pilots.add(pilot);
        pilot.setName("1");

        lap = new Lap();
        lap.setNumber(1);
        lap.setTime(64352);
        pilot.put(lap);

        lap = new Lap();
        lap.setNumber(2);
        lap.setTime(64002);
        pilot.put(lap);
    }

    @Test
    public void testCalculateFinishingPosition() {
        final DataRacingProcess dataRacingProcess = new DataRacingProcess(2);
        dataRacingProcess.setPilots(pilots);
        dataRacingProcess.process();

        Pilot pilot = pilots.get(0);
        Assert.assertEquals(new Integer(1), pilot.getFinishingPosition());

        pilot = pilots.get(1);
        Assert.assertEquals(new Integer(2), pilot.getFinishingPosition());

    }

}
