package Test;

import org.junit.*;
import org.junit.runner.JUnitCore;

import ca.ucalgary.seng301.vendingmachine.AbstractHardware;

import static org.junit.Assert.*;

public class AbstractHardwareTest {
	protected static AbstractHardware<?> hardware;

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
}
