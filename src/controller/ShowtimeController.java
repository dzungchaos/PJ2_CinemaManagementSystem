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

    public ShowtimeController() {
        showtimes = FXCollections.observableArrayList();
        loadShowtimes();
    }

    public ObservableList<Showtime> getShowtimes() {
        return showtimes;
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

    public ObservableList<Showtime> getListShowtime(String movieNamePart) {
        ObservableList<Showtime> listShowtimes = null;

        return listShowtimes;
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

    public void loadShowtimes() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Showtime").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
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
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
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
