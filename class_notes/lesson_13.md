# Lesson 13

## Why should you implement both push & pull migration to balance load in multiple program scheduling

You should implement both because they fix load imbalance in different ways. Push migration is proactive: the OS detects a busy CPU and moves some tasks to a less busy CPU. Pull migration is reactive: an idle CPU asks for work from a busier CPU.

Using both keeps the system balanced more consistently. Push prevents one core from staying overloaded for too long, while pull quickly uses idle cores. Together they improve CPU usage, throughput, and response time.

## "Load balancing counteracts the benefits of processor affinity" - how would you address this issue

Processor affinity helps performance by keeping a process on the same CPU so it can reuse cached data. But load balancing may move the process and lose that cache benefit. A practical fix is soft affinity: keep a process on its current CPU when possible, but allow migration when imbalance is significant.

As an OS designer, I would use migration thresholds and only move tasks when needed. I would also avoid moving cache-sensitive or interactive tasks unless required. This keeps most affinity benefits while still preventing overloaded CPUs.

## Explain race condition through an example of bounded buffer in interprocess communication

In a bounded buffer, producers add items and consumers remove items from shared memory. A race condition happens when both update shared variables like `count`, `in`, or `out` at the same time without locks or semaphores. For example, producer and consumer both read `count` at once, then write back different values, causing one update to be lost.

Even if each thread's code looks correct, the interleaving of instructions makes the final result wrong. This is why shared buffer operations must be synchronized.

### What are the implications of race condition in your example

The buffer state can become incorrect. `count` may no longer match the real number of items, which can lead to overflow, underflow, missing data, or duplicate reads.

It also causes unpredictable behavior that is hard to debug. Bugs may appear only sometimes, and in serious cases it can cause crashes or deadlocks if control logic depends on corrupted shared data.

## Explain following terms in the context of critical section problem:

### Mutual exclusion

Mutual exclusion means only one process or thread can enter the critical section at a time. This prevents simultaneous access to shared data.

Without mutual exclusion, race conditions can corrupt data because multiple threads can read and write the same values at once.

### Progress and bounded waiting

Progress means that if no thread is in the critical section and some want to enter, the system must choose one in finite time. Threads that do not want to enter should not delay this decision.

Bounded waiting means a thread that requests entry will eventually get in after a limited number of turns by others. This prevents starvation.

## As an OS designer, how would you balance between possibilities of race condition & response times of user application

I would first guarantee correctness by protecting shared data with synchronization (mutexes, semaphores, atomics), but I would keep critical sections as short as possible. Short locks reduce waiting and keep apps responsive.

Then I would improve performance with fine-grained locking, lock-free structures where appropriate, and scheduling policies that reduce blocking (such as priority inheritance). This gives both safety and good response time.


* We may have race condition in non-preemptive scheduling - FALSE