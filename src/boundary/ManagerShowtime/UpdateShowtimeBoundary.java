package boundary.ManagerShowtime;

import controller.CinemaController;
import controller.ShowtimeController;
import entity.Showtime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateShowtimeBoundary {
    private Showtime selectedShowtime;
    private ObservableList<String> timeList = FXCollections.observableArrayList();
    private ObservableList<String> unlockCinemaNameList = FXCollections.observableArrayList();
    private CinemaController cinemas = new CinemaController();
    private ShowtimeController showtimes = new ShowtimeController();

    @FXML
    public Button buttonUpdate;
    @FXML
    public Button buttonExit;
    @FXML
    public ComboBox<String> boxTime;
    @FXML
    public TextField fieldDate;
    @FXML
    public ComboBox<String> boxCinema;

    public void initData(Showtime showtime) {
        selectedShowtime = showtime;
        timeList.add("08:00");
        timeList.add("10:00");
        timeList.add("12:00");
        timeList.add("14:00");
        timeList.add("16:00");
        timeList.add("18:00");
        timeList.add("20:00");
        timeList.add("22:00");
        boxTime.setItems(timeList);
        boxTime.getSelectionModel().select(selectedShowtime.getShowtimes_time());

        unlockCinemaNameList = cinemas.getUnlockCinemasName();
        boxCinema.setItems(unlockCinemaNameList);
        boxCinema.getSelectionModel().select(selectedShowtime.getShowtimes_cinemas_name());
    }

    @FXML
    public void doUpdateShowtime(ActionEvent event) {
        String time = boxTime.getSelectionModel().getSelectedItem();
        String date = fieldDate.getText();
        String cinemaName = boxCinema.getSelectionModel().getSelectedItem();
        Integer cinemaID = cinemas.getCinemaIDByName(cinemaName);

        if (time == null || date == null || cinemaName == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG TIN KHÔNG HỢP LỆ");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập đủ các trường thông tin cần thiết, vui lòng nhập lại");
            alert.show();
            return;
        }

        if (showtimes.checkDuplicated(selectedShowtime.getShowtimes_movies_id(), cinemaID, time, date)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG TIN KHÔNG HỢP LỆ");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin bạn vừa nhập bị trùng với một suất chiếu khác! Vui lòng nhập lại");
            alert.show();
            return;
        }

        showtimes.updateShowtime(selectedShowtime, time, date, cinemaID, cinemaName);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SỬA SUẤT CHIẾU THÀNH CÔNG!");
        alert.setHeaderText(null);
        alert.setContentText("Sửa suất chiếu thành công! Người dùng có thể đặt vé với suất chiếu phim này");
        alert.showAndWait();
        showtimes.clearData();
        showtimes.loadShowtimes();
        Stage stage = (Stage) buttonUpdate.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
