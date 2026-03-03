# Lesson 13

## Why should you implement both push & pull migration to balance load in multiple program scheduling

    Migration is moving the load of the ready queue to cores that can sustain it

    a push migration is by a thread that pushes processes from a heavy CPU to one that currently has
    a lighter load, periodically

    a pull migration is done by the CPU core and based off availability, If empty, it will pull from a heavy load
    to take the process over

## "Load balancing counteracts the benefits of processor affinity" - how would you address this issue

    processor affinity is the idea that one process sticks to one core in order to have one cache, but load balancing
    moves the process to a new CPU to imrpove efficiency but takes away the benefit of a single cache

    soft affinity is when they prefer a single cpu but will move if absolutely neccesary

## Explain race condition through an example of bounded buffer in interprocess communication

    

### What are the implications of race condition in your example



## Explain following terms in the context of critical section problem:

### Mutual exclusion

### Progress and bounded waiting


## As an OS designer, how would you balance between possibilities of race condition & response times of user application


