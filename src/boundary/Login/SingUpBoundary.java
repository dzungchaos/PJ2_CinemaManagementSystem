package boundary.Login;

import controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SingUpBoundary {
    @FXML
    public TextField fieldUserName;

    @FXML
    public PasswordField fieldPassword;

    @FXML
    public Button buttonSignUp;

    @FXML
    public Button buttonExit;

    public UserController users;

    public void initialize() {
        users = new UserController();
    }

    @FXML
    public void closeWindows() {
        users.clearData();
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doSignUp() {
        String userName = fieldUserName.getText();
        String password = fieldPassword.getText();

        if (users.checkUserExist(userName)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Đăng ký thất bại");
            alert.setHeaderText(null);
            alert.setContentText("Tên đăng nhập đã có người sử dụng, vui lòng chọn tên đăng nhập khác");
            alert.show();
            return;
        }

        users.addUser(userName, password, "", "Khách", "", "", "", "MEMBER", "", true, false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đăng ký thành công");
        alert.setHeaderText(null);
        alert.setContentText("Bạn đã đăng ký thành công tài khoản mới. \nHãy đăng nhập để chỉnh sửa thông tin cá nhân.");
        alert.show();
        users.clearData();
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
