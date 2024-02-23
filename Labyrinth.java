public class Labyrinth {
    private boolean cupcake;
    
    public Labyrinth(boolean cupcake) {
        this.cupcake = cupcake;
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
