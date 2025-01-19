package com.zikri.lib.services.book;

import com.zikri.lib.models.Books;
import com.zikri.lib.payload.request.BookRequest;
import com.zikri.lib.payload.response.BookResponse;
import com.zikri.lib.respositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public BookResponse addBook(BookRequest request) {
        //Membuat Object buku
        Books books = new Books();

        // Transfer data from request to entity
        books.setTitle(request.getJudul());
        books.setAuthor(request.getPengarang());
        books.setPublisher(request.getPenerbit());
        books.setIsbn(request.getIsbn());
        books.setYear(request.getTahunTerbit());

        bookRepository.save(books);


        // Buat object Response
        BookResponse response = new BookResponse();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Book created successfully!");
        response.setData(books);


        // Return object response
        return response;
    }

    @Override
    public BookResponse getBooks() {
        List<Books> books = bookRepository.findAll();
        BookResponse response = new BookResponse(HttpStatus.OK.value(), "Success", books);
        return response;
    }

    @Override
    public BookResponse getBookById(String uuid) {
        // Buat object buku yang dicari berdasarkan id
        Books book = bookRepository.findById(uuid).orElseThrow(() ->
                new NoSuchElementException("Book not found"));
        return new BookResponse(HttpStatus.OK.value(), "success", book);
    }

    @Transactional
    @Override
    public BookResponse updateBookById(String uuid, BookRequest request) {
        // Buat object buku yang dicari berdasarkan id
        Books book = bookRepository.findById(uuid).orElseThrow(() ->
                new NoSuchElementException("Book not found"));
        //Perbarui data buku
        book.setTitle(request.getJudul());
        book.setAuthor(request.getPengarang());
        book.setPublisher(request.getPenerbit());
        book.setIsbn(request.getIsbn());
        book.setYear(request.getTahunTerbit());

        book = bookRepository.save(book);

        return new BookResponse(HttpStatus.OK.value(), "updated success", book);

    }

    @Transactional
    @Override
    public BookResponse deleteBookById(String uuid) {
        Books book = bookRepository.findById(uuid).orElseThrow(() ->
                new NoSuchElementException("Book not found"));
//        book.setDeleted(true);
//        bookRepository.save(book);
        bookRepository.delete(book);

        return new BookResponse(HttpStatus.OK.value(), "deleted success", book);
    }
}
