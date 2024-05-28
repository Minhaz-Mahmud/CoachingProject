package com.example.coachingproject;

import junit.framework.TestCase;
import org.junit.Test;
import junit.framework.TestCase;

public class loginTest extends TestCase {

    @Test
    public void testSingletonInstance() {
        // Call getInstance() multiple times and ensure it returns the same instance
        login login1 = login.getInstance();
        login login2 = login.getInstance();

        assertNotNull(login1);
        assertSame(login1, login2);
    }

    @Test
    public void testPrintDetails() {

        login login_a = login.getInstance();
        login_a.set("xyz@gmail.com","123456");



        assertEquals("Email: xyz@gmail.com Password: 123456", login_a.printDetails());
    }
}