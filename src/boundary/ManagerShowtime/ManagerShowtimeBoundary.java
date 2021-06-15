package boundary.ManagerShowtime;

import controller.ShowtimeController;
import entity.Showtime;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerShowtimeBoundary {
    private User currentUser;
    private ShowtimeController showtimes;

    @FXML
    public GridPane showtimePanel;
    @FXML
    public Button buttonBuyTicket;
    @FXML
    public Button buttonAdd;
    @FXML
    public Button buttonUpdate;
    @FXML
    public TextField fieldFindShowtime;
    @FXML
    public TableView<Showtime> tableViewShowtime;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        showtimes = new ShowtimeController();
        tableViewShowtime.setItems(showtimes.getShowtimes());
    }

    @FXML
    public void doBuyTicket(ActionEvent event) {
        Showtime selectedShowtime = tableViewShowtime.getSelectionModel().getSelectedItem();
        if (selectedShowtime == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CHƯA CHỌN SUẤT CHIẾU");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn suất chiếu. Hãy chọn suất chiếu để chỉnh sửa hoặc đặt vé");
            alert.show();
            return;
        }


    }

    @FXML
    public void doSearchShowtime(KeyEvent event) {
        String movieNamePart = fieldFindShowtime.getText();
        tableViewShowtime.setItems(showtimes.getListShowtime(movieNamePart));
    }

    @FXML
    public void doUpdateShowtime(ActionEvent event) throws IOException {
        Showtime selectedShowtime = tableViewShowtime.getSelectionModel().getSelectedItem();
        if (selectedShowtime == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CHƯA CHỌN SUẤT CHIẾU");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn suất chiếu. Hãy chọn suất chiếu để chỉnh sửa hoặc đặt vé");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerShowtime/UpdateShowtimeBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CẬP NHẬT SUẤT CHIẾU");
        stage.setScene(new Scene(parent));
        UpdateShowtimeBoundary boundary = loader.getController();
        boundary.initData(selectedShowtime);
        stage.initOwner((Stage) buttonUpdate.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        showtimes.clearData();
        showtimes.loadShowtimes();
        tableViewShowtime.setItems(showtimes.getShowtimes());
    }
}
