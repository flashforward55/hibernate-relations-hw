package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Initialize DAOs
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        // Initialize Services
        CountryService countryService = new CountryServiceImpl(countryDao);
        final ActorService actorService = new ActorServiceImpl(actorDao);
        final MovieService movieService = new MovieServiceImpl(movieDao);

        // Create and save Country
        Country usa = new Country("USA");
        countryService.add(usa);
        System.out.println("Added country: " + usa);

        // Create and save Actor
        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);
        System.out.println("Added actor: " + vinDiesel);

        // Create and save Movie
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);
        System.out.println("Added movie: " + fastAndFurious);

        // Retrieve and print Movie
        Movie retrievedMovie = movieService.get(fastAndFurious.getId());
        System.out.println("\nRetrieved movie: " + retrievedMovie);
        System.out.println("Movie actors: " + retrievedMovie.getActors());
    }
}
