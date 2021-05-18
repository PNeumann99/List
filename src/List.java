import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Eine einfache Implementierung einfach verketteter Listen mit generischem Datentyp.
 * Die Klasse enthält eine main-Methode, welche eine leere verkettete Liste erzeugt und den Nutzer
 * interaktiv nach verschiedenen Aktionen fragt und diese Durchführt. Als Template-Parameter T wird
 * der Typ Integer benutzt.
 * Der Nutzer kann beliebig viele Aktionen hintereinander ausführen, bis er das Programm mit einem exit code beendet.
 *
 * @param <T> Der Datentyp der einfach verketteten Liste.
 * @author akubf
 */
public class List<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    /**
     * Erzeugt eine neue, leere, einfach verkettete Liste.
     */
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
        System.out.println("""
                Durchführbare Aktionen:
                                            
                a(ppend) : fügt ein neues Element am Ende der Liste ein
                i(nsert) : fügt ein neues Element an einer vom Nutzer gegebenen Position in die Liste ein
                d(elete) : löscht das Element an einer vom Nutzer gegebenen Position
                g(et)    : gibt den Wert des Elements an der vom Nutzer gegebenen Position aus
                r(everse): kehrt die Liste um
                p(rint)  : gibt die Liste auf dem Bildschirm aus
                e(xit)   : beendet das Programm""");
        while (keepGoing) {
            System.out.println("Welche Aktion soll durchgeführt werden?\nEingabe: ");
            String input = in.readLine();
            switch (input) {
                case "e":  // exit : Programm verlassen
                    System.out.println("Aktion: exit\nDas Programm wird beendet.");
                    keepGoing = false;
                    break;

                case "a":  // append : Nutzer nach Wert fragen und am Ende der Liste einfügen
                    System.out.println("Aktion: append\nWelchen Wert hat das einzufügende Element?\nEingabe: ");
                    String dataStr = in.readLine();

                    // überprüfen, ob der Nutzer einen gültigen Wert eingegeben hat.
                    while (!dataStr.matches("-?\\d+")) {
                        System.out.println("Kein gültiger Wert! Nur Ganzzahlen!\nEingabe: ");
                        dataStr = in.readLine();
                    }

                    int data = Integer.parseInt(dataStr);
                    liste.append(data);
                    System.out.println("Element mit Wert " + liste.tail.data + " wurde am Ende der Liste eingefügt.\n");
                    break;

                case "i":  // insert : Nutzer nach Wert und Position fragen und dann dort einfügen
                    System.out.println("Aktion: insert\nWelchen Wert hat das einzufügende Element?\nEingabe: ");
                    dataStr = in.readLine();

                    // überprüfen, ob der Nutzer einen gültigen Wert eingegeben hat.
                    while (!dataStr.matches("-?\\d+")) {
                        System.out.println("Kein gültiger Wert! Nur Ganzzahlen!\nEingabe: ");
                        dataStr = in.readLine();
                    }

                    System.out.println("An welcher Position soll das Element eingefügt werden?\nEingabe: ");
                    String posStr = in.readLine();

                    // überprüfen, ob der Nutzer einen Gültigen Wert eingeben hat.
                    while (!posStr.matches("\\d+")) {
                        System.out.println("Kein gültiger Wert! Index muss >= 0 sein!\nEingabe: ");
                        posStr = in.readLine();
                    }

                    data = Integer.parseInt(dataStr);
                    int pos = Integer.parseInt(posStr);

                    if (pos == liste.size) { // falls am Ende eingefügt werden soll: append, weil schneller
                        liste.append(data);
                    } else if (pos > liste.size) { /* falls pos ins leere zeigt, also >= size+1 soll der index mit
                                                    Hinweis für den Nutzer korrigiert werden */
                        System.out.println("Index zeigt ins leere! Position wird korrigiert und neues Element " +
                                "wird am Ende eingefügt.");
                        pos = liste.size;
                        liste.append(data);
                    } else liste.insert(data, pos); // sonst
                    System.out.println("Ein neues Element mit dem Wert " + data + " wurde an Position " + pos +
                            " in die Liste eingefügt.\n");
                    break;

                case "d":  // delete : Nutzer nach Position fragen und Element löschen
                    if (!liste.empty()) {
                        System.out.println("""
                                Aktion: delete
                                An welcher Position soll das Element gelöscht werden?
                                Eingabe:\s""");
                        posStr = in.readLine();

                        // überprüfen, ob der Nutzer einen Gültigen Wert eingeben hat.
                        while (!posStr.matches("\\d+") || Integer.parseInt(posStr) >= liste.size) {
                            if (!posStr.matches("\\d+")) System.out.println("Kein gültiger Wert! " +
                                    "Index muss >= 0 sein!\nEingabe: ");
                            else System.out.println("Index zeigt ins leere! Der Index muss zwischen 0 und "
                                    + (liste.size - 1) + " liegen!\nEingabe: ");
                            posStr = in.readLine();
                        }

                        pos = Integer.parseInt(posStr);
                        liste.delete(pos);
                        System.out.println("Das Element an Position " + pos + " wurde gelöscht.\n");
                    } else {
                        System.out.println("Aktion: delete\nDie Liste ist leer!\n");
                    }
                    break;

                case "g":  // get : Nutzer nach Position fragen und Wert des Elementes ausgeben
                    if (!liste.empty()) {
                        System.out.println("""
                                Aktion: get
                                Von welchem Element (Position in d. Liste) soll der Wert ausgegeben werden?
                                Eingabe:\s""");
                        posStr = in.readLine();

                        // überprüfen, ob der Nutzer einen Gültigen Wert eingeben hat.
                        while (!posStr.matches("\\d+") || Integer.parseInt(posStr) >= liste.size) {
                            if (!posStr.matches("\\d+")) System.out.println("Kein gültiger Wert! " +
                                    "Index muss >= 0 sein!\nEingabe: ");
                            else System.out.println("Index zeigt ins leere! Der Index muss zwischen 0 und "
                                    + (liste.size - 1) + " liegen!\nEingabe: ");
                            posStr = in.readLine();
                        }

                        pos = Integer.parseInt(posStr);
                        data = liste.get(pos);
                        System.out.println("Das Element an Position " + pos + " hat den Wert " + data + ".\n");
                    } else { // falls die Liste noch leer ist.
                        System.out.println("Aktion: get\nDie Liste ist leer!\n");
                    }
                    break;

                case "r":  // reverse : Liste umkehren
                    System.out.println("Aktion: reverse\nDie Liste wurde umgekehrt.\n");
                    liste.reverse();
                    break;

                case "p":  // print : Liste auf dem Bildschirm ausgeben
                    System.out.println("Aktion: print\nDie Liste: " + liste + "\n");
                    break;

                default:
                    System.out.println("""
                            Ungültige Eingabe!
                                                        
                            Mögliche Aktionen:
                            a(ppend) : fügt ein neues Element am Ende der Liste ein
                            i(nsert) : fügt ein neues Element an einer vom Nutzer gegebenen Position in die Liste ein
                            d(elete) : löscht das Element an einer vom Nutzer gegebenen Position
                            g(et)    : gibt den Wert des Elements an der vom Nutzer gegebenen Position aus
                            r(everse): kehrt die Liste um
                            p(rint)  : gibt die Liste auf dem Bildschirm aus
                            e(xit)   : beendet das Programm
                            """);
                    break;
            }
        }
    }

    /**
     * Gibt den Wert des Elementes an Position k zurück.
     * Laufzeit: O(k)
     *
     * @param k Position des Elements dessen Wert ausgegeben werden soll
     * @return der Wert des Elements, gibt null zurück falls Element nicht existiert.
     */
    public T get(int k) {
        Node<T> current = this.head;
        int i = 0;
        while (current != null && i < k) {
            current = current.next;
            i++;
        }
        if (current != null) return current.data;
        else return null;
    }

    /**
     * Überprüft, ob die Liste leer ist.
     * Laufzeit: O(1)
     *
     * @return ist genau dann true, wenn die Liste leer ist.
     */
    public boolean empty() {
        return this.head == null;
    }

    /**
     * Gibt die Anzahl der Listenelemente zurück.
     * Laufzeit: O(1)
     *
     * @return die Anzahl der Listenelemente.
     */
    public int size() {
        return this.size;
    }

    /**
     * Löscht alle Listenelemente.
     * Laufzeit: O(1)
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        /* Nachdem wir also die Referenz auf unsere linked list löschen (also den head = null setzen), haben wir keinen
        Zugriff mehr auf die Liste und ist ist quasi gelöscht, zumindest für unsere Wahrnehmung. Im Hintergrund muss der
        Garbage Collector (GC) die losen Elemente jetzt noch "wegräumen", was theoretisch dann ja nicht mehr O(1) wäre.*/
    }

    /**
     * Fügt ein neues Element am Ende der Liste ein.
     * Laufzeit: O(1)
     *
     * @param t Der Wert des neuen Elements.
     */
    public void append(T t) {
        Node<T> newElement = new Node<>(t);
        // falls die Liste leer ist
        if (this.head == null) {
            this.head = newElement;
            this.tail = this.head;
        } else { // sonst
            this.tail.next = newElement;
            this.tail = newElement;
        }
        this.size++;
    }

    /**
     * Fügt ein neues Element in die Liste ein.
     * Laufzeit: O(k)
     *
     * @param t Der Wert des Elements.
     * @param k Die Position an der das Element in die Liste eingefügt werden soll.
     */
    public void insert(T t, int k) {
        Node<T> current = this.head;
        for (int i = 0; i < k; i++) {
            current = current.next;
        }
        Node<T> newElement = new Node<>(t);
        newElement.next = current.next;
        current.next = newElement;
        this.size++;
    }

    /**
     * Löscht das letzte Element in der Liste.
     * Laufzeit: O(size())
     */
    public void deleteLast() {

        // falls die liste leer ist
        if (head == null) return;

        // falls die liste nur aus dem head besteht.
        if (head.next == null) this.clear();

        else { // sonst
            Node<T> temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
            tail = temp;
        }
    }

    /**
     * Fügt eine zweite verkettete Liste am Ende an.
     * Laufzeit: O(1)
     *
     * @param l die zweite Liste.
     */
    public void concatenate(List<T> l) {
        if (l.head != null) {
            this.size += l.size();
            this.tail.next = l.head;
            this.tail = l.tail;
        }
    }

    /**
     * Gibt die Liste als String aus.
     * Laufzeit: O(size())
     *
     * @return die Liste als String in der Form [22, 5, 3].
     */
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

    /**
     * Kehrt die Reihenfolge der Elemente um.
     * Laufzeit: O(size())
     */
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

    /**
     * Löscht das element an einer gegebenen Position. Der Listenkopf entspricht dabei der Position k = 0.
     * Laufzeit: O(k)
     *
     * @param k die Position des Elementes, das gelöscht werden soll.
     */
    public void delete(int k) {
        // falls die Liste leer ist
        if (head == null) return;

        // pointer auf den head setzen
        Node<T> temp = head;

        // falls der head gelöscht werden soll
        if (k == 0) {
            head = temp.next;
            size--;
            return;
        }

        // das Element vor dem zu löschenden Element finden
        for (int i = 0; temp != null && i < k - 1; i++) {
            temp = temp.next;
        }

        // falls index ins leere zeigt
        if (temp == null || temp.next == null) {
            return;
        }

        // falls letztes Element gelöscht werden soll
        if (temp.next == tail) {
            temp.next = null;
            tail = temp;
            size--;
            return;
        }

        temp.next = temp.next.next;
        size--;
    }

    /**
     * Löscht alle Elemente mit kleinerem Wert als der gegebene und gibt die Anzahl der gelöschten Elemente aus.
     * Laufzeit: O(size())
     *
     * @param t der Wert, für den alle Elemente mit Wert x < t gelöscht werden.
     * @return die Anzahl der gelöschten Elemente.
     */
    public int deleteAllSmallerElements(T t) {
        int deleted = 0; // Rückgabewert mit 0 initialisieren
        // Erster Schritt: lösche alle Elemente mit kleinerem Wert, bis wir das Erste mit größerem Wert finden.
        while (head != null && head.data.compareTo(t) < 0) {
            head = head.next;
            deleted++;
        }

        // Nun enthält head null oder ein Element mit größerem Wert.
        // Zweiter Schritt: Geh durch die Liste und entferne alle Elemente mit kleinerem Wert.
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.next == null) this.tail = current;  // falls das letzte Element gefunden wurde,
            // setze den tail pointer neu
            // lösche alle Elemente mit kleinerem Wert, bis das Letzte mit Größerem gefunden wurde
            while (current.next != null && current.next.data.compareTo(t) < 0) {
                current.next = current.next.next;
                deleted++;
            }
        }
        this.size -= deleted; // Listengröße aktualisieren

        return deleted;
    }
}