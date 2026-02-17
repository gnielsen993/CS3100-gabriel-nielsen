# CS3100 Practice Quiz — Lectures 6–8 (True/False)

1. A policy defines *how* something is done, while a mechanism defines *what* needs to be done. F

2. A timer is an example of a mechanism, not a policy. T

3. Modern operating systems typically use only high-level languages because assembly is obsolete. F

4. In Android's OS design, the kernel is written in Assembly and C, while the API layer uses Java. T

5. In a monolithic OS structure, if one component fails, the entire system can fail. T

6. The layered approach to OS design reduces overhead compared to a monolithic structure. F

7. A microkernel has a small attack surface but suffers from overhead due to inter-process communication. T

8. Loadable Kernel Modules (LKM) require all modules to be loaded into main memory at boot time. F

9. Two separate processes cannot be associated with the same program. F

10. The text and data sections of a process grow and shrink dynamically during execution. F

11. A process can transition directly from the waiting state to the running state. F

12. The PCB stores the process state, program counter, registers, and list of open files, among other data. T

13. A multithreaded process has a separate PCB for each thread. F

14. The degree of multiprogramming must be greater than 1 to gain any benefit from multiprogramming. T

15. CPU-bound processes cause frequent context switches in a multiprogramming environment. F

16. Context switching is considered overhead because no useful work is performed during the switch. T

17. In UNIX, a child process created by `fork()` receives an exact copy of the parent's address space. T

18. Only the child process can invoke `terminateProcess()` on its parent. F

19. In the shared memory model of IPC, the operating system controls access to the shared region to prevent race conditions. F

20. In indirect message passing, a message is placed in a mailbox and the recipient reads it when available, without being interrupted. T

---

## Bonus — Gap Coverage (True/False)

21. In the layered OS approach, if debugging reveals no issues in layer M, you can conclude that the bug is not in layer M or any layer below it. T

22. When a process is in the I/O waiting state, the device driver interrupts the CPU to move data from the local buffer to main memory. F

23. A parent process may terminate a child process if the child requires more resources than the parent can provide. T

24. When a parent process exits, its child processes are allowed to continue running independently. F

25. If a mailbox is stored in the OS address space, the sender must communicate through the OS to deliver a message. T

26. If a mailbox is stored in a process's address space, the downside is that it requires allocated memory from that process. T

27. In direct message passing, the recipient process is not interrupted and can check for messages at its convenience. F

28. Splitting a large process into smaller cooperative processes running on separate cores improves both speed and resilience. T

29. In UNIX, after `fork()` creates a child process, the child calls `exec()` to load a new program and discards unneeded portions of the parent's copied address space. T

30. An independent process is one that uses shared memory but does not use message passing. F
