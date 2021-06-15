package boundary.ManagerCinema;

import controller.CinemaController;
import entity.Cinema;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateCinemaBoundary {
    public TextField fieldNameCinema;
    public TextField fieldSeatsCinema;
    public TextField fieldManagerCinema;
    public Button buttonUpdate;
    public Button buttonExit;
    Cinema selectedCinema;
    CinemaController cinemas = new CinemaController();

    public void initData(Cinema cinema) {
        selectedCinema = cinema;

        fieldNameCinema.setText(selectedCinema.getCinemas_name());
        fieldSeatsCinema.setText(selectedCinema.getCinemas_totalSeats().toString());
        fieldManagerCinema.setText(selectedCinema.getCinemas_manager());
    }

    public void doUpdateCinema(ActionEvent event) {
        String cinemaName = fieldNameCinema.getText();
        Integer cinemaSeats = Integer.valueOf(fieldSeatsCinema.getText());
        String cinemaManager = fieldManagerCinema.getText();

        cinemas.updateCinema(selectedCinema, cinemaName, cinemaSeats, cinemaManager);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cập nhật phòng chiếu thành công");
        alert.setHeaderText(null);
        alert.setContentText("Cập nhật phòng chiếu phim thành công, khách hàng có thể đặt vé để xem phim tại phòng chiếu này");
        alert.showAndWait();
        Stage stage = (Stage) buttonUpdate.getScene().getWindow();
        stage.close();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
