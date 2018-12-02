package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name) {
            player1Score += 1;
        } else if (playerName == player2Name) {
            player2Score += 1;
        } else {
            throw new IllegalArgumentException("No player " + playerName);
        }
    }

    public String getScore() {
        String score = "";

        if (player1Score == player2Score) {
            score = equalScore();
        } else if (player1Score >= 4 || player2Score >= 4) {
            int scoreDifference = player1Score - player2Score;

            score = winOrAdvantage(scoreDifference);       
        } else {
            score = calculateScore(player1Score) + "-" + calculateScore(player2Score);
        }
        return score;
    }

    private String winOrAdvantage(int difference) {
        if (difference == 1){
            return "Advantage " + player1Name;  
        } else if (difference == -1) {
            return "Advantage " + player2Name;
        } else if (difference >= 2) {
            return "Win for " + player1Name;
        } else {
            return "Win for " + player2Name;
        }    
    }

    private String equalScore() {
        switch (player1Score) {
        case 0:
            return "Love-All";
        case 1:
            return "Fifteen-All";
        case 2:
            return "Thirty-All";
        case 3:
            return "Forty-All";
        default:
            return "Deuce";
        }
    }

    private String calculateScore(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                throw new IllegalArgumentException("Invalid score");    
            }
    }
}