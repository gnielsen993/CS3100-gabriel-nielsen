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

Internal fragmentation is wasted memory **inside** an allocated block. It occurs when the allocated block is larger than what the process actually uses, so part of that reservation stays unused forever.  
Example: fixed-size allocation gives a process 8MB when it needs 6MB, so 2MB is internal waste.

Difference from external fragmentation:  
Internal fragmentation = space wasted inside allocated memory blocks.  
External fragmentation = free memory is split into separated holes outside allocations.

## Explain: "Compaction offers a solution to external fragmentation, but only in a system that allows dynamic relocation
Compaction is the process of moving allocated blocks and packing them together so that small gaps are removed and larger free space is formed. It reduces external fragmentation by turning scattered holes into one bigger free block. It only works if the system supports **dynamic relocation** (processes can be moved to different physical addresses at runtime), which requires address-translation support and saved process state updates.

## T/F: External fragmentation is possible only in variable partitioning for contiguous memory allocation
True.

In **fixed partitioning**, the issue is mostly **internal** fragmentation.  
In **variable partitioning (contiguous)**, external fragmentation is the main issue.

## T/F Compaction gaurantees a new process to be allocated the requested memory space

False

Even if compacted, there still might not be enough total memory space, so a 4mb process cant fit in 2mb remaining

## T/F for compaction, all the processes in memory need to be relocated every time a process terminates

False

The largest/only hole might be connected to where the process is terminated

## How does paging solve external fragmentation issue without the need for compaction
Paging breaks a process into fixed-size pages and maps each page to any free physical frame. Since pages do not need to be contiguous, scattered free frames can be used directly, so there are no gap-size failures like variable contiguous allocation.

Result: external fragmentation is removed in main memory, though small internal fragmentation can still occur in the last page of a process.
