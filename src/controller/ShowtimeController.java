package controller;

import entity.Showtime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class ShowtimeController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    ObservableList<Showtime> showtimes;
    ObservableList<Showtime> foundShowtimes;
    ObservableList<Showtime> showtimesByMovie;

    public ShowtimeController() {
        showtimes = FXCollections.observableArrayList();
        foundShowtimes = FXCollections.observableArrayList();
        showtimesByMovie = FXCollections.observableArrayList();
        loadShowtimes();
    }

    public ObservableList<Showtime> getShowtimes() {
        return showtimes;
    }

    public ObservableList<Showtime> getListShowtime(String movie_name) {
        foundShowtimes.clear();

        for (Showtime showtime : showtimes) {
            if (movie_name.equals(showtime.getShowtimes_movies_name())) {
                foundShowtimes.add(showtime);
            }
        }

        return foundShowtimes;
    }

    public ObservableList<Showtime> getListShowtimeByMovie(Integer movies_id) {
        for (Showtime showtime : showtimes) {
            if (movies_id.equals(showtime.getShowtimes_movies_id())) {
                showtimesByMovie.add(showtime);
            }
        }

        return showtimesByMovie;
    }

    public Integer addShowtime(Integer showtimes_movies_id,
                               Integer showtimes_cinemas_id,
                               String showtimes_time,
                               String showtimes_date,
                               String showtimes_movies_name,
                               String showtimes_cinemas_name) {

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer showtimes_id = null;

        try {
            transaction = session.beginTransaction();
            Showtime showtime = new Showtime(showtimes_movies_id,
                    showtimes_cinemas_id,
                    showtimes_time,
                    showtimes_date,
                    showtimes_movies_name,
                    showtimes_cinemas_name);
            showtimes_id = (Integer) session.save(showtime);
            transaction.commit();
            showtimes.add(showtime);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return showtimes_id;
    }

    public Showtime getShowTime(Integer showtimes_id) {
        Showtime gotShowtime = null;

        for (Showtime showtime : showtimes) {
            if (showtimes_id.equals(showtime.getShowtimes_id())) {
                gotShowtime = showtime;
            }
        }

        return gotShowtime;
    }

    public Boolean checkDuplicated(Integer movies_id, Integer cinemas_id, String time, String date) {
        for (Showtime showtime : showtimes) {
            if (movies_id.equals(showtime.getShowtimes_movies_id()) &&
                cinemas_id.equals(showtime.getShowtimes_cinemas_id()) &&
                time.equals(showtime.getShowtimes_time()) &&
                date.equals(showtime.getShowtimes_date())) {
                return true;
            }
        }

        return false;
    }

    public void updateShowtime(Showtime selectedShowtime,
                               String showtimes_time,
                               String showtimes_date) {

        selectedShowtime.setShowtimes_time(showtimes_time);
        selectedShowtime.setShowtimes_date(showtimes_date);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedShowtime);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        showtimes.clear();
        loadShowtimes();
    }

    public void loadShowtimes() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Showtime").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Showtime showtime = (Showtime) iterator.next();
                showtimes.add(showtime);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listShowtimes() {
        int count = 0;
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Showtime").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                count++;
                System.out.println(count);
                Showtime showtime = (Showtime) iterator.next();
                System.out.println("ID: " + showtime.getShowtimes_id());
                System.out.println("Movie ID: " + showtime.getShowtimes_movies_id());
                System.out.println("Cinema ID: " + showtime.getShowtimes_cinemas_id());
                System.out.println("Time: " + showtime.getShowtimes_time());
                System.out.println("Date: " + showtime.getShowtimes_date());
                System.out.println("Movie Name: " + showtime.getShowtimes_movies_name());
                System.out.println("Cinema Name: " + showtime.getShowtimes_cinemas_name());
                System.out.println();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void clearData() {
        showtimes.clear();
    }

}
