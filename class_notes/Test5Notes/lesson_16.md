# Lesson 16

## Explain the worst scenario of internal fragmentation in paging

A process needs n pages + 1 byte, but since they are fixed sized, n + 1 frames would be allocated, wasting almost a full page of memory

## For a new process, how does OS provide a separation between programmers view of memory and actual physical memory in paging

The OS keeps a page table that translates the process’s virtual pages into physical frames. Each memory reference is converted by the MMU so the process works with a contiguous, private virtual address space while the OS maps the pages wherever free frames exist in RAM.

## How can shared pagegs save memory space

By letting multiple processes map the same physical page into their distinct virtual spaces (e.g., shared libraries or copy-on-write regions), the OS avoids loading duplicate copies, so a single frame satisfies several address spaces and overall RAM usage drops.

## How can demand paging and swapping increase degree of multiprogramming

Demand paging only loads the pages a process actually touches, so each process needs fewer frames at once. Swapping lets the OS move idle processes’ pages to disk when RAM is tight. Together they shrink the resident set per process, freeing frames for more processes and boosting the number of concurrent jobs the CPU can multiplex.

## How can we increase systsem efficiency in handling a page fault

Reduce page-fault overhead by using a TLB to cache recent translations, batching page faults to amortize disk seeks, prefetching likely-needed pages, and keeping frequently used pages resident via working-set tracking so page faults happen less often and have less impact.

When a process references a page that is not resident in memory, the MMU raises a page fault. The OS pauses the process, locates an empty frame (evicting a resident page if needed), reads the missing page from disk into that frame, updates the page table and TLB, and then resumes the process. Choosing which resident page to evict is the page-replacement problem; algorithms range from optimal (future knowledge) to practical approximations like LRU, second-chance, and clock to minimize future faults while balancing implementation cost. Keeping the working set in memory and using page-replacement heuristics that avoid Belady’s anomalies are key to efficient handling.

## Explain Beladys anomaly with an example

Belady’s anomaly is when some policies (notably FIFO) incur more page faults after you give a process more frames. Using the reference string `1 2 3 4 1 2 5 1 2 3 4 5`, FIFO with 3 frames faults nine times, but boosting the allocation to four frames raises the count to ten: the newcomer frame holds a page that the algorithm will keep past its next use while evicting another page needed sooner, so the order of future faults shifts and the total increases despite more RAM.

That concrete sequence shows why Belady’s anomaly is paradoxical—adding memory should help, yet FIFO’s ignorance of upcoming references lets the larger frame set keep the wrong pages alive just long enough to cause an extra fault.

### How can we address this issue through optimal page replacement

Optimal replacement identifies the resident page whose next reference lies farthest in the future (or not at all) and evicts it, so it never makes the wrong eviction and can’t show Belady’s anomaly. While real OSes cannot predict the future perfectly, this optimal behavior defines the lowest possible fault rate; heuristics such as aging counters or reuse-distance estimates try to mimic it, and keeping the working set resident further narrows the gap to optimal.

## How can we design a practical scheme to address the feasibility issues of optimal page replacement while offering resiliance to belodys anomaly
Clock (second-chance) with reference bits is a practical scheme: it gives each page a chance to stay if it was recently referenced and advances a circular pointer to evict older, unreferenced pages, so the policy behaves like LRU without tracking precise access order and cannot swing into Belady’s- anomaly territory. Combining that with working-set tracking or aging keeps the active pages resident, while reference/age bits feed into the clock scan to avoid evicting needed pages, approximating optimal behavior with manageable hardware support.

### Illustration of page faults for `1 2 3 4 1 2 5 1 2 3 4 5` with FIFO + 4 frames

| Reference | Frames (oldest→newest) | Fault? | Notes |
|-----------|------------------------|--------|-------|
| 1         | 1                      | Yes    | Load page 1 (frames were empty). |
| 2         | 1 2                    | Yes    | Load page 2. |
| 3         | 1 2 3                  | Yes    | Load page 3. |
| 4         | 1 2 3 4                | Yes    | Load page 4 fills all frames. |
| 1         | 1 2 3 4                | No     | Hit, no eviction needed. |
| 2         | 1 2 3 4                | No     | Hit again. |
| 5         | 5 2 3 4                | Yes    | FIFO evicts oldest (1) to bring in 5. |
| 1         | 5 2 3 4                | Yes    | 1 was evicted, so we fault and replace 2 (next oldest). |
| 2         | 5 1 3 4                | Yes    | Fault; evict 3 (FIFO order) to load 2. |
| 3         | 5 1 4 2                | Yes    | Fault; evict 4, load 3. |
| 4         | 5 1 4 2                | Yes    | Fault; evict 5, load 4. |
| 5         | 5 1 4 2                | Yes    | Fault; evict 1, load 5 (frame set rotates). |

Counting faults shows ten occurrences; several happen after the working set (1-5) has already been seen once, because FIFO keeps replacing pages with the oldest load order instead of predicting that 5 or 1 is about to be reused. Every fault triggers the OS’s fault handling (stop, load from disk, update tables, resume), so this example makes the race between extra frames and poor eviction visible.
