package algs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Week1AssessmentAnalisysOfAlgorithmsTest {
    @Test
    public void testQuestion1Seed194035() {
        double[][] in = new double[][]{
                // new double[]{2048, 0.000},
                //new double[]{4096, 0.001},
                //new double[]{8192, 0.002},
                //new double[]{16384, 0.005},
                new double[]{32768, 0.015},
                new double[]{65536, 0.045},
                new double[]{131072, 0.130},
                new double[]{262144, 0.379},
                new double[]{524288, 1.104},
                new double[]{1048576, 3.229},
                new double[]{2097152, 9.415},
                new double[]{4194304, 27.499},
                new double[]{8388608, 80.242},
                new double[]{16777216, 234.215},
                new double[]{33554432, 683.480},
                new double[]{67108864, 1995.050},
        };
        Week1AssessmentAnalisysOfAlgorithms aa = new Week1AssessmentAnalisysOfAlgorithms(in);
        System.out.println(aa);
        assertEquals(1.54, aa.getB());
    }
}
