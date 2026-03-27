# Test 5 -- Answer Key (True/False)

| #  | Answer | Explanation |
|----|--------|-------------|
| 1  | **True** | Semaphores enforce mutual exclusion but don't prevent circular wait or other deadlock conditions. |
| 2  | **True** | Limiting to 4 eaters prevents all 5 from grabbing one chopstick each, breaking circular wait. |
| 3  | **False** | Best fit requires a full search (slower) and can leave many tiny unusable fragments. First fit is generally preferred. |
| 4  | **False** | Worst fit tends to *increase* external fragmentation, not reduce it. |
| 5  | **True** | By definition, internal fragmentation is unused space inside an allocated block. |
| 6  | **False** | Fixed partitioning primarily causes *internal* fragmentation. External fragmentation is the issue in *variable* partitioning. |
| 7  | **True** | Compaction merges scattered free holes but requires the ability to move processes at runtime (dynamic relocation). |
| 8  | **False** | Even after compaction, total free memory may still be insufficient for the new process. |
| 9  | **True** | Pages map to any free frame, so scattered free frames are usable; no external fragmentation. |
| 10 | **False** | Paging eliminates external fragmentation but can still have internal fragmentation in the last page. |
| 11 | **True** | If a process needs n pages + 1 byte, n+1 frames are allocated, wasting nearly a full page. |
| 12 | **True** | The page table + MMU translate virtual page numbers to physical frame numbers. |
| 13 | **True** | Shared pages (e.g., shared libraries) let multiple processes reference the same physical frame. |
| 14 | **False** | Demand paging loads pages *only when referenced*, not all at startup. |
| 15 | **False** | They *increase* the degree of multiprogramming by reducing per-process memory footprint. |
| 16 | **True** | A page fault is triggered when the referenced page is not resident in RAM. |
| 17 | **True** | The TLB caches recent translations, reducing the need for full page-table lookups. |
| 18 | **False** | Belady's anomaly says it *can* happen with certain algorithms (FIFO), not that it *always* happens. |
| 19 | **True** | FIFO is the classic algorithm that exhibits Belady's anomaly. |
| 20 | **False** | The opposite: 3 frames = 9 faults, 4 frames = 10 faults (this IS the anomaly). |
| 21 | **True** | Optimal replacement picks the page with the farthest future reference for eviction. |
| 22 | **True** | Optimal guarantees minimum faults for any frame count, so more frames never hurts. |
| 23 | **False** | It requires knowledge of future references, which is impossible in practice. |
| 24 | **False** | LRU is a stack-based algorithm and is immune to Belady's anomaly. |
| 25 | **True** | Each page-table entry gets a time-of-use field updated from a logical clock on each reference. |
| 26 | **False** | The top of the stack is the *most* recently used page; the bottom is the least recently used. |
| 27 | **True** | The stack uses a doubly linked list with head (top/MRU) and tail (bottom/LRU) pointers. |
| 28 | **True** | Clock/second-chance approximates LRU using a circular list and reference bits. |
| 29 | **False** | Clock approximates LRU (a stack algorithm) and is resilient to Belady's anomaly. |
| 30 | **True** | All four conditions (mutual exclusion, hold & wait, no preemption, circular wait) must hold together. |
| 31 | **False** | They are interdependent -- e.g., circular wait implies hold & wait. |
| 32 | **True** | Circular wait is the fourth necessary condition. |
| 33 | **True** | Without preemption, resources can only be freed voluntarily. |
| 34 | **False** | With multiple instances per resource type, a cycle does not guarantee deadlock. |
| 35 | **True** | No cycle = no circular wait = no deadlock. |
| 36 | **False** | RAG cycle detection only works reliably with *single-instance* resource types. |
| 37 | **True** | Wait-for graphs accurately detect deadlock even with multi-instance resources. |
| 38 | **True** | Safe state = a completion sequence exists for all processes. |
| 39 | **True** | Need = Max - Allocation is a core formula in Banker's algorithm. |
| 40 | **False** | The request is *rolled back* (not permanently denied); it may be retried later when conditions change. |
| 41 | **True** | Moving processes requires translating their addresses to new physical locations at runtime. |
| 42 | **True** | Variable partitioning creates scattered free holes = external fragmentation. |
| 43 | **False** | Not necessarily -- the freed space may already be adjacent to existing free space, requiring no relocation. |
| 44 | **False** | First fit has *lower* search overhead (stops at the first sufficient hole); best fit searches everything. |
| 45 | **False** | *External* fragmentation is the primary issue in variable partitioning; internal fragmentation is the issue in *fixed* partitioning. |
| 46 | **True** | Fewer resident pages per process = more processes can fit = higher multiprogramming. |
| 47 | **True** | Keeping the working set in memory reduces the frequency and impact of page faults. |
| 48 | **True** | Banker's algorithm handles multi-instance resources where RAG cycle detection falls short. |
| 49 | **True** | The page table gives each process a contiguous virtual view while physical frames can be anywhere. |
| 50 | **True** | Fixed partitioning causes internal fragmentation; external fragmentation is specific to variable partitioning in contiguous allocation. |
