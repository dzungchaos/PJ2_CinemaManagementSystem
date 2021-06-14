package boundary.ManagerMovie;

import controller.MovieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieBoundary {
    private MovieController movies = new MovieController();
    @FXML
    public TextField fieldMovieName;
    @FXML
    public TextField fieldMovieDirector;
    @FXML
    public TextArea fieldMovieDescription;
    @FXML
    public TextField fieldMovieActors;
    @FXML
    public TextField fieldMovieGenres;
    @FXML
    public TextField fieldMovieDuration;
    @FXML
    public TextField fieldMovieAirDate;
    @FXML
    public TextField fieldMovieShowtime;
    @FXML
    public Button buttonExit;
    @FXML
    public Button buttonAdd;

    @FXML
    public void doAddMovie(ActionEvent event) {
        if (fieldMovieName.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trống dữ liệu");
            alert.setHeaderText(null);
            alert.setContentText("Bạn phải nhập các trường bắt buộc này, không được để trống thông tin!");
            alert.show();
            return;
        }

        String movieName = fieldMovieName.getText();
        String movieDescription = fieldMovieDescription.getText();
        String movieDirector = fieldMovieDirector.getText();
        String movieActors = fieldMovieActors.getText();
        String movieGenres = fieldMovieGenres.getText();
        Integer movieDuration = Integer.valueOf(fieldMovieDuration.getText());
        String movieAirDate = fieldMovieAirDate.getText();
        String movieShowtime = fieldMovieShowtime.getText();
        movies.addMovie("", movieName, movieDescription, movieDirector, movieActors, movieGenres, movieDuration, movieAirDate, movieShowtime);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm phim thành công");
        alert.setHeaderText(null);
        alert.setContentText("Thêm phim thành công, khách hàng có thể đặt vé để xem phim này");
        alert.showAndWait();
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
