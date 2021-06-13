package controller;

import entity.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class TicketController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    ObservableList<Ticket> tickets;

    public TicketController() {
        tickets = FXCollections.observableArrayList();
        loadTickets();
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }

    public Integer addTicket(Integer tickets_showtimes_id,
                             Integer tickets_users_id,
                             String tickets_movies_name,
                             String tickets_cinemas_name,
                             String tickets_users_name,
                             String tickets_showtimes_time,
                             String tickets_showtimes_date,
                             String tickets_seats,
                             String tickets_purchasedDate) {

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer tickets_id = null;

        try {
            transaction = session.beginTransaction();
            Ticket ticket = new Ticket(tickets_showtimes_id,
                    tickets_users_id,
                    tickets_movies_name,
                    tickets_cinemas_name,
                    tickets_users_name,
                    tickets_showtimes_time,
                    tickets_showtimes_date,
                    tickets_seats,
                    tickets_purchasedDate);
            tickets_id = (Integer) session.save(ticket);
            transaction.commit();
            tickets.add(ticket);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return tickets_id;
    }

    public ObservableList<Ticket> getListTicket(String movieNamePart) {
        ObservableList<Ticket> listTickets = null;

        return listTickets;
    }

    public Ticket getTicket(Integer tickets_id) {
        Ticket gotTicket = null;

        for (Ticket ticket : tickets) {
            if (tickets_id.equals(ticket.getTickets_id())) {
                gotTicket = ticket;
            }
        }

        return gotTicket;
    }

    public void loadTickets() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Ticket").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Ticket ticket = (Ticket) iterator.next();
                tickets.add(ticket);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listTickets() {
        int count = 0;
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Ticket").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                count++;
                System.out.println(count);
                Ticket ticket = (Ticket) iterator.next();
                System.out.println("ID: " + ticket.getTickets_id());
                System.out.println("ShowtimeID: " + ticket.getTickets_showtimes_id());
                System.out.println("UserID: " + ticket.getTickets_users_id());
                System.out.println("Movie Name: " + ticket.getTickets_movies_name());
                System.out.println("Cinema Name: " + ticket.getTickets_cinemas_name());
                System.out.println("User Name: " + ticket.getTickets_users_name());
                System.out.println("Showtime: " + ticket.getTickets_showtimes_time());
                System.out.println("Date: " + ticket.getTickets_showtimes_date());
                System.out.println("Seat: " + ticket.getTickets_seats());
                System.out.println("Purchased date: " + ticket.getTickets_purchasedDate());
                System.out.println();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void clearData() {
        tickets.clear();
    }

}
