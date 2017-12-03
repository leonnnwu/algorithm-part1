package com.lwu.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int count;

    /**
     * construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    /**
     * is the deque empty?
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * add the item to the front
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The input item must not be null.");
        }

        Node<Item> newFirst = new Node<>();
        newFirst.value = item;
        newFirst.prev = null;
        newFirst.next = first;

        if (isEmpty()) {
            last = newFirst;
        } else {
            first.prev = newFirst;
        }

        first = newFirst;
        count++;
    }

    /**
     * add the item to the end
     * @param item
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The input item must not be null.");
        }

        Node<Item> newLast = new Node<>();
        newLast.value = item;
        newLast.prev = last;
        newLast.next = null;

        if (isEmpty()) {
            first = newLast;
        } else {
            last.next = newLast;
        }

        last = newLast;
        count++;
    }

    /**
     * remove and return the item from the front
     * @return
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty!");
        }

        Item item = first.value;
        first = first.next;
        count--;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return item;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty!");
        }

        Item item = last.value;
        last = last.prev;
        count--;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return item;
    }

    /**
     * return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(first);
    }

    private class Node<Item> {
        Item value;
        Node<Item> next;
        Node<Item> prev;
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        Node<Item> current;
        DequeIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item temp = current.value;
            current = current.next;
            return temp;
        }
    }
}
