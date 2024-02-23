public class Labyrinth {
    private boolean cupcake;
    private int currentVictim;
    
    public Labyrinth(boolean cupcake, int currentVictim) {
        this.cupcake = cupcake;
        this.currentVictim = currentVictim;
    }

    // The problem allows us to eat it or respawn it without eating,
    // thus it's really just a toggle.
    public synchronized void setCupcake(boolean cupcake) {
        this.cupcake = cupcake;
    }

    public synchronized boolean getCupcake() {
        return this.cupcake;
    }
}