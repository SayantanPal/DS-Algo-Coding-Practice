import java.util.ArrayList;
import java.util.List;

// Use final for the class and fields
// No setters allowed!
// Defensive copying for mutable fields
// Override equals() and hashCode() for consistency

public final class PersonImmutableClass {
    private final String name;
    private final List<String> hobbies;

    public PersonImmutableClass(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = new ArrayList<>(hobbies); // deep copy
    }
    public String getName() { return name; }
    public List<String> getHobbies() {
        return new ArrayList<>(hobbies); // return copy
    }
}