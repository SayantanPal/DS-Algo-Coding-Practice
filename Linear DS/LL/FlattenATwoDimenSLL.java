import model.SLLNode;
/*
* Flatten a linked list:
Problem Description

Given a linked list where every node represents a linked list and contains two pointers of its type:
Pointer to next node in the main list (right pointer)
Pointer to a linked list where this node is head (down pointer). All linked lists are sorted.
You are asked to flatten the linked list into a single list. Use down pointer to link nodes of the flattened list. The flattened linked list should also be sorted.

Problem Constraints:
1 <= Total nodes in the list <= 100000
1 <= Value of node <= 109

Input Format:
The only argument given is head pointer of the doubly linked list.

Output Format: Return the head pointer of the Flattened list.



Example Input
Input 1:

   3 -> 4 -> 20 -> 20 ->30
   |    |    |     |    |
   7    11   22    20   31
   |               |    |
   7               28   39
   |               |
   8               39
Input 2:

   2 -> 4
   |    |
   7    11
   |
   7


Example Output
Output 1: 3 -> 4 -> 7 -> 7 -> 8 -> 11 -> 20 -> 20 -> 20 -> 22 -> 28 -> 30 -> 31 -> 39 -> 39
Output 2:  2 -> 4 -> 7 -> 7 -> 11

Example Explanation
Explanation 1: The return linked list is the flatten sorted list.
*
*
* */

/*
* What if we were given only two lists how we would have mergerd them?
The idea is to extend the same on multiple lists, select any two list and merge them to make a single list.
Now we have (total - 1) lists to merge, again repeat the above process untill we have only one list left.
* */

public class FlattenATwoDimenSLL {

    // vertical merging of two sorted SLL
    SLLNode mergeSortedLLWithDownPtr(SLLNode head1, SLLNode head2){
        SLLNode c1 = head1, c2 = head2;
        SLLNode mergedHead = new SLLNode(-1);
        SLLNode c3 = mergedHead;
        while(c1!=null && c2!=null){
            if(c1.data < c2.data){
                c3.down = c1;
                c1 = c1.down;
            }else{
                c3.down = c2;
                c2 = c2.down;
            }
            c3 = c3.down;
        }
        while(c1!=null){
            c3.down = c1;
            c1 = c1.down;
            c3 = c3.down;
        }
        while(c2!=null){
            c3.down = c2;
            c2 = c2.down;
            c3 = c3.down;
        }
        mergedHead = mergedHead.down;
        return mergedHead;
    }

    SLLNode flatten(SLLNode root) {
        if(root == null || root.next == null) return root;

        SLLNode curr = root;
        SLLNode mergedHead = null;
        while(curr.next != null){
            SLLNode prev = curr;
            curr = curr.next;
            SLLNode after = curr.next;

            prev.next = null; // isloate vertical chain from horizontal chain
            curr.next = null; // isloate vertical chain from horizontal chain
            mergedHead = mergeSortedLLWithDownPtr(prev, curr);
            mergedHead.next = after;
            curr = mergedHead;
        }
        return mergedHead;
    }
}
