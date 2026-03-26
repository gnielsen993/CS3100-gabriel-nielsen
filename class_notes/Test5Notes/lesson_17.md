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

    Resource Allocation Graph precisely describes a deadlock. There is a list of threads and a list a resources. 

    A cycle can tell if we are in a deadlock. No cycle, no circular wait, no deadlock
    
    For a deaadlock to occur, a cycle does not need to include all resource types. 

## As an OS designer, how would you consider a system to be in safe state in avoiding a deadlock?


## How can we avoid a deadlock through Resource Allocation Graph algortihm

### What is the limitation of this approach?


### How can we address that limitation?
