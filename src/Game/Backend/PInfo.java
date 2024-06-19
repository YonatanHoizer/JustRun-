package Game.Backend;

public class PInfo {
    private int score;
    private int level;
    public PInfo(int score, int level) {
        this.score = score;
        this.level = level;
    }

    public void setScore(int score) { this.score = score; }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "score:  " + score + " " + "level " + level;
    }
}
