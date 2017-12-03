package com.lwu.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int count;


    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (items.length == count) {
            resize(2*count);
        }

        items[count++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(count);
        Item item = items[randomIndex];
        items[randomIndex] = items[count-1];
        items[count-1] = null;
        count--;

        if (count > 0 && count == items.length / 4) {
            resize(items.length/2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(count);
        return items[randomIndex];
    }

    private void resize(int capacity) {
        assert capacity >= count;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            temp[i] = items[i];
        }

        items = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] itemsClone;
        private int countClone;

        RandomizedQueueIterator() {
            itemsClone = items.clone();
            countClone = count;
        }

        @Override
        public boolean hasNext() {
            return countClone != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int randomIndex = StdRandom.uniform(countClone);
            Item item = itemsClone[randomIndex];
            itemsClone[randomIndex] = itemsClone[countClone-1];
            itemsClone[countClone-1] = null;
            countClone--;
            return item;
        }

    }
}
