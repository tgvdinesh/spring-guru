package com.example.springguru.bootstrap;

import com.example.springguru.model.Author;
import com.example.springguru.model.Book;
import com.example.springguru.repository.AuthorRepository;
import com.example.springguru.repository.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain-Driven Design: Tackling Complexity in the Heart of Software", "0321125215", "Addison-Wesley");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author dan = new Author("Dan", "Brown");
        Book deception = new Book("Deception Point", "0671027387 ", "Pocket Books");
        dan.getBooks().add(deception);
        deception.getAuthors().add(dan);
        authorRepository.save(dan);
        bookRepository.save(deception);
    }
}
