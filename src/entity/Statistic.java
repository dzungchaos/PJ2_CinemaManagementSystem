package entity;

public class Statistic {
    private String purchasedDate;
    private String movies_name;
    private Integer viewCount; // Số vé được mua
    private Integer turnover; // doanh số

    public Statistic() {

    }

    public Statistic(String purchasedDate, String movies_name, Integer viewCount, Integer turnover) {
        this.purchasedDate = purchasedDate;
        this.movies_name = movies_name;
        this.viewCount = viewCount;
        this.turnover = turnover;
    }

    public String getMovies_name() {
        return movies_name;
    }

    public void setMovies_name(String movies_name) {
        this.movies_name = movies_name;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "purchasedDate='" + purchasedDate + '\'' +
                ", movies_name='" + movies_name + '\'' +
                ", viewCount=" + viewCount +
                ", turnover=" + turnover +
                '}';
    }
}
