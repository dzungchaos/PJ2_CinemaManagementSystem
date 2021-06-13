package controller;

import entity.Cinema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class CinemaController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    ObservableList<Cinema> cinemas;

    public CinemaController() {
        cinemas = FXCollections.observableArrayList();
        loadCinemas();
    }

    public ObservableList<Cinema> getCinemas() {
        return cinemas;
    }

    public Integer addCinema(String cinemas_name,
                             Integer cinemas_totalSeats,
                             String cinemas_manager,
                             Boolean cinemas_isActive) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Integer cinemas_id = null;

        try {
            transaction = session.beginTransaction();
            Cinema cinema = new Cinema(cinemas_name,
                                        cinemas_totalSeats,
                                        cinemas_manager,
                                        cinemas_isActive);
            cinemas_id = (Integer) session.save(cinema);
            transaction.commit();
            cinemas.add(cinema);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cinemas_id;
    }

    public ObservableList<Cinema> getListCinema(String cinemaNamePart) {
        ObservableList<Cinema> listCinemas = null;

        return listCinemas;
    }

    public Cinema getCinema(Integer cinemas_id) {
        Cinema gotCinema = null;

        for (Cinema cinema : cinemas)
            if (cinemas_id.equals(cinema.getCinemas_id()))
                gotCinema = cinema;

        return gotCinema;
    }

    public void loadCinemas() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Cinema").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Cinema cinema = (Cinema) iterator.next();
                cinemas.add(cinema);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listCinema() {
        int count = 0;
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Cinema").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                count ++;
                System.out.println(count + "\n");
                Cinema cinema = (Cinema) iterator.next();
                System.out.println("Name: " + cinema.getCinemas_name());
                System.out.println("Num of Seats: " + cinema.getCinemas_totalSeats());
                System.out.println("Manager: " + cinema.getCinemas_manager());
                System.out.println("Is active: " + cinema.getCinemas_isActive());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void lockCinema(Cinema selectedCinema) {

    }

    public void unlockCinema(Cinema selectedCinema) {

    }

    public ObservableList<Cinema> getUnlockCinema() {
        ObservableList<Cinema> unlockCinemas = null;

        for (Cinema cinema : cinemas) {
            if (cinema.getCinemas_isActive()) {
                unlockCinemas.add(cinema);
            }
        }

        return unlockCinemas;
    }

}
