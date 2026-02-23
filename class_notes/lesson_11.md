# Lesson 11

## Explain CPU I/O bursts cycle in a typical interactive system

CPU -> I/O wait -> CPU -> I/O wait -> ...

I/O intensive processes have many small CPU bursts
CPU intensive processes have less long CPU bursts

## Explain preemptive & nonpreemptive scheduling in the context of multiprogramming & multitasking

preemtive scheduling is when a process is forced to switch

nonpreemtive scheduling, it is up to process itself when to context switch

Preemptive scheduling is possible in multiprogramming - FALSE

Both preemptive and nonpreemptive scheduling is possible in multitasking - TRUE

Context switch is voluntary in nonpreemptive scheduling - TRUE

## How can a race condition occur in preemptive scheduling

In preemptive scheduling, a race condition occurs when the OS interrupts one thread in the middle of updating shared data, allowing another thread to run and access that same data before the first thread finishes.

How to stop:

    process p1 cannot access file until unlocked


## What is the role of a dispatcher in CPU scheduling? What is dispatcher latency

When a context switch happens, there is a need to save the state and start the state, this is what the dispatcher does, and the time it takes is the latency

## Explain:

## An increase in CPU Utilization does not gaurantee increase in throughput

if we consider multipgrommaing environment, program is running state and cpu is active, we are considering multiprogramming environment right, only one process in being served here

IF multitasking, more processes can be served

## Waiting time cannot be higher than turnaround time

 waiting time is always less than or equal to turnaround time because turnaround time is equal to CPU execution + I/O wait + waiting time in ready queue, so fuck sake, it is impossible because waiting time is a direction portion of the turnaround time, stop fucking explaining this

 ##