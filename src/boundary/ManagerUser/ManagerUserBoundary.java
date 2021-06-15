package boundary.ManagerUser;

import boundary.ManagerCinema.UpdateCinemaBoundary;
import controller.UserController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerUserBoundary {
    private UserController users;

    private User currentUser;

    @FXML
    public Button buttonShowDetail;
    @FXML
    public Button buttonLock;
    @FXML
    public Button buttonUnlock;
    @FXML
    public TextField fieldFindUser;
    @FXML
    public TableView<User> tableViewUser;

    public void initData(User user) {
        currentUser = user;
        System.out.println(currentUser.getUsers_userName());
        System.out.println(currentUser.getUsers_name());
        System.out.println(currentUser.getUsers_permission());
    }

    public void initialize() {
        users = new UserController();
        tableViewUser.setItems(users.getUsers());
    }

    @FXML
    public void doSearchUser(KeyEvent keyEvent) {
        String userPart = fieldFindUser.getText();
        tableViewUser.setItems(users.getListUserByName(userPart));
    }

    @FXML
    public void doShowUserDetail(ActionEvent event) throws IOException {
        User selectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn người dùng");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn người dùng, hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
            alert.show();
            return;
        }

        if (currentUser.getUsers_permission().equals("HEAD")) {
            if (selectedUser.getUsers_permission().equals("HEAD")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        }

        if (currentUser.getUsers_permission().equals("ADMIN")) {
            if (selectedUser.getUsers_permission().equals("HEAD") || selectedUser.getUsers_permission().equals("ADMIN")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerUser/UserDetailBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("XEM THÔNG TIN NGƯỜI DÙNG");
        stage.setScene(new Scene(parent));
        UserDetailBoundary boundary = loader.getController();
        boundary.initData(selectedUser, currentUser);
        stage.initOwner((Stage) buttonShowDetail.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
        tableViewUser.setItems(users.getUsers());
    }

    @FXML
    public void doLockUser(ActionEvent event) {
        User selectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn người dùng");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn người dùng, hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
            alert.show();
            return;
        }

        if (currentUser.getUsers_permission().equals("ADMIN")) {
            if (selectedUser.getUsers_permission().equals("HEAD") || selectedUser.getUsers_permission().equals("ADMIN")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        } else if (currentUser.getUsers_permission().equals("HEAD")) {
            if (selectedUser.getUsers_permission().equals("HEAD")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        }

        if (!selectedUser.getUsers_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Người dùng đã bị khoá");
            alert.setHeaderText(null);
            alert.setContentText("Người dùng bạn chọn đã bị khoá, hãy chọn một người dùng khác để tiến hành khoá người dùng");
            alert.show();
            return;
        }

        users.lockUser(selectedUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Khoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã khoá người dùng được chọn! Người dùng sẽ không thể đăng nhập bằng tài khoản này");
        alert.show();
        users.clearData();
        users.loadUsers();
        tableViewUser.setItems(users.getUsers());
    }

    @FXML
    public void doUnlockUser(ActionEvent event) {
        User selectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn người dùng");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn người dùng, hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
            alert.show();
            return;
        }

        if (currentUser.getUsers_permission().equals("ADMIN")) {
            if (selectedUser.getUsers_permission().equals("HEAD") || selectedUser.getUsers_permission().equals("ADMIN")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        } else if (currentUser.getUsers_permission().equals("HEAD")) {
            if (selectedUser.getUsers_permission().equals("HEAD")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Người dùng không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Người dùng có quyền hạn bằng hoặc cao hơn bạn! Hãy chọn một người dùng để tiến hành xem chi tiết, phân quyền, mở khoá hoặc khoá người dùng");
                alert.show();
                return;
            }
        }

        if (selectedUser.getUsers_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Người dùng đã được mở khoá");
            alert.setHeaderText(null);
            alert.setContentText("Người dùng bạn chọn đã được mở khoá, hãy chọn một người dùng khác để tiến hành mở khoá người dùng");
            alert.show();
            return;
        }

        users.unlockUser(selectedUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mở hhoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã mở khoá người dùng được chọn! Người dùng sẽ có thể đăng nhập bằng tài khoản này");
        alert.show();
        users.clearData();
        users.loadUsers();
        tableViewUser.setItems(users.getUsers());
    }


}
