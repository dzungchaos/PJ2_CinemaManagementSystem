package boundary.ManagerHeadTransfer;

import controller.UserController;
import entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class HeadTransferBoundary {
    @FXML
    public TextField fieldReceiveUser;
    @FXML
    public TextField fieldUserName;
    @FXML
    public PasswordField fieldPassword;
    @FXML
    public Button buttonOK;
    @FXML
    public Button buttonExit;

    private UserController users = new UserController();

    private User currentUser;

    public void initData(User user) {
        currentUser = user;
    }

    public void doTransfer(ActionEvent event) {
        String receiveUser = fieldReceiveUser.getText();
        String userName = fieldUserName.getText();
        String password = fieldPassword.getText();

        if (!users.checkUserExist(receiveUser)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Người dùng không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText("Không tồn tại người dùng này, vui lòng kiểm tra lại");
            alert.show();
            return;
        }

        User selectedUser = users.getUser(receiveUser);
        if (!selectedUser.getUsers_permission().equals("ADMIN")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Người dùng không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText(selectedUser.getUsers_userName() + " không phải quản trị viên, vui lòng kiểm tra lại");
            alert.show();
            return;
        }

        if (!(userName.equals(currentUser.getUsers_userName()) && password.equals(currentUser.getUsers_password()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông tin đăng nhập không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng kiểm tra lại tên đăng nhập và mật khẩu của bạn");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("XÁC NHẬN NHƯỢNG QUYỀN!");
        alert.setTitle(null);
        alert.setContentText("Bạn vừa chọn " + selectedUser.getUsers_userName() + " để nhượng quyền chủ rạp. Nếu điều này được thực hiện, bạn sẽ trở thành một quản trị viên. Bạn có chắc chắn không?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            users.changePermissionUser(selectedUser, "HEAD");
            users.changePermissionUser(currentUser, "ADMIN");
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("NHƯỢNG QUYỀN THÀNH CÔNG!");
            alertSuccess.setTitle(null);
            alertSuccess.setContentText("Bạn đã nhượng quyền thành công. Hệ thống sẽ tự tắt và bạn sẽ phải đăng nhập lại");
            alertSuccess.showAndWait();
            Platform.exit();
        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
