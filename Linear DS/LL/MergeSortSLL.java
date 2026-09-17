import model.SLLNode;

public class MergeSortSLL {
    public SLLNode mergeSort(SLLNode head){
        if(head == null || head.next == null) return head;
        SLLNode middleNode = SLLBasics.findFirstMiddleNode_v2(head);
        SLLNode head2 = middleNode.next;
        middleNode.next = null;

        SLLNode l = mergeSort(head);
        SLLNode r = mergeSort(head2);

        SLLNode mergedHead = SLLBasics.mergeSortedLL(l, r);
        return mergedHead;
    }
}
