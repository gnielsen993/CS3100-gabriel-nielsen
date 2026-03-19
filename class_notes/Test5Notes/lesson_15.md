# Lesson 15

## Explain "Semaphore can be used for synchronization, but it doesn't guarantee a solution against deadlock"

In the Dining Philosopher problem, there are 5 people and only 5 chopsticks to eat with. If everyone reaches for the their left chopstick first, they are deadlocked, there is nothing preventing a race condition because they grabbed for separate items, so they are stuck waiting indefinitly. In a synchronization issue, it would occur reaching for the same resource, with a semaphor stopping the second person reaching for a chopstick

### solutions

- only allow four eaters at a time
- only allow initial pickup if both available

## In variable partitioning for contiguous memory allocation, which one would you prefer to satisfy a memory request
- First fit
- best fit
- worst fit

### Explain your rationale in the context of external fragmentation and seasrch overhead



## What is internal fragmentation in memory allocation?



## Explain: "Compaction offers a solution to external fragmentation, but only in a system that allows dynamic relocation


## T/F: External fragmentation is possible only in variable partitioning for contiguous memory allocation