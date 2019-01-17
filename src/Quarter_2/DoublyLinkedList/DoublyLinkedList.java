package Quarter_2.DoublyLinkedList;

public class DoublyLinkedList<E> {

    private ListNode<E> root, end;
    private int size = 0;

    public int size() {
        return size;
    }

    public ListNode getEnd() {
        return end;
    }

    public ListNode getRoot() {
        return root;
    }

    public void add(E element) {
        if (size == 0)
            root = end = new ListNode<E>(element, null);
        else {
            ListNode<E> next = new ListNode<E>(element, end);
            end.setNext(next);
            end = next;
        }
        size++;
    }

    public void add(int position, E element) {
        if (position > size || position < 0)
            throw new IndexOutOfBoundsException();
        else if (position == size)
            add(element);
        else if (position == 0) {
            ListNode<E> newRoot = new ListNode<E>(element, null);
            newRoot.setNext(root);
            root.setLast(newRoot);
            root = newRoot;
            size++;
        } else {
            ListNode<E> current = root;
            for (int i = 0; i < position; i++)
                current = current.getNext();
            ListNode<E> addNode = new ListNode<E>(element, current.getLast());
            current.getLast().setNext(addNode);
            addNode.setNext(current);
            current.setLast(addNode);
            size++;
        }
    }

    public E get(int position) {
        if (position > size || position < 0 || size == 0)
            throw new IndexOutOfBoundsException();
        E value;
        ListNode<E> current = root;
        for (int i = 0; i < position; i++)
            current = current.getNext();
        value = current.getValue();
        return value;
    }

    public void set(int position, E value) {
        if (position > size || position < 0 || size == 0)
            throw new IndexOutOfBoundsException();
        ListNode<E> current = root;
        for (int i = 0; i < position; i++)
            current = current.getNext();
        current.setValue(value);
    }

    public E remove(int position) {
        if (position > size || position < 0 || size == 0)
            throw new IndexOutOfBoundsException();
        E value = root.getValue();
        if (size == 1) {
            root = null;
            end = null;
        } else if (position == 0)
            root = root.getNext();
        else if (position == size - 1) {
            value = end.getValue();
            end = end.getLast();
            end.setNext(null);
        } else {
            ListNode<E> current = root;
            for (int i = 0; i < position; i++)
                current = current.getNext();
            value = current.getValue();
            current.getLast().setNext(current.getNext());
            current.getNext().setLast(current.getLast());
        }
        size--;
        return value;
    }

    public void clear() {
        root = null;
        end = null;
        size = 0;
    }

    public boolean contains(E element) {
        ListNode<E> check = root;
        while (check.hasNext()) {
            if (check.getValue() == element)
                return true;
            check = check.getNext();
        }
        return false;
    }

    public String toString() {
        String string = "[";
        ListNode<E> check = root;
        if (check != null) {
            while (check.hasNext()) {
                string += String.valueOf(check.getValue());
                check = check.getNext();
                string += ", ";
            }
            string += check.getValue();
        }
        string += "]";
        return string;
    }

    public String toReversedString() {
        String string = "[";
        ListNode<E> check = end;
        if (check != null) {
            while (check.hasLast()) {
                string += check.getValue();
                check = check.getLast();
                string += ", ";
            }
            string += check.getValue();
        }
        string += "]";
        return string;
    }

    public ListNode<E> getNext() {
        return root = root.getNext();
    }

    public ListNode<E> getLast() {
        return root = root.getLast();
    }

    public boolean hasNext() {
        return root.hasNext();
    }

    public boolean hasLast() {
        return root.hasLast();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public class ListNode<E> {

        private E value;
        private ListNode<E> next, last;

        public ListNode(E value, ListNode<E> last) {
            this.value = value;
            this.last = last;
            this.next = null;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public ListNode<E> getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode<E> getLast() {
            return last;
        }

        public void setLast(ListNode last) {
            this.last = last;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasLast() {
            return last != null;
        }
    }
}
