## Assume that there are two compilers: Sec-Comp and Reg-Comp/ Sec-Comp has StackGuard buffer overflow prevention technique implemented in it. Reg-Comp does not have any buffer overflow prevention mechanism. We do not consider static analysis as a prevention technique in this example

### Imagine that Sec-Comp is installed in a system where there is no OS-level or hardware-level modification implemented to protect against buffer overflow vulnerabilities. In this system, mention two different ways that an adversary can exploit to bypass the protection offered by Sec-Comp to launch a buffer overflow attack. et's term these two ways as attack-techniques

#### Between the two attack techniques you mentioned, can ProPolice scheme offer higher resilience to at least one of them compared to StackGuard? Why/why not?

#### If we are to replace StackGuard, which prevention mechanism would you recommend for Sec-Comp to offer resilience against both the attach techniques you mentioned?

### Assume that Reg-Comp is installed in a system that uses program shepherding. Prog1 is a program that has buffer overflow vulnerabilities; for example, it uses unbounded copying function like strcpy(). If Prog1 is compiled using Reg-Comp, will it be possible to protect from buffer overflow attacks? Why/why not