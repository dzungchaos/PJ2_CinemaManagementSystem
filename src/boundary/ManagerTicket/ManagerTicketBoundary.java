package boundary.ManagerTicket;

import controller.TicketController;
import entity.Ticket;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class ManagerTicketBoundary {
    @FXML
    public TextField fieldFindTicket;
    @FXML
    public TableView<Ticket> tableViewTicket;
    @FXML
    public GridPane ticketPanel;
    @FXML
    public Button buttonExportTicket;

    private User currentUser;

    private TicketController tickets;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        tickets = new TicketController();
        tableViewTicket.setItems(tickets.getTickets());
    }

    @FXML
    public void doSearchTicket(KeyEvent keyEvent) {
        String movieNamePart = fieldFindTicket.getText();
        tableViewTicket.setItems(tickets.getListTicket(movieNamePart));
    }

    @FXML
    public void doExportTicket(ActionEvent event) {

    }
}
