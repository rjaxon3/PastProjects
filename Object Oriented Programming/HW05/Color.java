/**
 * @author Rhea Jaxon
 * @version 1
 */
public enum Color {
    RED, GREEN, BLUE, YELLOW, MAGENTA, CYAN, BLACK, DARKGRAY,
    LIGHTGRAY, BROWN, BLONDE, WHITE;
    /**
     * @return String format of the instance created in this class
     * in lower case
     */
    @Override
    public String toString() {
        String s = super.toString();
        return s.toLowerCase();
    }
}