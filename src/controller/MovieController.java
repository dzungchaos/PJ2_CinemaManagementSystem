package controller;

import entity.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class MovieController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    ObservableList<Movie> movies;

    public MovieController() {
        movies = FXCollections.observableArrayList();
        loadMovies();
    }

    public ObservableList<Movie> getMovies;

    public Integer addMovie(String movies_avatarUrl,
                            String movies_name,
                            String movies_description,
                            String movies_director,
                            String movies_actors,
                            String movies_genres,
                            Integer movies_duration,
                            String movies_airDate,
                            Boolean movies_adultRated,
                            String movies_showtimes,
                            Boolean movies_isActive) {

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer movies_id = null;

        try {
            transaction = session.beginTransaction();
            Movie movie = new Movie(movies_avatarUrl,
                                    movies_name,
                                    movies_description,
                                    movies_director,
                                    movies_actors,
                                    movies_genres,
                                    movies_duration,
                                    movies_airDate,
                                    movies_adultRated,
                                    movies_showtimes,
                                    movies_isActive);
            movies_id = (Integer) session.save(movie);
            transaction.commit();
            movies.add(movie);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return movies_id;
    }

    public ObservableList<Movie> getListMovie(String movieNamePart) {
        ObservableList<Movie> listMovies = null;

        return listMovies;
    }

    public Movie getMovie(Integer movies_id) {
        Movie gotMovie = null;

        for (Movie movie : movies)
            if (movies_id.equals(movie.getMovies_id()))
                gotMovie = movie;

        return gotMovie;
    }

    public void loadMovies() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Movie").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Movie movie = (Movie) iterator.next();
                movies.add(movie);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listMovies() {
        int count = 0;
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Movie").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                count++;
                System.out.println(count);
                Movie movie = (Movie) iterator.next();
                System.out.println("ID: " + movie.getMovies_id());
                System.out.println("Avatar: " + movie.getMovies_avatarUrl());
                System.out.println("Name: " + movie.getMovies_name());
                System.out.println("Description: " + movie.getMovies_description());
                System.out.println("Director: " + movie.getMovies_director());
                System.out.println("Actors: " + movie.getMovies_actors());
                System.out.println("Genres: " + movie.getMovies_genres());
                System.out.println("Duration: " + movie.getMovies_duration() + " mins");
                System.out.println("AirDate: " + movie.getMovies_airDate());
                System.out.println("Adult Rated: " + movie.getMovies_adultRated());
                System.out.println("Showtimes: " + movie.getMovies_showtimes());
                System.out.println("IsActive: " + movie.getMovies_isActive());
                System.out.println();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void lockMovie(Movie selectedMovie) {

    }

    public void unlockMovie(Movie selectedMovie) {

    }

    public ObservableList<Movie> getUnlockMovie() {
        ObservableList<Movie> unlockMovies = null;

        for (Movie movie : movies) {
            if (movie.getMovies_isActive()) {
                unlockMovies.add(movie);
            }
        }

        return unlockMovies;
    }

    public void clearData() {
        movies.clear();
    }
}
