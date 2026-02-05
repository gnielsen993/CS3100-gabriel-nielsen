# Class 8

## How do the following aspects affect multiprogramming and multitasking environments:

### Degree of multiprogramming

    The number of processes currently held in main memory. If the degree is one, you wouldnt get the advantage of multiprogramming or multitasking because there are no processes to switch to. You must have a degree > 1 to have an advantage

### I/O bound processes

    This is a process which spends most of the life cycle dependant on input/output events. In multiprogramming environments, as the process goes into I/O waiting state, new processes can cycle into the CPU core, allowing the frequency of switches to be high.

### CPU bound processes

    This is a process which spends most of the life cycle running, not waiting on I/O events. In multiprogramming, the processes in the queue must wait until the process is finished, there is not operations that push them to the waiting state, making switching low. In multitasking, it does not matter because the operations are on a timer, they will switch on a preset interval no matter what

## Describe the possible scenarios of process scheduling through a queueing diagram

- Scenario 1:

    When the time slice expires, it will go back to the ready queue

- Scenario 2:

    If it needs an I/O operation, it will go to the I/O wait queue and go back to the ready queue

- Scenario 3:
        
    A child process is created, allowing the parent process to continue execution or wait. If the parent process waits, it will call wait_time() or wait_event(). If based on wait time, it will wait until the time expires and move back to the ready queue. If based on wait_event, it will wait until an event happens, such as child process completing execution, then the parent will move back to the ready queue

    **If on final exam, explain the full picture*

- Scenario 4:

    If the process is waiting for an interrupt, they will wait for an interrupt to ockkur in the queue and then moves back to the ready queue once the interrupt ockkurs

## Explain: "Time spent in context switching is an overhead"

    When context switching occurs, the underlining process is storing the process control block of the current process and load the new PCB of the process off the queue. During this time, no real work can be done, so it is considered an overhead.

## How does OS manage system overload from creating too many child processes by a single process

    For a process, you have fixed and variable areas, the variable areas include the heap and stack. Suppose a parent calls create_process(), creating its own child process. The child process must have its own address space. Suppose the parent keeps creating new child processes, each child process must hold address space on the main memory, and can potentially overload. 

    To solve this, the OS can have 3 different policies

    1: Have a limit on memory allocation for each parent process, giving them a maximum they cannot exceed
    2: Have a limit on number of child processes per parent
    3: do not allocate additional memory for child processes, they must take up address space from the parent address space

## "Parent and child processes can easily communicate with each other at the early stage of a child proccess creationand then go their separate ways in executing separate sets of instructions" explain in the context of UNIX

    The child process can inheret an exact copy of address space of the parent process. The message will already exist in the address space

    In unix, the parent will call fork() to create a child process. When the fork system call is invoked, the exact copy of of address space of the parent process. Then, the child process will invoke exec(), it will keep the piece of the address space needed from the parent, then load a new program from hard disk to remaining space to execute usage of instruction. It gets rid of parent copy address space that isn't needed in order to free up space to run what is asked by parent

## In which cases parent process may invoke terminateProcess() system call

    Only the parent process can invoke terminateProcess for the child process. There are three cases in which it will invoke

    1: If the process the child is running is no longer needed, it will terminate
    2: When the parent process exits, it will end all child processes as well
    3: If the child process needs more space but parent cannot give it, it may need to terminate because it is no longer completable. It must pass the id of the child process in order to know which one to terminate. The parent knows the ID by being passed by OS to parent on creation of child

## How does process co-operation contribute to system parformance and stability

    process co-operation: There are independant and cooperative processes. An independant process is a process that does not depend on other processes and other processes do not depend on it. It does not use shared memory or message passage model. It does not participate in communication at all. Large processes are divided into smaller parts, each running on a different core, running in parallel, making it run faster. If one core stops, the other processes can still run, then the process that was interrupted can run on a different core. If they all ran as one process on the same core, it fails, the whole process will have to run again. **Modularization**

## In shared memory model, the communication is under the control of user processes, not the operating system

    In shared memory we assign part of the address space for interprocess communication. P0 invokes shared_memory_create(address space). P1 will use shared_memory_attach(P1, address space). It is under user control to make sure it doesnt run into race condition, where it is read before the full information is written. The shared location can only be unlocked once the process finishes writing

## How a mailbox is maintained in indirect communication

    Direct communication requires contact between the two parties. The recipient process will be interrupted and need to address the message send(P0, message)

    Indirect communication requires a mailbox. The recipient process gets the message in the mailbox and checks the message when available, it will not be interrupted. sned(A0, message) A0 is the address of the mailbox

    The mailbox can be apart of the process address space or OS address space. If part of OS address space, it will have to communicate with the OS to send the message. If part of process, the OS can notify the sender that it does not exist and the recipient can read it whenever it wants. The downside to being part of process is needing allocated memory space part of the process.
