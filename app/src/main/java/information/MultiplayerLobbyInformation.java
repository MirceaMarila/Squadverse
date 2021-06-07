package information;

public class MultiplayerLobbyInformation {

    String Host, Mode, Guest, Host_rating, Host_chemistry, Guest_rating, Guest_chemistry, Host_score, Guest_score, Host_best_att, Host_best_mid, Host_best_def, Guest_best_att, Guest_best_mid, Guest_best_def, Winner;

    public MultiplayerLobbyInformation(){}

    public MultiplayerLobbyInformation(String host, String mode, String guest, String host_rating, String host_chemistry, String guest_rating, String guest_chemistry, String host_score, String guest_score, String host_best_att, String host_best_mid, String host_best_def, String guest_best_att, String guest_best_mid, String guest_best_def, String winner) {
        Host = host;
        Mode = mode;
        Guest = guest;
        Host_rating = host_rating;
        Host_chemistry = host_chemistry;
        Guest_rating = guest_rating;
        Guest_chemistry = guest_chemistry;
        Host_score = host_score;
        Guest_score = guest_score;
        Host_best_att = host_best_att;
        Host_best_mid = host_best_mid;
        Host_best_def = host_best_def;
        Guest_best_att = guest_best_att;
        Guest_best_mid = guest_best_mid;
        Guest_best_def = guest_best_def;
        Winner = winner;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getGuest() {
        return Guest;
    }

    public void setGuest(String guest) {
        Guest = guest;
    }

    public String getHost_rating() {
        return Host_rating;
    }

    public void setHost_rating(String host_rating) {
        Host_rating = host_rating;
    }

    public String getHost_chemistry() {
        return Host_chemistry;
    }

    public void setHost_chemistry(String host_chemistry) {
        Host_chemistry = host_chemistry;
    }

    public String getGuest_rating() {
        return Guest_rating;
    }

    public void setGuest_rating(String guest_rating) {
        Guest_rating = guest_rating;
    }

    public String getGuest_chemistry() {
        return Guest_chemistry;
    }

    public void setGuest_chemistry(String guest_chemistry) {
        Guest_chemistry = guest_chemistry;
    }

    public String getHost_score() {
        return Host_score;
    }

    public void setHost_score(String host_score) {
        Host_score = host_score;
    }

    public String getGuest_score() {
        return Guest_score;
    }

    public void setGuest_score(String guest_score) {
        Guest_score = guest_score;
    }

    public String getHost_best_att() {
        return Host_best_att;
    }

    public void setHost_best_att(String host_best_att) {
        Host_best_att = host_best_att;
    }

    public String getHost_best_mid() {
        return Host_best_mid;
    }

    public void setHost_best_mid(String host_best_mid) {
        Host_best_mid = host_best_mid;
    }

    public String getHost_best_def() {
        return Host_best_def;
    }

    public void setHost_best_def(String host_best_def) {
        Host_best_def = host_best_def;
    }

    public String getGuest_best_att() {
        return Guest_best_att;
    }

    public void setGuest_best_att(String guest_best_att) {
        Guest_best_att = guest_best_att;
    }

    public String getGuest_best_mid() {
        return Guest_best_mid;
    }

    public void setGuest_best_mid(String guest_best_mid) {
        Guest_best_mid = guest_best_mid;
    }

    public String getGuest_best_def() {
        return Guest_best_def;
    }

    public void setGuest_best_def(String guest_best_def) {
        Guest_best_def = guest_best_def;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }
}
