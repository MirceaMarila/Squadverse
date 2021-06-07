package information;

public class HistoryInformation {
    String Chemistry, DateTime, Mode, Rating, Score;

    public HistoryInformation(){}

    public HistoryInformation(String chemistry, String dateTime, String mode, String rating, String score) {
        Chemistry = chemistry;
        DateTime = dateTime;
        Mode = mode;
        Rating = rating;
        Score = score;
    }

    public String getChemistry() {
        return Chemistry;
    }

    public void setChemistry(String chemistry) {
        Chemistry = chemistry;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
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

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
