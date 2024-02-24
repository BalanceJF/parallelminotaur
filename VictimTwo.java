import java.util.Random;

public class VictimTwo extends Thread {
    private Showroom room;
    private boolean visited; // We will be honest and say we didn't go yet.
    private int id;

    private static final int maxWaitingTime = 3000;

    public VictimTwo(Showroom room, boolean visited, int id) {
        this.room = room;
        this.visited = visited;
        this.id = id;
    }

    // The problem doesn't really define what exactly someone "does" in the room, 
    // so I'm just gonna have this thing wait for a bit as it admires the vase.
    public void doYoThang() throws InterruptedException {
        System.out.println("Victim " + id + " is entering the room!");
        if (!visited) {
            System.out.println("Ooooh, it's their first time! Lucky rascal!");
            visited = true;
        }
        room.enter();
        Random rand = new Random();
        Thread.sleep(rand.nextInt(maxWaitingTime));
        System.out.println("Victim " + id + " is exiting the room.");
        room.exit();
    }
    
}
