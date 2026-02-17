# OS: Design: Mechanism and Policy 1/27/2026

## Explain the following in two examples: Separation Between policy and mechanism allows flexibility in OS design and decision making

- Policy: What needs to be done
- Mechanism: How to do something

* Example: timer
- **Mechanism**: Timer
- **Policy**: Interrupt after every 100 seconds


* **Examples**

- **Policies:**
- All processes must have a unique ID number
- Users can't access files belonging to other users
- Process cannot access the memory of other users

- **Mechanisms**
- Maintain a list of in-use PIDs, don't assign a new process a number from that list
- User and group ownership of filesystem objects; permission bits on each file and directory
- Process ownership of memory regions; permission bits in the kernel's memory table

## As an OS designer, how do you decide whether to use assembly or high level language?

- Historically, Operating systems were written in assembly language

* ASM provides high level of control
* ASM is tightly related to a particular CPU
* ASM saves time and storage for OS

    The issue is assembly code for different processing cores, therefore it is not portable
    High level languages, such as C/C++/Java, are portable
    Modern OS uses both High and low level languages in order to combat this issue and be efficient

* **Android OS**

    Kernel: Assembly and C
    System programs: C/C++
    API: Java


    When you write an OS that doesnt need to be portable, use Assembly
    When you know it needs to be portable, use High Level


# OS Structures

* Monolithic Structure

## Explain the following, Monolithic structure gives high efficiency with little overhead but suffers from single point of failure

    Everything provided by the OS is written in a single File. This includes everything in the kernel, such as kernel interface, system call interface, file system, etc

    Everything is in a single file, so it loads quickly, intrakernel communication is very quick, loaded into main memory at the same time as a single file

    The issue is that if one of these processes break, everything breaks with it because they are loaded in the same file

## How does layerd approach address modularization issues of monolithic structures

    The operating system is divided into a number of layers

    Each layer is built on top of layers

    A layer uses functions and services of only lower level layers

    Layer M consists of data structures and sets of functions that can be invoked by the layer M + 1 and higher

    Layer  M invokes operations on the layer M - 1 and below

## Explain debugging is structured & simplified in layed approach

    You can go layer by layer, and if there isnt an issue in that layer, you know it isn't in that layer or below

    When you find the bug, you know what layer it exists at

## Why doesnt modern OS use a fully-layer approach

    There are challenges in appropriately defining the functionality of each alyer

    Increased overhead

## What factors should you consider before deciding to use microkernels structure as an OS designer

    + Lightweight
    + Flexibility in extension
    + Portability
    + Small attack surface

    - Overhead in inter-process communication

    Most important services remain in the kernel. Message passing model is slower but easier to implement

## Explain "LKM offers higher flexibility than layered approach & more efficiency than mirokernel

    LKM - Loadable Kernel Modules

    Kernel accommodates core components

    Multiple modules, each module can be loaded dynamically at time of system execution

    Core services always in main memory, other services loaded in as needed
    Uses both shared memory and message passing