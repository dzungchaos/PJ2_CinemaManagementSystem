package boundary.ManagerCinema;

import controller.CinemaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCinemaBoundary {
    private CinemaController cinemas = new CinemaController();
    @FXML
    public TextField fieldNameCinema;
    @FXML
    public TextField fieldSeatsCinema;
    @FXML
    public TextField fieldManagerCinema;
    @FXML
    public Button buttonAdd;
    @FXML
    public Button buttonExit;

    @FXML
    public void doAddCinema(ActionEvent event) {
        String cinemaName = fieldNameCinema.getText();
        Integer cinemaSeats = Integer.valueOf(fieldSeatsCinema.getText());
        String cinemaManager = fieldManagerCinema.getText();

        if (cinemas.checkDuplicated(cinemaName)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG TIN KHÔNG HỢP LỆ");
            alert.setHeaderText(null);
            alert.setContentText("Tên phòng chiếu bị trùng với phòng chiếu khác, vui lòng nhập lại dữ liệu");
            alert.show();
            return;
        }

        cinemas.addCinema(cinemaName, cinemaSeats, cinemaManager, true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm phòng chiếu thành công");
        alert.setHeaderText(null);
        alert.setContentText("Thêm phòng chiếu phim thành công, khách hàng có thể đặt vé để xem phim tại phòng chiếu này");
        alert.showAndWait();
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
    }
}
