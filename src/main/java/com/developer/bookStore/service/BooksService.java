package com.developer.bookStore.service;

import com.developer.bookStore.model.Books;
import com.developer.bookStore.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    public BooksRepo repo;

    public List<Books> getAllBooks() {
        return repo.findAll();
    }

    public Optional<Books> findById(int id) {
        return repo.findById(id);
    }

    public Books addBook(Books book) {
       return repo.save(book);
    }
}
