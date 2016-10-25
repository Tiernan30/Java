package Test;

import org.junit.*;
import org.junit.runner.JUnitCore;

import ca.ucalgary.seng301.vendingmachine.AbstractHardware;
import ca.ucalgary.seng301.vendingmachine.AbstractHardwareListener;

import static org.junit.Assert.*;

public class AbstractHardwareTest {
	protected static AbstractHardware<AbstractHardwareListener> hardware;
	
//	//public class AbstractHardwareListenerStub implements AbstractHardwareListener{
//
//		@Override
//		public void disabled(AbstractHardware<AbstractHardwareListener> arg0) {
//			System.out.println("Hardware is disabled.");
//			
//		}
//
//		@Override
//		public void enabled(AbstractHardware<AbstractHardwareListener> arg0) {
//			System.out.println("Hardware is enabled.");
//			
//		}
//		
//	}
	@Before
	public void setUp(){
		 this.hardware = new AbstractHardware(){};
	}
	@After
	public void tearDown(){
		this.hardware = null;
	}
	@Test
	public void test1(){
		assertTrue(!hardware.isDisabled());
	}
	@Test
	public void test2(){
		hardware.disable();
		assertTrue(hardware.isDisabled());
		hardware.enable();
		assertFalse(hardware.isDisabled());
	}
	@Test
	public void test3(){
		hardware.enable();
		assertTrue(hardware.isDisabled());
	}
	
	@Test
	public void testEnabling() {
	    final MutableInteger enabledCount = new MutableInteger(0);
	    hardware.register(new AbstractHardwareListener() {
	        public void enabled(AbstractHardware<AbstractHardwareListener> arg0) {
	            enabledCount.increment();
	        }

	        public void disabled(AbstractHardware<AbstractHardwareListener> arg0) {
	            fail("This should not have been called");
	        }
	    });
	    hardware.enable();
	    assertFalse(hardware.isDisabled());
	    assertEquals(1, enabledCount.getValue());
	}

	@Test
	public void testDisabling() {
	    final MutableInteger disabledCount = new MutableInteger(0);
	    hardware.register(new AbstractHardwareListener() {      
	        public void enabled(AbstractHardware<AbstractHardwareListener> arg0) {
	            fail("This should not have been called");
	        }

	        public void disabled(AbstractHardware<AbstractHardwareListener> arg0) {
	            disabledCount.increment();
	        }

			
	    });
	    hardware.disable();
	    assertTrue(hardware.isDisabled());
	    assertEquals(1, disabledCount.getValue());
	}

	class MutableInteger {
	    private int value;
	    public MutableInteger(int val) {
	        value = val;
	    }

	    public void increment() {
	        value++;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	

}