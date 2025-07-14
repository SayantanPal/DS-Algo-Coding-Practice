package DS;

public class CustomHashSet<E> {

    // Internal array to hold the set elements
    private Object[] elements;

    // The current size (number of elements) in the set
    private int size;

    // The initial capacity of the HashSet
    private static final int INITIAL_CAPACITY = 16;

    // Load factor to determine when to resize the internal array
    private static final float LOAD_FACTOR = 0.75f;

    // Constructor to initialize the hash set with default capacity
    public CustomHashSet() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Method to add an element to the set
    public boolean add(E element) {
        // Check if the element already exists, if so, return false (no duplicates in a set)
        if (contains(element)) {
            return false;
        }

        // If the set is full based on the load factor, resize it
        if (size >= elements.length * LOAD_FACTOR) {
            resize();
        }

        // Find an index to insert the element, using a simple hash function (modulo of the hash code)
        int index = getIndex(element);

        // Insert the element in the calculated index
        elements[index] = element;
        size++;
        return true;
    }

    // Method to remove an element from the set
    public boolean remove(E element) {
        // Find the index of the element to remove
        int index = getIndex(element);

        // If the element is not found, return false
        if (elements[index] == null) {
            return false;
        }

        // Set the element at the index to null and decrease the size
        elements[index] = null;
        size--;
        return true;
    }

    // Method to check if the set contains an element
    public boolean contains(E element) {
        int index = getIndex(element);
        return elements[index] != null;
    }

    // Method to get the number of elements in the set
    public int size() {
        return size;
    }

    // Private method to calculate the index for an element based on its hash code
    private int getIndex(E element) {
        return Math.abs(element.hashCode()) % elements.length;
    }

    // Private method to resize the internal array when the load factor exceeds the threshold
    private void resize() {
        // Create a new array with double the current capacity
        Object[] newElements = new Object[elements.length * 2];

        // Rehash all elements and place them into the new array
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                int newIndex = Math.abs(elements[i].hashCode()) % newElements.length;
                newElements[newIndex] = elements[i];
            }
        }

        // Update the reference to the new array
        elements = newElements;
    }

    // Override the toString method to display the elements in the set
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                result.append(elements[i].toString()).append(", ");
            }
        }
        // Remove the last comma and space, and close the curly brace
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        // Create a new custom HashSet
        CustomHashSet<Integer> set = new CustomHashSet<>();

        // Add elements to the set
        System.out.println(set.add(10));   // true, 10 added
        System.out.println(set.add(20));   // true, 20 added
        System.out.println(set.add(30));   // true, 30 added
        System.out.println(set.add(10));   // false, 10 already exists

        // Print the set contents
        System.out.println("Set: " + set);

        // Remove an element from the set
        System.out.println(set.remove(20)); // true, 20 removed
        System.out.println(set.remove(50)); // false, 50 does not exist

        // Print the set after removal
        System.out.println("Set after removal: " + set);

        // Check if the set contains certain elements
        System.out.println(set.contains(10)); // true
        System.out.println(set.contains(50)); // false

        // Print the size of the set
        System.out.println("Set size: " + set.size()); // 2
    }
}
