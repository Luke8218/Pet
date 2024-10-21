
/*
 * name: The name the user enters for their pet
 * age: How old the pet is in days (starts from 0)
 * food: How hungry the pet is (100 = full, 0 = hungry)
 * awakeness: How awake the pet is (100 = awake, 0 = asleep)
 * exercise: How exercised the pet is (100 = well exercised, 0 = needs exercise)
 * 
 * 
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

    public void feed() {
        food = Math.min(food + 10, 100);
    }

    public void sleep() {
        awakeness = Math.min(awakeness + 30, 100);
    }

    public void exercise() {
        exercise = Math.min(exercise + 30, 100);
    }

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
