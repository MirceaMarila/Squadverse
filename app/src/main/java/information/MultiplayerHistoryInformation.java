package information;

public class MultiplayerHistoryInformation {

    String Your_score, Opponent_score, Winner, Mode, DateTime, Opponent_name;

    public MultiplayerHistoryInformation(){}

    public MultiplayerHistoryInformation(String yor_score, String opponent_score, String winner, String mode, String dateTime, String opponent_name) {
        Your_score = yor_score;
        Opponent_score = opponent_score;
        Winner = winner;
        Mode = mode;
        DateTime = dateTime;
        Opponent_name = opponent_name;
    }

    public String getYour_score() {
        return Your_score;
    }

    public void setYor_score(String yor_score) {
        Your_score = yor_score;
    }

    public String getOpponent_score() {
        return Opponent_score;
    }

    public void setOpponent_score(String opponent_score) {
        Opponent_score = opponent_score;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getOpponent_name() {
        return Opponent_name;
    }

    public void setOpponent_name(String opponent_name) {
        Opponent_name = opponent_name;
    }
}
