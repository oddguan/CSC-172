import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class URLinkedList<E> implements URList<E>, Iterable<E> {
    private URLink<E> head;
    private int size;

    public URLinkedList(){
        head = null;
        size = 0;
    }

    public URLinkedList(Collection<? extends E> c){
        head = null;
        size=0;
        for(E element : c) {
            add(element);
            size++;
        }
    }

    @Override
    public boolean add(E e) {
        if(head == null){
            head = new URLink<E>(e, null,null);
            size++;
        }
        else{
            URLink<E> current = head;

            while(current.next()!=null){
                current = current.next();
            }
            current.setNext(new URLink<E>(e, current,null));

            size++;
        }
        return true;
    }

    @Override
    public void add(int index, E element){
        URLink<E> current = head.next();
        for(int i=0;i<index;i++){
            current = current.next();
        }
        URLink<E> e = new URLink<E>(element, current, current.next());
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E element : c){
            add(element);
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        URLink<E> current = head.next();
        for(int i=0;i<index;i++){
            current = current.next();
        }
        for(E element : c){
            current.setNext(new URLink<E>(element, current, current.next()));
            current = current.next();
            size++;
        }

        return true;
    }

    @Override
    public void clear() {
        head = null;
        size=0;
    }

    @Override
    public boolean contains(Object o) {
        boolean a=false;
        if(head.element().equals(o)){
            return true;
        }
        URLink<E> current = head.next();
        while(!current.element().equals(o)){
            current = current.next();
            if(current.equals(null)){
                break;
            }
            if(current.element().equals(o)){
                a=true;
                break;
            }
            else{
                a=false;
            }
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for(Object o : c){
            URLink<E> current = head.next();
            if(head == o || current==o){
                continue;
            }
            else if(current.next()==o){
                count++;
                continue;
            }
            else{
                current = current.next();
            }
        }
        return c.size()==count;
    }

    @Override
    public E get(int index) {
        if(index==0){
            return head.element();
        }
        URLink<E> current = head.next();
        for(int i=1;i<index;i++){
            current = current.next();
        }
        return current.element();
    }

    @Override
    public int indexOf(Object o) {
        if(head.element()==o){
            return 0;
        }
        URLink<E> current = head.next();
        if(current.element()==o){
            return 1;
        }
        else{
            int index = 1;
            while(current!=null){
                current = current.next();
                if(current.element()==o){
                    return index;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E>{
        URLink<E> current = head.next();
        @Override
        public boolean hasNext() {

            return current.next()!=null;
        }

        @Override
        public E next() {
            return current.next().element();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public E remove(int index) {
        URLink<E> current = head.next();
        for(int i=0;i<index;i++){
            current = current.next();
        }
        current.prev().setNext(current.next());
        size--;
        return current.element();
    }

    @Override
    public boolean remove(Object o) {
        URLink<E> current = head.next();
        while(current!=null){
            if(current.element()==o){
                current.prev().setNext(current.next());
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c){
            URLink<E> current = head.next();
            while(current!=null){
                if(current.element()==o){
                    current.prev().setNext(current.next());
                    size--;
                }
            }
        }
        return true;
    }

    @Override
    public E set(int index, E element) {
        URLink<E> current = head.next();
        for(int i=0;i<index;i++){
            current = current.next();
        }
        current.setElement(element);
        return element;
    }

    @Override
    public int size() { return size; }

    @Override
    public URList<E> subList(int fromIndex, int toIndex) {
        URLink<E> newHead = new URLink<E>(get(fromIndex), null, null);
        URLink<E> current = new URLink<E>(null, newHead,null);
        current = newHead.next();
        for(int i=fromIndex;i<toIndex;i++){
            current.setElement(get(i));
            URLink<E> next = new URLink<E>(get(i+1),current,null);
            current.setNext(next);
            next = current;
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        if(head!=null){
            int i = 0;
            array[i] = head;
            URLink<E> current = head.next();
            while(current!=null){
                i++;
                array[i] = current;
                current = current.next();
            }
            return array;
        }
        else{
            return null;
        }
    }
    void addFirst(E e){
        URLink<E> newHead = new URLink<E>(e, null, head);
        head = newHead;
    }

    void addLast(E e){
        add(e);
    }

    E peekFirst(){
        return head.element();
    }

    E peekLast(){
        return get(size);
    }

    E pollFirst(){
        head.next().setPrev(null);
        head = head.next();
        return head.element();
    }

    E pollLast(){
        URLink<E> current = head;
        for(int i=0;i<size-1;i++){
            current = current.next();
        }
        current.prev().setNext(null);
        return current.element();
    }
}
