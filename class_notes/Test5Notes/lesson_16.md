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

## Explain Beladys anomaly with an example

### How can we address this issue through optimal page replacement


## How can we design a practical scheme to address the feasibility issues of optimal page replacement while offering resiliance to belodys anomaly
