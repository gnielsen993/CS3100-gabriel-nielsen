# Lesson 15

## Explain "Semaphore can be used for synchronization, but it doesn't guarantee a solution against deadlock"

A semaphore is a counter used to control how many processes can access a shared resource at once (for a binary semaphore, only one at a time). A semaphore prevents two philosophers from using the same chopstick at once, but it does not by itself prevent deadlock.

Example: If each philosopher does `wait(left); wait(right)` and all start together, everyone may grab one left chopstick and then wait forever for the right one. All chopsticks are taken, nobody can continue, and the system is deadlocked.

Plain answer: Semaphores give synchronization (mutual exclusion), but deadlock prevention requires extra rules (ordering, timeouts/retry, or limiting who can pick up at once).

### solutions

- only allow four eaters at a time
- only allow initial pickup if both available

## In variable partitioning for contiguous memory allocation, which one would you prefer to satisfy a memory request
- First fit - First space with enough units
- best fit - Space with least amount of leftover units in the hole
- worst fit - Space with the most amount of leftover units in the hole

I’d pick **first fit**.

In contiguous memory allocation, each process must occupy one continuous block in RAM. Variable partitioning means free memory is split into blocks of different sizes, and a process gets one block that is large enough. The block is exactly the size the process needs

First fit is a good default: it uses low search overhead because it chooses the first block that is large enough. Best fit can reduce leftover space a bit on each allocation (sometimes lower external fragmentation), but it needs a full search each time and can be slower. Worst fit usually leaves large holes and tends to increase external fragmentation.

### Explain your rationale in the context of external fragmentation and seasrch overhead

First fit is usually preferred because it balances external fragmentation and speed: reasonably good allocation with much lower overhead than best fit, and generally better behavior than worst fit.

## What is internal fragmentation in memory allocation?



## Explain: "Compaction offers a solution to external fragmentation, but only in a system that allows dynamic relocation


## T/F: External fragmentation is possible only in variable partitioning for contiguous memory allocation
