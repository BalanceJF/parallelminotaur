import java.util.concurrent.locks.ReentrantLock;

public class Victim extends Thread {
    private Labyrinth labyrinth;
    private boolean visited = false; // We will be honest and say we didn't go yet.

    // One of our guys has tasked himself with eating every single cupcake.
    // It may be more logical to flip it and have him replenish all cupcakes,
    // which is easily doable, but this is way funnier.
    private boolean isHungryHungryHippo;
    private int timesToggled; // Helps keep track of both types of people


    public Victim(Labyrinth labyrinth, boolean isHungryHungryHippo, int timesToggled) {
        this.labyrinth = labyrinth;
        this.isHungryHungryHippo = isHungryHungryHippo;
        this.timesToggled = timesToggled;
    }

    // Does the labyrinth, returning true if we are ready to pronounce we have found the answer.
    public boolean doLabyrinth(ReentrantLock labyrinthLock) {

        this.visited = true;

        // Hungry hungry hippo has his own strategy. We need to now enact it.
        if (this.isHungryHungryHippo) {
            if (this.labyrinth.getCupcake()) {
                this.labyrinth.setCupcake(false); // nom
                this.timesToggled++;
                return (timesToggled == 99);
            }
        } else {
            if (timesToggled == 0 && !this.labyrinth.getCupcake()) {
                this.labyrinth.setCupcake(true); // help HHH undo nom
            }
            return false;
        }

        // I'm paranoid
        return false;
    }

    public boolean getVisited() {
        return this.visited;
    }
}
