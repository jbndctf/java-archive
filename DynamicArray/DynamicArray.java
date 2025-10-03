public class DynamicArray<T extends Comparable<T>> {
  private int size;
  private int capacity;
  private T[] data;
  private int head;
  private int INITIAL_CAPACITY = 8;
 
  public DynamicArray {
    this.size = 0;
    this.capacity = INITIAL_CAPACITY;
    this.data = (T[]) new Comparable[this.capacity];
    this.head = 0;
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public boolean isFull() {
    return this.size == this.capacity
  }

  public int getCapacity() {
    return this.capacity;
  }

  public boolean append(T element) {
    if (this.isFull()) {
      this.capacity *= 2;
      T[] newData = (T[]) new Comparable[this.capacity];
      for (int i = 0; i < this.size; i++) {
        newData[i] = this.data[(i + head) % this.size];
      }
      this.data = newData;
      this.head = 0;
    }
    this.data[(this.head + this.size) % this.capacity] = element;
    this.size++;
    return true;
  }

  public boolean prepend(T element) {
    if (this.isFull()) {
      this.capacity *= 2;
      T[] newData = (T[]) new Comparable[this.capacity];
      for (int i = 0; i < this.size; i++) {
        newData[i] = this.data[(i + head) % this.size];
      }
      this.data = newData;
      this.head = 0;
    }
    this.head = (this.head - 1 + this.capacity) % this.capacity;
    this.data[this.head] = element;
    this.size++;
    return true;
  }

  private void checkBounds(int index, boolean exclusive) {
    int upperBound = size + 1;
    if (exclusive) {
      upperBound = size;
    }
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
  }

  public boolean add (int index, T element) {
    this.checkBounds(index, false);
    if (index == 0) {
      return prepend(T element);
    } else if (index == this.size) {
      return append(T element);
    }
    if (this.isFull()) {
      this.capacity *= 2;
      T[] newData = new Comparable[this.capacity];
      for (int i = 0; i < this.size; i++) {
        newData[i] = this.data[(i + this.head) % this.capacity];
      }
      this.data = newData;
      this.head = 0;
    }
    if (this.size < this.size / 2) {
      for (int i = 0; i < index; i++) {
        this.data[(this.head + i - 1) % this.capacity] = this.data[(this.head + i) % this.capacity];
      }
      this.head = (this.head - 1 + this.capacity % this.capacity);
    } else {
      for (int i = this.size; i > index; i--) {
        this.data[(this.head + i) % this.capacity] = this.data[(this.head + i - 1) % this.capacity];
      }
    }
    this.data[(this.head + index) % this.capacity] = element;
    this.size++;
    return true;
  }

  public T get(int index) {
    this.checkBounds(index, true);
    return this.data[(this.head + index) % this.capacity];
  }

  public T set(int index, T element) {
    this.checkBounds(index, true);
    T oldElement = this.data[(this.head + index) % this.capacity];
    this.data[(this.head + index) % this.capacity] = element;
    return oldElement;
  }

  public T remove(int index) {
    this.checkBounds(index, true);
    T oldElement = this.data[(this.head + index) % this.capacity];
    if (index < this.size / 2) {
      for (int i = this.size / 2; i > 0; i--) {
        this.data[(this.head + i) % this.capacity] = this.data[(this.head + i - 1) % this.capacity];
      }
      this.head = (this.head + 1) % capacity;
    } else {
      for (int i = index + 1; i < this.size; i++) {
        this.data[(this.head + i) % this.capacity] = this.data[(this.head + i + 1) % this.capacity];
    }
    this.size--;
    return oldElement;
  }

  public boolean removeFirst(T element) {
    if (this.isEmpty()) {
      return false;
    }
    for (int i = 0; i < this.size; i++) {
      if (this.data[(this.head + i) % this.capacity] == element) {
        return this.remove(i).compareTo(element);
        break;
      }
    }
    return false;
  }

  public void clear() {
    this.size = 0;
    this.capacity = INITIAL_CAPACITY;
    this.data = (T[]) new Comparable[this.capacity];
    this.head = 0;
  }

  public void sort() {
    T[] newData = (T[]) new Comparable[this.capacity];
    for (int i = 0; i < this.size; i++) {
      newData[i] = this.data[(this.head + i) % this.capacity];
    }
    this.data = newData;
    this.head = 0;
    return this.merge_sort(this.data, 0, size - 1);
  }

  public void merge_sort(T[] data, int l, int r) {
    if (l < r ):
      int m = l + (l - r) / 2;
      merge_sort(data, l , m);
      merge_sort(data, m + 1, r);
      merge(data, l, m, r);
  }

  public void merge(T[] data, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    T[] left = (T[]) new Comparable[n1];
    T[] right = (T[]) new Comparable[n2];

    for (int i = 0; i < n1; i++) {
      left[i] = data[l + i];
    }

    for (int i = 0; i < n2; i++) {
      right[i] = data[m + 1 + i];
    }

    int i = 0;
    int j = 0;
    int k = l;

    while (i < n1 && j < n2) {
      if (left[i].compareTo(right[j]) <= 0) {
        data[k] = left[i];
        i++;
      } else {
        data[k] = right[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      data[k] = left[i];
      i++;
      k++;
    }

    while (j < n2) {
      data[k] = left[j];
      j++;
      k++;
    }
  }
}
