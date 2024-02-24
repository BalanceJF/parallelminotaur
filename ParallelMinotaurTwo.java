import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelMinotaurTwo extends Thread {
    private static final int numVictims = 100;
    private static final int numIterations = 100; // Number of times people went in and saw the thing
    private static LinkedBlockingQueue<VictimTwo> victimQueue;
    private static boolean done;

    public void run() {
        try {
            while(!done) {
                VictimTwo newVictim = victimQueue.take();
                newVictim.doYoThang();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        VictimTwo [] victims = new VictimTwo[numVictims];

        Showroom room = new Showroom(true); // Room starts open

        for (int i = 0; i < numVictims; i++) {
            victims[i] = new VictimTwo(room, false, i + 1);
            victims[i].start();
        }

        Random rand = new Random();

        victimQueue = new LinkedBlockingQueue<>(MAX_PRIORITY); 
        ParallelMinotaurTwo guyWhoWorksTheDoor = new ParallelMinotaurTwo();
        guyWhoWorksTheDoor.start();
        for (int i = 0; i < numIterations; i++) {
            int nextVictim = rand.nextInt(numVictims);
            victimQueue.put(victims[nextVictim]);
        }

        while(!victimQueue.isEmpty()) {
            int Oh = 1;
        }

        done = true;
    }
}