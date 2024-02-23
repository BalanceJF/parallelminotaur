import java.util.Random;

public class ParallelMinotaur extends Thread {
    private static final int numVictims = 100;

    public static void main(String [] args) throws InterruptedException {
        Victim [] victims = new Victim[numVictims];

        Labyrinth labyrinth = new Labyrinth(true, 0); // Cupcake starts as being there

        for (int i = 0; i < numVictims; i++) {
            victims[i] = new Victim(labyrinth, i == 0, 0);
            victims[i].start();
        }

        Random rand = new Random();

        int nextVictim;
        do {
            nextVictim = rand.nextInt(numVictims);
        } while (!victims[nextVictim].doLabyrinth());

    }
}