public class Compartment {
    private CompartmentSize size;
    private int id;
    public Compartment(int id, CompartmentSize size) {
        this.size = size;
        this.id = id;
    }

    public CompartmentSize getSize() {
        return size;
    }

    public int getId() {
        return id;
    }
}
