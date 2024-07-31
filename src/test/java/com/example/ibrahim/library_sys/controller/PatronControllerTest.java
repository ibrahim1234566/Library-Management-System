package com.example.ibrahim.library_sys.controller;

import com.example.ibrahim.library_sys.entity.Patron;
import com.example.ibrahim.library_sys.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PatronControllerTest {

    @InjectMocks
    private PatronController patronController;

    @Mock
    private PatronService patronService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        Patron patron1 = new Patron();
        patron1.setId(1L);
        patron1.setName("Patron 1");
        Patron patron2 = new Patron();
        patron2.setId(2L);
        patron2.setName("Patron 2");
        List<Patron> patrons = Arrays.asList(patron1, patron2);

        when(patronService.getAllPatrons()).thenReturn(patrons);

        ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetPatronById() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("Patron 1");

        when(patronService.getPatronById(1L)).thenReturn(Optional.of(patron));

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patron 1", response.getBody().getName());
    }

    @Test
    public void testGetPatronByIdNotFound() {
        when(patronService.getPatronById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddPatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("New Patron");

        when(patronService.addPatron(any(Patron.class))).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.addPatron(patron);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Patron", response.getBody().getName());
    }

    @Test
    public void testUpdatePatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("Updated Patron");

        when(patronService.updatePatron(1L, patron)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Patron", response.getBody().getName());
    }

    @Test
    public void testUpdatePatronNotFound() {
        Patron patron = new Patron();
        when(patronService.updatePatron(1L, patron)).thenReturn(null);

        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletePatron() {
        ResponseEntity<Void> response = patronController.deletePatron(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
