package br.com.gympass;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class RacingReportTest {

    private static final String EMPTY_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n";

    @Test
    public void testcreateReportEmpty() {
        final RacingReport racingReport = new RacingReport();
        racingReport.setPilots(new ArrayList<>());
        racingReport.createReport();
        final String report = racingReport.getReport();

        Assert.assertEquals(EMPTY_REPORT, report);
    }

}
