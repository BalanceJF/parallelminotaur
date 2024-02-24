import java.util.concurrent.locks.ReentrantLock;

public class Victim extends Thread {
    private Labyrinth labyrinth;
    private boolean visited = false; // We will be honest and say we didn't go yet.

    // One of our guys has tasked himself with eating every single cupcake.
    // It may be more logical to flip it and have him replenish all cupcakes,
    // which is easily doable, but this is way funnier.
    private boolean isHungryHungryHippo;
    private int timesToggled; // Both strategies need to know how much they've eaten/replenished.
    private int numVictims; // HHH would like to know how many victims there are to count for


    public Victim(Labyrinth labyrinth, boolean isHungryHungryHippo, int timesToggled, int numVictims) {
        this.labyrinth = labyrinth;
        this.isHungryHungryHippo = isHungryHungryHippo;
        this.timesToggled = timesToggled;
        this.numVictims = numVictims;
    }

    // Does the labyrinth, returning true if we are ready to pronounce we have found the answer.
    public boolean doLabyrinth(ReentrantLock labyrinthLock) {

        this.visited = true;

        // Hungry hungry hippo has his own strategy of keeping track
        // of how many cupcakes he has eaten. We need to now enact it.
        if (this.isHungryHungryHippo) {
            if (this.labyrinth.getCupcake()) {
                this.labyrinth.setCupcake(false); // nom
                this.timesToggled++;
                return (timesToggled == this.numVictims);
            }
        } else {
            // Opposite strategy: replenish them cupcakes once
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

    // Debug to make sure my strategy is actually being employed
    public int getTimesToggled() {
        return this.timesToggled;
    }
}
