package com.example.ibrahim.library_sys.controller;

import com.example.ibrahim.library_sys.entity.BorrowingRecord;
import com.example.ibrahim.library_sys.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BorrowingRecordControllerTest {

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBook() {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);

        when(borrowingRecordService.borrowBook(1L, 1L)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testBorrowBookNotFound() {
        when(borrowingRecordService.borrowBook(1L, 1L)).thenReturn(null);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testReturnBook() {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);

        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testReturnBookNotFound() {
        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(null);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}