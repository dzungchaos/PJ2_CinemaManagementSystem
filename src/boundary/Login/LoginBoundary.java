package boundary.Login;

import boundary.Homepage.HomepageAdminBoundary;
import boundary.Homepage.HomepageMemberBoundary;
import boundary.Homepage.HomepageStaffBoundary;
import boundary.Pay.PurchaseTicketBoundary;
import controller.UserController;
import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginBoundary {
    private final static String HEAD_UI = "/boundary/Homepage/HomepageHeadBoundary.fxml";
    private final static String HEAD_TITTLE = "Chào mừng Chủ Rạp";
    private final static String ADMIN_UI = "/boundary/Homepage/HomepageAdminBoundary.fxml";
    private final static String ADMIN_TITTLE = "Chào mừng Quản trị viên";
    private final static String STAFF_UI = "/boundary/Homepage/HomepageStaffBoundary.fxml";
    private final static String STAFF_TITTLE = "Chào mừng Nhân viên";
    private final static String MEMBER_UI = "/boundary/Homepage/HomepageMemberBoundary.fxml";
    private final static String MEMBER_TITTLE = "Chào mừng thành viên";
    private final static String SIGNUP_UI = "/boundary/Login/SignUpDialog.fxml";
    private final static String SIGNUP_TITTLE = "Đăng ký tài khoản mới";

    public User loginUser;

    @FXML
    public TextField fieldUserName;

    @FXML
    public PasswordField fieldPassword;

    @FXML
    public Button buttonSignUp;

    @FXML
    public Button buttonSignIn;

    @FXML
    public Button buttonExit;

    public UserController users;

    @FXML
    public GridPane windowLogin;

    public LoginBoundary() {

    }

    public void initialize() {
        users = new UserController();
    }


    public void loadWindow(String loc, String tittle) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(loc));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(tittle);
        stage.setScene(new Scene(parent));
        switch (loc) {
            case ADMIN_UI:
                HomepageAdminBoundary boundaryAdmin = loader.getController();
                boundaryAdmin.initData(loginUser);
                break;
            case STAFF_UI:
                HomepageStaffBoundary boundaryStaff = loader.getController();
                boundaryStaff.initData(loginUser);
                break;
            case MEMBER_UI:
                HomepageMemberBoundary boundaryMember = loader.getController();
                boundaryMember.initData(loginUser);
                break;
            default:
                break;
        }
//        PurchaseTicketBoundary boundary = loader.getController();
//        boundary.initData(loginUser);
        stage.initOwner((Stage) buttonSignIn.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void closeWindow() {
        users.clearData();
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doSignUp() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(SIGNUP_UI));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(SIGNUP_TITTLE);
        stage.setScene(new Scene(parent));
        stage.initOwner((Stage) buttonSignIn.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void doLogin() throws IOException {
        String userName = fieldUserName.getText();
        String password = fieldPassword.getText();
        fieldUserName.clear();
        fieldPassword.clear();

        if (!users.checkLoginInfo(userName, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sai thông tin đăng nhập!");
            alert.setHeaderText(null);
            alert.setContentText("Sai tên đăng nhập hoặc mật khẩu, vui lòng nhập lại thông tin để đăng nhập");
            alert.show();
            return;
        }

        if (!users.checkUserUnlock(userName, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tài khoản đã bị khoá!");
            alert.setHeaderText(null);
            alert.setContentText("Tài khoản này đã bị khoá. Vui lòng liên hệ với quản trị viên để biết thêm thông tin chi tiết");
            alert.show();
            return;
        }

        loginUser = users.getUser(userName);
        loginUser.setUsers_isOnline(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đăng nhập thành công");
        alert.setHeaderText(null);
        switch (loginUser.getUsers_permission()) {
            case "HEAD":
                alert.setContentText("Đăng nhập thành công với vai trò Chủ rạp");
                alert.showAndWait();
                loadWindow(HEAD_UI, HEAD_TITTLE);
                break;
            case "ADMIN":
                alert.setContentText("Đăng nhập thành công với vai trò QTV");
                alert.showAndWait();
                loadWindow(ADMIN_UI, ADMIN_TITTLE);
                break;
            case "STAFF":
                alert.setContentText("Đăng nhập thành công với vai trò nhân viên");
                alert.showAndWait();
                loadWindow(STAFF_UI, STAFF_TITTLE);
                break;
            case "MEMBER":
                alert.setContentText("Đăng nhập thành công với vai trò thành viên");
                alert.showAndWait();
                loadWindow(MEMBER_UI, MEMBER_TITTLE);
                break;
            default:
                break;
        }

    }

    public User getLoginUser() {
        return loginUser;
    }
}
