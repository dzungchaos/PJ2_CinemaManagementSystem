package boundary.ManagerMovie;

import controller.MovieController;
import entity.Movie;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerMovieBoundary {
    private User currentUser;

    @FXML
    public TableView<Movie> tableViewMovie;

    @FXML
    public TextField fieldFindMovie;

    private MovieController movies;

    @FXML
    public GridPane moviePanel;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        movies = new MovieController();
        tableViewMovie.setItems(movies.getMovies());
    }

    @FXML
    public void doSearchMovie(KeyEvent keyEvent) {
        String movieNamePart = fieldFindMovie.getText();
        tableViewMovie.setItems(movies.getListMovie(movieNamePart));
    }

    @FXML
    public void doShowMovieDetail() throws IOException {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để xem chi tiết");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/MovieDetailBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH PHIM RẠP ĐANG CHIẾU");
        stage.setScene(new Scene(parent));
        MovieDetailBoundary boundary = loader.getController();
        boundary.initData(selectedMovie);
        stage.initOwner((Stage) tableViewMovie.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void doBuyTicket(ActionEvent event) {

    }

    @FXML
    public void doAddMovie(ActionEvent event) {

    }

    @FXML
    public void doUpdateMovie(ActionEvent event) {

    }

    @FXML
    public void doLockMovie(ActionEvent event) {

    }

    @FXML
    public void doUnlockMovie(ActionEvent event) {

    }
}
