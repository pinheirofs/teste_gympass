package br.com.gympass;

import org.junit.Assert;
import org.junit.Test;

public class LineSplitterTest {

    private static final String LINE_OK = "23:49:08.277      038 – F.MASSA                           1        1:02.852                        44,275";

    @Test
    public void testSplitLineOk() {
        final LineSplitter splitter = new LineSplitter();
        splitter.setLine(LINE_OK);
        splitter.split();

        Assert.assertTrue(splitter.isValid());

        Assert.assertEquals(23, splitter.getHours());
        Assert.assertEquals(49, splitter.getMinutes());
        Assert.assertEquals(8, splitter.getSeconds());
        Assert.assertEquals(277, splitter.getMiliseconds());
        Assert.assertEquals(38, splitter.getPilotCode());
        Assert.assertEquals("F.MASSA", splitter.getPilotName());
        Assert.assertEquals(1, splitter.getLapNumber());
        Assert.assertEquals(1, splitter.getLapMinute());
        Assert.assertEquals(2, splitter.getLapSeconds());
        Assert.assertEquals(852, splitter.getLapMilisecond());
        Assert.assertEquals(44.275, splitter.getAverageSpeed(), 0.0001);
    }

}
