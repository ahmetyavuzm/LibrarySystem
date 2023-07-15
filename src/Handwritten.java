public class Handwritten extends Book{
    public Handwritten() {
        super();
    }

    @Override
    public String toString() {
        return String.format("Handwritten [id: %d]", this.getId());
    }
}
