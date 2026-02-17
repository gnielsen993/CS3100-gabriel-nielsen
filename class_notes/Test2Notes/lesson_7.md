# Processes

## Agree/Disagree - Two processes can be associated with the same program

    True, for example if therre are two windows running safari, those are two separate processes for the safari program

## How text and data sections differ from the stack and heap sections in the process layout in memory

    Text and data sections have a set amount of used space, while stack and heap will grow or shrink based on how much space it needs while running

    Part of the address space in the heap can be allocated for shared memory model of communication

## In the life cycle of a process

### How is ready state different from new state

    When a new process is created, it creates a new ID for the process in memory (by the kernel). Then the load system call is invoked and os allocates address space in main memory. Then the process must be placed in the ready queue. Once these happens, it allows the process go from the new state to ready state

    In the ready state, it is in the ready queue and can be ran, but has not been ran yet

### Can the switch between ready and running state be cyclic and how

    When operations require I/O operations, the switch between ready and running state can be cyclic through the waiting state, in which it is waiting for input. This happens in multiprocessing. If it is multiprocessing and no I/O, it will not be cyclic

    When in a multitasking environment, it will go from running to ready anfter the timeslice, and each process will cycle through in time slices

### What happens in the background when a process is in I/O waiting state

    While the process is waiting, the input will be in the local buffer. The device driver will interrupt the CPU and move the input from local buffer to main memory and inform the process in waiting state that it has gotten the input and can be moved back to ready state

### Can a process switch directly from waiting state to running state? Why/why not

    No? Because it is not on the ready queue, which exists in the ready state 

### In which cases does a process switch from running to the terminated state

    The process cn be forcefully terminated on exit(), abort(), or terminate()

## Explain: "PCB serves as a repository for all the data needed to start or restart a process"

    The PCB is a piece of data structure maintained by os for every program in the system. It consists of:

    Process State
    Process Number
    Program Coutners
    Registers
    Memory allocated & limit
    List of open files

    The information needed for the process all lives in the PCB. When a process is in the running state, the information will be stored in the PCB so that when the process is scheduled to run again, it can load the data to start where it was left off at

## how does a multithreaded process offer better reaction time to users through parallel computation of tasks

    A thread represents a unit of task within a process 
    A multithreaded process alows for multiple of units to be ran at the same time
    A single threaded process only allow one of these units to be ran at the same time


    **There is only one PCB for multithreaded processes becaues it is still only one process**
