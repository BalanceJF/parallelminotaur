import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelMinotaurTwo extends Thread {
    private static final int numVictims = 100;
    private static final int numIterations = 1000; // Number of times people went in and saw the thing

    public static void main(String [] args) throws InterruptedException {
        VictimTwo [] victims = new VictimTwo[numVictims];

        Showroom room = new Showroom(true); // Room starts open

        for (int i = 0; i < numVictims; i++) {
            victims[i] = new VictimTwo(room, false);
            victims[i].start();
        }

        Random rand = new Random();

        LinkedBlockingQueue<VictimTwo> victimQueue = new LinkedBlockingQueue<>(MAX_PRIORITY); 

        for (int i = 0; i < numIterations; i++) {
            int nextVictim = rand.nextInt(numVictims);
            victimQueue.put(victims[nextVictim]);
        }


        boolean problemFinished = false;
        while (!problemFinished) {
            nextVictim = rand.nextInt(numVictims);
            System.out.println("Victim " + (nextVictim + 1) + " has entered the labyrinth!");
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

    public static boolean validateAnswer(Victim[] victims) {
        for (int i = 0; i < numVictims; i++) {
            if (!victims[i].getVisited()) {
                return false;
            }
        }

        return true;
    }
}