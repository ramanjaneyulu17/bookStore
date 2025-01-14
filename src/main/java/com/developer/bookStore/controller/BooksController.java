package com.developer.bookStore.controller;

import com.developer.bookStore.model.Books;
import com.developer.bookStore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksService service;

    @GetMapping("/")
    public String welcome(){
        return "Hello, Welcome to XYZ Book store. ";
    }

    @GetMapping("/books")
    public List<Books> getAllBooks(){
       return service.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Optional<Books> findById(@PathVariable int id){
        Optional<Books> book =service.findById(id);
        if(book.isPresent()){
            return book;
        }
        return Optional.of(new Books());
    }

    @PostMapping("/books")
    public ResponseEntity<Books> addBook(@RequestBody Books book){
        return new ResponseEntity<>(service.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<String> updateBook(@RequestBody Books book){
        Optional<Books> books = service.findById(book.getId());
        if(books.isPresent()){
            service.updateBook(book);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        Optional<Books> book = service.findById(id);
        if (book.isPresent()) {
            service.deleteBook(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
}
