package com.example.coachingproject;

import org.junit.Test;
import junit.framework.TestCase;

public class UserTest extends TestCase {


    @Test
    public void testSingletonInstance() {
        User user1 = User.getInstance();
        User user2 = User.getInstance();

        assertNotNull(user1);
        assertSame(user1, user2);
    }

    @Test
    public void testUserProperties() {
        User user = new User("Rahim", "Khulna", "12345679", "abc@gmail.com", "123");

        assertEquals("Rahim", user.name);
        assertEquals("Khulna", user.address);
        assertEquals("12345679", user.phone);
        assertEquals("abc@gmail.com", user.email);
        assertEquals("123", user.roll);
    }



    @Test
    public void testShowUser() {
        // Create a new User with the provided details
        User user = new User("Rahim", "Khulna", "12345679", "abc@gmail.com", "123");
        assertEquals("Name: Rahim\nAddress: Khulna\nPhone: 12345679\nEmail: abc@gmail.com\nRoll: 123",user.showUser());
    }

}

