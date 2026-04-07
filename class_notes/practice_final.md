## Assume that there are two compilers: Sec-Comp and Reg-Comp/ Sec-Comp has StackGuard buffer overflow prevention technique implemented in it. Reg-Comp does not have any buffer overflow prevention mechanism. We do not consider static analysis as a prevention technique in this example

### Imagine that Sec-Comp is installed in a system where there is no OS-level or hardware-level modification implemented to protect against buffer overflow vulnerabilities. In this system, mention two different ways that an adversary can exploit to bypass the protection offered by Sec-Comp to launch a buffer overflow attack. et's term these two ways as attack-techniques

1. **Canary disclosure/prediction attack**  
   If the attacker can leak the stack canary (for example via an information leak/format-string bug) or predict it, they can craft an overflow that rewrites the return address while restoring the correct canary value. StackGuard check passes, but control flow is hijacked.

2. **Overwrite control data not protected as a return address**  
   StackGuard is focused on return-address corruption. An attacker can overflow a buffer to corrupt other sensitive targets (for example function pointers or security-critical local state) and then trigger an indirect transfer/hijack without needing a direct return-address overwrite.

#### Between the two attack techniques you mentioned, can ProPolice scheme offer higher resilience to at least one of them compared to StackGuard? Why/why not?

Yes. ProPolice is generally stronger against the second technique because it reorders stack variables to make overwriting non-buffer control data harder from a local buffer overflow.  
However, ProPolice does **not** fundamentally solve the first technique: if the canary is known/predictable, a canary-preserving overwrite can still bypass canary-based checks.

#### If we are to replace StackGuard, which prevention mechanism would you recommend for Sec-Comp to offer resilience against both the attach techniques you mentioned?

I would recommend **program shepherding (runtime control-flow enforcement / CFI-style checking)**.  
Reason: it validates runtime control transfers (returns and indirect branches) against legal targets, so even if a canary is bypassed or an indirect control object is corrupted, the redirection attempt is blocked.

### Assume that Reg-Comp is installed in a system that uses program shepherding. Prog1 is a program that has buffer overflow vulnerabilities; for example, it uses unbounded copying function like strcpy(). If Prog1 is compiled using Reg-Comp, will it be possible to protect from buffer overflow attacks? Why/why not

**Mostly yes for control-hijacking BOA, but not complete prevention of memory corruption.**  
Even with Reg-Comp (no compiler hardening), program shepherding can still stop many BO exploits by rejecting illegal control-flow redirections (for example overwritten returns/indirect jumps to attacker-chosen code).  
But the underlying overflow bug still exists, so non-control-data corruption or crash/DoS effects may still occur.
