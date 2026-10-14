import java.util.ArrayDeque;
import java.util.Deque;

/** Decode a run-length-encoded string like "3[a2[c]]" into "accaccacc".
 *  Uses a stack to handle nested bracket groups.
 **/
public class RunLenEncodedStringDecoder {
    public static String decodeString(String s) {
        Deque<String> stringStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                stringStack.push(current.toString());
                countStack.push(num);
                current = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                StringBuilder prev = new StringBuilder(stringStack.pop());
                int count = countStack.pop();
                for (int i = 0; i < count; i++) {
                    prev.append(current);
                }
                current = prev;
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }
}

/*
Tracing decode_string("3[a2[c]]") yields "accaccacc" with these stack operations:
When 3 is read: num = 3
When first [ is read: stringStack gets "" (empty current), countStack gets 3, current resets to ""
When a is read: current = "a"
When 2 is read: num = 2
When second [ is read: stringStack gets "a", countStack gets 2, current resets to ""
When c is read: current = "c" — this is what current holds right before the first ]
When first ] is processed: pops "a" and 2, builds "a" + "cc" → current = "acc"
current = "acc" right before the second ] — pops "" and 3, builds "" + "accaccacc" → final result "accaccacc"


Without a separate countStack, the repetition multipliers for each nesting level would be lost.

When [ is encountered, both the current string and the current count must be saved — they are two distinct pieces of information.
A single string stack can save the pending partial string (e.g., "a" in 3[a2[c]]), but has no way to also store the integer 3.
With nested encodings like 3[a2[c]], multiple counts (3 and 2) can be pending at the same time, requiring a stack (not just one variable).
Without countStack, when ] is hit, you wouldn't know how many times to repeat current and append it to the previous string.
* */
