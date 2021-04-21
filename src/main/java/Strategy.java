import java.util.List;

public interface Strategy {
    Shape getShape();
    void setHistory(List<MatchHistory> history);
}