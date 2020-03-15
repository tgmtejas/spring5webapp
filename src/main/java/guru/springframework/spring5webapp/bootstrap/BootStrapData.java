package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.SocketOption;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private  final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        // Add publisher resources
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publish");
        publisher.setCity("st Peters");
        publisher.setState("WA");

        // Save publisher resources
        publisherRepository.save(publisher);


        // Add author and book example one
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // Add publisher and book example one
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        // save into database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);


        // Add author and book example two
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "39393459");
        rod.getBooks().add(ddd);
        noEJB.getAuthors().add(eric);

        // Add publisher and book example two
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        // save into database
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());
    }
}
