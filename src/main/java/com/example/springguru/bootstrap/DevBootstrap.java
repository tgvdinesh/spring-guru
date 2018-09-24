package com.example.springguru.bootstrap;

import com.example.springguru.model.Author;
import com.example.springguru.model.Book;
import com.example.springguru.model.Publisher;
import com.example.springguru.repository.AuthorRepository;
import com.example.springguru.repository.BookRepository;
import com.example.springguru.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher aw = new Publisher("Addison-Wesley", "USA");
        Book ddd = new Book("Domain-Driven Design: Tackling Complexity in the Heart of Software", "0321125215", aw);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisherRepository.save(aw);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author dan = new Author("Dan", "Brown");
        Publisher pB = new Publisher("Pocket Books", "UK");
        Book deception = new Book("Deception Point", "0671027387 ", pB);
        dan.getBooks().add(deception);
        deception.getAuthors().add(dan);
        publisherRepository.save(pB);
        authorRepository.save(dan);
        bookRepository.save(deception);
    }
}
