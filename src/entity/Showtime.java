package entity;

import javax.persistence.*;

@Entity
@Table(name = "PJ2_showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtimes_id", unique = true, nullable = false)
    private Integer showtimes_id;

    @Column(name = "showtimes_movies_id")
    private Integer showtimes_movies_id;

    @Column(name = "showtimes_cinemas_id")
    private Integer showtimes_cinemas_id;

    @Column(name = "showtimes_time", length = 16)
    private String showtimes_time;

    @Column(name = "showtimes_date", length = 16)
    private String showtimes_date;

    @Column(name = "showtimes_movies_name")
    private String showtimes_movies_name;

    @Column(name = "showtimes_cinemas_name")
    private String showtimes_cinemas_name;

    public Showtime() {

    }

    public Showtime(Integer showtimes_movies_id,
                    Integer showtimes_cinemas_id,
                    String showtimes_time,
                    String showtimes_date,
                    String showtimes_movies_name,
                    String showtimes_cinemas_name) {
        this.showtimes_movies_id = showtimes_movies_id;
        this.showtimes_cinemas_id = showtimes_cinemas_id;
        this.showtimes_time = showtimes_time;
        this.showtimes_date = showtimes_date;
        this.showtimes_movies_name = showtimes_movies_name;
        this.showtimes_cinemas_name = showtimes_cinemas_name;
    }

    public Integer getShowtimes_id() {
        return showtimes_id;
    }

    public Integer getShowtimes_movies_id() {
        return showtimes_movies_id;
    }

    public void setShowtimes_movies_id(Integer showtimes_movies_id) {
        this.showtimes_movies_id = showtimes_movies_id;
    }

    public Integer getShowtimes_cinemas_id() {
        return showtimes_cinemas_id;
    }

    public void setShowtimes_cinemas_id(Integer showtimes_cinemas_id) {
        this.showtimes_cinemas_id = showtimes_cinemas_id;
    }

    public String getShowtimes_time() {
        return showtimes_time;
    }

    public void setShowtimes_time(String showtimes_time) {
        this.showtimes_time = showtimes_time;
    }

    public String getShowtimes_date() {
        return showtimes_date;
    }

    public void setShowtimes_date(String showtimes_date) {
        this.showtimes_date = showtimes_date;
    }

    public String getShowtimes_movies_name() {
        return showtimes_movies_name;
    }

    public void setShowtimes_movies_name(String showtimes_movies_name) {
        this.showtimes_movies_name = showtimes_movies_name;
    }

    public String getShowtimes_cinemas_name() {
        return showtimes_cinemas_name;
    }

    public void setShowtimes_cinemas_name(String showtimes_cinemas_name) {
        this.showtimes_cinemas_name = showtimes_cinemas_name;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "showtimes_id=" + showtimes_id +
                ", showtimes_movies_id=" + showtimes_movies_id +
                ", showtimes_cinemas_id=" + showtimes_cinemas_id +
                ", showtimes_time='" + showtimes_time + '\'' +
                ", showtimes_date='" + showtimes_date + '\'' +
                ", showtimes_movies_name='" + showtimes_movies_name + '\'' +
                ", showtimes_cinemas_name='" + showtimes_cinemas_name + '\'' +
                '}';
    }
}
