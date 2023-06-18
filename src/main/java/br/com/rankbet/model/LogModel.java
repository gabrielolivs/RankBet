package br.com.rankbet.model;




import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_log")
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "api")
    private String api;

    @Column(name = "data_log")
    private LocalDateTime dataLog;

    @Column(name = "game")
    private String game;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "odd")
    private Float odd;

    @Column(name = "bet_type")
    private String betType;

    @Column(name = "bet")
    private String bet;

    public long getId() {
        return id;
    }

    public String getApi() {
        return api;
    }

    public LocalDateTime getDataLog() {
        return dataLog;
    }

    public String getGame() {
        return game;
    }

    public long getUserId() {
        return userId;
    }

    public Float getOdd() {
        return odd;
    }

    public String getBetType() {
        return betType;
    }

    public String getBet() {
        return bet;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setDataLog(LocalDateTime dataLog) {
        this.dataLog = dataLog;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setOdd(Float odd) {
        this.odd = odd;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }
}
