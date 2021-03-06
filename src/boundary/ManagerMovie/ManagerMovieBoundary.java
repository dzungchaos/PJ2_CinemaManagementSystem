package boundary.ManagerMovie;

import boundary.ManagerShowtime.AddShowtimeBoundary;
import boundary.ManagerShowtime.ManagerShowtimeBoundary;
import controller.MovieController;
import entity.Movie;
import entity.User;
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
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
    public Button buttonImportMovie;
    @FXML
    public Button buttonExportMovie;
    @FXML
    private MovieController movies;
    @FXML
    public GridPane moviePanel;

    private User currentUser;

    String movieNamePart;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        movies = new MovieController();
        tableViewMovie.setItems(movies.getMovies());
    }

    @FXML
    public void doSearchMovie(KeyEvent keyEvent) {
        movieNamePart = fieldFindMovie.getText();
        tableViewMovie.setItems(movies.getListMovie(movieNamePart));
    }

    @FXML
    public void doShowMovieDetail() throws IOException {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? xem chi ti???t");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/MovieDetailBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH S??CH PHIM R???P ??ANG CHI???U");
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
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? mua v??");
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
        stage.setTitle("TH??M PHIM");
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
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? ti???n h??nh c???p nh???t phim");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/UpdateMovieBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("C???P NH???T PHIM");
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
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? ti???n h??nh kho?? phim");
            alert.show();
            return;
        }

        if (!selectedMovie.getMovies_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phim ???? b??? kho??");
            alert.setHeaderText(null);
            alert.setContentText("Phim b???n ch???n ???? b??? kho??, h??y ch???n m???t phim kh??c!");
            alert.show();
            return;
        }

        movies.lockMovie(selectedMovie);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kho?? th??nh c??ng");
        alert.setHeaderText(null);
        alert.setContentText("???? kho?? phim ???????c ch???n! Ng?????i d??ng s??? kh??ng th??? mua v?? xem phim c???a phim n??y");
        alert.show();
        movies.clearData();
        movies.loadMovies();
    }

    @FXML
    public void doUnlockMovie(ActionEvent event) {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? ti???n h??nh m??? kho?? phim");
            alert.show();
            return;
        }

        if (selectedMovie.getMovies_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phim ???? ???????c m??? kho??");
            alert.setHeaderText(null);
            alert.setContentText("Phim b???n ch???n ???? ???????c m??? kho??, h??y ch???n m???t phim kh??c!");
            alert.show();
            return;
        }

        movies.unlockMovie(selectedMovie);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("M??? kho?? th??nh c??ng");
        alert.setHeaderText(null);
        alert.setContentText("???? m??? kho?? phim ???????c ch???n! Ng?????i d??ng s??? c?? th??? mua v?? xem phim c???a phim n??y");
        alert.show();
        movies.clearData();
        movies.loadMovies();
    }

    @FXML
    public void enableButton(MouseEvent event) {
        System.out.println("Table clicked!!!");
        System.out.println();
    }

    @FXML
    public void doManageShowtime(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerShowtime/ManagerShowtimeBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH S??CH SU???T CHI???U");
        stage.setScene(new Scene(parent));
        ManagerShowtimeBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonShowtime.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void doAddShowtime(ActionEvent event) throws IOException {
        Movie selectedMovie= tableViewMovie.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ch??a ch???n phim");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a ch???n phim, h??y ch???n m???t phim ????? ti???n h??nh th??m su???t chi???u");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerShowtime/AddShowtimeBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("TH??M SU???T CHI???U");
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

    @FXML
    public void doImportMovie(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Nh???p danh s??ch phim");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File fileOpen = chooser.showOpenDialog(buttonImportMovie.getScene().getWindow());

        if (fileOpen != null) {
            System.out.println(fileOpen.getPath());
            movies.loadMoviesFromExcelFile(fileOpen);
            ObservableList<Movie> listNewMovies = movies.getNewMovies();
            movies.listNewMovies(listNewMovies);
            movies.addNewMoviesToMoviesList(listNewMovies);
        } else {
            System.out.println("Chooser was cancelled");
        }

    }

    @FXML
    public void doExportMovie(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Xu???t danh s??ch phim");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File fileSave = chooser.showSaveDialog(buttonExportMovie.getScene().getWindow());

        if (fileSave != null) {
            ObservableList<Movie> listMovies = movies.getListMovie(movieNamePart);
            movies.saveMoviesToExcelFile(listMovies, fileSave);
            System.out.println("Saved file: " + fileSave.getPath());
        } else {
            System.out.println("Chooser was cancelled");
        }
    }
}
