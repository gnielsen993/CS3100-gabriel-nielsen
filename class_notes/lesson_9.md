# Lesson 9

## How do the scopes of buffering affect interprocess communication in message passing model

There are three different scopes of buffering

- Zero Capacity Buffering

    Used when using direct communication

- Finite-length buffering (Bounded)

    The length of the message must be less than or equal to the size of the mailbox, or the part larger will never be seen

- Infinite-length buffering (unbounded)

    There is a initial size but can grow with the size of the message, allowing the full message to be passed

### Synchronous communication

- Blocking send() - will pause until the consumer receives the message, enters interrupt wait queue until acknowledgement
- Blocking receive() - Consumer process invokes when it needs to wait until it gets the message from producer, wait in the interrupt queue

### Asynchronous communication

No processes wait

- Non-blocking send() - producer will continue regular operation after the send
- Non-blocking receive() - consumer will never wait for an input


- producer: Sends the message
- consumer: receives the message

Zero capacity queue cannot be used with asynchronous communication because there is no storage capacity, therefore there is no way for the proceses to keep going without acknowledging the message

In asynchronous communication happens, we need a place to store the message either bounded or unbouded capacity. In bounded capacity, if the buffer is already full and another message is trying to be sent, the producer process can one message and wait, invoking blocking send system call waiting for an event to occur. It will wait for space to open up, then send the message. If there is unbounded capacity queue, the queue will grow to the needed size. It will never need to invoke blocking send() because it will always grow to the size of the message

## Whih IPC method would you choose in a distributed environment

They will use message passing model. Each computer is independant, so they don't have any shared ram and couldnt have a shared memory space

## Illustrate the differences between a single-threaded and a multithreaded process in terms of their address space and PCB

A single threaded process cannot take advantage of parallel programming by itself

In single threaded, one thread will take all of the address space and pcb

In multi threaded, all threads reside in the same address space. In the PCB, there are shared and unique parts between the threads

## As a system designer, why should you create & use a multithreaded application for a web server

Multithreaded application allows for a new thread to run per request/user so there isnt a lot of latency for high requests. If it was single threaded, it would hang on each client request. If it was creating child processes, it would be too heavy, too much memory, and too slow, creating new address space

## Explain "We need multiple CPU cores for parallelism, but not for concurrency

concurrency ones multiple tasks in the same core at the same time, not technically because they switch, but it feels that way to the user, but parallelism requires different tasks different cores same time

X - Task one
O - Task two

concurrency

CPU1 - XOXOXOXOXO

Parallelism

CPU1 - XXXXX
CPU2 - OOOOO

Without multiple cores, it would be impossible

## Explain "Data & task parallelism are not mutually exclusive"

In data parallism, it is same operation on different threads/core
In task parallelism, it is different operations on different threads/core.

Task parallelism is same set of data, but different operations while data parallelism is same operation different dataset per core. They are not mutually exclusive because 

## What are the implications of the following statements:

### "As N approaches infinity, speedup approaches 1/s"

The more parallel portions we have, the more threads we can create and the speedup ratio will increase

Amdahl's law identifies performance gains from adding additional cores to an application that has both serial and parallel components

speedup is less than or equal to 1/(S + (1-S)/N)

S is serial portion
N is processing cores

If 75% parallel and 25% serial, then one to 2 cores wil speedup by 1.6 times
So as the amount of cores gets bigger, the add portion gets smaller, gets closer to 1/S. so speedup is limited by serial size

## What Challenges would you expect in developing a multithreaded application

identifying tasks that can run in parallel, data parallelism or task parallelism
Balance in terms of what modules done when they run in parallel
Data splitting
Data dependency - synchronize modules depending on how they depend on eachother
Testing and debugging -

### Other Notes

We can reduce overhead in a multithreading environment