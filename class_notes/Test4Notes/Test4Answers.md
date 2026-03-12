# CS3100 Practice Quiz — Answer Key (Lessons 12–14)

---

## Section A: Round Robin Scheduling

**1. FALSE** — If the CPU burst is *shorter* than the quantum, the process voluntarily releases the CPU on its own. The OS only forces preemption when the burst *exceeds* the quantum.

**2. TRUE** — When a process runs out its time quantum without finishing, the OS preempts it and places it at the back of the circular ready queue.

**3. TRUE** — With n processes and quantum q, any process will wait at most (n − 1) turns of q each. This is the max wait time formula for RR.

**4. TRUE** — If the quantum is so large that every process finishes within one turn, there's no meaningful preemption — effectively FCFS behavior.

**5. FALSE** — Very small quantum means the CPU spends most of its time on context switches (dispatch latency), not real work. Performance tanks due to overhead.

**6. TRUE** — Round Robin is essentially FCFS with preemption added. It uses a circular queue where each process gets a turn.

**7. FALSE** — A larger quantum does *not* always increase turnaround time. In fact, when all processes have similar burst lengths, a larger quantum that lets them finish in one turn reduces turnaround time.

**8. FALSE** — With 3 processes each needing 10 units: q=10 gives average turnaround of 20 units, and q=1 gives 29. So q=10 gives *lower* turnaround, not higher.

**9. FALSE** — The formula is (n − 1) × q, not (n + 1) × q.

**10. FALSE** — Round Robin is a *preemptive* scheduling algorithm. That's its key feature — it adds preemption to FCFS via the time quantum.

---

## Section B: Priority Scheduling & Starvation

**11. FALSE** — In *preemptive* priority scheduling, a high-priority arrival immediately preempts the running process. No waiting required.

**12. TRUE** — In *nonpreemptive* priority scheduling, the current process finishes its burst before the scheduler hands off to a higher-priority process.

**13. TRUE** — This is the definition of starvation in priority scheduling. Low-priority processes can wait indefinitely if the queue is constantly fed with higher-priority arrivals.

**14. FALSE** — Aging *increases* a waiting process's priority over time, not decreases it. The goal is to get it eventually scheduled, not push it further back.

**15. TRUE** — When two processes tie in priority, Round Robin is a natural tie-breaker that fairly distributes CPU time between them.

**16. FALSE** — Multilevel queue scheduling uses *separate queues* for each distinct priority level. That's the whole point.

**17. TRUE** — If high-priority queues keep getting new arrivals, lower-priority queues never get to run — starvation.

**18. TRUE** — Assigning time quantums per queue ensures even low-priority queues eventually get CPU time.

**19. FALSE** — In *preemptive* scheduling, a running process can absolutely be interrupted if a higher-priority process arrives. That's what preemptive means.

**20. FALSE** — Higher-priority queues should get *larger* time quantums, not smaller. They're high priority for a reason — you want them to run longer, not be cut off quickly.

---

## Section C: Multiprocessor Queue Design

**21. TRUE** — Per-processor queues keep threads on the same CPU, preserving warm L1/L2 caches. This is the main cache locality advantage.

**22. FALSE** — A common queue actually *hurts* cache locality because threads can be picked up by any CPU, hopping around and finding cold caches.

**23. TRUE** — Each CPU mainly accesses its own queue, reducing lock contention and cache-line bouncing. Scales better as core count grows.

**24. FALSE** — The common queue has *more* lock contention as core count grows, because every CPU must acquire the same lock to pull work.

**25. TRUE** — Without work-stealing, one CPU might sit idle while another is overloaded, since tasks are local to each CPU's queue.

**26. TRUE** — Work-stealing allows idle CPUs to take tasks from busier CPUs' queues, balancing the load dynamically.

**27. TRUE** — CPU affinity means the thread reuses the same CPU's caches (warm L1/L2, sometimes L3), which significantly speeds up memory access.

**28. FALSE** — Cache-line bouncing is a concern with the *common* global queue, where the queue data structure itself gets accessed (and cached) by many CPUs simultaneously. Per-processor queues reduce this.

---

## Section D: Load Balancing & Processor Affinity

**29. FALSE** — Push migration is *proactive* — the OS pushes tasks from a busy CPU to a less busy one. Pull migration is the reactive one.

**30. TRUE** — Pull migration is reactive: an idle CPU detects it has no work and pulls tasks from a busier CPU.

**31. FALSE** — Both are needed because they work in different directions. Push prevents long overloads; pull quickly utilizes idle cores. Having only one leaves gaps in coverage.

**32. TRUE** — When a process migrates to a new CPU, its data is no longer in that CPU's cache. The cache is cold, and performance drops until the cache warms up again.

**33. FALSE** — Soft affinity *prefers* keeping a process on the same CPU but *allows* migration when needed. Hard affinity is the one that locks a process to a specific CPU.

**34. FALSE** — It's *soft* affinity that allows migration under significant imbalance. Hard affinity strictly restricts which CPUs a process can run on.

**35. TRUE** — Migration thresholds are the practical solution: migrate only when imbalance crosses a threshold, preserving most affinity benefits while preventing sustained overloads.

**36. FALSE** — They conflict directly. Load balancing wants to move processes; affinity wants to keep them put. This is a known OS design tension requiring trade-off decisions.

---

## Section E: Race Conditions & Critical Section

**37. TRUE** — Both read `count`, both compute an update, and both write back — one write is lost. Classic read-modify-write race condition.

**38. FALSE** — Race conditions are notoriously hard to debug *because* they appear only sometimes, depending on exact timing and instruction interleaving. They're non-deterministic.

**39. TRUE** — A corrupted `count` can make the producer think there's space when there isn't (overflow), or the consumer think there's data when there isn't (underflow), or cause duplicate reads.

**40. FALSE** — Race conditions can still occur in nonpreemptive scheduling. If a thread voluntarily yields while mid-update on shared data, and another thread reads that data, a race condition results. Synchronization is always needed.

**41. FALSE** — Mutual exclusion means only *one* process at a time regardless of what it's doing. Even readers are subject to mutual exclusion in standard critical section design.

**42. TRUE** — This is the definition. One process at a time in the critical section — period.

**43. TRUE** — Progress means the system cannot stall indefinitely on deciding who enters. It must pick someone in finite time when the critical section is free and processes want in.

**44. TRUE** — This is the definition of bounded waiting. The number of times others can enter before you is bounded — you won't wait forever.

**45. FALSE** — Threads that do *not* want to enter must *not* influence the choice. Only the processes actually competing for entry participate in the decision.

**46. FALSE** — Short critical sections are far better. Long critical sections mean other threads wait longer, hurting response time. The goal is to minimize time spent with the lock held.

**47. TRUE** — Fine-grained locking locks smaller portions of shared data, allowing multiple threads to access different parts concurrently. More concurrency = better responsiveness.

**48. TRUE** — Priority inheritance temporarily raises the priority of a low-priority lock holder so it can finish faster, releasing the lock sooner and unblocking higher-priority waiters.

---

## Section F: Busy Waiting & Atomic Operations

**49. FALSE** — On a single-core system, the OS *should* context switch away from P1. P1 spinning on the only core blocks P2 from ever running to release the lock — guaranteed deadlock if left spinning.

**50. TRUE** — On multi-core, P2 is on another core and can release the lock very soon. If the wait is short, spinning avoids the overhead of a sleep/wake context switch, making it net faster.

**51. TRUE** — For long waits, the cost of spinning (wasted CPU cycles) far outweighs the cost of two context switches. Block and let other threads use the CPU.

**52. TRUE** — The two context switches: one when P1 blocks (switches out), one when P1 is woken up after the lock is released (switches back in).

**53. TRUE** — "Short" means the spin duration is less than the overhead of doing a context switch. If spinning costs less than switching, spin. This threshold should be measured empirically.

**54. FALSE** — An atomic operation is by definition *indivisible* — it cannot be interrupted partway through. That's the entire point of atomic operations.

**55. TRUE** — `test_and_set` reads the current lock value and sets it to 1 as a single uninterruptible operation, making it the classic building block for spinlocks and mutex implementations.

**56. FALSE** — Atomic operations are implemented at the hardware level (special CPU instructions). They do not require OS support; the CPU guarantees their indivisibility.

---

## Section G: Semaphores & Bounded Buffer

**57. FALSE** — A binary semaphore is restricted to values 0 and 1 only. That's what makes it "binary."

**58. FALSE** — A *counting* semaphore ranges from 0 to N. It's the binary semaphore that's limited to 0 and 1.

**59. TRUE** — A binary semaphore with values 0 (locked) and 1 (unlocked) works exactly like a mutex: only one thread can acquire it at a time.

**60. TRUE** — `mutex = 1` ensures that at any moment only the producer OR the consumer is accessing the shared buffer — not both simultaneously.

**61. TRUE** — `empty` starts at N (all slots empty). Producer calls `wait(empty)` before inserting — if `empty` reaches 0, all slots are full and the producer blocks. Prevents overflow.

**62. TRUE** — `full` starts at 0 (no items). Consumer calls `wait(full)` before removing — if `full` is 0, there's nothing to consume and the consumer blocks. Prevents underflow.

**63. FALSE** — The producer calls `wait(empty)` (not `wait(full)`) before inserting. It needs to check for available empty slots, not filled ones.

**64. TRUE** — The consumer calls `wait(full)` before removing. It needs to confirm there's at least one filled slot before trying to take an item.

**65. TRUE** — After inserting an item, the producer calls `signal(full)` to increment the filled-slot count and wake any waiting consumers.

**66. FALSE** — `empty` is initialized to N (the buffer size) because all N slots are available at the start. Initializing it to 0 would mean no slots are available and the producer would immediately block.

---

## Section H: Readers-Writers Problem

**67. TRUE** — Multiple concurrent reads are safe because readers don't modify the data. Allowing this increases throughput.

**68. TRUE** — A writer needs exclusive access to prevent inconsistent state — no other thread (reader or writer) can be active while a write is happening.

**69. FALSE** — The first problem prioritizes *readers*, not writers. No reader waits unless a writer is already actively writing.

**70. TRUE** — If readers keep arriving continuously, a waiting writer never gets `rw_mutex` because the last reader never signals it. Writer starvation is the known weakness of the first problem.

**71. FALSE** — In the second problem, once a writer is waiting, *new readers are blocked* from starting. Existing readers can finish, but new ones must wait behind the writer.

**72. TRUE** — In the second problem, under heavy write load writers continuously queue up, blocking all new readers. Reader starvation is the known weakness of the second problem.

**73. TRUE** — The first reader performs `wait(rw_mutex)` on behalf of the whole reader group, blocking any writer from entering while at least one reader is active.

**74. FALSE** — Only the *last* reader (when `read_count` drops to 0) calls `signal(rw_mutex)`. Individual readers don't release it — they just decrement `read_count` and check if they're last.

**75. TRUE** — A fair Readers-Writers solution adds FIFO ordering so neither readers nor writers can be perpetually bypassed. Both sides make bounded-wait progress.

**76. TRUE** — The writer simply calls `wait(rw_mutex)` directly. The reader coordination (via `read_count` and `mutex`) is the readers' responsibility; the writer just waits for the global lock.
