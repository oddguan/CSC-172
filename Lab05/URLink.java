class URLink<E> {      // Doubly linked list node
    private E e;        // Value for this node
    private URLink<E> n;  // Pointer to next node in list
    private URLink<E> p;  // Pointer to previous node
    // Constructors
    URLink(E it, URLink<E> inp, URLink<E> inn) { e = it; p = inp; n = inn; }
    URLink(URLink<E> inp, URLink<E> inn) { p = inp; n = inn; }
    // Get and set methods for the data members
    public E element() { return e; }                          // Return the value
    public E setElement(E it) { return e = it; }              // Set element value
    public URLink<E> next() { return n; }                      // Return next link
    public URLink<E> setNext(URLink<E> nextval) { return n = nextval; } // Set next link
    public URLink<E> prev() { return p; }                      // Return prev link
    public URLink<E> setPrev(URLink<E> prevval) { return p = prevval; } // Set prev link
}