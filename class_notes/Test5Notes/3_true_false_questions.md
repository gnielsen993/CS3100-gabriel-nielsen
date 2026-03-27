# Test 5 -- True/False Questions

1. A semaphore guarantees mutual exclusion but does not by itself guarantee freedom from deadlock.
2. In the Dining Philosophers problem, limiting the number of simultaneous eaters to four is a valid deadlock prevention strategy.
3. Best fit is generally preferred over first fit because it always produces less external fragmentation.
4. Worst fit tends to leave large remaining holes, which generally reduces external fragmentation.
5. Internal fragmentation occurs when a process is given more memory than it actually uses within its allocated block.
6. External fragmentation can occur in fixed partitioning for contiguous memory allocation.
7. Compaction eliminates external fragmentation by moving allocated blocks together, but it requires dynamic relocation support.
8. Compaction guarantees that a new process can be allocated its requested memory space.
9. Paging eliminates external fragmentation by mapping fixed-size pages to any free physical frame.
10. Paging completely eliminates all forms of fragmentation (both internal and external).
11. The worst case of internal fragmentation in paging wastes nearly an entire page.
12. The page table translates a process's virtual addresses to physical frame addresses via the MMU.
13. Shared pages allow multiple processes to use the same physical frame, saving memory.
14. Demand paging loads all of a process's pages into memory at startup.
15. Swapping combined with demand paging decreases the degree of multiprogramming.
16. A page fault occurs when a process references a page not currently in physical memory.
17. The TLB (Translation Lookaside Buffer) helps reduce page fault overhead by caching recent page-to-frame translations.
18. Belady's anomaly states that increasing the number of frames always increases page faults.
19. Belady's anomaly can occur with the FIFO page replacement algorithm.
20. With reference string `1 2 3 4 1 2 5 1 2 3 4 5`, FIFO with 4 frames produces fewer faults than FIFO with 3 frames.
21. Optimal page replacement evicts the page whose next reference is farthest in the future.
22. Optimal page replacement is immune to Belady's anomaly.
23. Optimal page replacement is practical for real operating systems because future references can be predicted efficiently.
24. LRU page replacement is susceptible to Belady's anomaly.
25. The counter-based LRU implementation associates a time-of-use field with each page-table entry.
26. In the stack-based LRU implementation, the page at the top of the stack is the least recently used page.
27. The stack-based LRU approach uses a doubly linked list with head and tail pointers.
28. The clock (second-chance) algorithm is a practical approximation of LRU.
29. The clock algorithm is susceptible to Belady's anomaly.
30. All four necessary conditions for deadlock must hold simultaneously for deadlock to occur.
31. The four necessary conditions for deadlock are completely independent of each other.
32. Circular wait is one of the four necessary conditions for deadlock.
33. If no preemption is allowed, the only way to break a deadlock impasse is for a process to voluntarily release its resources.
34. A cycle in a Resource Allocation Graph always guarantees deadlock regardless of the number of resource instances.
35. If a Resource Allocation Graph has no cycle, the system is deadlock-free.
36. The RAG cycle-detection approach works correctly when each resource type has multiple instances.
37. A wait-for graph can be used to detect deadlock when resource types have multiple instances.
38. A system is in a safe state if the OS can find a sequence in which all processes can complete.
39. The Banker's algorithm uses the formula Need = Max - Allocation.
40. In the Banker's algorithm, if no safe sequence exists after tentatively granting a request, the request is permanently denied.
41. Compaction only works in systems that support dynamic relocation because processes must be moved to different physical addresses at runtime.
42. In variable partitioning, external fragmentation occurs because free memory becomes split into separated holes.
43. For compaction, all processes in memory must be relocated every time any process terminates.
44. First fit has higher search overhead than best fit.
45. Internal fragmentation is the primary issue in variable partitioning.
46. Demand paging and swapping together allow more processes to be in memory simultaneously, increasing multiprogramming.
47. Working-set tracking helps keep frequently used pages resident to reduce page faults.
48. The Banker's algorithm can be used as an alternative to RAG cycle detection for deadlock avoidance with multi-instance resources.
49. In paging, the programmer sees a contiguous virtual address space even though physical frames may be scattered.
50. External fragmentation is possible only in variable partitioning for contiguous memory allocation.
