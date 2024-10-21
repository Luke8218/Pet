import java.util.Date;

public class Interaction {

    public Date date;
    public Type type;

    public Interaction(Type type) {
        this.date = new Date();
        this.type = type;
    }

    public enum Type {
        FEED,
        SLEEP,
        EXERCISE
    }

}


