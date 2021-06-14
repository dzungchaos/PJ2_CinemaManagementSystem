package boundary.Pay;

import boundary.Login.LoginBoundary;
import controller.BankAccountController;
import entity.BankAccount;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PurchaseTicketBoundary {
    @FXML
    public TextField fieldOwnerName;

    @FXML
    public TextField fieldCardNumber;

    public BankAccountController bankAccounts;

    @FXML
    public Button buttonCANCEL;

    @FXML
    public Button buttonOK;

    private User currentUser;

    public void initialize() {
        bankAccounts = new BankAccountController();

    }

    public void initData(User selectedUser) {
        currentUser = selectedUser;
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonCANCEL.getScene().getWindow();
        if (currentUser != null) {
            System.out.println("Current User ID: " + currentUser.getUsers_id());
            System.out.println("Current User Name:" + currentUser.getUsers_name());
            System.out.println("Current User username: " + currentUser.getUsers_userName());
            System.out.println("Current User permission: " + currentUser.getUsers_permission());
        }
        bankAccounts.clearData();
        stage.close();
    }

    @FXML
    public void checkBankAccountExist(ActionEvent event) {
        String ownerName = fieldOwnerName.getText();
        String cardNumber = fieldCardNumber.getText();

        if (!bankAccounts.checkBankAccountExist(ownerName, cardNumber)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Không tồn tại tài khoản này!");
            alert.setHeaderText(null);
            alert.setContentText("Tài khoản bạn vừa nhập bị sai hoặc không tồn tại, vui lòng nhập lại!");
            alert.show();
            return;
        }

        BankAccount bankAccount = bankAccounts.getBankAccount(ownerName, cardNumber);
        if (bankAccounts.checkBankAccountBalance(bankAccount)) {
            bankAccounts.subtractBankAccountBalance(bankAccount);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thanh toán thành công");
            alert.setHeaderText(null);
            alert.setContentText("Cảm ơn bạn đã sử dụng dịch vụ của rạp chiếu phim Anh Sáu! Chúc bạn xem phim vui vẻ!");
            alert.showAndWait();
            bankAccounts.clearData();
            Stage stage = (Stage) buttonOK.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Không đủ số dư!");
            alert.setHeaderText(null);
            alert.setContentText("Tài khoản bạn vừa nhập không đủ số dư để thanh toán!");
            alert.show();
            return;
        }

    }
}
