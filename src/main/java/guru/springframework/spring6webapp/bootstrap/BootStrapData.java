package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {


  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Publisher ifaPublisher = new Publisher("ifa", "ul. Gorska Polqna 6", "Sofia", "Sofia", 1234);

    publisherRepository.save(ifaPublisher);

    Author eric = new Author("Eric", "Exotic");
    Book ddd = new Book("DDD", "132324");

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    ddd.setPublisher(ifaPublisher);
    ifaPublisher.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);
    publisherRepository.save(ifaPublisher);

    Author rod = new Author("Rod", "Todd");
    Book noEJB = new Book("noEJB", "153454");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    noEJB.setPublisher(ifaPublisher);
    ifaPublisher.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(ifaPublisher);



    System.out.println("Started with data");
    System.out.println("Number of books: " + bookRepository.count());
    System.out.println("Number of publishers books: " + ifaPublisher.getBooks().size());
  }
}
