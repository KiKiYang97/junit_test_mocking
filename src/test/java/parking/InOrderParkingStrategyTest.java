package parking;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    static InOrderParkingStrategy inOrderParkingStrategy;

//    @BeforeAll
//    static void init() {
//        inOrderParkingStrategy = Mockito.mock(InOrderParkingStrategy.class);
//    }

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() throws Exception {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        inOrderParkingStrategy = new InOrderParkingStrategy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        given(parkingLot.getName()).willReturn("kiki");
        given(car.getName()).willReturn("eason");
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
        assertEquals("kiki", receipt.getParkingLotName());
        assertEquals("eason", receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        inOrderParkingStrategy = new InOrderParkingStrategy();
        Car car = mock(Car.class);
        given(car.getName()).willReturn("eason");
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
        assertEquals("No Parking Lot", receipt.getParkingLotName());
        assertEquals("eason", receipt.getCarName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = Collections.emptyList();
        Car car = new Car("eason");
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy,times(0)).createReceipt(any(ParkingLot.class),any(Car.class));
        verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(any(Car.class));
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("kiki",1);
        parkingLots.add(parkingLot);
        Car car = new Car("eason");
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy,times(1)).createReceipt(any(ParkingLot.class),any(Car.class));
        verify(inOrderParkingStrategy,times(0)).createNoSpaceReceipt(any(Car.class));

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("kiki",0);
        parkingLots.add(parkingLot);
        Car car = new Car("eason");
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy,times(0)).createReceipt(any(ParkingLot.class),any(Car.class));
        verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(any(Car.class));

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("kiki1",0);
        ParkingLot parkingLot2 = new ParkingLot("kiki2",1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car("eason");
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot2,car);
        verify(inOrderParkingStrategy,times(0)).createNoSpaceReceipt(car);

    }


}
