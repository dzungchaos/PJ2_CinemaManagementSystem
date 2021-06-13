package boundary.Pay;

import controller.BankAccountController;
import entity.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void initialize() {
        bankAccounts = new BankAccountController();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        bankAccounts.clearData();
        Stage stage = (Stage) buttonCANCEL.getScene().getWindow();
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
            alert.show();
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
