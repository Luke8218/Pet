
/*
 * name: The name the user enters for their pet
 * age: How old the pet is in days (starts from 0)
 * food: How hungry the pet is (100 = full, 0 = hungry)
 * awakeness: How awake the pet is (100 = awake, 0 = asleep)
 * exercise: How exercised the pet is (100 = well exercised, 0 = needs exercise)
 */

public class Pet {

    public String name;
    public int age;
    public int food;
    public int awakeness;
    public int exercise;

    public Pet(String name) {
        this.name = name;
        this.age = 0;
        this.food = 100;
        this.awakeness = 100;
        this.exercise = 100;
    }

    // Increases Pet's food value by 10. Capped at 100 to avoid over feeding
    public void feed() {
        food = Math.min(food + 10, 100);
    }

    // Increases Pet's awakeness value by 30. Capped at 100 to avoid over sleeping
    public void sleep() {
        awakeness = Math.min(awakeness + 30, 100);
    }

    // Increases Pet's exercise value by 30. Capped at 100 to avoid over exercising
    public void exercise() {
        exercise = Math.min(exercise + 30, 100);
    }

    /*
     * Depending on the values of food, awakeness, and exercise it will
     * return the appropriate image name for the pet
     * 
     * If multiple values are below the normal levels, the following priority is given: 
     *      awakeness -> food -> exercise
     */
    public String getPetImageName() {
        if (awakeness <= 20) {
            return "pet-tired.png";
        }

        if (food <= 20) {
            return "pet-hungry.png";
        }

        if (exercise <= 20) {
            return "pet-bored.png";
        }

        return "pet-happy.png";
    }
}
