package com.romanenkov.controllers;

import com.romanenkov.entities.Book;
import com.romanenkov.services.hibernate.BookRepository;
import com.romanenkov.services.rest.BookRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookRepository repository;
    private BookRestService bookRestService;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setBookRestService(BookRestService bookRestService) { this.bookRestService = bookRestService; }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(repository.save(book), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Book> getBookWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(
            params = {"name"},
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> findBookWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBookFromDB(@PathVariable("id") long id, @RequestBody Book book) {

        return new ResponseEntity<>(bookRestService.updateBook(id, book), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteBookWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public void deleteAllBooks() {
        repository.deleteAll();
    }
}
