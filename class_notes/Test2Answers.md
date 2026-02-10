# CS3100 Practice Quiz — Answer Key

---

## Questions 1–20

**1. FALSE** — It's the opposite. A policy defines *what* needs to be done; a mechanism defines *how* to do it.

**2. TRUE** — A timer is the mechanism (the tool). The policy is the rule built on top of it, like "interrupt every 100 seconds."

**3. FALSE** — Modern OSes use both. Assembly for low-level kernel work (speed, hardware control), high-level languages for portability and system programs.

**4. TRUE** — Android uses Assembly + C for the kernel, C/C++ for system programs, and Java for the API layer.

**5. TRUE** — Everything lives in a single file, so one component failure can bring down the whole system. This is the main drawback of monolithic structure.

**6. FALSE** — The layered approach actually *increases* overhead compared to monolithic. Monolithic has the least overhead because everything is in one file with fast intrakernel communication.

**7. TRUE** — Microkernels keep the kernel small (small attack surface, portable, flexible), but moving services outside the kernel means inter-process communication via message passing, which adds overhead.

**8. FALSE** — LKM loads modules dynamically as needed during execution. Only core services stay in memory at all times.

**9. FALSE** — Two processes absolutely can run the same program. Example: two Safari windows are two separate processes associated with one program.

**10. FALSE** — Text and data sections are fixed in size. It's the stack and heap that grow and shrink dynamically at runtime.

**11. FALSE** — A process in the waiting state must go to the ready queue first (ready state), then get dispatched to running. There is no direct waiting → running transition.

**12. TRUE** — The PCB contains process state, process number, program counter, registers, memory limits, and list of open files. It's everything needed to start or restart a process.

**13. FALSE** — There is only one PCB per process, regardless of how many threads it has. Multithreaded or not, it's still one process.

**14. TRUE** — If only one process is in memory (degree = 1), there's nothing to switch to, so multiprogramming/multitasking provides no benefit.

**15. FALSE** — CPU-bound processes spend most of their time running, not waiting on I/O, so there are few opportunities to switch. I/O-bound processes are the ones that cause frequent switches in multiprogramming.

**16. TRUE** — During a context switch, the OS saves the current PCB and loads the next one. No useful work is done during this time — pure overhead.

**17. TRUE** — `fork()` creates a child with an exact copy of the parent's address space. The cleanup happens later when `exec()` is called.

**18. FALSE** — Only the *parent* can invoke `terminateProcess()` on the *child*, not the other way around. The parent knows the child's ID because the OS passes it to the parent at creation.

**19. FALSE** — In shared memory, the OS creates the shared region, but after that it's under *user/process* control. The processes themselves must handle synchronization and prevent race conditions (e.g., locking until a write is complete).

**20. TRUE** — In indirect (mailbox) communication, the sender places a message in the mailbox and the recipient checks it when available. The recipient is not interrupted, unlike in direct communication.

---

## Bonus — Questions 21–30

**21. TRUE** — In the layered approach, each layer only uses the layers below it. If layer M is clean, the bug can't be in M or anything beneath it. You work upward until you find the problem.

**22. TRUE** — While the process waits, the device driver handles the I/O. When finished, it interrupts the CPU to move data from the local buffer into main memory, then signals the process to return to the ready state.

**23. TRUE** — This is one of the three cases for `terminateProcess()`. The others are: the child's task is no longer needed, or the parent process itself is exiting.

**24. FALSE** — When a parent exits, all of its child processes are terminated as well. They do not continue independently.

**25. TRUE** — If the mailbox lives in OS address space, the sender must go through the OS to deliver the message.

**26. TRUE** — A process-owned mailbox works without OS involvement for reading, but the trade-off is it consumes memory from that process's address space.

**27. FALSE** — In *direct* message passing, the recipient IS interrupted and must handle the message immediately. It's *indirect* (mailbox) communication where the recipient checks at its convenience.

**28. TRUE** — Cooperative processes on separate cores run in parallel (faster) and provide resilience — if one core fails, the other processes continue. This is the modularization benefit.

**29. TRUE** — `fork()` copies the parent's address space, then `exec()` loads a new program into the child, discarding the parts of the parent's copy that aren't needed. These are two distinct steps.

**30. FALSE** — An independent process does NOT use shared memory OR message passing. It doesn't participate in inter-process communication at all.
