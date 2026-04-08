# DS-Algo-Coding-Practice
Coding Practice for clearing Interviews

# Floor of a number when div by 200:
Given by: (A >= 0) ? (A / 200): ( (A % 200) == 0 ? (A / 200) : (A / 200) - 1 )

only 1 edge cond:
if(A < 0 && A%200 !=0)
    return (A/200) - 1
return (A/200)


# Ceil of a number when div by 20-0:
if(A >= 0 && A%200 !=0)
    return (A/200) + 1
return (A/200)

Subsets - arr or string elements/items picked should be only from the ones contained in the parent arr/string but can be picked in any order of sequence, also can be randomly dispersed here and there in main parent array Eg: Parent/main Arr: {1,2,3} then {3, 1} is a subset since ordering does not matter. But, {1, 4} is not a subset since 4 is outside element of the main parent array

SubSequences- Subset + ordering/sequence of elements should be maintained. Eg: Parent/main Arr: {1,2,3} then {1,3} is one subsequence, where ordering of 1 and 3 are maintained but any elements might be present in between. But {3, 1} is not a subsequence since ordering/sequence not maintained

SubArray/SubString - SubSequences + no dispersion ie always together with no element in between. Eg:  Parent/main Arr: {1,2,3} then {1, 2} or {1, 2, 3} all are subarray but {1, 3} is not a subarray since without 2 in between, they are not consecutively placed

Therefore,
All Subarrays are subsequences and Subsets.
All Subsequences are Subsets.
But all subsets are not subsequences and all subsets or subsequences are not subarrays/substrings.

## JAVA HACKS FOR OPTIMISATION
* In 2D Arrays, for ASC order SORT, use: Arrays.sort(input2DArr, (a, b) -> a[0] - b[0]); faster than: Arrays.sort(input2DArr, Comparator.comparingInt( (int[] input1DArr) -> input1DArr[0]));


## GOLDEN RULES OF THUMB FOR INTERVIEWS
* When tracking occurance:
  - Fixed/small charset (26 letters, 128 ASCII, 10 digits) → frequency array (even at the cost of multiple pass is more preferrable) over HashMap
    - use:
        int[] freq = new int[26];                                                                                                                                               
        for (char c : wordStr.toCharArray()) freq[c - 'a']++;
  - Unknown/large key space (strings, arbitrary integers, objects) → HashMap over frequency array
  - Prefer HashMap/freq arr over HashSet when we can harness the extra power of associated index with the elem either to shift pointers directly or to return indexes(eg: Two Sum, Longest Substr without Repeating Chars)
* Optimization Problem: When all possible subset(where order or sequence need NOT to be maintained) combinations -> mostly DP
* Optimization Problem: When all subsequence or subarray -> mostly Sliding Window
  - BUT for summation optimization problem, sliding window only works for all numbers are among 0 or +ve nos.
  - in case if there is -ve no, the sliding window logic breaks
  - prefix sum + hashmap is best for solving such problems

## REMEMBER SHORTCUTS:
- Set<Integer> set = new HashSet<>(Arrays.asList(/* nums boxed */));
- map.computeIfPresent(key, v -> new ArrayList<>().add(num);
- lookUpMap.put(key,lookUpMap.getOrDefault(key, 0) + 1)
- string.length() vs arr.length vs list.size()
- char[] c = wordStr.toCharArray() whereas wordStr = new String(c)
- for nums as new Integer[], then only HashSet<Integer> set = new HashSet<>(Arrays.asList(nums));

## KMP + LPS
KMP (pattern search in text):
- Build LPS array of pattern only → O(m) space
- Scan through text using two pointers + LPS for fallback
- Text is never stored or copied — just read character by character
- Total space: O(m) where m = pattern length

LPS alone (computing prefix-suffix for a string):
- You build LPS array for that string itself → O(n) space
- Without LPS, when a mismatch happens at position j in the pattern, you go back to the beginning of the pattern and move one position forward in the text.   
- With LPS, you skip to LPS[j-1] in the pattern — you already know those characters match.
- Total space: O(n) where n = string length

KMP with LPS:
    - O(n + m) — never backtracks in the text

So when people say KMP is space-efficient for pattern matching, it's because:
- Pattern is typically small (say 10 chars)
- Text can be huge (say 1GB file)
- You only need O(m) = O(10) extra space, not O(n) = O(1GB)

LPS is closer to: An automaton / state machine. Each index is a state, and on mismatch you follow failure links to a previous state. 
That's why KMP is often taught as a finite automaton.


## Dynamic Programming(DP)
* 1. Optimal Substructure — optimal solution to the problem contains optimal solutions to subproblems
* 2. Overlapping Subproblems — same subproblems are solved repeatedly

  Without overlapping subproblems, it's just regular recursion (divide and conquer). 
  Without optimal substructure, you can't build the final answer from subproblem answers.

## Dynamic Programming(DP) on linear transition
* Minimum Cost of Climbing Stairs
* House Robbers
* House Robber II
* Maximum Score which Questions to solve
* Minimum Ticket Pass Booking Cost to travel

## Dynamic Programming(DP) on every possible subsequences/subsets
* 0/1 Knapsack(values given array, given array of each weight to reach target) - single supply of each item, NOT hard fast to achieve the target but as much as possible, min/max
* Unbounded Knapsack(values given array, given array of each weight to reach target) - infinite supply of each item, NOT hard fast to achieve the target but as much as possible, min/max
* Rod Cutting(values given array, each weight to reach target is index range) - infinite supply of each item, hard fast to achieve the target, min/max
* Coin Change With Min Coins(values given array, each weight to reach target is values ie weight array = values array) - infinite supply of each item, hard fast to achieve the target, min/max
* Total Ways of Coin Change(values given array, each weight to reach target is values ie weight array = values array) - infinite supply of each item, hard fast to achieve the target, total ways/count
* Count Subset with Target Sum(values given array, each weight to reach target is values ie weight array = values array) - single supply of each item, hard fast to achieve the target, total ways/count

  * Note: General Thumb Rule of such problem:

      i) If infinite supply, during take each item, hold on to same index i after each take, and at base condition for last element check:
            If hard fast to reach the target check full condition if rem target % weight of 0th elem == 0(can fullfill the target or not), then only take all counts with rem target/weight of 0th elem, else if min/max then invalid state, for total ways 0
            If not hard fast, then simply rem target/weight of 0th elem(irrespective of fulfill the target or not)
         Else in case of single supply, move to previous index i-1 after each take, and at base condition for last element check:
            If hard fast to reach the target check full condition if rem target >= weight of 0th elem(can fullfill the target or not), then only take all counts with rem target/weight of 0th elem, else if min/max then invalid state, for total ways 0
            If not hard fast, then simply value of 0th element(irrespective of fulfill the target or not) or else 0 for both min/max or total ways

      ii) If min/max, mostly every counts of items are considered. So, do 1 + each recursive call and at the end of subset formation at base condition target == 0, return 0 always and consider default take value as either Integer MIN_VALUE or MAX_VALUE and return main logic as Math.min/max(take, notTake)
          If count total ways, then count of 1 is needed at the end of subset formation. So, at the end of subset formation at base condition target == 0, return 1 to mark it as a possible valid subset and consider default take value as 0 and return main logic as take + notTake
    
      iii) During Min calculation, in case of invalid state, returning invalid values should be marked as 1000000000 and not Integer.MAX_VALUE since adding any element to Integer.MAX_VALUE will result in integer overflow
           During Max calculation, in case of invalid state, returning invalid values should be marked as -1000000000 and not Integer.MIN_VALUE since adding any element to Integer.MIN_VALUE will result in integer underflow
            
      In all the problems mostly, the values are given in terms of array
      and the partly forming weights are either separate weight array(Knapsack) or values itself(coin change) or index itself(rod cutting) 

    
    // template with Pseudocode

    base condition when target == 0

    base condition when last index element ie i or ind == 0
    
    (optional)handle dp cache array to return stored values to prevent re-computation for overlapping sub-problems
    
    int notTake = f(i-1, weight)
    
    Initialise int take with 0 or Integer.MAX/MIN _VALUE // default
    
    if(weight >= weight_i)
        int take =  f(i-1 or i, weight - weight_i) // hold to i or not


    return main function logic as sum or min/max of take, notTake

## LL
In case of odd length LL, fast pointer point to last node, when we reach middle node with slow pointer. here fast.next == null when loop terminates
In case of even length LL, fast pointer will never point to last node. rather it will point/jump straight from (prev to last node) to null. fast will never point to last node. Here fast == null when loop terminates -> here slow stops at m2. If we want slow to stop at m1, then check if fast.next.next == null

Odd Length: first.next == null => slow points to middle node m
Even Length: first.next.next == null => slow points to middle node m1
             first == null => slow points to middle node m2

=-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-=
Memory  - LRU Cache
————————————————————————

TRACKING(2 step process):
======================

// maintain access in tracker
// - step a. move from earlier access order only for existing element
if(tracker.contains(key)){
removeInBetween(tracker, key);
}
// step-b. always add to end/rear for tracking the existing/non-existing element as recently accessed/used
tracker.offer(key);

-----------------------------------------------

SILENT EVICTION:
===============
map.remove((Integer)tracker.remove());

-----------------------------------------------

GET(Retrieve)
——————————
-> not present in map, -1. (Return)
TRACKING - NO
SILENT EVICTION - NO
CAPACITY CHECK - NO

-> present in map
TRACKING - YES (do tracking + return)
SILENT EVICTION - NO
CAPACITY CHECK - NO

-----------------------------------------------

PUT(Add)
——————
-> present in map
SILENT EVICTION - NO
CAPACITY CHECK - NO
TRACKING - YES (common action)
ADD to MAP - YES  (common action)

-> not present in map (new elem)
CAPACITY CHECK -
|-> YES  -> SILENT EVICTION - YES
|-> NO.  -> SILENT EVICTION - NO
TRACKING - YES (common action)
ADD to MAP - YES  (common action)

Note:
1. Tracking elem & ADD to map should come after checking the elem if present/not in map
2. While doing Capacity checking, consider after you add the element (virtually ie. Using + 1) will it exceed the capacity or not

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=