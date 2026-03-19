# Lesson 15

## Explain "Semaphore can be used for synchronization, but it doesn't guarantee a solution against deadlock"

A semaphore is a counter used to control how many processes can access a shared resource at once (for a binary semaphore, only one at a time). A semaphore prevents two philosophers from using the same chopstick at once, but it does not by itself prevent deadlock.

Example: If each philosopher does `wait(left); wait(right)` and all start together, everyone may grab one left chopstick and then wait forever for the right one. All chopsticks are taken, nobody can continue, and the system is deadlocked.

Plain answer: Semaphores give synchronization (mutual exclusion), but deadlock prevention requires extra rules (ordering, timeouts/retry, or limiting who can pick up at once).

### solutions

- only allow four eaters at a time
- only allow initial pickup if both available

## In variable partitioning for contiguous memory allocation, which one would you prefer to satisfy a memory request
- First fit
- best fit
- worst fit

In contiguous memory allocation, a program must be loaded into main memory. Variable partition is when memory usage for processes in variable depending on size needs. It is allocated exactly the amount of memory it needs

### Explain your rationale in the context of external fragmentation and seasrch overhead



## What is internal fragmentation in memory allocation?



## Explain: "Compaction offers a solution to external fragmentation, but only in a system that allows dynamic relocation


## T/F: External fragmentation is possible only in variable partitioning for contiguous memory allocation
