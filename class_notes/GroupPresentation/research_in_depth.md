# Topic 1 — Foundations & Constraints of IoT Operating Systems
**Presentation Plan | 3–4 Minutes | ~4 Slides**

---

## Slide 1: What Is an IoT Device?

### Slide Content
**Title:** What Is an IoT Device?

**Definition block (top of slide):**
> **IoT — Internet of Things:** physical devices embedded with sensors, software, and network connectivity that collect and exchange data automatically, with little to no human interaction.

**What they do (three-part flow):**
`Sense → Compute → Communicate`

**Examples:**
- Soil sensors buried in fields
- Smart thermostats, door locks, appliances
- Industrial controllers on factory floors
- Traffic monitors, medical implants, vehicle ECUs

**Visual:**
A simple icon grid (3×2) showing 6 device types — each with a small icon and a one-word label:
`Sensor | Thermostat | Lock | Controller | Camera | Wearable`
Use a muted, dark background with small glowing icons to evoke an embedded/hardware feel.

### Script
> "Let's start with the basics — what even is an IoT device? IoT stands for Internet of Things. The idea is simple: take a physical object, embed it with sensors and software, connect it to a network, and let it collect and share data on its own — no human in the loop. Every IoT device does some version of three things: it senses something about the world, it does some minimal computation, and it communicates that data somewhere. That's the loop. And the 'things' doing this are everywhere — soil sensors buried in a field for two years, the thermostat on your wall, an industrial valve controller on a factory floor, a traffic monitor on a highway, a medical implant. These aren't laptops with a different case. They're a fundamentally different class of device — and there are *billions* of them deployed right now."


### ACTUAL SCRIPT
" Our presentation is on IoT devices. IoT stands for Internet of Things. The idea is taking a physical object, embedding it with sensors and software, connect it to a network, and allow it to collect and distribute data on its own, without the need for human input. In most cases, an IoT will do three things: sense information, computate on the data given, and send that data out. There are billions of IoT devices doing this exact thing: home thermostats, soil sensors buried in fields, medical implants, traffic monitors, the list goes on?
---

## Slide 2: Built to Run Alone

### Slide Content
**Title:** Built to Run Alone

**Top section — The autonomy reality:**
- Deployed once, unattended for months or years
- No user. No IT admin. No power outlet.
- Must self-sustain: sense, compute, communicate — indefinitely

**Bottom section — What that demands (comparison table):**

| Typical Laptop       | Typical IoT MCU        |
|----------------------|------------------------|
| 16 GB RAM            | 64–256 **KB** RAM      |
| 1 TB SSD             | 512 KB – 2 MB flash    |
| 3 GHz, 8 cores       | 16–80 MHz, 1 core      |
| Plugged into wall    | AA battery, 2+ years   |
| You're sitting there | No one is there        |

**Callout box:**
> "You *could* add more hardware — but more hardware means more power, more heat, more cost, and a device that can no longer run unattended for years. The constraints aren't a limitation. They're the design."

**Visual:**
Split layout — left side shows a remote field with a small sensor node on a pole, battery icon beneath it, no connectivity bars. Right side shows the comparison table. A bold arrow connects the deployment image to the table with the label: *"This environment defines these numbers."*

### Script
> "Now here's what makes IoT devices genuinely different — they're built to run completely on their own. A soil sensor gets buried in a field. A controller gets installed in a pipeline. A monitor gets bolted to a bridge. And then nobody touches them for years. No user logging in, no IT admin pushing updates, no one nearby to reboot it when something goes wrong. That autonomy is the whole point — and it drives every hardware decision. These devices can't be plugged into a wall, so they run on a small battery, sometimes for two or three years straight. They can't waste cycles on idle background services. A typical IoT microcontroller has 64 to 256 kilobytes of RAM — your laptop has 16 gigabytes. Now, you might ask: why not just put more hardware in and make it bigger? You could. But more hardware means more power draw, which means a bigger battery, which means more cost, more heat, and a device that no longer survives unattended in a field for two years. The constraints aren't accidental. They exist because the deployment conditions demand them."

### ACTUAL SCRIPT
" IoT devices are different from other computers because they are built to run on their own for a specific task. Take the soil sensor, you bury it in a field and leave it there for years, no one to push updates, no one nearby to reboot it. This is why their technical specs are so minimal. A typical IoT microcontroller has 64 to 256 kilobytes of RAM while a typical laptop might have 16 gigs. This all runs on a small battery expected to last years. If you shove more hardware or compute into it, it would either shorten its life span or require a bigger battery, no longer being the device that can last for years on its own"
---

## Slide 3: The Right Tool for the Job

### Slide Content
**Title:** The Right Tool for the Job

**Left column — "What a soil sensor needs to do:"**
- Wake up every 30 minutes
- Read one value from one sensor
- Transmit it over a low-power radio
- Go back to sleep

**Right column — "What Linux brings:"**
- Full POSIX filesystem & multi-user process management
- Dynamic libraries and package manager
- Network stack for every protocol
- Dozens of background services running at boot
- ~512 MB RAM minimum

**Callout box:**
> "IoT devices don't need general-purpose. They need specific-purpose — an OS built around exactly what the device actually does, and nothing else."

**Visual:**
A two-column layout with a bold dividing line. Left column has a clean icon list (clock, sensor, radio wave, moon/sleep). Right column has the Linux feature list with most items faded out or greyed — visually suggesting that the device never uses them. A small label beneath: *"Most of this goes unused."*

### Script
> "So why not just run Linux? Here's the real issue — it's not only that Linux is too large. It's that most of what Linux does, an IoT device will never use. Think about what a soil sensor actually needs: wake up every thirty minutes, read one value from one sensor, send it over a low-power radio, go back to sleep. That's the entire job. Linux brings a full filesystem, multi-user process management, dynamic library loading, a network stack that supports every protocol imaginable, and dozens of background services — none of which that sensor will ever touch. Linux is a general-purpose OS. It's designed to run anything. But IoT devices don't need to run anything — they need to run one thing, reliably, for years, on a battery. A purpose-built IoT OS includes exactly the capabilities the device needs: a minimal scheduler, the right sensor drivers, one lightweight network protocol, and aggressive power management. Nothing more. That specificity is the point."

---

## Slide 4: The Six Constraints That Define IoT OS Design

### Slide Content
**Title:** Six Constraints, One Design Philosophy

**Six tiles in a 2×3 grid, each with an icon and short label:**

| 🔋 Power | 🧠 Memory |
|----------|-----------|
| 🌐 Connectivity | ⏱ Real-Time |
| 🔩 Heterogeneity | 🛠 Longevity |

**Caption beneath grid:**
> *"Every design choice in an IoT OS traces back to one of these."*

**Visual:**
The 6 tiles styled as circuit board "nodes," connected by thin lines (like a PCB trace pattern) running between them — suggesting they're interdependent constraints, not isolated issues.

### Script
> "Everything about IoT OS design flows from six core constraints. Power — devices are battery-operated, so the OS must aggressively sleep the radio and CPU. Memory — kilobytes, not gigabytes, so kernels must be tiny and modular. Connectivity — low-bandwidth wireless with packet loss, so the networking stack must be purpose-built. Real-time — industrial valves and sensors need microsecond-level timing guarantees. Heterogeneity — thousands of different chip families, so portability and hardware abstraction are critical. And Longevity — these devices sit in a field for five years with no maintenance, so reliability and over-the-air updates aren't optional luxuries. Together, these constraints explain *why* IoT operating systems look nothing like the OS running on your laptop. That's what the rest of this topic unpacks."

---

## Timing Guide

| Slide | Content | Target Time |
|-------|---------|-------------|
| 1 | What Is an IoT Device? | ~40 sec |
| 2 | The Hardware Reality | ~50 sec |
| 3 | Why Not Just Use Linux? | ~55 sec |
| 4 | The Six Constraints | ~55 sec |
| **Total** | | **~3:20** |

*Buffer remaining (~40 sec) can be used for a brief intro sentence before Slide 1 or a one-line closer after Slide 4.*

---

## Presenter Notes (General)

- **Don't read the slides.** The bullets are anchors — your script is the talk.
- **Slide 2 is your strongest visual moment** — let the size comparison land before you speak over it.
- **Slide 4 is your handoff** — end on "...which is what the rest of this topic unpacks" to smoothly transition into deeper material.
- Keep a clicker, don't linger on any slide more than 60 seconds.m