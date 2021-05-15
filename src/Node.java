public class Node<T extends Comparable<T> > {
    public T data;
    public Node next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }
}
