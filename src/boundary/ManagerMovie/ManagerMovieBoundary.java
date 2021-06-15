package boundary.ManagerMovie;

import boundary.ManagerShowtime.AddShowtimeBoundary;
import boundary.ManagerShowtime.ManagerShowtimeBoundary;
import controller.MovieController;
import entity.Movie;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerMovieBoundary {
    @FXML
    public Button buttonUnlockMovie;
    @FXML
    public Button buttonLockMovie;
    @FXML
    public TableView<Movie> tableViewMovie;
    @FXML
    public TextField fieldFindMovie;
    @FXML
    public Button buttonShowtime;
    @FXML
    public Button buttonUpdateMovie;
    @FXML
    public Button buttonAddMovie;
    @FXML
    public Button buttonShowDetail;
    @FXML
    public Button buttonBuyTicket;
    @FXML
    public Button buttonAddShowtime;
    @FXML
    private MovieController movies;
    @FXML
    public GridPane moviePanel;

    private User currentUser;

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
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để mua vé");
            alert.show();
            return;
        }
    }

    @FXML
    public void doAddMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/AddMovieBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("THÊM PHIM");
        stage.setScene(new Scene(parent));
        stage.initOwner((Stage) buttonAddMovie.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        movies.clearData();
        movies.loadMovies();
        tableViewMovie.setItems(movies.getMovies());
    }

    @FXML
    public void doUpdateMovie(ActionEvent event) throws IOException {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để tiến hành cập nhật phim");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/UpdateMovieBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CẬP NHẬT PHIM");
        stage.setScene(new Scene(parent));
        UpdateMovieBoundary boundary = loader.getController();
        boundary.initData(selectedMovie);
        stage.initOwner((Stage) buttonUpdateMovie.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        movies.clearData();
        movies.loadMovies();
        tableViewMovie.setItems(movies.getMovies());
    }

    @FXML
    public void doLockMovie(ActionEvent event) {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để tiến hành khoá phim");
            alert.show();
            return;
        }

        if (!selectedMovie.getMovies_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phim đã bị khoá");
            alert.setHeaderText(null);
            alert.setContentText("Phim bạn chọn đã bị khoá, hãy chọn một phim khác!");
            alert.show();
            return;
        }

        movies.lockMovie(selectedMovie);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Khoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã khoá phim được chọn! Người dùng sẽ không thể mua vé xem phim của phim này");
        alert.show();
        movies.clearData();
        movies.loadMovies();
    }

    @FXML
    public void doUnlockMovie(ActionEvent event) {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để tiến hành mở khoá phim");
            alert.show();
            return;
        }

        if (selectedMovie.getMovies_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phim đã được mở khoá");
            alert.setHeaderText(null);
            alert.setContentText("Phim bạn chọn đã được mở khoá, hãy chọn một phim khác!");
            alert.show();
            return;
        }

        movies.unlockMovie(selectedMovie);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mở khoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã mở khoá phim được chọn! Người dùng sẽ có thể mua vé xem phim của phim này");
        alert.show();
        movies.clearData();
        movies.loadMovies();
    }

    @FXML
    public void enableButton(MouseEvent event) {

    }

    @FXML
    public void doManageShowtime(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerShowtime/ManagerShowtimeBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH SUẤT CHIẾU");
        stage.setScene(new Scene(parent));
        ManagerShowtimeBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonShowtime.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public void doAddShowtime(ActionEvent event) throws IOException {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phim");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phim, hãy chọn một phim để tiến hành thêm suất chiếu");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerShowtime/AddShowtimeBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("THÊM SUẤT CHIẾU");
        stage.setScene(new Scene(parent));
        AddShowtimeBoundary boundary = loader.getController();
        boundary.initData(selectedMovie);
        stage.initOwner((Stage) buttonAddShowtime.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        movies.clearData();
        movies.loadMovies();
        tableViewMovie.setItems(movies.getMovies());
    }
}
