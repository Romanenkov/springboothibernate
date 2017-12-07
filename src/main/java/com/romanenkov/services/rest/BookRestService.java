package com.romanenkov.services.rest;

import com.romanenkov.entities.Book;

public interface BookRestService {
  public Book updateBook(long id, Book book);
}
