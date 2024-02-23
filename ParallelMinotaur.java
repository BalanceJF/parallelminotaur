import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelMinotaur extends Thread {
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

        int numEntries = 0;
        int nextVictim;
        boolean problemFinished = false;
        while (!problemFinished) {
            nextVictim = rand.nextInt(numVictims);
            System.out.println("Victim " + (nextVictim + 1) + " has entered the labyrinth!");
            if (labyrinthLock.tryLock()) {
                try {
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

    public static boolean validateAnswer(Victim[] victims) {
        for (int i = 0; i < numVictims; i++) {
            if (!victims[i].getVisited()) {
                return false;
            }
        }

        return true;
    }
}