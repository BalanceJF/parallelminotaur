import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelMinotaurTwo extends Thread {
    private static final int numVictims = 100;
    private static final int numIterations = 100; // Number of times people went in and saw the thing (you could do input and while loop but this is way easier)
    private static LinkedBlockingQueue<VictimTwo> victimQueue;
    private static boolean done;

    // The guy working the door will just kinda keep letting people in as needed.
    // The queue should keep it fair and locking.
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

        // Let's spawn the guy who works the door to let people in when it's ready.
        ParallelMinotaurTwo guyWhoWorksTheDoor = new ParallelMinotaurTwo();
        guyWhoWorksTheDoor.start();

        // People spontaneously decide they want in
        for (int i = 0; i < numIterations; i++) {
            int nextVictim = rand.nextInt(numVictims);
            victimQueue.put(victims[nextVictim]);
        }

        // I'm pretty sure there's a better way to wait for the queue to empty,
        // but this is the funny and cool way so it's preferable.
        while(!victimQueue.isEmpty()) {
            int Oh = 1;
        }

        // Let the guy working the door know we're all good now and he can stop.
        // Technically we need to do this here since he doesn't know if it's just empty
        // 'cuz he went too fast.
        done = true;
    }
}