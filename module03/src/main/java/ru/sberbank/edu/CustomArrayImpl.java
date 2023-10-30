package ru.sberbank.edu;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private Object[] elementData;

    public CustomArrayImpl() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayImpl(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T item) {
        add(item, elementData, size);
        return true;
    }

    private void add(T item, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = item;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity <= 0) {
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return newCapacity;
    }

    @Override
    public boolean addAll(T[] items) {
        return addAll(size, items);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<T> items) {
        T[] a = (T[]) items.toArray();

        return addAll(a);
    }

    @Override
    public boolean addAll(int index, T[] items) {
        rangeCheck(index);
        int numNew = items.length;
        if (numNew == 0) {
            return false;
        }
        final int s = size;
        Object[] elementData = this.elementData;
        if (numNew > elementData.length - s) {
            elementData = grow(s + numNew);
        }

        int numMoved = s - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        }
        System.arraycopy(items, 0, elementData, index, numNew);
        size = s + numNew;

        return true;
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    @SuppressWarnings("unchecked")
    T elementData(int index) {
        return (T) elementData[index];
    }

    @Override
    public T set(int index, T item) {
        rangeCheck(index);
        T oldValue = elementData(index);
        elementData[index] = item;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final Object[] es = elementData;
        T oldValue = elementData(index);
        fastRemove(es, index);
        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public boolean remove(T item) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        int k = -1;
        if (item == null) {
            for (; i < size; i++) {
                if (es[i] == null) {
                    k = i;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (item.equals(es[i])) {
                    k = i;
                    break;
                }
            }
        }

        if (k == -1) {
            return false;
        }

        fastRemove(es, k);
        return true;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) > 0;
    }

    @Override
    public int indexOf(T item) {
        Object[] es = elementData;

        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > elementData.length) {
            grow(newElementsCount);
        }
    }

    @Override
    public int getCapacity() {
        return elementData.length - size;
    }

    @Override
    public void reverse() {
        Object temp;
        for (int i = 0; i < size / 2; i++) {
            temp = elementData[i];
            elementData[i] = elementData[size - i - 1];
            elementData[size - i - 1] = temp;
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public String toString() {
        return "CustomArrayImpl{" +
                "size=" + size +
                ", capacity=" + getCapacity() +
                ", elementData=" + Arrays.toString(toArray()) +
                '}';
    }
}
