import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelMinotaur {
    private static final int numVictims = 100;

    public static void main(String [] args) throws InterruptedException {
        Victim [] victims = new Victim[numVictims];

        Labyrinth labyrinth = new Labyrinth(true); // Cupcake starts as being there

        for (int i = 0; i < numVictims; i++) {
            victims[i] = new Victim(labyrinth, i == 0, 0, numVictims);
            victims[i].start();
        }

        Random rand = new Random();

        ReentrantLock labyrinthLock = new ReentrantLock();

        int numEntries = 0; // Number of times we've had to go in
        int nextVictim; // >:)
        boolean problemFinished = false;
        // Let's let people into the labyrinth one by one and see what they do.
        while (!problemFinished) {
            nextVictim = rand.nextInt(numVictims);
            System.out.println("Victim " + (nextVictim + 1) + " has entered the labyrinth!");
            // Keep the doors nice and locked until the previous person leaves
            if (labyrinthLock.tryLock()) {
                try {
                    labyrinthLock.lock();
                    problemFinished = victims[nextVictim].doLabyrinth(labyrinthLock);
                    numEntries++;
                } finally {
                    labyrinthLock.unlock();
                }
            }
        }

        System.out.println("We believe everyone has been in the labyrinth! And it only took " + numEntries + " entries.");

        if (validateAnswer(victims)) {
            System.out.println("Woohoo! We were right!");
        } else {
            System.out.println("Aw man! We were wrong.");
        }
    }

    // The victims are very honest about whether they've visited, so we'll check to be 100% sure.
    public static boolean validateAnswer(Victim[] victims) {
        for (int i = 0; i < numVictims; i++) {
            if (!victims[i].getVisited()) {
                return false;
            }
        }

        return true;
    }
}