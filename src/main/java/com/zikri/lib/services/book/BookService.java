package com.zikri.lib.services.book;

import com.zikri.lib.payload.request.BookRequest;
import com.zikri.lib.payload.response.BookResponse;

public interface BookService {

    BookResponse addBook(BookRequest request);

    BookResponse getBooks();

    BookResponse getBookById(String uuid);

    BookResponse updateBookById(String uuid, BookRequest request);

    BookResponse deleteBookById(String uuid);


}
