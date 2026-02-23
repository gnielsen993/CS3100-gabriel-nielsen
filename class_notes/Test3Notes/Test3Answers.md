# CS3100 Practice Quiz — Answer Key (Lessons 9–11)

---

## Section A: IPC Buffering & Communication

**1. FALSE** — Zero capacity means no storage. With async communication, neither side waits — but there's nowhere to put the message. It's impossible. Zero capacity requires synchronous (blocking) communication.

**2. TRUE** — In bounded buffering, the message must fit within the mailbox size. Anything larger than the buffer will not be seen by the receiver.

**3. FALSE** — An unbounded buffer grows to fit any message size. The producer never needs to block because the buffer always expands.

**4. TRUE** — Blocking send() pauses the producer and puts it in the interrupt wait queue until the consumer acknowledges receipt.

**5. FALSE** — Asynchronous communication is non-blocking. The consumer never waits — that's the whole point. Blocking receive() belongs to synchronous communication.

**6. TRUE** — Even in async communication, if a bounded buffer is full, the producer has no choice but to temporarily block and wait for space. This is the bounded buffer edge case.

**7. FALSE** — In distributed systems, shared memory is not possible because each computer has independent RAM. Message passing is the only option.

**8. TRUE** — Each machine is independent with its own memory, so message passing is the required IPC method.

**9. FALSE** — Blocking receive() means the consumer WAITS until a message arrives. It does not continue execution.

**10. TRUE** — Zero capacity buffering is used with direct communication, where the sender and receiver synchronize directly.

---

## Section B: Threads & Process Structure

**11. FALSE** — All threads in a multithreaded process share the SAME address space. That's what makes threads lightweight compared to processes.

**12. TRUE** — The PCB has shared parts (address space, open files) and unique parts per thread (program counter, registers, stack).

**13. TRUE** — Threads share the parent's address space, so no new address space allocation is needed. Much lighter and faster than spawning child processes.

**14. FALSE** — A single-threaded server handles one request at a time. Under high load, every other request hangs while waiting, causing significant latency.

**15. TRUE** — Each child process gets its own address space, which requires memory allocation and is much heavier than sharing threads within one process.

**16. TRUE** — Threads share address space and resources, avoiding the overhead of creating entirely new address spaces for child processes.

---

## Section C: Concurrency, Parallelism & Amdahl's Law

**17. FALSE** — Concurrency only needs one core. Tasks switch rapidly on a single core, giving the appearance of simultaneous execution.

**18. TRUE** — Parallelism means tasks truly run at the same time, which requires separate cores.

**19. FALSE** — Concurrency is rapid switching, not true simultaneous execution. It "feels" simultaneous to the user, but tasks take turns on the core.

**20. FALSE** — They are NOT mutually exclusive. You can combine data and task parallelism in the same application.

**21. TRUE** — Data parallelism splits data across cores and runs the same operation on each subset.

**22. TRUE** — Task parallelism assigns different operations to different cores, all working on the same data set.

**23. TRUE** — As N (cores) → ∞, the parallel portion becomes negligible and speedup is limited to 1/S, where S is the serial fraction.

**24. FALSE** — If S = 0.5, then 1/S = 2. The maximum theoretical speedup is 2x, and you can never exceed it no matter how many cores you add.

---

## Section D: Multithreading Models

**25. TRUE** — Many-to-one has only one kernel thread. If any user thread makes a blocking call, that single kernel thread is occupied and ALL other threads are stuck waiting.

**26. FALSE** — One-to-one creates a kernel thread for EACH user thread. That means significant thread creation overhead, not less. It's the many-to-one model that avoids OS thread creation overhead.

**27. FALSE** — Most modern OSes use the one-to-one model, not many-to-one.

**28. TRUE** — Each user thread has its own kernel thread, so one thread blocking doesn't affect the others.

**29. TRUE** — In many-to-many, all user threads are treated equally. High-priority threads still wait in the queue, which is a disadvantage.

**30. TRUE** — The two-level model builds on many-to-many by letting critical/high-priority threads bypass the queue with dedicated kernel threads.

**31. TRUE** — Only one kernel thread exists in many-to-one, so the OS has no overhead from creating multiple kernel threads.

**32. TRUE** — Because each user thread requires a kernel thread, the system usually imposes a limit to prevent being overwhelmed.

---

## Section E: fork/exec, Signals & LWP

**33. TRUE** — `fork()` in a multithreaded environment creates a child process that receives a copy of the parent's threads.

**34. FALSE** — There is NO difference. `exec()` replaces everything with a new program regardless of whether the process was single-threaded or multithreaded.

**35. FALSE** — `exec()` overrides all threads from the parent. It loads an entirely new program, discarding inherited threads.

**36. FALSE** — A synchronous signal is delivered to the SPECIFIC thread that generated it, not all threads.

**37. FALSE** — Your notes specifically say this is false. An async signal is NOT always delivered to all threads, even though it can be in some cases.

**38. TRUE** — Since threads share the same process space, a mechanism is needed to route signals to the correct thread rather than broadcasting to the whole process.

**39. TRUE** — I/O-bound threads frequently block (waiting on I/O), so more LWPs are needed to keep other user threads serviced. CPU-bound threads rarely block, so fewer LWPs suffice.

**40. FALSE** — 0 means locked/currently serving a user thread. 1 means unlocked and ready to serve.

---

## Section F: Thread Cancellation & TLS

**41. FALSE** — Asynchronous cancellation terminates the thread IMMEDIATELY. It's deferred cancellation that waits for a safe checkpoint.

**42. TRUE** — In symmetric multiprocessing, the OS isn't tied to a specific core, so deferred cancellation is preferred — it's safer and avoids corrupting shared data mid-operation.

**43. TRUE** — In asymmetric multiprocessing, you want immediate termination, so asynchronous cancellation is preferred.

**44. FALSE** — TLS is the UNIQUE/private portion of the address space for each thread, not the shared portion. Shared data is separate from TLS.

**45. TRUE** — TLS gives each thread its own private storage, preventing threads from interfering with each other's data during execution.

**46. TRUE** — Without TLS, threads would have no dedicated private space for their own execution state. All data would be shared, making independent operation impossible.

---

## Section G: CPU Scheduling — Lesson 11

**47. FALSE** — It's the opposite. I/O-intensive processes have MANY SHORT CPU bursts (they frequently pause for I/O). CPU-intensive processes have fewer but longer bursts.

**48. TRUE** — CPU-intensive processes spend most of their time running on the CPU, resulting in fewer but longer bursts between I/O operations.

**49. FALSE** — In preemptive scheduling, the OS FORCES the process to give up the CPU. It's nonpreemptive scheduling where the process decides on its own.

**50. TRUE** — In nonpreemptive scheduling, the process voluntarily gives up the CPU (e.g., when it finishes or needs I/O). The OS does not force it.

**51. FALSE** — Preemptive scheduling is NOT possible in multiprogramming. It requires multitasking, which uses timers to forcibly switch processes.

**52. TRUE** — Multitasking supports both. It can force switches (preemptive via timers) or allow processes to switch voluntarily (nonpreemptive).

**53. TRUE** — If the OS interrupts a thread mid-update on shared data and lets another thread access that same data, both threads are working on inconsistent state — classic race condition.

**54. TRUE** — Locking ensures a resource is only accessible by one process at a time. Other processes must wait until the lock is released.

**55. TRUE** — The dispatcher handles the save/load mechanics of a context switch: save the current PCB, load the next PCB.

**56. FALSE** — Dispatcher latency is overhead, not useful computation. It's the time spent saving and loading process states during which NO real work gets done.

**57. FALSE** — In multiprogramming, the CPU can be active (high utilization) but only serving one process. Throughput (processes completed per unit time) doesn't necessarily increase just because the CPU is busy.

**58. TRUE** — In multiprogramming, only one process runs at a time. The CPU is active, but it's dedicated to a single process until that process yields.

**59. FALSE** — Waiting time is a direct component of turnaround time (turnaround = CPU + I/O wait + ready queue wait). A component can never exceed the whole.

**60. TRUE** — This is the definition. Turnaround time accounts for everything: CPU execution, I/O waiting, and time spent in the ready queue.
