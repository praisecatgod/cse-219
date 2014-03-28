/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package account_data;

import java.io.Serializable;

/**
 *
 * @author yv
 */
public class Score <T extends Comparable<T>> implements Comparable<Score<T>>, Serializable{
    
    private String type;
    private int timesPlayed;
    private int highScore;
    private int fastTime;
    
    public Score(String initType, int initPlayed,
                        int initScore, int initTime)
    {
        type = initType;
        timesPlayed = initPlayed;
        highScore = initScore;
        fastTime = initTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getFastTime() {
        return fastTime;
    }

    public void setFastTime(int fastTime) {
        this.fastTime = fastTime;
    }

    @Override
    public int compareTo(Score<T> o) {
        return type.compareToIgnoreCase(o.type);  
    }
    
        public boolean equals(Object scoreAsObject)
    {
        if (scoreAsObject instanceof Score)
        {
            Score s = (Score)scoreAsObject;
            return type.equals(s.type);
        }
        return false;
    }
    
    
}
