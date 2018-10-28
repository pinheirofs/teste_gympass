package br.com.gympass;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DataFileReaderTest {
    private static final String FILEPATH_ONE_PILOT_ONE_LAP_OK = "./test/pilot_one_lap.txt";
    private static final int PILOT_CODE_F_MASSA = 38;
    private static final String PILOT_NAME_F_MASSA = "F.MASSA";
    private static final int LAP_NUMBER = 1;
    private static final float LAP_AVARAGE = 44.275F;
    private static final long LAP_TIME = 62852;
    private static final long LAP_START_TIME = 85748277;

    @Test
    public void readDataFileOK() {
        final DataFileReader reader = new DataFileReader();
        reader.setFilePath(FILEPATH_ONE_PILOT_ONE_LAP_OK);
        reader.read();

        final Map<String, Pilot> pilots = reader.getPilots();
        Assert.assertEquals(1, pilots.size());

        final Pilot pilot = pilots.get(PILOT_NAME_F_MASSA);
        Assert.assertNotNull(pilot);

        Assert.assertEquals(PILOT_NAME_F_MASSA, pilot.getName());
        Assert.assertEquals(PILOT_CODE_F_MASSA, pilot.getCode());

        final Map<Integer, Lap> laps = pilot.getLaps();
        Assert.assertNotNull(laps);
        Assert.assertEquals(1, laps.size());

        final Lap lap = laps.get(LAP_NUMBER);
        Assert.assertNotNull(lap);
        Assert.assertEquals(LAP_NUMBER, lap.getNumber());
        Assert.assertEquals(LAP_AVARAGE, lap.getAvarage(), 0.0001F);
        Assert.assertEquals(LAP_TIME, lap.getTime());
        Assert.assertEquals(LAP_START_TIME, lap.getStartTime());

    }

}
