import java.util.Date;

/*
 * date: The date that the interaction was performed (always current date when instantiated)
 * type: Type of interaction (FEED, SLEEP, EXERCISE) to correspond with Pet's characteristics
 */
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