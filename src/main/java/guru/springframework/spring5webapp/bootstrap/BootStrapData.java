package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Dark Dolin", "123456");

        Publisher publisher = new Publisher("Dave", "Brest, BEL, 1234");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisherRepository.save(publisher);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author evgen = new Author("Gill", "Evgen");
        Book bookEvgen = new Book("Gore ot uma", "12345678");

        evgen.getBooks().add(bookEvgen);
        bookEvgen.getAuthors().add(evgen);
//
//        bookEvgen.setPublisher(publisher);
//        eric.getBooks().add(bookEvgen);

        authorRepository.save(evgen);
        bookRepository.save(bookEvgen);
//        publisherRepository.save(publisher);

        System.out.println("Publisher count " + publisherRepository.count());
       System.out.println("Book count " + bookRepository.count());
        System.out.println("Publisher number of books " + publisher.getBooks().size());
    }
}
