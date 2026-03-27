# Test 5 -- Topic Summary (Study Guide)

### A. Semaphores & Deadlock (Lesson 15 / 17)

- **Semaphore** = counter controlling concurrent access to a shared resource. Binary semaphore = mutex (one at a time).
- Semaphores provide **synchronization/mutual exclusion** but do **not** prevent deadlock on their own.
- Dining Philosophers: if all do `wait(left); wait(right)` simultaneously, everyone grabs one chopstick and waits forever = deadlock.
- Deadlock solutions for philosophers: limit eaters to 4 at a time, or only pick up if both chopsticks are available.

### B. Memory Allocation -- Contiguous (Lesson 15)

- **Variable partitioning** strategies:
  - **First fit** -- first hole big enough (fast, decent fragmentation).
  - **Best fit** -- smallest sufficient hole (less waste per alloc, but slow full search).
  - **Worst fit** -- largest hole (tends to increase external fragmentation).
- **Internal fragmentation** = wasted space *inside* an allocated block (block bigger than needed).
- **External fragmentation** = free memory split into scattered holes *outside* allocations; happens in **variable partitioning**.
- **Fixed partitioning** primarily suffers from *internal* fragmentation.
- **Compaction** = moving allocated blocks together to merge free holes. Requires **dynamic relocation** (address translation hardware). Does **not** guarantee enough total memory exists.

### C. Paging (Lesson 15 / 16)

- **Paging** breaks processes into fixed-size pages mapped to any free physical frame -- eliminates external fragmentation without compaction.
- Internal fragmentation can still occur in the **last page** of a process.
- **Worst-case internal fragmentation**: process needs n pages + 1 byte, so n+1 frames allocated, wasting nearly a full page.
- OS uses a **page table** (translated by MMU) to separate the programmer's contiguous virtual view from scattered physical frames.
- **Shared pages**: multiple processes map the same physical frame (shared libraries, COW) -- saves RAM.
- **Demand paging**: only load pages actually touched; combined with **swapping** idle pages to disk, increases degree of multiprogramming.

### D. Page Faults & Replacement (Lesson 16 / 17)

- **Page fault**: process references a page not in RAM. OS pauses process, finds/evicts a frame, loads page from disk, updates page table & TLB, resumes.
- Reduce faults: TLB caching, prefetching, working-set tracking.
- **Belady's anomaly**: with FIFO, *more frames can cause more faults*. Example string `1 2 3 4 1 2 5 1 2 3 4 5`: 3 frames = 9 faults, 4 frames = 10 faults.
- **Optimal replacement**: evict page whose next use is farthest in future -- lowest fault rate, immune to Belady's anomaly, but requires future knowledge (impractical).
- **LRU** (Least Recently Used): approximate optimal; immune to Belady's anomaly. Implementation challenges:
  - **Counters**: each page gets a time-of-use field updated on every reference; evict lowest timestamp.
  - **Stacks**: doubly-linked list of page numbers; referenced page moves to top, bottom = LRU victim. Stack consumes address-space memory.
- **Clock / Second-Chance**: circular pointer + reference bit; practical LRU approximation, resilient to Belady's anomaly.

### E. Deadlock Conditions & Avoidance (Lesson 17)

- **Four necessary conditions** (all must hold simultaneously):
  1. **Mutual exclusion** -- at least one resource non-shareable.
  2. **Hold & wait** -- process holds resource(s) while waiting for more.
  3. **No preemption** -- resources can't be forcibly taken.
  4. **Circular wait** -- cycle of processes each waiting on the next.
- These conditions are **not independent** -- they reinforce each other.
- **Resource Allocation Graph (RAG)**:
  - Cycle in RAG implies circular wait. No cycle = no deadlock.
  - Limitation: cycle detection only reliable when each resource type has **a single instance**.
  - Multi-instance fix: convert to **wait-for graph** or use **Banker's algorithm**.
- **Safe state**: OS can find a completion sequence for all processes. Banker's algorithm checks this using `Allocation`, `Max`, `Need = Max - Allocation`, and `Work` (available) vectors.
- **RAG-based avoidance**: tentatively add request edge; if cycle forms, deny request.
