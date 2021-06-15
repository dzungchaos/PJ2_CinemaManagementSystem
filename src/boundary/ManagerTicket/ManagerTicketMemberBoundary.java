package boundary.ManagerTicket;

import controller.TicketController;
import entity.Ticket;
import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class ManagerTicketMemberBoundary {
    @FXML
    public GridPane ticketPanel;
    @FXML
    public TextField fieldFindTicket;

    private User currentUser;

    private TicketController tickets = new TicketController();

    @FXML
    public TableView<Ticket> tableViewTicket;

    public void initData(User user) {
        currentUser = user;
        System.out.println(currentUser.getUsers_name());
        System.out.println(currentUser.getUsers_permission());
        tableViewTicket.setItems(tickets.getListTicketsByUserID(currentUser.getUsers_id()));
    }

}
