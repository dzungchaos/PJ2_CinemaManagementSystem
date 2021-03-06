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
            alert.setTitle("TH??NG TIN KH??NG H???P L???");
            alert.setHeaderText(null);
            alert.setContentText("B???n ch??a nh???p ????? c??c tr?????ng th??ng tin c???n thi???t, vui l??ng nh???p l???i");
            alert.show();
            return;
        }

        if (showtimes.checkDuplicated(selectedShowtime.getShowtimes_movies_id(), cinemaID, time, date)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("TH??NG TIN KH??NG H???P L???");
            alert.setHeaderText(null);
            alert.setContentText("Th??ng tin b???n v???a nh???p b??? tr??ng v???i m???t su???t chi???u kh??c! Vui l??ng nh???p l???i");
            alert.show();
            return;
        }

        showtimes.updateShowtime(selectedShowtime, time, date, cinemaID, cinemaName);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("S???A SU???T CHI???U TH??NH C??NG!");
        alert.setHeaderText(null);
        alert.setContentText("S???a su???t chi???u th??nh c??ng! Ng?????i d??ng c?? th??? ?????t v?? v???i su???t chi???u phim n??y");
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
