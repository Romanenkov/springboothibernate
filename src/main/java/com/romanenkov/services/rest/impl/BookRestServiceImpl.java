package com.romanenkov.services.rest.impl;

import com.romanenkov.entities.Book;
import com.romanenkov.services.hibernate.BookRepository;
import com.romanenkov.services.rest.BookRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRestServiceImpl implements BookRestService {
  private BookRepository repository;

  @Autowired
  public void setRepository(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Book updateBook(long id, Book book) {
    Book currentBook = repository.findOne(id);
    currentBook.setName(book.getName());
    currentBook.setDescription(book.getDescription());
    currentBook.setTags(book.getTags());
    return repository.save(currentBook);
  }
}
