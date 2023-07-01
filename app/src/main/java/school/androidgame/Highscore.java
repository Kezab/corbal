package school.androidgame;

import java.util.Date;

public class Highscore {

    private int score;
    private Date timestamp;
    private int runtime;

    public Highscore(int score, int runtime) {
        this.score = score;
        this.runtime = runtime;
        this.timestamp = new Date();
    }


    public Highscore(int score, int runtime, Date timestamp) {
        this.score = score;
        this.runtime = runtime;
        this.timestamp = timestamp;
    }


    public int getRuntime() {
        return runtime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getScore() {
        return score;
    }
}
