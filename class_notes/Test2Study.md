# CS3100 Study Guide — Lectures 6–8

---

## Lecture 6: OS Design & OS Structures

### Policy vs. Mechanism
- **Policy** = *what* needs to be done; **Mechanism** = *how* to do it.
- Separating them gives flexibility — you can change policies without rewriting mechanisms.
- Example: A **timer** is the mechanism; "interrupt every 100 seconds" is the policy.
- Other examples: unique PIDs (policy) enforced by maintaining a PID list (mechanism); file privacy (policy) enforced by permission bits (mechanism).

### Assembly vs. High-Level Languages
- Assembly gives fine-grained control and saves time/storage, but is **not portable** (tied to a specific CPU).
- High-level languages (C, C++, Java) are **portable**.
- Modern OSes use **both**: assembly for low-level kernel components, high-level for system programs and APIs.
- Android example: kernel in Assembly + C, system programs in C/C++, API in Java.
- Rule of thumb: assembly when portability isn't needed; high-level when it is.

### OS Structures (know the trade-offs!)

| Structure | Pros | Cons |
|---|---|---|
| **Monolithic** | High efficiency, low overhead, fast intrakernel communication | Single point of failure — one component breaks, everything breaks |
| **Layered** | Modular; debugging is simplified (check layer by layer) | Hard to define layer boundaries; increased overhead |
| **Microkernel** | Lightweight, flexible, portable, small attack surface | Overhead from inter-process communication (message passing is slower) |
| **LKM (Loadable Kernel Modules)** | More flexible than layered, more efficient than microkernel; modules loaded dynamically as needed | Core must always be in memory |

**Key detail on LKM:** Core services stay in main memory; other modules load on demand. Uses both shared memory and message passing.

---

## Lecture 7: Processes

### Core Concepts
- Two processes **can** be associated with the same program (e.g., two Safari windows = two processes, one program).
- **Text & data sections** are fixed in size; **stack & heap** grow/shrink dynamically at runtime.
- Part of the heap can be allocated for shared memory communication.

### Process Life Cycle (state transitions)

**New → Ready:** Kernel creates a PID, `load` system call allocates address space, process enters ready queue.

**Ready → Running:** Scheduler dispatches process to CPU.

**Running → Ready:** In multitasking, the time slice expires and process goes back to ready queue (cyclic).

**Running → Waiting:** Process needs I/O; it waits while the device driver handles data transfer from local buffer → main memory, then moves back to ready.

**Waiting → Running:** **Not possible directly** — must go through the ready queue first.

**Running → Terminated:** Via `exit()`, `abort()`, or `terminate()`.

### PCB (Process Control Block)
The PCB stores everything needed to start or restart a process: process state, process number, program counter, registers, memory limits, and list of open files. When a process is paused, its state is saved to the PCB so it can resume later.

### Multithreading
- A thread is a unit of work within a process.
- Multithreaded = multiple units executing in parallel → better responsiveness.
- **Only one PCB per process**, even if multithreaded (still one process).

---

## Lecture 8: Scheduling, Process Creation & IPC

### Multiprogramming & Multitasking Factors

- **Degree of multiprogramming:** Number of processes in main memory. Must be > 1 to get any benefit.
- **I/O-bound processes:** Spend most time waiting on I/O → frequent switching → good for multiprogramming.
- **CPU-bound processes:** Spend most time running → low switching in multiprogramming. In **multitasking**, switching happens on a timer regardless.

### Scheduling Scenarios (queueing diagram)
1. **Time slice expires** → process returns to ready queue.
2. **I/O needed** → process goes to I/O wait queue → returns to ready queue when done.
3. **Child process created** → parent can continue or wait (`wait_time()` / `wait_event()`); returns to ready queue when condition met.
4. **Waiting for interrupt** → sits in interrupt queue → returns to ready queue when interrupt occurs.

### Context Switching
Saving the current PCB and loading another is pure **overhead** — no useful work happens during the switch.

### Managing Child Processes (preventing overload)
Each child needs its own address space, which can overload memory. Three OS policies to prevent this:

1. **Memory limit per parent** — cap on total allocation.
2. **Limit on number of child processes** per parent.
3. **No extra memory** — children share the parent's address space.

### Process Creation in UNIX
- `fork()` creates a child with an **exact copy** of the parent's address space (enables early communication).
- Child then calls `exec()` to load a new program, discarding unneeded parts of the parent's address space.

### `terminateProcess()` — three cases
1. Child's task is **no longer needed**.
2. **Parent exits** → all children terminate too.
3. Child needs **more resources** than the parent can provide.

(Parent knows child's ID because the OS passes it to the parent at creation.)

### Inter-Process Communication (IPC)

**Shared Memory Model:**
- Part of address space is designated for IPC.
- P0 calls `shared_memory_create()`; P1 calls `shared_memory_attach()`.
- **User-controlled**, not OS-controlled — user must prevent race conditions (lock until write is complete).

**Message Passing — Direct vs. Indirect:**

| | Direct | Indirect (Mailbox) |
|---|---|---|
| How | `send(P0, message)` — recipient is interrupted | `send(A0, message)` — message placed in mailbox |
| Recipient | Must handle immediately | Checks mailbox when available (no interruption) |

**Mailbox location matters:**
- In **OS address space** → must go through OS to send.
- In **process address space** → OS can notify sender if mailbox doesn't exist; recipient reads at will. Downside: uses process memory.

---

*Good luck on your exam, Gabriel!*
