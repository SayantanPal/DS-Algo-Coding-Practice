import model.SLLNode;
/*
* Problem Description

Given a singly linked list A

A: A0 → A1 → … → An-1 → An
reorder it to: A0 → An → A1 → An-1 → A2 → An-2 → …
*
*
You must do this in-place without altering the nodes' values.
Problem Constraints: 1 <= |A| <= 106

Input Format : The first and the only argument of input contains a pointer to the head of the linked list A.
Output Format: Return a pointer to the head of the modified linked list.

Example Input
Input 1: A = [1, 2, 3, 4, 5]
Input 2: A = [1, 2, 3, 4]

Example Output

Output 1: [1, 5, 2, 4, 3]
Output 2: [1, 4, 2, 3]

Example Explanation
Explanation 1: The array will be arranged to [A0, An, A1, An-1, A2].
Explanation 2: The array will be arranged to [A0, An, A1, An-1, A2].

*
* */

/*
Idea -
We can try to break down the solution approach into majorly three parts.
Firstly, we try to break the list from the middle into two separate linked lists.
Now, we reverse the latter half of the linked list.
Finally, we would merge the lists so that the nodes alternate to get the required answer.
*/
public class ReorderSLL {
    public SLLNode findFirstMiddleNode(SLLNode head){
        if(head == null || head.next == null) return head;
        SLLNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public SLLNode mergeLL(SLLNode headA, SLLNode headB){
        SLLNode c1 = headA, c2 = headB;
        SLLNode mergedHead = new SLLNode(-1);
        SLLNode c3 = mergedHead;
        int i = 1;
        while(c1!=null && c2!=null){
            if(i%2 == 0){
                c3.next = new SLLNode(c2.data);
                c2 = c2.next;
            }else{
                c3.next = new SLLNode(c1.data);
                c1 = c1.next;
            }
            i++;
            c3 = c3.next;
        }
        while(c1!=null){
            c3.next = new SLLNode(c1.data);
            c1 = c1.next;
            c3 = c3.next;
        }

        while(c2!=null){
            c3.next = new SLLNode(c2.data);
            c2 = c2.next;
            c3 = c3.next;
        }
        mergedHead = mergedHead.next;
        return mergedHead;
    }

    public SLLNode reverseSLL(SLLNode head){
        SLLNode prev = null;
        SLLNode curr = head;
        while(curr!=null){
            SLLNode after = curr.next;
            curr.next = prev;
            prev = curr;
            curr = after;
        }
        return prev;
    }

    public SLLNode reorderList(SLLNode A) {
        if(A == null || A.next == null) return A;
        SLLNode middleNode = findFirstMiddleNode(A);
        SLLNode headA = A;
        SLLNode headB = middleNode.next;
        middleNode.next = null;

        SLLNode reversedHeadB = reverseSLL(headB);
        SLLNode mergedHead = mergeLL(headA, reversedHeadB);
        return mergedHead;
    }
}
