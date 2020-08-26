package parking;

import mocking.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})

public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class);
        VipParkingStrategy strategy = new VipParkingStrategy();
        given(ParkingLot.getBasicHourlyPrice()).willReturn(25);
        assertEquals(50, strategy.calculateHourlyPrice());
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class);
        VipParkingStrategy strategy = new VipParkingStrategy();
        given(ParkingLot.getBasicHourlyPrice()).willReturn(20);
        assertEquals(40, strategy.calculateHourlyPrice());

    }
}
