package entity;

import javax.persistence.*;

@Entity
@Table(name = "PJ2_tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tickets_id", unique = true, nullable = false)
    private Integer tickets_id;

    @Column(name = "tickets_showtimes_id")
    private Integer tickets_showtimes_id;

    @Column(name = "tickets_users_id")
    private Integer tickets_users_id;

    @Column(name = "tickets_movies_name", length = 256)
    private String tickets_movies_name;

    @Column(name = "tickets_cinemas_name", length = 256)
    private String tickets_cinemas_name;

    @Column(name = "tickets_users_name", length = 256)
    private String tickets_users_name;

    @Column(name = "tickets_showtimes_time", length = 16)
    private String tickets_showtimes_time;

    @Column(name = "tickets_showtimes_date", length = 16)
    private String tickets_showtimes_date;

    @Column(name = "tickets_seats", length = 16)
    private String tickets_seats;

    @Column(name = "tickets_purchasedDate", length = 16)
    private String tickets_purchasedDate;

    public Ticket() {

    }

    public Ticket(Integer tickets_showtimes_id,
                  Integer tickets_users_id,
                  String tickets_movies_name,
                  String tickets_cinemas_name,
                  String tickets_users_name,
                  String tickets_showtimes_time,
                  String tickets_showtimes_date,
                  String tickets_seats,
                  String tickets_purchasedDate) {
        this.tickets_showtimes_id = tickets_showtimes_id;
        this.tickets_users_id = tickets_users_id;
        this.tickets_movies_name = tickets_movies_name;
        this.tickets_cinemas_name = tickets_cinemas_name;
        this.tickets_users_name = tickets_users_name;
        this.tickets_showtimes_time = tickets_showtimes_time;
        this.tickets_showtimes_date = tickets_showtimes_date;
        this.tickets_seats = tickets_seats;
        this.tickets_purchasedDate = tickets_purchasedDate;
    }

    public Integer getTickets_id() {
        return tickets_id;
    }

    public Integer getTickets_showtimes_id() {
        return tickets_showtimes_id;
    }

    public void setTickets_showtimes_id(Integer tickets_showtimes_id) {
        this.tickets_showtimes_id = tickets_showtimes_id;
    }

    public Integer getTickets_users_id() {
        return tickets_users_id;
    }

    public void setTickets_users_id(Integer tickets_users_id) {
        this.tickets_users_id = tickets_users_id;
    }

    public String getTickets_movies_name() {
        return tickets_movies_name;
    }

    public void setTickets_movies_name(String tickets_movies_name) {
        this.tickets_movies_name = tickets_movies_name;
    }

    public String getTickets_cinemas_name() {
        return tickets_cinemas_name;
    }

    public void setTickets_cinemas_name(String tickets_cinemas_name) {
        this.tickets_cinemas_name = tickets_cinemas_name;
    }

    public String getTickets_users_name() {
        return tickets_users_name;
    }

    public void setTickets_users_name(String tickets_users_name) {
        this.tickets_users_name = tickets_users_name;
    }

    public String getTickets_showtimes_time() {
        return tickets_showtimes_time;
    }

    public void setTickets_showtimes_time(String tickets_showtimes_time) {
        this.tickets_showtimes_time = tickets_showtimes_time;
    }

    public String getTickets_showtimes_date() {
        return tickets_showtimes_date;
    }

    public void setTickets_showtimes_date(String tickets_showtimes_date) {
        this.tickets_showtimes_date = tickets_showtimes_date;
    }

    public String getTickets_seats() {
        return tickets_seats;
    }

    public void setTickets_seats(String tickets_seats) {
        this.tickets_seats = tickets_seats;
    }

    public String getTickets_purchasedDate() {
        return tickets_purchasedDate;
    }

    public void setTickets_purchasedDate(String tickets_purchasedDate) {
        this.tickets_purchasedDate = tickets_purchasedDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tickets_id=" + tickets_id +
                ", tickets_showtimes_id=" + tickets_showtimes_id +
                ", tickets_users_id=" + tickets_users_id +
                ", tickets_movies_name='" + tickets_movies_name + '\'' +
                ", tickets_cinemas_name='" + tickets_cinemas_name + '\'' +
                ", tickets_users_name='" + tickets_users_name + '\'' +
                ", tickets_showtimes_time='" + tickets_showtimes_time + '\'' +
                ", tickets_showtimes_date='" + tickets_showtimes_date + '\'' +
                ", tickets_seats='" + tickets_seats + '\'' +
                ", tickets_purchaseDate='" + tickets_purchasedDate + '\'' +
                '}';
    }
}
