import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class List<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
        this.tail = null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean keepGoing = true;
        while(keepGoing){
            String input = in.readLine();
            if (input.equals("e")){
                keepGoing = false;
            } else if (input.equals("a")){

            } else if (input.equals("i")){

            } else if (input.equals("d")){

            } else if (input.equals("g")){

            } else if (input.equals("r")){

            } else if (input.equals("p")){

            } else {
                System.out.println("Invalid input! Possible operations:\na(ppend)\ni(nsert)\nd(elete)\ng(et)\nr(everse)\np(rint)\ne(xit)");
            }
        }
    }

    // ist genau dann true, wenn die Liste leer ist. Laufzeit O(1).
    public boolean empty() {
        return this.head == null;
    }

    // gibt die Anzahl der Listenelemente zurück. Laufzeit O(1).
    public int size() {
        return this.size;
    }

    // löscht alle Listenelemente. Laufzeit O(1).
    public void clear() {

    }

    // fügt ein neues Element mit Wert t am Ende an. Laufzeit O(1).
    public void append(T t) {
        Node<T> newElement = new Node<>(t);

        // falls die Liste leer ist
        if (this.head == null) {
            this.head = newElement;
            this.tail = this.head;
        } else { // sonst
            this.tail.next = newElement;
            this.tail = newElement;
            this.size += 1;
        }
    }

    // löscht das Element am Ende der Liste. Laufzeit O(1).
    public void deleteLast() {
        this.tail = null;
        this.size -= 1;

        /* in diesem Moment ist mit einer Laufzeit von O(1) das letzte Element zwar gelöscht,
        jedoch muss der pointer auf den tail neu gesetzt werden. In einfach verketteten Listen ist das Löschen
        des letzten Elements in O(1) nicht möglich, lediglich in O(n). */

        Node<T> temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        this.tail = temp;
    }

    // hängt 1 ans Ende der Liste an. Laufzeit O(1).
    public void concatenate(List<T> l) {
        if (l.head != null) {
            this.size += l.size();
            this.tail.next = l.head;
        }
    }

    // ausgabe der Liste in der Form [22, 5, 3]. Laufzeit O(size()).
    public String toString() {
        String output = "[";
        Node<T> temp = this.head;

        while (temp != null) {
            output += temp.data;
            if (temp.next != null) output += ", ";
            temp = temp.next;
        }

        output += "]";

        return output;
    }

    // kehrt die Reihenfolge der Elemente um. Laufzeit O(size()).
    public void reverse() {
        Node<T> previous = null;
        Node<T> current = this.head;
        while (current != null) {
            Node<T> nextElement = current.next;
            current.next = previous;
            previous = current;
            current = nextElement;
        }
        this.head = previous;
    }

    // löscht das Element an Position k. Der Listenkopf entspricht dabei der Position k = 0. Laufzeit O(k).
    public void delete(int k) {
        Node<T> current = this.head;

        // falls der head gelöscht werden soll
        if (k == 0) {
            head = current.next;
            return;
        }

        // die Node vor der zu löschenden Node finden
        for (int i = 0; current != null && i < k - 1; i++) {
            current = current.next;
        }

        // falls k auf nichts zeigt
        if (current == null || current.next == null)
            return;

        // die node nach current muss gelöscht werden
        // einen pointer auf die node nach der zu löschenden initialisieren
        Node<T> next = current.next.next;
        current.next = next; // die zu löschende node aus der liste werfen

    }

    // löscht alle Elemente mit Wert x < t aus der Liste und gibt die Anzahl der gelöschten Elemente zurück.
    // Laufzeit O(size()).
    public int deleteAllSmallerElements(T t) {
        int deleted = 0;
        while (head != null && head.data.compareTo(t) < 0) {
            head = head.next;
            deleted++;
        }

        for (Node<T> current = head; current != null; current = current.next) {
            while (current != null && current.next.data.compareTo(t) < 0) {
                current.next = current.next.next;
                deleted++;
            }
        }
        return deleted;
    }
}
