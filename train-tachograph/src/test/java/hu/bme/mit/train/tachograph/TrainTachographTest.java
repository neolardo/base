package hu.bme.mit.train.tachograph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class TrainTachographTest {

    private TrainTachograph tachograph;
    @Before
    public void before() {
        tachograph = new TrainTachograph();
    }

    @Test
    public void TachographHasSomeElements() {
        tachograph.addRow(LocalDateTime.now(), 5, 10);
        tachograph.addRow(LocalDateTime.now(), 60, 10);
        Assert.assertEquals(2, tachograph.getCount());
        tachograph.addRow(LocalDateTime.now(), -10, 30);
        Assert.assertEquals(3, tachograph.getCount());
    }

}