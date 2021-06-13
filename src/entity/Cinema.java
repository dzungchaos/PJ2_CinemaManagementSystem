package entity;

import javax.persistence.*;

@Entity
@Table(name = "PJ2_cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinemas_id", unique = true, nullable = false)
    private Integer cinemas_id;

    @Column(name = "cinemas_name", length = 256)
    private String cinemas_name;

    @Column(name = "cinemas_totalSeats")
    private Integer cinemas_totalSeats;

    @Column(name = "cinemas_manager", length = 256)
    private String cinemas_manager;

    @Column(name = "cinemas_isActive")
    private Boolean cinemas_isActive;

    public Cinema() {

    }

    public Cinema(String cinemas_name,
                  Integer cinemas_totalSeats,
                  String cinemas_manager,
                  Boolean cinemas_isActive) {
        this.cinemas_name = cinemas_name;
        this.cinemas_totalSeats = cinemas_totalSeats;
        this.cinemas_manager = cinemas_manager;
        this.cinemas_isActive = cinemas_isActive;
    }

    public Integer getCinemas_id() {
        return cinemas_id;
    }

    public String getCinemas_name() {
        return cinemas_name;
    }

    public void setCinemas_name(String cinemas_name) {
        this.cinemas_name = cinemas_name;
    }

    public Integer getCinemas_totalSeats() {
        return cinemas_totalSeats;
    }

    public void setCinemas_totalSeats(Integer cinemas_totalSeats) {
        this.cinemas_totalSeats = cinemas_totalSeats;
    }

    public String getCinemas_manager() {
        return cinemas_manager;
    }

    public void setCinemas_manager(String cinemas_manager) {
        this.cinemas_manager = cinemas_manager;
    }

    public Boolean getCinemas_isActive() {
        return cinemas_isActive;
    }

    public void setCinemas_isActive(Boolean cinemas_isActive) {
        this.cinemas_isActive = cinemas_isActive;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemas_id=" + cinemas_id +
                ", cinemas_name='" + cinemas_name + '\'' +
                ", cinemas_totalSeats=" + cinemas_totalSeats +
                ", cinemas_manager='" + cinemas_manager + '\'' +
                ", cinemas_isActive=" + cinemas_isActive +
                '}';
    }
}
