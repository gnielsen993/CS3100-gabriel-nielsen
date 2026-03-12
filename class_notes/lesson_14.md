# Lesson 14

## As an OS designer, should you eliminate the need of context switch for a process during its busy waiting? Explain

Do not eliminate context switching entirely; decide based on hardware and wait length.

Single-core scenario:
If process P1 spins waiting for a lock held by P2, the OS should context switch to P2 quickly.
Otherwise, P1 burns the only CPU and P2 cannot run to release the lock.

Multi-core, short-duration scenario:
If P1 waits for a lock held by P2 running on another core and P2 is expected to release it very soon, P1 can spin briefly.
Here, avoiding a context switch may be faster than sleeping/waking.

Multi-core, long-wait (two-context-switch) scenario:
If the expected wait is longer, block P1 and run another thread.
When the lock is released, wake P1.
This typically costs about two context switches (switch out when blocking, switch back in when awakened), but saves CPU time and improves fairness.

### How would you define 'short duration'?

A short duration is a wait time smaller than the expected context-switch and scheduling overhead.
In practice, this is often only a few microseconds (or similarly tiny on the target system), and should be measured empirically.

## Define atomic operation with an example

An atomic operation is an indivisible operation that cannot be interrupted in the middle.
Example: `test_and_set(lock)` atomically reads the old lock value and sets it, which is used to implement mutual exclusion.

## Discuss an example of how an application developer can use counting semaphore to address synchronization issue in parallel programming

Semaphore: a synchronization primitive controlled by an integer value, updated atomically using `wait(P)` and `signal(V)`.
Binary semaphore: semaphore restricted to values 0/1, commonly used for mutual exclusion (similar to a lock).
Counting semaphore: semaphore with value 0..N, used to track multiple identical resources or available slots/items.

Example: producer-consumer with a bounded queue.
Use counting semaphore `empty = N` (available slots) and `full = 0` (available items), plus a mutex for queue access.
Producer: `wait(empty)`, lock mutex, insert item, unlock mutex, `signal(full)`.
Consumer: `wait(full)`, lock mutex, remove item, unlock mutex, `signal(empty)`.
This coordinates parallel threads and prevents buffer overflow/underflow.

## As an application developer, how would use semaphore to address synchronization issue in bounded buffer problem

Bounded Buffer problem:
Buffer is empty: consumer waits
Buffer is full: producer wait for the consumer to remove items
Only producer or consumer can access the shared buffer at the same time

Use three semaphores:
`mutex = 1` (binary semaphore for mutual exclusion), `empty = N` (counting semaphore for empty slots), and `full = 0` (counting semaphore for filled slots).

Producer:
`wait(empty)` -> `wait(mutex)` -> add item -> `signal(mutex)` -> `signal(full)`.

Consumer:
`wait(full)` -> `wait(mutex)` -> remove item -> `signal(mutex)` -> `signal(empty)`.

This prevents race conditions (`mutex`), overflow (`empty`), and underflow (`full`).

## Explain the readers writers problem, including First and second problems

Readers-Writers problem:
Multiple processes/threads share a data object (file/database/structure).
Readers only read, writers modify.
Goals:
1. Allow multiple readers at the same time (safe concurrency for reads).
2. Writers need exclusive access (no readers/writers during write).
3. Avoid starvation, depending on policy.

### First Readers-Writers Problem (reader-priority)
Requirement: no reader waits unless a writer is already writing.
Effect: high read throughput, but writers can starve if readers keep arriving.

Typical semaphore idea:
- `rw_mutex = 1`: controls access to shared data (held by writer or by reader group).
- `mutex = 1`: protects `read_count`.
- `read_count = 0`.

Reader:
1. `wait(mutex)`; `read_count++`; if first reader (`read_count == 1`) then `wait(rw_mutex)`; `signal(mutex)`.
2. read.
3. `wait(mutex)`; `read_count--`; if last reader (`read_count == 0`) then `signal(rw_mutex)`; `signal(mutex)`.

Writer:
1. `wait(rw_mutex)`.
2. write.
3. `signal(rw_mutex)`.

### Second Readers-Writers Problem (writer-priority)
Requirement: once a writer is waiting, no new readers should start.
Effect: prevents writer starvation, but readers can starve under heavy write load.

Typical idea:
- Add a "turnstile" / gate semaphore so arriving readers must wait behind waiting writers.
- Existing readers can finish, then waiting writer proceeds.
- While writers are queued, new readers are blocked from entering.

Summary:
- First problem prioritizes readers (writer starvation possible).
- Second problem prioritizes writers (reader starvation possible).
- Fair versions add FIFO ordering to avoid starvation for both sides.


## Code Questions
