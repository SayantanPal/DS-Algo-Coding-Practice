import model.SLLNode;

import java.util.HashMap;

public class SLLBasics {

    // Function to insert a node at the end of the linked list.
    public static SLLNode insertAtEndTailReadLast(SLLNode head, int x) {
        // code here
        if(head == null){
            return head = new SLLNode(x);
        }
        SLLNode curr = head;
        while(curr.next != null){ // SLL traversal
            curr = curr.next;
        }
        curr.next = new SLLNode(x); // insert at the end
        return head;
    }

    // insert at head is equivalent to insert before existing head
    public static SLLNode insertAtFrontHeadFirst(SLLNode head, int x) {
        // code here
        if(head == null){
            return head = new SLLNode(x);
        }
        SLLNode newNode = new SLLNode(x);
        newNode.next = head; // connect new node to existing old head
        return head = newNode; // new node becomes the new head
    }

    public static SLLNode removeFrontHeadFirst(SLLNode head) {
        if(head == null) return head;
        head = head.next;
        return head;
    }

    public static SLLNode removeEndTailRearLast_v2(SLLNode head){
        if(head == null) return head;
        if(head.next == null) return head = null;

        SLLNode curr = head;
        while(curr.next.next != null){
            curr = curr.next; // LL traversal: curr point to node previous to tail
        }
        curr.next = null;
        return head;
    }


    public static SLLNode removeEndTailRearLast(SLLNode head){
        if(head == null) return head;

        SLLNode curr = head;
        SLLNode prev = head;
        while(curr.next != null){
            prev = curr; // point to 1 node before current
            curr = curr.next; // LL traversal: curr point to node which equals nodeValue
        }
        prev.next = null;
        return head;
    }

    public static SLLNode insertBeforeIndex(SLLNode head, int data, int toBeInsertedBeforeIndex) {
        // code here
        SLLNode newNode = new SLLNode(data);
        if(head == null){ // insert at head
            return head = newNode;
        }
        if(toBeInsertedBeforeIndex == 0){ // insert left to existing head
            newNode.next = head; // connect new node to existing old head
            return head = newNode; // new node becomes the new head
        }

        // insert after current index node starting from head
        SLLNode curr = head;
        int i = 0;
        //  before target index, the index at which needs to be inserted is (toBeInsertedBeforeIndex - 2)
        while(curr.next != null && i < toBeInsertedBeforeIndex - 1){ // at end of loop, curr points to toBeInsertedBeforeIndex - 1 ie 1 node index before toBeInsertedBeforeIndex
            curr = curr.next;
            i++;
        }
        SLLNode nextListNodeChain = curr.next;
        curr.next = newNode;
        newNode.next = nextListNodeChain;
        return head;
    }

    public static SLLNode deleteAtIndex_v2(SLLNode head, int toBeDeletedIndex) {
        if(head == null) return null;
        if(toBeDeletedIndex == 0){
            head = head.next;
            return head;
        }
        SLLNode curr = head;
        int i = 0;

        // go one step before index 'toBeDeletedIndex' where toBeDeletedIndex is to be deleted
        while(curr.next != null && i < toBeDeletedIndex - 1){ // at end of loop, curr points to toBeDeletedIndex - 1 ie 1 node index before toBeDeletedIndex
            curr = curr.next;
            i++;
        }
        curr.next = curr.next.next;
        return head;
    }


    public static SLLNode deleteAtIndex(SLLNode head, int toBeDeletedIndex) {
        if(head == null) return null;
        if(toBeDeletedIndex == 0){
            head = head.next;
            return head;
        }
        SLLNode curr = head;
        SLLNode prev = null;
        int i = 0;
        // go one step before index 'toBeDeletedIndex' so that on exiting loop, curr points at 'toBeDeletedIndex' and prev points 1 node index before 'toBeDeletedIndex'
        while(curr.next != null && i < toBeDeletedIndex){
            prev = curr;
            curr = curr.next;
            i++;
        }
        prev.next = curr.next;
        return head;
    }

    // delete input node passed as argument
    // assume that input node definitely exists in SLL
    // node might not be head of SLL - it can be any node
    // node is definitely not tail node, so node.next always exists as not null
    public static void deleteNode(SLLNode node){
        // approach: if input node is to be deleted,
        // make/clone current input node as next node with data copy
        // drop the next node since current node already behaves as next node
        if(node.next != null) {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

    public static SLLNode detectCycleUsingHashing(SLLNode head) {
        //Your code goes here
        if(head == null) return null;
        HashMap<SLLNode, Integer> map = new HashMap<>();

        while(head.next != null){
            if(!map.containsKey(head)){
                map.put(head, 1);
            } else{
                return head;
            }
            head = head.next;
        }
        return null;
    }

    // for arr: [1, 2, 3 , 4], it will return 3 as middle element/node
    public static SLLNode findMiddleNode(SLLNode head) {
        if(head == null || head.next == null) return head;
        SLLNode slow = head;
        SLLNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

// Event queue that starts reprocessing old events due to misconfigured pointers.

    // Link: https://leetcode.com/problems/linked-list-cycle-ii/
    // Link: https://leetcode.com/problems/linked-list-cycle/description/
    public static SLLNode detectCycleUsingFloydCycleOrTortoiseHare(SLLNode head) {
        //Your code goes here

        SLLNode slow = head;
        SLLNode fast = head;

        boolean hasCycle = false;
        while(fast != null && fast.next != null){ // in case of cyclic LL, this condition will never fail
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                hasCycle = true;
                break;
            }
        }

        if(!hasCycle) return null;

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    // Link: https://leetcode.com/problems/reverse-linked-list/description/
    public static SLLNode reverseSLL(SLLNode head) {
        if(head == null) return head;

        SLLNode prev = null;
        SLLNode curr = head;

        while(curr != null){
            SLLNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static SLLNode reverseSLLRec(SLLNode head) {
        if(head == null || head.next == null) return head;

        SLLNode newHead = reverseSLLRec(head.next);
        SLLNode smallerReversedLL = head.next; // initially head.next points to smaller subproblem which is already reversed
        smallerReversedLL.next = head;
        head.next = null;
        return newHead;
    }

    public SLLNode reverseLLBetweenRange(SLLNode head, SLLNode tail){
        if(head == null) return head;
        SLLNode prev = null;
        SLLNode curr = head;
        while(curr != null && curr != tail.next){
            SLLNode after = curr.next;
            curr.next = prev;
            prev = curr;
            curr = after;
        }
        return prev;
    }
    public SLLNode reverseList(SLLNode head, int k) {
        if(k == 1) return head;
        SLLNode dummy = new SLLNode(-1);
        dummy.next = head;
        SLLNode curr = dummy;
        while(curr.next != null){
            SLLNode before = curr;
            SLLNode start = curr.next;
            for(int i = 1; i <= k; i++){
                if(curr.next == null){
                    break;
                }
                curr = curr.next;
            }
            SLLNode end = curr;
            SLLNode after = curr.next;

            before.next = null;
            end.next = null;

            SLLNode reversedhead = reverseLLBetweenRange(start, end);

            before.next = reversedhead;
            start.next = after;
            curr = start;
        }
        return dummy.next;
    }

    public static SLLNode cloneSLL(SLLNode head){
        SLLNode dummy = new SLLNode(-1);
        SLLNode cloneCurr = dummy;
        SLLNode originalCurr = head;
        while(originalCurr != null){
            cloneCurr.next = new SLLNode(originalCurr.data);
            cloneCurr = cloneCurr.next;
            originalCurr = originalCurr.next;
        }
        SLLNode headOfClone = dummy.next;
        return headOfClone;
    }

    public static SLLNode cloneSLLWithRandom(SLLNode head) {
        SLLNode dummy = new SLLNode(-1);
        SLLNode cloneCurr = dummy;
        SLLNode originalCurr = head;
        HashMap<SLLNode, SLLNode> map = new HashMap<>();
        while(originalCurr != null){
            cloneCurr.next = new SLLNode(originalCurr.data);
            cloneCurr = cloneCurr.next;
            map.put(originalCurr, cloneCurr);
            originalCurr = originalCurr.next;
        }
        dummy = dummy.next;
        SLLNode headOfClone = dummy;

        cloneCurr = headOfClone;
        originalCurr = head;
        while(originalCurr != null){
            cloneCurr.random = map.get(originalCurr.random);
            cloneCurr = cloneCurr.next;
            originalCurr = originalCurr.next;
        }
        return headOfClone;
    }

    // without using hashmap
    public static SLLNode cloneSLLWithRandom_v2(SLLNode head) {
        return null;
    }

    public static SLLNode findFirstMiddleNode_v1(SLLNode head) { // finding 2nd middle in case of even length
        if(head == null || head.next == null) return head;
        int l = 0;
        SLLNode curr = head;
        while(curr != null){
            curr = curr.next;
            l++;
        }
        int midPos = (l - 1)/2;
        curr = head;
        for(int i = 1; i <= midPos; i++){
            curr = curr.next;
        }
        return curr;
    }

    public static SLLNode findFirstMiddleNode_v2(SLLNode head){
        if(head == null || head.next == null) return head;
        SLLNode slow = head, fast = head;
        // fast.next.next!=null stop check for even length SLL
        // fast.next!=null stop check for odd length SLL
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static SLLNode findSecondMiddleNode(SLLNode head){
        if(head == null || head.next == null) return head;
        SLLNode slow = head, fast = head;
        // fast!=null stop check for even length SLL
        // fast.next!=null stop check for odd length SLL
        while(fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(SLLNode A) {
        SLLNode firstMiddleNode = findFirstMiddleNode_v2(A);

        // split list into 2 equal halves at firstMiddleNode
        SLLNode head2 = firstMiddleNode.next;
        firstMiddleNode.next = null;

        // reverse the 2nd half of SLL
        head2 = reverseSLL(head2);

        SLLNode c1 = A;
        SLLNode c2 = head2;

        while(c1 != null && c2 != null){
            if(c1.data != c2.data) return false;
            c1 = c1.next;
            c2 = c2.next;
        }

        return true;
    }

    public int maxPalindromicLen(SLLNode head) {
        if(head == null) return 0;
        SLLNode prev = null;
        SLLNode curr = head;
        int maxPalindromeLen = 1; // in case only 1 node is present, that itself is paindrome
        while(curr.next != null){
            SLLNode after = curr.next;
            curr.next = prev;

            SLLNode l = prev;
            SLLNode r = after;
            int len = 1;
            while(l!=null && r!=null){
                if(l.data != r.data){
                    break;
                }
                len += 2;
                l = l.next;
                r = r.next;
            }
            maxPalindromeLen = Math.max(maxPalindromeLen, len);

            l = curr;
            r = after;
            len = 0;
            while(l!=null && r!=null){
                if(l.data != r.data){
                    break;
                }
                len += 2;
                l = l.next;
                r = r.next;
            }
            maxPalindromeLen = Math.max(maxPalindromeLen, len);


            prev = curr;
            curr = after;
        }
        return maxPalindromeLen;
    }

    // In contrast to arrays, in case of SLL, merging 2 sorted LL into 1 has S.C. = O(1)
    // T.C. is same across both arrays and SLL which is TC = O(N + M)
    public static SLLNode mergeSortedLL(SLLNode head1, SLLNode head2){
        SLLNode head3 = new SLLNode(-1);
        SLLNode c3 = head3;
        SLLNode c1 = head1, c2 = head2;

        while(c1 != null && c2 != null){
            if(c1.data < c2.data){
                c3.next = c1;
                c1 = c1.next;
            }else{
                c3.next = c2;
                c2 = c2.next;
            }
            c3 = c3.next;
        }

        while(c1 != null){
            c3.next = c1;
            c1 = c1.next;
            c3 = c3.next;
        }

        while(c2 != null){
            c3.next = c2;
            c2 = c2.next;
            c3 = c3.next;
        }
        return head3.next;
    }

    public SLLNode deleteDuplicatesFromSortedSLL(SLLNode head) {
        SLLNode curr = head;
        while(curr != null){
            while(curr.next != null && curr.next.data == curr.data){
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }

    public SLLNode removeKthFromEnd(SLLNode head, int targetPosK) { // assume: targetPosK is equivalent to 1-based indexing
        int n = 1;
        SLLNode curr = head;

        while(curr.next != null){
            curr = curr.next;
            n++;
        }

        if(n == 1) return head = null;

        // GIVEN in PROB STAT: If targetPosK is greater than the size of the list, remove the first node of the list.
        // Edge case: when targetPosK == n
        if(n - targetPosK <= 0) return head.next;

        curr = head;

        // when B strictly < n, then
        // from front, for k steps, we need (k - 1) jumps ie from back it is ( (n - k) - 1)) jumps
        // (k - 1) total jumps is given by 0 to ((k - 1) - 1) or 1 to (k - 1)
        for(int i = 1; i <= ( (n - targetPosK) - 1); i++){ //  for(int i = 0; i < ((n - B) - 1); i++){
            curr = curr.next;
        }

        if(curr.next!=null){
            curr.next = curr.next.next;
        }

        return head;
    }

    // Link: https://leetcode.com/problems/intersection-of-two-linked-lists/
    public SLLNode getIntersectionNode(SLLNode headA, SLLNode headB) {
        int n1 = 1, n2 = 1;
        SLLNode currA = headA, currB = headB;

        while(currA!=null){
            currA = currA.next;
            n1++;
        }

        while(currB!=null){
            currB = currB.next;
            n2++;
        }

        SLLNode longer, shorter;
        int diffOfLenToSkip;
        if(n1 > n2){
            longer = headA;
            shorter = headB;
            diffOfLenToSkip = n1 - n2;
        }else{
            longer = headB;
            shorter = headA;
            diffOfLenToSkip = n2 - n1;
        }

        for(int i = 1; i <= diffOfLenToSkip; i++){
            longer = longer.next;
        }

        while(longer != null && shorter != null){
            if(longer == shorter) return longer;
            longer = longer.next;
            shorter = shorter.next;
        }

        return null;
    }
}
