package com.example.ibrahim.library_sys.service;


import com.example.ibrahim.library_sys.repository.BookRepository;
import com.example.ibrahim.library_sys.repository.BorrowingRecordRepository;
import com.example.ibrahim.library_sys.repository.PatronRepository;
import com.example.ibrahim.library_sys.entity.Book;
import com.example.ibrahim.library_sys.entity.BorrowingRecord;
import com.example.ibrahim.library_sys.entity.Patron;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            // Check if the book is already borrowed
            if (book.getBorrowingRecords() != null) {
                // Book is already borrowed, handle accordingly (e.g., throw exception)
                // You can implement your custom logic here
                throw new RuntimeException("Book with ID " + bookId + " is already borrowed.");
            }

            // Create a new borrowing record
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowDate(LocalDate.now());

            // Set the borrowing record for the book
            book.setBorrowingRecord((List<BorrowingRecord>) borrowingRecord);

            // Save the changes
            bookRepository.save(book);
            return borrowingRecordRepository.save(borrowingRecord);
        }
        return null;
    }

    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            // Check if the book is borrowed by the specified patron
            BorrowingRecord borrowingRecord = (BorrowingRecord) book.getBorrowingRecords();
            if (borrowingRecord != null && borrowingRecord.getPatron().equals(patron)) {
                // Update the return date
                borrowingRecord.setReturnDate(LocalDate.now());

                // Clear the borrowing record for the book
                book.setBorrowingRecord(null);

                // Save the changes
                bookRepository.save(book);
                return borrowingRecordRepository.save(borrowingRecord);
            }
        }
        return null;
    }
}
