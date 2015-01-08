package fr.viewpoint.client;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeTest {

    @Before
    public void before() {
        StorageFactory.setType(StorageFactory.StorageType.NULL);
        StorageFactory.init(new Configuration());
    }

    @Test
    public void testCalculDelay() throws Exception {

        int total = 30;
        // nbre d'erreur
        int errorCount = 0;
        for (int i = 0; i < total; i++) {
            long start = System.nanoTime();

            long approxExpectedDelay = (long) (Math.random() * i * 10);
            Thread.sleep(approxExpectedDelay);

            long end = System.nanoTime();

            Double endFloat = new Double(end);
            Double startFloat = new Double(start);
            Double t = endFloat - startFloat;

            long simpleDelayInMs = (end - start) / 1000000;
            double webcareDelayInMs = t / 1000000;
            float directFloatDelay = new Float(end - start) / 1000000;

            // ecart de plus de 10 ms
            long ecartMesure = ((long) webcareDelayInMs) - simpleDelayInMs;
            if (ecartMesure > 10) {
                errorCount++;
                System.out.println("Excessive delay > start : " + start
                        + " end : " + end + ", approxExpectedDelay : "
                        + approxExpectedDelay + ", simpleDelayInMs : "
                        + simpleDelayInMs + ", directFloatDelay : "
                        + directFloatDelay + ", webcareDelayInMs : "
                        + webcareDelayInMs + " >>> " + ecartMesure);
            }

        }

        System.out.println("nbre erreur "
                + errorCount
                + ", ratio : "
                + BigDecimal.valueOf(errorCount)
                        .divide(BigDecimal.valueOf(total))
                        .multiply(BigDecimal.valueOf(100)));
    }

    @Test
    public void testConstructors() throws InterruptedException {

        Time id = new Time("id1", IndicatorType.TIME);
        id.start();
        Thread.sleep(1000, 0);
        id.stop();

        Assert.assertEquals("id1", id.getName());
        Assert.assertEquals(IndicatorType.TIME, id.getType());
        Assert.assertEquals(true, id.isOk().booleanValue());
        Assert.assertEquals(1.00f, id.getValue(), 5);

        id = new Time("id1", IndicatorType.TIME);
        id.start();
        Thread.sleep(1000, 0);
        id.setDetail("TEST");
        id.stop();
        id.setOk(false);

        Assert.assertEquals("id1", id.getName());
        Assert.assertEquals(IndicatorType.TIME, id.getType());
        Assert.assertEquals(false, id.isOk().booleanValue());
        Assert.assertEquals(1.00f, id.getValue(), 5);

        id = new Time("id1", IndicatorType.TIME);
        id.start();
        Thread.sleep(1000, 0);
        id.stop();
        id.setMsg("this is the message");
        id.stop();

        Assert.assertEquals("id1", id.getName());
        Assert.assertEquals(IndicatorType.TIME, id.getType());
        Assert.assertEquals(true, id.isOk().booleanValue());
        Assert.assertEquals("this is the message", id.getMsg());
        Assert.assertEquals(1.00f, id.getValue(), 5);

        id = new Time("id1", IndicatorType.TIME);
        id.start();
        Thread.sleep(1000, 0);
        id.stop();
        id.setMsg("this is the message");
        id.setDetail("TEST");
        id.setOk(false);

        Assert.assertEquals("id1", id.getName());
        Assert.assertEquals(IndicatorType.TIME, id.getType());
        Assert.assertEquals(false, id.isOk().booleanValue());
        Assert.assertEquals("this is the message", id.getMsg());
        Assert.assertEquals("TEST", id.getDetail());
        Assert.assertEquals(1.00f, id.getValue(), 5);
    }

}
