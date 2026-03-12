# CS3100 Study Guide — Lessons 12–14

---

## Lesson 12: CPU Scheduling — Round Robin, Priority, Multilevel Queue & Multiprocessor

### Round Robin (RR) Scheduling

| Situation | What Happens |
|---|---|
| CPU burst < 1 time quantum | Process voluntarily releases the CPU on its own |
| CPU burst > 1 time quantum | OS forces preemption, process goes back to end of queue |

**Wait time formula:**
- n = number of processes in ready queue, q = time quantum
- Maximum wait time = **(n − 1) × q**

**Effect of quantum size:**
- Very large quantum → behaves like FCFS (no meaningful preemption)
- Very small quantum → dispatch latency dominates, system overwhelmed by context switches

**Turnaround time depends on quantum size:**
- Example: 3 processes, 10 time units each
  - q = 1 → average turnaround = 29 units
  - q = 10 → average turnaround = 20 units
- Rule: a larger quantum tends to reduce average turnaround when processes are of similar length.

---

### Priority Scheduling: Preemptive vs. Nonpreemptive

- **Preemptive:** When a new high-priority process arrives, it immediately preempts the currently running lower-priority process.
- **Nonpreemptive:** The current process finishes its CPU burst before the higher-priority process runs.

### Starvation & Aging

- **Starvation:** Low-priority processes may wait indefinitely if high-priority processes keep arriving.
- **Aging (fix):** The OS gradually increases a waiting process's priority the longer it waits — eventually it gets scheduled.

### Combining RR with Priority

- When two or more processes share the same priority level, use Round Robin between them to fairly share CPU time.

---

### Multilevel Queue Scheduling

- Each distinct priority level gets its own separate queue.
- **Starvation risk:** Higher-priority queues get served first; processes in lower queues can be blocked indefinitely.
- **Fix:** Assign a time quantum to each priority queue. Higher-priority queues get larger time quantums to ensure they get served; lower queues eventually get turns.

---

### Multiprocessor Scheduling: Queue Design

| Design | Best For | Cache Locality | Load Balancing |
|---|---|---|---|
| **Common (global) ready queue** | Short/bursty tasks | Worse — threads hop CPUs → cold caches | Automatic — any CPU pulls work |
| **Per-processor ready queue** | Cache-sensitive tasks | Better — CPU affinity keeps caches warm | Risk of imbalance; needs work-stealing |

**For cache efficiency → per-processor ready queue wins** (usually). A thread stays on the same CPU, reusing its warm L1/L2 caches.

**Common queue cons:** more lock contention on the single queue; cache-line bouncing worsens as core count grows.

**Per-processor queue cons:** one CPU can be overloaded while others idle without a work-stealing mechanism.

---

## Lesson 13: Load Balancing, Processor Affinity & the Critical Section Problem

### Push Migration vs. Pull Migration

- **Push migration (proactive):** OS detects an overloaded CPU and moves tasks *to* less busy CPUs.
- **Pull migration (reactive):** An idle CPU requests (pulls) tasks from a busier CPU.
- **Why implement both?** They fix imbalance in complementary directions — push prevents long overloads, pull quickly utilizes idle cores. Together they improve throughput, CPU utilization, and response time.

### Load Balancing vs. Processor Affinity Trade-Off

- **Processor affinity:** Keep a process on the same CPU so it can reuse that CPU's cached data → faster execution.
- **Load balancing may migrate processes** → cache is cold on the new CPU → performance hit.
- **Fix — soft affinity:** Prefer to keep a process on its current CPU, but allow migration when imbalance is *significant*. Use migration thresholds; avoid moving cache-sensitive/interactive tasks unless necessary.

---

### Race Condition — Bounded Buffer Example

- Producers add items and consumers remove items using shared variables (`count`, `in`, `out`).
- **Race condition:** Both a producer and consumer read `count` simultaneously and write back different values → one update is lost.
- Even if each thread's code is individually correct, instruction interleaving causes wrong results.
- **Implications:** Buffer state becomes corrupted — `count` no longer matches actual items, leading to overflow, underflow, missing data, duplicate reads, or deadlock.

---

### Critical Section Problem

| Requirement | Meaning |
|---|---|
| **Mutual exclusion** | Only one process/thread in the critical section at a time. Prevents simultaneous writes to shared data. |
| **Progress** | If no thread is in the critical section and some want to enter, the system must choose one in *finite* time. Threads not trying to enter must not delay this. |
| **Bounded waiting** | A thread requesting entry must eventually get in after a *bounded* number of turns by others. Prevents starvation. |

### ⚠️ REVIEW THIS — common trap: Race conditions in nonpreemptive scheduling

- **Race conditions can still occur in nonpreemptive scheduling** — FALSE that they cannot. If multiple threads share data and one updates it without protection, a race can still happen when control is voluntarily handed off. Locks/semaphores are always needed for shared mutable data.

### Balancing Correctness vs. Response Time

- Protect all shared data with synchronization (mutexes, semaphores, atomics).
- Keep critical sections **as short as possible** — long locks increase waiting time and hurt responsiveness.
- Use fine-grained locking, lock-free structures where appropriate, and priority inheritance to reduce blocking without sacrificing correctness.

---

## Lesson 14: Busy Waiting, Atomic Operations, Semaphores & Readers-Writers

### Busy Waiting (Spinlocks) — When to Context Switch

| Scenario | Recommendation |
|---|---|
| **Single-core** | Always context switch — P1 spinning wastes the only CPU; P2 can't run to release the lock |
| **Multi-core, short wait** | Allow brief spinning — avoiding context switch overhead may be faster if P2 releases soon |
| **Multi-core, long wait** | Block P1 and context switch — costs ~2 context switches but saves CPU cycles and improves fairness |

**Defining "short duration":** A wait time smaller than the expected context-switch + scheduling overhead. Typically only a few microseconds — should be measured empirically on the target hardware.

---

### Atomic Operations

- **Definition:** An indivisible operation that cannot be interrupted mid-execution.
- **Example:** `test_and_set(lock)` — atomically reads the old lock value and sets it to 1. Used as the foundation for mutual exclusion without OS intervention.

---

### Semaphores

| Type | Value Range | Primary Use |
|---|---|---|
| **Binary semaphore** | 0 or 1 | Mutual exclusion (acts like a lock) |
| **Counting semaphore** | 0 to N | Track multiple identical resources / available slots |

**Operations:**
- `wait(S)` (P): decrement S; if S < 0, block the calling process.
- `signal(S)` (V): increment S; if any processes are blocked, wake one.

---

### Bounded Buffer with Semaphores

Use **three semaphores:**
- `mutex = 1` — binary; ensures only one party accesses buffer at a time
- `empty = N` — counting; tracks available empty slots (starts full)
- `full = 0` — counting; tracks filled slots (starts empty)

**Producer:**
```
wait(empty) → wait(mutex) → insert item → signal(mutex) → signal(full)
```

**Consumer:**
```
wait(full) → wait(mutex) → remove item → signal(mutex) → signal(empty)
```

`mutex` prevents race conditions. `empty` prevents overflow. `full` prevents underflow.

---

### Readers-Writers Problem

**Setup:** Multiple threads share a data object. Readers only read (safe to share). Writers modify (need exclusive access).

| Problem | Policy | Risk |
|---|---|---|
| **First (reader-priority)** | No reader waits unless a writer is already writing | Writer starvation — readers keep arriving, writer never gets in |
| **Second (writer-priority)** | Once a writer waits, no new readers start | Reader starvation — heavy write load blocks all new reads |
| **Fair version** | FIFO ordering | No starvation for either side |

**First problem — key semaphores:**
- `rw_mutex = 1`: held by the writer *or* by the first/last reader (on behalf of the group)
- `mutex = 1`: protects `read_count`
- `read_count = 0`

**Reader entry:** acquire `mutex` → increment `read_count` → if first reader, `wait(rw_mutex)` → release `mutex` → read → acquire `mutex` → decrement `read_count` → if last reader, `signal(rw_mutex)` → release `mutex`

**Writer:** `wait(rw_mutex)` → write → `signal(rw_mutex)`

**Second problem adds** a turnstile/gate semaphore: arriving readers must wait behind queued writers. Existing readers can finish, then the waiting writer proceeds.
