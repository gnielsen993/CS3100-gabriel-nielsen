# Test 5 -- Short Answer Questions

**Q1.** Why do semaphores alone not prevent deadlock? Give an example.

> Semaphores enforce mutual exclusion (only one process accesses a resource at a time), but they don't prevent circular wait. In the Dining Philosophers problem, if all five philosophers execute `wait(left); wait(right)` simultaneously, each holds one chopstick and waits for the other -- deadlock. Extra rules (limiting concurrent eaters, requiring both-or-nothing pickup) are needed.

**Q2.** Compare first fit, best fit, and worst fit. Which is generally preferred and why?

> First fit picks the first sufficient hole (fast, low search overhead, reasonable fragmentation). Best fit picks the tightest hole (minimizes per-allocation waste but requires full search and can leave many tiny unusable fragments). Worst fit picks the largest hole (tends to increase external fragmentation). First fit is preferred for its balance of speed and fragmentation behavior.

**Q3.** Distinguish internal vs. external fragmentation.

> Internal fragmentation = unused space *within* an allocated block (block is bigger than what the process needs). External fragmentation = total free memory is sufficient but split into non-contiguous holes too small to satisfy a request. Fixed partitioning causes internal fragmentation; variable partitioning causes external fragmentation.

**Q4.** Why does compaction require dynamic relocation?

> Compaction physically moves allocated blocks in memory to consolidate free space. For a running process to continue correctly after being moved, the system must translate its virtual addresses to new physical locations at runtime. This requires hardware address-translation support (dynamic relocation). Without it, pointers and code references would break.

**Q5.** How does paging eliminate external fragmentation?

> Paging divides a process into fixed-size pages and maps each page to any free physical frame. Pages don't need to be contiguous in physical memory, so scattered free frames are usable. There are no "holes too small to fit" -- any single free frame can hold any page.

**Q6.** Describe the worst case of internal fragmentation in paging.

> If a process needs exactly n full pages plus 1 extra byte, the OS must allocate n+1 frames. The last frame wastes nearly an entire page (page_size - 1 byte).

**Q7.** Explain Belady's anomaly with the FIFO example.

> With reference string `1 2 3 4 1 2 5 1 2 3 4 5`, FIFO with 3 frames produces 9 page faults, but FIFO with 4 frames produces 10 faults. More memory leads to more faults because FIFO evicts based on arrival order, not future need, so the larger frame set keeps the wrong pages alive at critical moments.

**Q8.** How does optimal page replacement avoid Belady's anomaly?

> Optimal replacement always evicts the page whose next reference is farthest in the future. This guarantees the minimum fault rate for any frame count, so adding frames can never increase faults. It's impractical because it requires knowledge of future references.

**Q9.** Compare the counter vs. stack implementations of LRU.

> Counter: each page entry has a timestamp updated on every memory reference; evict the page with the smallest (oldest) timestamp. Stack: a doubly-linked list where each referenced page moves to the top; the bottom page is the LRU victim. The stack approach has O(1) eviction lookup but uses process address space memory and requires pointer updates on every reference.

**Q10.** What are the four necessary conditions for deadlock? Why are they "not completely independent"?

> Mutual exclusion, hold & wait, no preemption, and circular wait. They are interdependent: circular wait implies hold & wait (each process in the cycle is holding a resource while waiting). Mutual exclusion is what makes holding exclusive. Removing any one condition breaks the deadlock possibility.

**Q11.** What does a cycle in a Resource Allocation Graph mean, and what is the limitation of RAG cycle detection?

> A cycle means circular wait exists -- potential deadlock. Limitation: with multiple instances of a resource type, a cycle does not guarantee deadlock (a different instance might satisfy the request). For multi-instance resources, use a wait-for graph or Banker's algorithm instead.

**Q12.** Explain the Banker's algorithm and what "safe state" means.

> A safe state means the OS can find an ordering of all processes such that each can get its remaining needed resources, finish, and release everything for the next. Banker's algorithm tracks Allocation, Max, and Need (= Max - Allocation) per process plus an Available vector. On each request, it tentatively grants, then simulates: find any process whose Need <= Work (available), mark it finished, add its Allocation to Work, repeat. If all finish, the state is safe and the request is granted; otherwise, it's rolled back.
