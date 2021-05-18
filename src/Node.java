/**
 * Einfache Implementierung einer Node für einfach verkettete Listen mit generischem Datentyp.
 *
 * @param <T> Der Datentyp der Node.
 * @author akubf
 */
public class Node<T extends Comparable<T>> {
    public T data; // Wert der Node
    public Node<T> next; // Pointer auf die nächste Node

    /**
     * Erzeugt eine neue Node.
     *
     * @param data Der Wert des neuen Elements.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
