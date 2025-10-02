public class DoublyLinkedList<T> {
  private Node head;
  private Node tail;
  private int size;

  public DoublyLinkedList {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public void checkBounds(int index, boolean exclusive) {
    int upperBound = this.size + 1;
    if (exclusive) {
      upperBound = this.size;
    }
    if (index < 0 || index >= upperBound) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
  }

  public T get(int index) {
    this.checkBounds(index, true);
    Node current = this.head;
    int current_index = 0;
    if (index < this.size / 2) {
      while (current_index != index) {
        current = current.getNext();
        current_index++;
      }
      return current.getData();
    }
    current = this.tail;
    int current_index = this.size - 1;
    while (current_index != index) {
      current = current.getPrev();
      current_index--;
    }
    return current.getData();
  }

  public T set(int index, T data) {
    this.checkBounds(index, true);
    Node current = this.head;
    int current_index = 0;
    if (index < this.size / 2) {
      while (current_index != index) {
        current = current.getNext();
        current_index++;
      }
      T oldData = current.getData();
      current.setData(data);
      return oldData;
    }
    current = this.tail;
    int current_index = this.size - 1;
    while (current_index != index) {
      current = current.getPrev();
      current_index--;
    }
    T oldData = current.getData();
    current.setData(data);
    return oldData;
  }

  public boolean append(T data) {
    Node newNode = Node(data);
    if (this.isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
      this.size++;
      return true;
    }
    this.tail.setNext(newNode);
    newNode.setPrev(this.tail);
    this.tail = newNode;
    this.size++;
    return true;
  }

  public boolean prepend(T data) {
    Node newNode = Node(data);
    if (this.isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.head.setPrev(newNode);
      newNode.setNext(this.head);
      this.head = newNode;
    }
    this.size++;
    return true;
  }

  public boolean add(int index, T data) {
    this.checkBounds(index, false);
    if (index == 0) {
      return this.prepend(data);
    } else if (index == size) {
      return this.append(data);
    }
    Node newNode = Node(data);
    Node current = this.head;
    int current_index = 0;
    if (index < this.size / 2) {
      while (current_index != index) {
        current = current.getNext();
        current_index++;
      }
    } else {
      current = this.tail;
      current_index = this.size - 1;
      while (current_index != index) {
        current = current.getPrev();
        current_index--;
      }
    }
    current.getPrev().setNext(newNode);
    newNode.setPrev(current.getPrev());
    newNode.setNext(current);
    current.setPrev(newNode);
    current = newNode;
    this.size++;
    return true;
  }

  public T getFirst() {
    if (isEmpty()) {
      return null;
    }
    return this.head;
  }

  public T getLast() {
    if (isEmpty()) {
      return null;
    }
    return this.tail;
  }

  public T remove(int index) {
    this.checkBounds(index, true);
    if (index == 0) {
      T oldData = this.getFirst();
      this.head = this.head.getNext();
      if (this.head != null) {
        this.head.setPrev(null);
      }
      this.size--;
      return oldData;
    } else if (index == size - 1) {
      T oldData = this.getLast();
      this.tail = this.tail.getPrev();
      if (this.tail != null) {
        this.tail.setNext(null);
      }
      this.size--;
      return oldData;
    }
    Node current = this.head;
    int current_index = 0;
    if (index < this.size / 2) {
      while (current_index != index) {
        current = current.getNext();
        current_index++;
      }
    } else {
      current = this.tail;
      current_index = size - 1;
      while (current_index != index) {
        current = current.getPrev();
        current_index--;
      }
    }
    T oldData = current.getData();
    current.getPrev().setNext(current.getNext());
    current.getNext().setPrev(current.getPrev());
    return oldData;
  }

  public boolean removeFirst(T data) {
    Node current = this.head;
    int current_index = 0;
    while (current != null && current.getData() != data) {
      current = current.getNext();
      current_index++;
    }
    return this.remove(current_index) == data;
  }

  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  private class Node {
    private Node next;
    private Node prev;
    private T data;

    public Node(T data) {
      this.next = null;
      this.prev = null;
      this.data = data;
    }

    public Node getNext() {
      return this.next;
    }

    public void setNext(Node newNext) {
      this.next = newNext;
    }

    public Node getPrev() {
      return this.prev;
    }

    public void setPrev(Node newPrev) {
      this.prev = newPrev;
    }

    public T getData() {
      return this.data;
    }

    public void setData(T newData) {
      this.data = newData;
    }
  }
}
