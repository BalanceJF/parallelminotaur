public class VictimTwo extends Thread {
    private Showroom room;
    private boolean visited; // We will be honest and say we didn't go yet.


    public VictimTwo(Showroom room, boolean visited) {
        this.room = room;
        this.visited = visited;
    }

    
}
