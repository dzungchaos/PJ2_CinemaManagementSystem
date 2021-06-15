package boundary.ManagerShowtime;

import controller.CinemaController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddShowtimeBoundary {

    private ShowtimeController showtimes = new ShowtimeController();
    private Movie selectedMovie;
    private ObservableList<String> timeList = FXCollections.observableArrayList();
    private CinemaController cinemas = new CinemaController();
    private ObservableList<String> unlockCinemaNameList = FXCollections.observableArrayList();

    public ComboBox<String> boxTime;
    public TextField fieldDate;
    public ComboBox<String> boxCinema;


    public Button buttonAdd;
    public Button buttonExit;

    public void initData(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;

        timeList.add("08:00");
        timeList.add("10:00");
        timeList.add("12:00");
        timeList.add("14:00");
        timeList.add("16:00");
        timeList.add("18:00");
        timeList.add("20:00");
        timeList.add("22:00");
        boxTime.setItems(timeList);

        unlockCinemaNameList = cinemas.getUnlockCinemasName();
        boxCinema.setItems(unlockCinemaNameList);
    }

    public void doAddShowtime(ActionEvent event) {
        String time = boxTime.getSelectionModel().getSelectedItem();
        String date = fieldDate.getText();
        String cinema = boxCinema.getSelectionModel().getSelectedItem();

        if ((time == null) || (date == null) || (cinema == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG TIN KHÔNG HỢP LỆ");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập đủ các trường thông tin cần thiết, vui lòng nhập lại");
            alert.show();
            return;
        }

        if (showtimes.checkDuplicated(selectedMovie.getMovies_id(), cinemas.getCinemaIDByName(cinema), time, date)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG TIN KHÔNG HỢP LỆ");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin bạn vừa nhập bị trùng với một suất chiếu khác! Vui lòng nhập lại");
            alert.show();
            return;
        }

        showtimes.addShowtime(selectedMovie.getMovies_id(), cinemas.getCinemaIDByName(cinema), time, date, selectedMovie.getMovies_name(), cinema);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("THÊM SUẤT CHIẾU THÀNH CÔNG!");
        alert.setHeaderText(null);
        alert.setContentText("Thêm suất chiếu thành công! Người dùng có thể đặt vé với suất chiếu phim này");
        alert.showAndWait();
        showtimes.clearData();
        showtimes.loadShowtimes();
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
