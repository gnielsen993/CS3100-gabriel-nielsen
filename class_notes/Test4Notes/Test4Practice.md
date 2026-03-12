# CS3100 Practice Quiz — Lessons 12–14 (True/False)

---

## Section A: Round Robin Scheduling (Q1–10)

1. In Round Robin scheduling, if a process's CPU burst is shorter than the time quantum, the OS forcibly preempts it.

2. In Round Robin scheduling, if a process's CPU burst exceeds the time quantum, the process is preempted and placed at the end of the ready queue.

3. With n processes and time quantum q, no process waits longer than (n − 1) × q time units.

4. A very large time quantum in Round Robin makes it behave similarly to FCFS.

5. A very small time quantum in Round Robin improves performance because processes switch faster.

6. Round Robin is a circular ready queue that adds preemption to FCFS.

7. A larger time quantum always increases average turnaround time compared to a smaller one.

8. With 3 processes each needing 10 time units, a time quantum of 10 gives a higher average turnaround time than a time quantum of 1.

9. In Round Robin, the maximum wait time for any process is (n + 1) × q.

10. Round Robin scheduling is a nonpreemptive scheduling algorithm.

---

## Section B: Priority Scheduling & Starvation (Q11–20)

11. In preemptive priority scheduling, a newly arrived high-priority process must wait for the current process to finish its CPU burst before running.

12. In nonpreemptive priority scheduling, the current process finishes its CPU burst before a higher-priority process runs.

13. Starvation occurs in priority scheduling when low-priority processes wait indefinitely because high-priority processes keep arriving.

14. Aging prevents starvation by gradually decreasing the priority of waiting processes.

15. When two processes share the same priority level, Round Robin can be used between them to fairly share CPU time.

16. Multilevel queue scheduling uses a single queue for all priority levels.

17. In multilevel queue scheduling, starvation can occur when higher-priority queues continuously receive new processes.

18. Assigning time quantums to each priority queue is one way to address starvation in multilevel queue scheduling.

19. In a preemptive priority scheduler, a running process can never be interrupted once it has started its CPU burst.

20. Higher-priority queues in a multilevel queue should receive smaller time quantums to limit their impact on lower-priority queues.

---

## Section C: Multiprocessor Queue Design (Q21–28)

21. A per-processor ready queue provides better cache locality than a common global ready queue.

22. A common (global) ready queue provides better cache locality because any CPU can pull work.

23. Per-processor queues scale better on many-core systems because each CPU mostly touches its own queue.

24. A common ready queue has less lock contention than per-processor queues as core count grows.

25. Per-processor queues can create load imbalance where one CPU is overloaded while others are idle.

26. Work-stealing is a mechanism used to address load imbalance in per-processor queue designs.

27. A thread staying on the same CPU benefits from warm L1/L2 caches, improving performance.

28. Cache-line bouncing is more of a concern with per-processor queues than with a common queue.

---

## Section D: Load Balancing & Processor Affinity (Q29–36)

29. Push migration is reactive — an idle CPU requests work from a busier CPU.

30. Pull migration is reactive — an idle CPU requests work from a busier CPU.

31. Implementing both push and pull migration is unnecessary if you have either one.

32. Processor affinity can be hurt by load balancing because migrating a process invalidates its cached data.

33. Soft affinity means a process is permanently locked to a specific CPU and cannot be migrated under any circumstances.

34. Hard affinity allows processes to migrate between CPUs when the load imbalance is significant.

35. A practical fix for the load balancing vs. affinity conflict is to use migration thresholds and only move tasks when the imbalance is significant.

36. Load balancing and processor affinity always work in harmony — there is no trade-off between them.

---

## Section E: Race Conditions & Critical Section (Q37–48) ⚠️ REVIEW — tricky section

37. In a bounded buffer, a race condition can occur when a producer and consumer simultaneously read and write the shared `count` variable.

38. Race conditions are easy to debug because the bug appears consistently every time the code runs.

39. A race condition in a bounded buffer can lead to buffer overflow, underflow, or duplicate reads.

40. Race conditions cannot occur in nonpreemptive scheduling because the process controls when it gives up the CPU.

41. Mutual exclusion allows multiple threads to be in the critical section at the same time as long as they are only reading.

42. Mutual exclusion means only one process or thread can be in the critical section at a time.

43. Progress requires that if no thread is in the critical section and some want to enter, the system chooses one in finite time.

44. Bounded waiting guarantees that a thread requesting entry to the critical section will get in after at most a fixed number of turns by others.

45. Threads that do not want to enter the critical section can delay the progress condition.

46. A long critical section is preferable to a short one because it reduces the number of lock/unlock operations.

47. Fine-grained locking can improve response time by allowing more concurrency compared to coarse-grained locking.

48. Priority inheritance is a technique that can reduce blocking time in synchronized systems.

---

## Section F: Busy Waiting & Atomic Operations (Q49–56)

49. On a single-core system, if P1 is spinning waiting for a lock held by P2, the OS should not context switch so P2 can run sooner.

50. On a multi-core system, a short spinlock may be faster than blocking because it avoids context switch overhead.

51. On a multi-core system with a long expected wait, blocking and context switching is generally better than spinning.

52. Blocking a process and waking it up when the lock is released typically costs approximately two context switches.

53. A "short duration" for busy waiting is a wait time smaller than the cost of a context switch and scheduling overhead.

54. An atomic operation can be interrupted partway through by an OS context switch.

55. `test_and_set(lock)` atomically reads the old lock value and sets it, making it useful for mutual exclusion.

56. Atomic operations require OS support to prevent interruption.

---

## Section G: Semaphores & Bounded Buffer (Q57–66)

57. A binary semaphore can take values 0, 1, or 2.

58. A counting semaphore is limited to values 0 and 1.

59. A binary semaphore behaves similarly to a mutex lock.

60. In the bounded buffer solution, the `mutex` semaphore prevents race conditions during buffer access.

61. In the bounded buffer solution, the `empty` semaphore prevents buffer overflow by tracking available empty slots.

62. In the bounded buffer solution, the `full` semaphore prevents buffer underflow by tracking filled slots.

63. In the bounded buffer solution, the producer calls `wait(full)` before inserting an item.

64. In the bounded buffer solution, the consumer calls `wait(full)` before removing an item.

65. In the bounded buffer solution, the producer signals `signal(full)` after inserting an item.

66. The `empty` semaphore is initialized to 0 because no slots are available at the start.

---

## Section H: Readers-Writers Problem (Q67–76) ⚠️ REVIEW — tricky section

67. In the Readers-Writers problem, multiple readers can read the shared data simultaneously.

68. In the Readers-Writers problem, a writer needs exclusive access — no readers or other writers can be active during a write.

69. The first Readers-Writers problem prioritizes writers — no reader waits unless a writer is already writing.

70. In the first Readers-Writers problem, writers can starve if readers keep arriving continuously.

71. In the second Readers-Writers problem, once a writer is waiting, new readers are allowed to start reading immediately.

72. In the second Readers-Writers problem, readers can starve under heavy write load.

73. In the first Readers-Writers problem, `rw_mutex` is acquired by the first reader to block writers while at least one reader is active.

74. In the first Readers-Writers problem, `rw_mutex` is released by each individual reader when it finishes.

75. A fair version of the Readers-Writers problem uses FIFO ordering to prevent starvation for both readers and writers.

76. In the first Readers-Writers problem, the writer acquires `rw_mutex` directly without any additional coordination semaphore.
