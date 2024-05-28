package com.example.coachingproject;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {

    private Address address;

    @Before
    public void setUp() {
        address = new Address();
    }

    @Test
    public void testDefaultConstructor() {
        address = new Address();
        assertEquals(null, address.getThana());
        assertEquals(null, address.getRoadNo());
        assertEquals(null, address.getUpzilla());
        assertEquals(null, address.getHouseNo());
        assertEquals(null, address.getMobile());
    }

    @Test
    public void testParameterizedConstructor() {
        address = new Address("Dhanmondi", "32", "Dhaka", "10", "0123456789");
        assertEquals("Dhanmondi", address.getThana());
        assertEquals("32", address.getRoadNo());
        assertEquals("Dhaka", address.getUpzilla());
        assertEquals("10", address.getHouseNo());
        assertEquals("0123456789", address.getMobile());
    }

    @Test
    public void testSetThana() {
        address.setThana("Gulshan");
        assertEquals("Gulshan", address.getThana());
    }

    @Test
    public void testSetRoadNo() {
        address.setRoadNo("50");
        assertEquals("50", address.getRoadNo());
    }

    @Test
    public void testSetUpzilla() {
        address.setUpzilla("Sylhet");
        assertEquals("Sylhet", address.getUpzilla());
    }

    @Test
    public void testSetHouseNo() {
        address.setHouseNo("5A");
        assertEquals("5A", address.getHouseNo());
    }

    @Test
    public void testSetMobile() {
        address.setMobile("0987654321");
        assertEquals("0987654321", address.getMobile());
    }
}
