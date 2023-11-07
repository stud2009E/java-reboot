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

    /**
     * count of filled elements of container
     * @return {int} size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * check is container empty
     * @return {boolean}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * add item into container
     * @param item {T} element
     * @return {boolean} result of adding
     */
    @Override
    public boolean add(T item) {
        add(item, elementData, size);
        return true;
    }

    /**
     * add item into array
     * @param item {T} new item
     * @param elementData {Object[]} array there item will be inserted
     * @param s {int} position of new item
     */
    private void add(T item, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = item;
        size = s + 1;
    }

    /**
     * increase capacity of container
     * @return {Object[]} new array
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * increase capacity with transferred value
     * @param minCapacity {int} minimal value to increase
     * @return new array
     */
    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    /**
     * calculate new optimal value of capacity
     * @param minCapacity {int} minimal required capacity
     * @return {int} calculated capacity
     */
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

    /**
     * add all elements of array
     * @param items {T[]} array of new items
     * @return {boolean} result of adding
     */
    @Override
    public boolean addAll(T[] items) {
        return addAll(size, items);
    }

    /**
     * add all elements of collection
     * @param items {Collection<T>} collection of new items
     * @return {boolean} result of adding
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<T> items) {
        T[] a = (T[]) items.toArray();

        return addAll(a);
    }

    /**
     * add all elements starting from index position
     * @param index {int} starting index
     * @param items {T[]} items for insert
     * @return {boolean} result of adding
     */
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

    /**
     * check index in range of container
     * @param index {int}
     * @throws{IndexOutOfBoundsException}
     */
    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * get element from container
     * @param index {int} position of element
     * @return {T} element
     */
    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * get donwcasted element
     * @param index {int} position of element
     * @return {T} element
     */
    @SuppressWarnings("unchecked")
    T elementData(int index) {
        return (T) elementData[index];
    }

    /**
     * replace element at position
     * @param index {int} position
     * @param item {T} new element
     * @return {T} old element
     */
    @Override
    public T set(int index, T item) {
        rangeCheck(index);
        T oldValue = elementData(index);
        elementData[index] = item;
        return oldValue;
    }

    /**
     * remove element from container
     * @param index {int}
     * @return {T} removed element
     */
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

    /**
     * optimized remove with array copy
     * @param es array
     * @param i element position to remove
     */
    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    /**
     * remove item if exist
     * @param item {T} item to be deleted
     * @return {boolean} result of removing
     */
    @Override
    public boolean remove(T item) {
        int i = indexOf(item);

        if (i == -1) {
            return false;
        }
        fastRemove(elementData, i);
        return true;
    }

    /**
     * check if container contains element
     * @param item {T} searched element
     * @return {boolean} result
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    /**
     * find position of element in container
     * @param item {T} searched element
     * @return {int} position
     */
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

    /**
     * increase capacity of container to put new elements
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > elementData.length) {
            grow(newElementsCount);
        }
    }

    /**
     * get capacity
     * @return {int} capacity of container
     */
    @Override
    public int getCapacity() {
        return elementData.length - size;
    }

    /**
     * revers elements in container
     */
    @Override
    public void reverse() {
        Object temp;
        for (int i = 0; i < size / 2; i++) {
            temp = elementData[i];
            elementData[i] = elementData[size - i - 1];
            elementData[size - i - 1] = temp;
        }
    }

    /**
     * get array copy of container
     * @return {Object[]} copy
     */
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