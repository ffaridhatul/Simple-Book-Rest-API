package com.zikri.lib.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zikri.lib.payload.request.BookRequest;
import com.zikri.lib.payload.response.BookResponse;
import com.zikri.lib.services.book.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        try {
            BookResponse response = bookService.addBook(bookRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<?> getBooks() {
        try {
            BookResponse response = bookService.getBooks();
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getBookById(@PathVariable("uuid") String id) {
        try {
            BookResponse response = bookService.getBookById(id);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateBook(@PathVariable("uuid") String id, @RequestBody BookRequest bookRequest) {
        try {
            BookResponse response = bookService.updateBookById(id, bookRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBook(@PathVariable("uuid") String id) {
        try {
            BookResponse response = bookService.deleteBookById(id);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
