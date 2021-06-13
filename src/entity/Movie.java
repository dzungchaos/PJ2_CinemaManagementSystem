package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PJ2_movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movies_id")
    private Integer movies_id;

    @Column(name = "movies_avatarUrl", length = 256)
    private String movies_avatarUrl;

    @Column(name = "movies_name", length = 256)
    private String movies_name;

    @Column(name = "movies_description", length = 1024)
    private String movies_description;

    @Column(name = "movies_director", length = 256)
    private String movies_director;

    @Column(name = "movies_actors", length = 256)
    private String movies_actors;

    @Column(name = "movies_genres", length = 256)
    private String movies_genres;

    @Column(name = "movies_duration")
    private Integer movies_duration;

    @Column(name = "movies_airDate", length = 10)
    private String movies_airDate;

    @Column(name = "movies_adultRated")
    private Boolean movies_adultRated;

    @Column(name = "movies_showtimes", length = 256)
    private String movies_showtimes;

    @Column(name = "movies_isActive")
    private Boolean movies_isActive;

    public Movie() {

    }

    public Movie(String movies_avatarUrl,
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
        this.movies_avatarUrl = movies_avatarUrl;
        this.movies_name = movies_name;
        this.movies_description = movies_description;
        this.movies_director = movies_director;
        this.movies_actors = movies_actors;
        this.movies_genres = movies_genres;
        this.movies_duration = movies_duration;
        this.movies_airDate = movies_airDate;
        this.movies_adultRated = movies_adultRated;
        this.movies_showtimes = movies_showtimes;
        this.movies_isActive = movies_isActive;
    }

    public Integer getMovies_id() {
        return movies_id;
    }

    public String getMovies_avatarUrl() {
        return movies_avatarUrl;
    }

    public void setMovies_avatarUrl(String movies_avatarUrl) {
        this.movies_avatarUrl = movies_avatarUrl;
    }

    public String getMovies_name() {
        return movies_name;
    }

    public void setMovies_name(String movies_name) {
        this.movies_name = movies_name;
    }

    public String getMovies_description() {
        return movies_description;
    }

    public void setMovies_description(String movies_description) {
        this.movies_description = movies_description;
    }

    public String getMovies_director() {
        return movies_director;
    }

    public void setMovies_director(String movies_director) {
        this.movies_director = movies_director;
    }

    public String getMovies_actors() {
        return movies_actors;
    }

    public void setMovies_actors(String movies_actors) {
        this.movies_actors = movies_actors;
    }

    public String getMovies_genres() {
        return movies_genres;
    }

    public void setMovies_genres(String movies_genres) {
        this.movies_genres = movies_genres;
    }

    public Integer getMovies_duration() {
        return movies_duration;
    }

    public void setMovies_duration(Integer movies_duration) {
        this.movies_duration = movies_duration;
    }

    public String getMovies_airDate() {
        return movies_airDate;
    }

    public void setMovies_airDate(String movies_airDate) {
        this.movies_airDate = movies_airDate;
    }

    public Boolean getMovies_adultRated() {
        return movies_adultRated;
    }

    public void setMovies_adultRated(Boolean movies_adultRated) {
        this.movies_adultRated = movies_adultRated;
    }

    public String getMovies_showtimes() {
        return movies_showtimes;
    }

    public void setMovies_showtimes(String movies_showtimes) {
        this.movies_showtimes = movies_showtimes;
    }

    public Boolean getMovies_isActive() {
        return movies_isActive;
    }

    public void setMovies_isActive(Boolean movies_isActive) {
        this.movies_isActive = movies_isActive;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movies_id=" + movies_id +
                ", movies_avatarUrl='" + movies_avatarUrl + '\'' +
                ", movies_name='" + movies_name + '\'' +
                ", movies_description='" + movies_description + '\'' +
                ", movies_director='" + movies_director + '\'' +
                ", movies_actors='" + movies_actors + '\'' +
                ", movies_genres='" + movies_genres + '\'' +
                ", movies_duration=" + movies_duration +
                ", movies_airDate=" + movies_airDate + '\'' +
                ", movies_adultRated=" + movies_adultRated +
                ", movies_showtimes='" + movies_showtimes + '\'' +
                ", movies_isActive=" + movies_isActive +
                '}';
    }
}
