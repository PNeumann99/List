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
        // die Liste die der Nutzer bearbeitet initialisieren
        List<Integer> liste = new List<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean keepGoing = true;
        System.out.println("Es wurde eine leere Liste erstellt.");
        while(keepGoing){
            System.out.println("Welche Aktion soll durchgeführt werden?\nEingabe: ");
            String input = in.readLine();
            switch (input) {
                case "e":  // exit : Programm verlassen
                    System.out.println("Aktion: exit\nDas Programm wird beendet.");
                    keepGoing = false;
                    break;

                case "a":  // append : Nutzer nach Wert fragen und am Ende der Liste einfügen
                    System.out.println("Aktion: append\nWelchen Wert hat das einzufügende Element?\nEingabe: ");
                    liste.append(Integer.parseInt(in.readLine()));
                    System.out.println("Element mit Wert " + liste.tail.data + " wurde am Ende der Liste eingefügt.");
                    break;

                case "i":  // insert : Nutzer nach Wert und Position fragen und dann dort einfügen
                    System.out.println("Aktion: insert\nWelchen Wert hat das einzufügende Element?\nEingabe: ");
                    int data = Integer.parseInt(in.readLine());
                    System.out.println("An welcher Position soll das Element eingefügt werden?\nEingabe: ");
                    int pos = Integer.parseInt(in.readLine());
                    if(pos == liste.size) liste.append(data);
                    else liste.insert(data, pos);
                    System.out.println("Ein neues Element mit dem Wert " + data+" wurde an Position "+pos+" in die Liste eingefügt.");
                    break;

                case "d":  // delete : Nutzer nach Position fragen und Element löschen
                    System.out.println("Aktion: delete\nAn welcher Position soll das Element gelöscht werden?\n Eingabe: ");
                    pos = Integer.parseInt(in.readLine());
                    if (pos == liste.size-1) liste.deleteLast();
                    else liste.delete(pos);
                    System.out.println("Das Element an Position "+pos+" wurde gelöscht.");
                    break;

                case "g":  // get : Nutzer nach Position fragen und Wert des Elementes ausgeben
                    if(!liste.empty()) {
                        System.out.println("Aktion: get\nVon welchem Element (Position in d. Liste) soll der Wert ausgegeben werden?\nEingabe: ");
                        pos = Integer.parseInt(in.readLine());
                        data = liste.get(pos);
                        System.out.println("Das Element an Position " + pos + " hat den Wert" + data + ".");
                    } else { // falls die Liste noch leer ist.
                        System.out.println("Aktion: get\nDie Liste ist leer!");
                    }
                    break;

                case "r":  // reverse : Liste umkehren
                    System.out.println("Aktion: reverse\nDie Liste wurde umgekehrt.");
                    liste.reverse();
                    break;

                case "p":  // print : Liste auf dem Bildschirm ausgeben
                    System.out.println("Aktion: print\n Die Liste: "+liste.toString());
                    break;

                default:
                    System.out.println("Ungültige Eingabe! Mögliche Aktionen:\na(ppend)\ni(nsert)\nd(elete)\ng(et)\nr(everse)\np(rint)\ne(xit)");
                    break;
            }
        }
    }

    // gibt den Wert des Elementes an Position k zurück.
    public T get(int k){
        Node<T> current = this.head;
        int i = 0;
        while(current != null && current.next != null && i < k){
            current = current.next;
            i++;
        }
        if(current.next != null) return current.next.data;
        else return null;
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

    // fügt ein neues Element mit Wert t an Position k ein.
    public void insert(T t, int k){
        Node<T> current = this.head;
        for(int i = 0; i < k; i++){
            current = current.next;
        }
        Node<T> newElement = new Node<>(t);
        newElement.next = current.next;
        current.next = newElement;
        this.size++;
    }

    // löscht das Element am Ende der Liste. Laufzeit O(1).
    public void deleteLast() {
        this.tail = null;
        this.size--;

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
            this.size--;
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
        this.size--;

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
            if (current.next == null) this.tail = current;
            while (current != null && current.next.data.compareTo(t) < 0) {
                current.next = current.next.next;
                deleted++;
            }
        }
        this.size -= deleted;

        return deleted;
    }
}
