package controller;

import entity.Statistic;
import entity.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    private ObservableList<Statistic> statistics;
    private ObservableList<Statistic> listStatisticByPurchaseDate;

    public StatisticController() {
        statistics = FXCollections.observableArrayList();
        listStatisticByPurchaseDate = FXCollections.observableArrayList();
    }

    public ObservableList<Statistic> getListStatisticByPurchaseDate() {
        return listStatisticByPurchaseDate;
    }

    public void loadStatisticByDate(String purchasedDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PJ2_CinemaDB;user=sa;password=1234");

            String sql = "SELECT\ttickets_movies_name AS movies_name, COUNT(tickets_id) AS viewCount, COUNT(tickets_id) * 40000 AS turnover\n" +
                    "FROM\tPJ2_tickets\n" +
                    "WHERE\ttickets_purchasedDate = ?\n" +
                    "GROUP BY tickets_movies_name";
            // 15-06-2021
            statement = connection.prepareCall(sql);
            statement.setString(1, purchasedDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Statistic statistic = new Statistic(resultSet.getString("movies_name"),
                        resultSet.getInt("viewCount"),
                        resultSet.getInt("turnover"));
                listStatisticByPurchaseDate.add(statistic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void listStatisticByDate(String purchasedDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PJ2_CinemaDB;user=sa;password=1234");

            String sql = "SELECT\ttickets_movies_name AS movies_name, COUNT(tickets_id) AS viewCount, COUNT(tickets_id) * 40000 AS turnover\n" +
                    "FROM\tPJ2_tickets\n" +
                    "WHERE\ttickets_purchasedDate = ?\n" +
                    "GROUP BY tickets_movies_name";
            // 15-06-2021
            statement = connection.prepareCall(sql);
            statement.setString(1, purchasedDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Statistic statistic = new Statistic(resultSet.getString("movies_name"),
                        resultSet.getInt("viewCount"),
                        resultSet.getInt("turnover"));
                // listStatisticByPurchaseDate.add(statistic);
                System.out.println("Phim: " + statistic.getMovies_name());
                System.out.println("Lượt xem: " + statistic.getViewCount().toString());
                System.out.println("Doanh thu: " + statistic.getTurnover().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer getTotalTurnover(ObservableList<Statistic> statisticsList) {
        Integer totalTurnover = 0;

        for (Statistic statistic : statisticsList) {
            totalTurnover += statistic.getTurnover();
        }

        return totalTurnover;
    }
}
