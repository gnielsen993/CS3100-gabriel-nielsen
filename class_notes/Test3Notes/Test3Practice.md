# CS3100 Practice Quiz — Lessons 9–11 (True/False)

---

## Section A: IPC Buffering & Communication (Q1–10)

1. A zero capacity buffer can be used with asynchronous (non-blocking) communication.

2. In bounded buffering, if the message is larger than the mailbox size, the excess portion of the message is lost.

3. With an unbounded buffer, the producer may still need to invoke a blocking send if the buffer runs out of space.

4. In synchronous communication, a blocking send() causes the producer to pause until the consumer receives the message.

5. In asynchronous communication, the consumer process invokes a blocking receive() and waits for the producer's message.

6. If a bounded buffer is full during asynchronous communication, the producer must temporarily invoke a blocking send and wait for space.

7. In a distributed environment, shared memory is the preferred IPC method because it is faster than message passing.

8. Message passing is the appropriate IPC method for distributed systems because each computer has independent RAM.

9. A blocking receive() means the consumer process will continue execution even if no message has arrived yet.

10. Zero capacity buffering is used with direct communication.

## Section B: Threads & Process Structure (Q11–16)

11. In a multithreaded process, each thread has its own separate address space.

12. In a multithreaded process, the PCB contains both shared and unique parts — shared across threads (address space, open files) and unique per thread (program counter, registers, stack).

13. A multithreaded web server is preferred over creating child processes per request because threads are lighter weight and don't require new address spaces.

14. A single-threaded web server can handle high request volumes with no latency issues.

15. Using child processes instead of threads for a web server is inefficient because each child process requires its own address space.

16. Multithreading reduces overhead compared to creating new child processes.

## Section C: Concurrency, Parallelism & Amdahl's Law (Q17–24)

17. Concurrency requires multiple CPU cores.

18. Parallelism requires multiple CPU cores.

19. Concurrency means multiple tasks truly execute at the exact same time on a single core.

20. Data parallelism and task parallelism are mutually exclusive — you must choose one or the other.

21. In data parallelism, the same operation is performed on different data subsets across cores.

22. In task parallelism, different operations are performed on the same data set across cores.

23. According to Amdahl's Law, as the number of cores approaches infinity, speedup approaches 1/S, where S is the serial portion.

24. According to Amdahl's Law, if an application is 50% serial, adding more cores can eventually make it more than 2x faster.

## Section D: Multithreading Models (Q25–32)

25. In the many-to-one model, if one user thread makes a blocking system call, all other user threads must wait.

26. The one-to-one model has no thread creation overhead in the OS because it reuses a single kernel thread.

27. Most modern operating systems use the many-to-one multithreading model.

28. In the one-to-one model, a blocking call by one user thread does not affect other threads.

29. The many-to-many model treats all user threads as equal priority, which can be a disadvantage for time-sensitive threads.

30. The two-level model extends many-to-many by giving high-priority user threads their own dedicated kernel threads.

31. The many-to-one model benefits from having no OS thread creation overhead since only one kernel thread exists.

32. In the one-to-one model, there is usually a system-imposed limit on the number of threads an application can create.

## Section E: fork/exec, Signals & LWP (Q33–40)

33. When `fork()` is called in a multithreaded process, the child process receives a copy of the parent's threads.

34. After `exec()` is called, there is a difference in behavior between single-threaded and multithreaded processes.

35. When `exec()` is called by a child process, it retains all threads inherited from the parent.

36. A synchronous signal is delivered to all threads in a multithreaded process.

37. An asynchronous signal is always delivered to all threads in a process.

38. In signal handling for multithreaded processes, a mechanism is needed to ensure signals reach the correct thread.

39. I/O-bound applications need more LWPs (Lightweight Processes) than CPU-bound applications.

40. An LWP status of 0 means the kernel thread is unlocked and ready to serve a user thread.

## Section F: Thread Cancellation & TLS (Q41–46)

41. Asynchronous thread cancellation waits for the thread to reach a safe checkpoint before terminating.

42. In symmetric multiprocessing, deferred thread cancellation is preferred over asynchronous cancellation.

43. In asymmetric multiprocessing, asynchronous thread cancellation is preferred because you want immediate termination.

44. TLS (Thread Local Storage) is the shared portion of the address space that all threads can access.

45. TLS exists so that threads in a multithreaded process can work independently without interfering with each other's data.

46. Without TLS, threads in a multithreaded process would have no private storage for their own execution state.

## Section G: CPU Scheduling — Lesson 11 (Q47–60)

47. I/O-intensive processes have fewer but longer CPU bursts compared to CPU-intensive processes.

48. CPU-intensive processes have fewer but longer CPU bursts compared to I/O-intensive processes.

49. In preemptive scheduling, the process itself decides when to give up the CPU.

50. In nonpreemptive scheduling, context switching is voluntary.

51. Preemptive scheduling is possible in a multiprogramming environment.

52. Both preemptive and nonpreemptive scheduling are possible in a multitasking environment.

53. A race condition in preemptive scheduling can occur when the OS interrupts a thread in the middle of updating shared data.

54. Locking a shared resource prevents race conditions by ensuring only one process can access it at a time.

55. The dispatcher is responsible for saving the current process state and loading the next process state during a context switch.

56. Dispatcher latency is the time spent doing useful computation during a context switch.

57. An increase in CPU utilization always guarantees an increase in throughput.

58. In a multiprogramming environment, the CPU may be active but only serving one process at a time.

59. Waiting time can be higher than turnaround time for a given process.

60. Turnaround time is equal to CPU execution time + I/O wait time + waiting time in the ready queue.
