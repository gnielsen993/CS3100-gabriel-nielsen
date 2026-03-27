# Lesson 17

## As an OS designer how would you address the implementation challenges of LRU algorithms?

- Using Counters:
* Each page-table entry is associated with a time-of-use field
* Logical clock/counter  to CPU: Incremented for every memory reference
* Reference to a page is made: Contents of clock register are copied to its time-of-use field
* 'Time' of the last reference 

- Using Stacks
* A stack of page numbers
* Page is referenced?: Remove from stack and put on top
* Top: Most recently used paged; Bottom: least recently used page
* Doubly linked list with a head poiinter and a tail pointer
* Tail pointer to bottom of stack

    The stack section is part of the memory space of the address, causing a potential memory issue when using the stack approach

## Explain: "Necessary conditions of deadlock are not completely independent of each other"

    mutual exclusion, hold & wait, No Preemption, and circular wait all need to occur in order for a deadlock to happen

    - mutual exclusion: at least one resource must be held in a non-shareable mode, so while one process uses it others must wait, which allows competing processes to block each other.
    - hold & wait: a process holding at least one resource is waiting for additional resources, creating dependences that can spiral into deadlock because those held resources cannot be released.
    - no preemption: resources cannot be forcibly taken away from processes, so the only way to break the impasse is for a process to voluntarily release its resources, which may never happen.
    - circular wait: a cycle of processes exists where each process holds a resource the next process needs, so the chain closes and no process can proceed without another releasing a resource first. 

## Explain the implications of a cycle in Resource Allocation Graph

    Resource Allocation Graph precisely describes which processes hold and request which resources. Each process (circle) can have edges pointing to the resources it is requesting, and each resource (square) has edges to the process that currently holds it.

    A cycle in the graph implies circular wait, so no process in the cycle can proceed because each is waiting on a resource held by the next process. If the graph has no cycle, then there is no circular wait and therefore no deadlock.

    Example deadlock graph:

    P1 -> R1 -> P2 -> R2 -> P1 forms a cycle (P1 holds R1 and waits for R2 while P2 holds R2 and waits for R1). This indicates a deadlock.

    Diagram (P=process circle, R=resource square):

    (P1) -- request --> [R2] -- assigned --> (P2) -- request --> [R1] -- assigned --> (P1)
    (arrowed loop closes in circle)

    Non-deadlock graph:

    P1 -> R1, R1 -> P1 (P1 holds and uses R1) and P2 -> R2, R2 -> P2, with no edges between the two groups. Since no cycle crosses multiple processes, they can both finish, so the system remains deadlock-free.

    Diagram:

    (P1) <-- assigned -- [R1]
    (P2) <-- assigned -- [R2]

## As an OS designer, how would you consider a system to be in safe state in avoiding a deadlock?

    A system is in a safe state when the OS can find a sequence of all processes such that each process can obtain the resources it still needs, run to completion, and release its resources, allowing the next process in the sequence to proceed. If such a sequence exists, then a request can be granted without risking a deadlock because every process will eventually finish and free resources.

    To evaluate safety, the OS can simulate granting a request by temporarily allocating the resources, checking whether a safe sequence still exists, and rolling back if it does not. Banker's algorithm is a canonical implementation of this idea.

    Banker's algorithm maintains three data structures for each process: `Allocation` (what resources it currently holds), `Max` (the maximum it might need), and `Need = Max - Allocation` (what it still requires). It also keeps a `Work` vector representing the currently available resources and a `Finish` flag per process.

    When a process requests resources, the algorithm tentatively allocates them (updating `Available`, `Allocation`, and `Need`) and then runs the safety check: it scans for a process whose `Need` can be satisfied by `Work`, marks it as `Finish`, adds its `Allocation` back to `Work` (since that process could finish and release its resources), and repeats. If all processes can eventually finish (`Finish` becomes true for each), the system is safe and the tentative allocation becomes permanent. If not, the request is rolled back.

    Example: Suppose there are 3 resource types and process P1 currently holds (1,0,1) and could need up to (3,2,1), so its `Need` is (2,2,0). The OS checks whether the currently available vector can cover (2,2,0), and if so, it simulates P1 finishing and recovers its allocation. The algorithm continues until every `Need` can be met in sequence, guaranteeing a safe state.

## How can we avoid a deadlock through Resource Allocation Graph algortihm

    Before granting a resource request, the OS can add the corresponding request edge to the Resource Allocation Graph and then check for a new cycle. If adding the edge creates a cycle, the request is denied or the process is forced to wait until the graph becomes acyclic again. This prevents the circular wait condition from forming, and therefore blocks deadlock from happening.

### What is the limitation of this approach?

    The Resource Allocation Graph cycle detection approach only works cleanly when each resource type has a single instance. With multiple instances per resource type, a cycle no longer guarantees deadlock, and cycle detection can falsely accuse healthy states or miss actual deadlocks.

### How can we address that limitation?

    We can transform the multi-instance RAG into a wait-for graph, where processes point directly to other processes they are waiting on. We then detect a cycle in the wait-for graph, which is accurate even when each resource type has many instances. Alternatively, algorithms like Banker's algorithm or state-based safety checks simulate future allocations and explicitly verify that a safe sequence exists before granting resources.
