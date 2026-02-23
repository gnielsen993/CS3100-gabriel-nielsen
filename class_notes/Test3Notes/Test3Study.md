# CS3100 Study Guide — Lessons 9–10

---

## Lesson 9: IPC Buffering, Threads & Parallelism

### Buffering in Message Passing

| Buffer Type | Description | Key Detail |
|---|---|---|
| **Zero Capacity** | No storage; used with direct communication | Cannot be used with asynchronous communication — nowhere to store the message |
| **Bounded (Finite)** | Fixed-size buffer | Message must fit within the mailbox size; anything larger is lost. If full, producer must invoke blocking send and wait for space |
| **Unbounded (Infinite)** | Starts at an initial size, grows as needed | Producer never needs to block — buffer always expands to fit the message |

### Synchronous vs. Asynchronous Communication

**Synchronous (blocking):**
- **Blocking send()** — producer pauses until consumer receives the message; enters interrupt wait queue.
- **Blocking receive()** — consumer waits until the message arrives from the producer.

**Asynchronous (non-blocking):**
- **Non-blocking send()** — producer continues regular operation after sending.
- **Non-blocking receive()** — consumer never waits for input.

**Key rule:** Zero capacity queue + asynchronous communication = **impossible**. No buffer means no way to keep going without acknowledgment.

**Bounded buffer edge case:** If the buffer is full during async communication, the producer must temporarily invoke a blocking send and wait for space to open up.

### Distributed Environments → Message Passing
In distributed systems, each computer is independent with its own RAM — no shared memory is possible. Must use message passing.

### Single-Threaded vs. Multithreaded Processes
- Single-threaded: one thread takes the entire address space and PCB.
- Multithreaded: all threads share the same address space. PCB has shared parts (address space, open files) and unique parts per thread (program counter, registers, stack).

### Why Multithreading for a Web Server?
- One thread per request/user → low latency under high load.
- Single-threaded would hang on each request.
- Creating child processes is too heavy — new address space each time, too much memory, too slow.

### Concurrency vs. Parallelism
- **Concurrency:** Multiple tasks on ONE core, switching so fast it feels simultaneous.
- **Parallelism:** Multiple tasks on MULTIPLE cores, truly simultaneous.
- You need multiple cores for parallelism, but not for concurrency.

### Data Parallelism vs. Task Parallelism
- **Data parallelism:** Same operation, different data subsets across cores.
- **Task parallelism:** Different operations on the same data set across cores.
- They are **not mutually exclusive** — you can combine both.

### Amdahl's Law
- Speedup ≤ 1 / (S + (1 - S) / N), where S = serial portion, N = cores.
- As N → ∞, speedup → 1/S.
- **Speedup is limited by the serial portion.** Adding more cores has diminishing returns.
- Example: 75% parallel, 25% serial → going from 1 to 2 cores gives ~1.6x speedup.

### Challenges of Multithreaded Development
1. Identifying parallelizable tasks
2. Balancing workload across modules
3. Data splitting
4. Data dependency / synchronization
5. Testing and debugging

---

## Lesson 10: Multithreading Models, Signals & fork/exec

### Multithreading Models (know the trade-offs!)

| Model | How It Works | Pros | Cons |
|---|---|---|---|
| **Many-to-One** | Multiple user threads → one kernel thread | No OS thread creation overhead | High wait time; one blocking call blocks ALL threads |
| **One-to-One** | Each user thread → its own kernel thread | No wait time; blocking doesn't affect others | System can be overwhelmed; usually a thread limit |
| **Many-to-Many** | Multiple user threads → fewer kernel threads | Better resource usage | Small wait time; all threads treated equally (no priority) |
| **Two-Level** | Many-to-many + high-priority threads get dedicated kernel threads | Priority threads aren't stuck waiting | More complex to manage |

**Most modern OSes use One-to-One.**

### fork() and exec() in Multithreaded Environments
- `fork()` creates a child process and passes threads to the new child.
- `exec()` overrides the threads from the parent with a new program.
- **There is no difference between single-threaded and multithreaded when `exec()` is called** — it replaces everything.

### Signal Handling in Multithreading
- Processes receive signals, not full messages initially.
- In a multithreaded process, a signal may target a specific thread, not the whole process.
- **Synchronous signals:** Delivered to the specific thread that generated it.
- **Asynchronous signals:** Delivered to all threads (initiated by external event) — **but not always.** An async signal is NOT always delivered to all threads.

### LWP (Lightweight Processes)
- LWP tracks the current status of a kernel thread: 0 = locked/serving, 1 = unlocked/ready.
- **I/O-bound applications need more LWPs than CPU-bound** — because I/O-bound threads frequently block, requiring more LWPs to keep user threads serviced.

---

*Note: Lesson 11 notes were empty — add those and I can update this guide.*
