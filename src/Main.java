import controller.*;
import entity.BankAccount;
import entity.Statistic;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("boundary/Login/LoginBoundary.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

//        UserController userController = new UserController();
//        userController.listUsers();

//        BankAccountController bankAccountController = new BankAccountController();
//        bankAccountController.listBankAccounts();

//        CinemaController cinemaController = new CinemaController();
//        cinemaController.listCinema();

//        MovieController movieController = new MovieController();
//        movieController.listMovies();

//        TicketController ticketController = new TicketController();
//        // ticketController.listTickets();
//        ObservableList<String> testString = ticketController.getListBoughtSeatByShowtimeID(5);
//        System.out.println("Các ghế đã đặt: " + testString.toString());
//
//        ShowtimeController showtimeController = new ShowtimeController();
//        showtimeController.listShowtimes();

//        StatisticController statisticController = new StatisticController();
//        statisticController.loadStatistic("15-06-2021");
//        statisticController.listStatistic("15-06-2021");

//        StatisticController statisticController = new StatisticController();
//        statisticController.loadStatisticByDate("15-06-2021");
//        ObservableList<Statistic> statistics = statisticController.getListStatisticByPurchaseDate();
//        System.out.println(statistics);
//        statisticController.listStatisticByDate("15-06-2021");
    }
}
