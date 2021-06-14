package boundary.PersonalInfo;

import controller.UserController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChangePasswordBoundary {
    private UserController users = new UserController();

    @FXML
    public PasswordField fieldOldPassword;

    @FXML
    public PasswordField fieldNewPassword;

    @FXML
    public PasswordField fieldNewPasswordRetype;

    @FXML
    public Label labelCheckSimilar;
    @FXML
    public Button buttonExit;
    @FXML
    public Button buttonChange;

    private User currentUser;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        buttonChange.setDisable(true);
    }

    @FXML
    public void doChangePassword(ActionEvent event) {
        String oldPassword = fieldOldPassword.getText();
        String newPassword = fieldNewPassword.getText();

        if (!users.checkLoginInfo(currentUser.getUsers_userName(), oldPassword)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sai mật khẩu");
            alert.setHeaderText(null);
            alert.setContentText("Bạn đã nhập sai mật khẩu của tài khoản này, vui lòng nhập lại");
            alert.show();
            return;
        }

        currentUser.setUsers_password(newPassword);
        users.changePassword(currentUser, newPassword);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đổi mật khẩu thành công");
        alert.setHeaderText(null);
        alert.setContentText("Mật khẩu của bạn đã được thay đổi. Bạn có thể dùng mật khẩu này để đăng nhập vào lần sau");
        alert.showAndWait();
        users.clearData();
        Stage stage = (Stage) buttonChange.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doCheckSimilar(KeyEvent keyEvent) {
        String newPassword = fieldNewPassword.getText();
        String newPasswordRetype = fieldNewPasswordRetype.getText();
        boolean disableButton = !newPasswordRetype.equals(newPassword);
        labelCheckSimilar.setVisible(disableButton);
        buttonChange.setDisable(disableButton);

    }
}
