public class Printed extends Book{
    public Printed() {
        super();
    }

    @Override
    public String toString() {
        return String.format("Printed [id: %d]", this.getId());
    }
}
