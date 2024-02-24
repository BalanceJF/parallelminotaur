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

    // Also known as Using Their Eyes (Hungry Hungry Hippo knows this very well)
    public synchronized boolean getCupcake() {
        return this.cupcake;
    }
}
