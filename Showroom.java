public class Showroom {
    private boolean available;
    
    public Showroom(boolean available) {
        this.available = available;
    }

    public void enter() {
        if (this.available) {
            this.available = false;
        } else {
            System.out.println("Uh oh! Someone tried to enter the room when they weren't supposed to.");
        }
    }

    public void exit() {
        this.available = true;
    }
}
