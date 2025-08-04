import java.lang.reflect.Field;


/*
* Write a Java program to demonstrate how to break the immutability of a String using reflection.?
(Expected: Accessing char[] value field via reflection.)

Here’s a complete Java program that breaks the immutability of a String using reflection, by directly modifying the internal char[] field of a String object.
* */

// Companies - Barclays

public class BreakStrImmutabilityUsingReflection {

    public static void main(String[] args) throws Exception {
        String original = "HelloWorld";

        System.out.println("Before modification:");
        System.out.println("original = " + original);

        // Use reflection to access the private char[] value field
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        // Get the internal char[] of the string
        char[] chars = (char[]) valueField.get(original);

        // Modify the internal array (e.g., change 'H' to 'J')
        chars[0] = 'J';

        System.out.println("\nAfter modification:");
        System.out.println("original = " + original); // Will print "JelloWorld"

        // Observe if interned strings are affected
        String s2 = "HelloWorld";
        System.out.println("\ns2 = " + s2); // Might also print "JelloWorld"
    }
}

/*
*Output (Java 8 Example)
* Before modification:
original = HelloWorld

After modification:
original = JelloWorld

s2 = JelloWorld
*
* Why This Works (in Java 8):
Java 8 stores String content in a private final char[] value.
Even though the field is final, reflection can bypass it and mutate the internal array.
*
*
* Does This Work in Java 9+?
No (or not easily).
In Java 9+, String was reimplemented to use a byte[] value (with a coder flag), for better memory efficiency (Compact Strings).
Also, final fields and access to core internals are stricter due to the module system.
You’ll get an error or unexpected behavior unless you use --add-opens flags in Java 9+.
*
*
* Safer Way to Demonstrate Without Causing Global Impact
To avoid affecting interned strings:
String original = new String("HelloWorld");
* */
