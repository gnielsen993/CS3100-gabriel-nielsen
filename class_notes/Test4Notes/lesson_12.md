# Lesson 12

## Explain:

### Performance of Round-Robin Scheduling depends on the length of a time quantum

    similar to FCFC except preemption is added. Time quantum is the amount of time a process can run before it is preempted. Round robin is a circular ready queue

- If process's CPU burst < 1 time quantum: Voluntary CPU release by process itself
- If process's CPU burst > 1 time quantum: Forced by CPU

    process in ready queue: n
    time quantum: q
    wait time no longer than (n-1)*q

    If very large quantum: basically FCFS
    If very small: Overwhelming dispatch latency

### Turnaround time depends on the length of time quantum

    three processes: 10 time units each

    if time quantum is 1 time unit,
        Average turnaround time is 29 units
    
    if time quantum = 10 time units
        Average turnaround time is 20 units

## In priority scheduling, how preemptive scheduling is different from nonpreemptive scheduling


## As an OS designer, how could you address starvation issue in priority scheduling?

    Starvation is when low priority processes can be waiting for a really long time because high priority processes keep coming

    The system can increase the priority of the process as it keeps waiting to get it through the queue

## In which context you you combine RR scheduling with priority scheduling algorithms

    Two proccesses have the same priority, so use round robin between the processes in order to fair share it

## When does starvation occur in multilevel queue scheduling? How can you address it?

    Multilevel queue has separate queues for each distinct priority. 

    Starvation can occur because new processes in higher queues can pop up and push them out. You can address it by defining time quantums for every priority queue. Higher time quantums for higher priority queues

## Which one could you choose to maximize efficient use of cache memory:

- Common Ready queue
    
- Per-processor ready queue

## Best for cache efficiency
**Per-processor ready queue** (usually) maximizes cache locality because a thread tends to stay on the same CPU, reusing that CPU’s caches (warm L1/L2, sometimes L3).

---

## Common (global) ready queue
**Pros**
- Simple load balancing (any CPU can pull work)
- Good when tasks are very short / highly bursty
- Fewer idle CPUs when work exists

**Cons**
- Worse cache locality (threads hop CPUs → cold caches)
- More contention on the single queue (locks/atomics, cache-line bouncing)
- Can hurt scalability as core count grows

---

## Per-processor ready queues
**Pros**
- Better cache locality (CPU affinity, fewer migrations)
- Less contention (each CPU mostly touches its own queue)
- Scales better on many-core systems

**Cons**
- Load imbalance risk (one CPU overloaded while others idle)
- Needs work-stealing / periodic rebalancing (extra complexity/overhead)
- Can be less fair globally without extra mechanisms