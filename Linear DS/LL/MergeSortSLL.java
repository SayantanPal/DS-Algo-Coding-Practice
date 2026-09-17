import model.SLLNode;

public class MergeSortSLL {
    public SLLNode mergeSort(SLLNode head){
        if(head == null || head.next == null) return head;
        SLLNode firstMiddleNode = SLLBasics.findFirstMiddleNode_v2(head);

        // split list into 2 equal halves at firstMiddleNode
        SLLNode head2 = firstMiddleNode.next;
        firstMiddleNode.next = null;

        // keep on breaking down or dividing the problem into smaller subproblems till the lead node reaches 1 single LL node
        SLLNode l = mergeSort(head);
        SLLNode r = mergeSort(head2);

        // conquer by merging back the sorted SLL nodes
        SLLNode mergedHead = SLLBasics.mergeSortedLL(l, r);
        return mergedHead;
    }
}
