# Lecture 18

## Explain:

### Creating 'ramp' increases the success probability of buffer overflow attack

A NOP ramp is a contiguous sequence of no-operation bytes inserted before shellcode in an overflow payload. Because any jump into the NOP sled slides execution down into the payload, the attacker does not need to hit the exact return address; it greatly tolerates address uncertainty and raises exploit reliability.

### Indirect BOA has more dependencies than a direct BOA

Indirect buffer overflow attacks require overwriting pointers used in indirect control transfers (function pointers, return addresses, vtable slots). They depend on more program state and timing (pointer location, code path, stack layout, non-executable protections) than direct overwrites of code, so they are often more complex to craft but more flexible across protections.

## Give an example of how static analysis can prevent BOA

- Static analysis can detect unsafe buffer writes by tracking input length and array bounds. For example, flagging `strcpy`/`gets` usage and recommending `strncpy`/`fgets` with size limits prevents unbounded overwrite paths before runtime.

## Compare & rationalize BOA prevention techniques that work through compiler modification

- Compiler-based techniques add stack canaries, enforce non-executable stack (DEP), and insert bounds checks. These techniques make overflow exploits harder by turning out-of-bounds writes into detectable faults or rejected execution.

- StackGuard: inserts a random canary value between local buffers and saved return address. On function return, it checks canary integrity to detect overwrite and aborts before hijack. This depends on the attacker not knowing the canary; if the canary is leaked or predictable, an overwrite can be crafted to preserve the value and bypass protection.

- ProPolice: extends StackGuard with frame reordering and local variable placement so buffers are behind non-pointer locals, reducing the odds that overflow touches control data.

- StackShield: maintains a separate return-address table; writes to the stack copy are not trusted, restore on return, so corrupted return pointers aren’t followed.

- Return Address Defender: protects return addresses by encoding/xoring them with a secret or using a shadow stack, making direct return pointer overwrites safer by checking authenticity on return.

## How does program shepherding offer resilience against BOA

- Program shepherding validates control-flow transfers against a precomputed legal graph at runtime. It prevents control hijacking via overwritten returns or indirect calls, so BOA payloads can’t redirect execution to attacker-controlled code.

## What are the advantages of hardware-level modification to protect against BOA? Explain in the context of SmashGuard scheme

- Hardware-level protections provide strong enforcement with low overhead and make attacks harder to bypass in software. SmashGuard uses a protected shadow stack for return addresses and checks them on returns, so a buffer overflow cannot silently corrupt call/return state.
