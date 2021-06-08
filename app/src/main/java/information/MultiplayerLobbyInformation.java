package information;

import android.media.Rating;

public class MultiplayerLobbyInformation {

    String Mode, Rating, Chemistry, Score, Winner, Opponent;

    public MultiplayerLobbyInformation(){}

    public MultiplayerLobbyInformation(String host, String mode, String guest, String rating, String chemistry, String score, String winner, String opponent) {
        Mode = mode;
        Rating = rating;
        Chemistry = chemistry;
        Score = score;
        Winner = winner;
        Opponent = opponent;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getChemistry() {
        return Chemistry;
    }

    public void setChemistry(String chemistry) {
        Chemistry = chemistry;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getOpponent() {
        return Opponent;
    }

    public void setOpponent(String opponent) {
        Opponent = opponent;
    }
}
