package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {CarDaoImpl.class})
public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() throws Exception {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        CarDaoImpl carDao = spy(new CarDaoImpl());
        whenNew(CarDaoImpl.class).withNoArguments()
                .thenReturn(carDao);
        VipParkingStrategy strategy = spy(new VipParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("kiki1",0);
        parkingLots.add(parkingLot1);
        Car car = new Car("A");
        doReturn(true).when(strategy).isAllowOverPark(car);
        strategy.park(parkingLots, car);
        verify(strategy,times(1)).createReceipt(parkingLot1,car);
        verify(strategy,times(0)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() throws Exception {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        CarDaoImpl carDao = spy(new CarDaoImpl());
        whenNew(CarDaoImpl.class).withNoArguments()
                .thenReturn(carDao);
        VipParkingStrategy strategy = spy(new VipParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("kiki1",0);
        parkingLots.add(parkingLot1);
        Car car = new Car("B");
        doReturn(false).when(strategy).isAllowOverPark(car);
        strategy.park(parkingLots, car);
        verify(strategy,times(0)).createReceipt(parkingLot1,car);
        verify(strategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() throws Exception {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        VipParkingStrategy strategy = spy(new VipParkingStrategy(carDao));
        Car car = new Car("A");
        given(carDao.isVip(car.getName())).willReturn(true);
        assertTrue(strategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        VipParkingStrategy strategy = spy(new VipParkingStrategy(carDao));
        Car car = new Car("B");
        given(carDao.isVip(car.getName())).willReturn(true);
        assertFalse(strategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        VipParkingStrategy strategy = spy(new VipParkingStrategy(carDao));
        Car car = new Car("A");
        given(carDao.isVip(car.getName())).willReturn(false);
        assertFalse(strategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        VipParkingStrategy strategy = spy(new VipParkingStrategy(carDao));
        Car car = new Car("B");
        given(carDao.isVip(car.getName())).willReturn(false);
        assertFalse(strategy.isAllowOverPark(car));
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
