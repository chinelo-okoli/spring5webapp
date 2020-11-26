package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher pearsons = new Publisher();
        pearsons.setName("Pearsons");
        pearsons.setAddressLine1("123 London Road");
        pearsons.setCity("London");
        pearsons.setZip("EC2N 1JY");
        pearsons.getBooks().add(ddd);
        pearsons.getBooks().add(noEJB);
        ddd.setPublisher(pearsons);
        noEJB.setPublisher(pearsons);
        publisherRepository.save(pearsons);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        System.out.printf("Number of Books: %d\n", bookRepository.count());
        System.out.printf("Number of Authors: %d\n", authorRepository.count());
        System.out.printf("Number of Publishers: %d\n", publisherRepository.count());
        System.out.printf("Publisher number of books: %d\n", pearsons.getBooks().size());
    }
}
