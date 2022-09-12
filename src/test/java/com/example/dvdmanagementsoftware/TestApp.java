package com.example.dvdmanagementsoftware;

import com.example.dvdmanagementsoftware.database.Database;
import com.example.dvdmanagementsoftware.dvd.DVD;
import com.example.dvdmanagementsoftware.users.User;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApp {
    private final Database db = new Database();

    @Test
    @Order(1)
    public void testSuccessfulSignIn() throws NoSuchAlgorithmException {
        User user = this.db.signIn("admin", "admin");
        assertNotNull(user);
    }

    @Test
    @Order(2)
    public void testUnsuccessfulSignIn() throws NoSuchAlgorithmException {
        User user = this.db.signIn("", "");
        assertNull(user);
    }

    @Test
    @Order(3)
    public void testSignUp() {
        User u = new User("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        assertTrue(this.db.newUser(u));
    }

    @Test
    @Order(4)
    public void testSignUpWithSameUsername() {
        User u = new User("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        assertFalse(db.newUser(u));
    }

    @Test
    @Order(5)
    public void testUserDeletion() throws NoSuchAlgorithmException {
        User u = this.db.signIn("test", "test");
        assertTrue(db.deleteUser(u.getId()));
    }

    @Test
    @Order(6)
    public void testGetDVD() {
        DVD dvd = this.db.getDVD(1);
        assertNotNull(dvd);
    }

    @Test
    @Order(7)
    public void testCreateDVD() {

        DVD dvd = new DVD("test", "Actor 1, Actor 2", "Director", "01/01/2022", 120, "English", "Greek, English", "sciFy", 19.00, 45);
        assertTrue(this.db.newDVD(dvd));
    }

    @Test
    @Order(8)
    public void testDeleteDVD() {
        DVD dvd = this.db.getDVDByTitle("test");
        assertTrue(this.db.deleteDVD(dvd.getId()));
    }
}
