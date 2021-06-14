package boundary.ManagerMovie;

import entity.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MovieDetailMemberBoundary {
    public Button buttonExit;
    private Movie currentMovie;

    @FXML
    public Label labelMovieActors;
    @FXML
    public Label labelMovieGenres;
    @FXML
    public Label labelMovieDuration;
    @FXML
    public Label labelMovieAirDate;
    @FXML
    public Label labelMovieShowtime;
    @FXML
    public Label labelMovieID;
    @FXML
    public Label labelMovieName;
    @FXML
    public Label labelMovieDescription;
    @FXML
    public Label labelMovieDirector;

    public void initData(Movie movie) {
        currentMovie = movie;
        labelMovieID.setText(currentMovie.getMovies_id().toString());
        labelMovieName.setText(currentMovie.getMovies_name());
        labelMovieDescription.setText(currentMovie.getMovies_description());
        labelMovieDirector.setText(currentMovie.getMovies_director());
        labelMovieActors.setText(currentMovie.getMovies_actors());
        labelMovieGenres.setText(currentMovie.getMovies_genres());
        labelMovieDuration.setText(currentMovie.getMovies_duration().toString() + " phút");
        labelMovieAirDate.setText(currentMovie.getMovies_airDate());
        labelMovieShowtime.setText(currentMovie.getMovies_showtimes());
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
