# Topic 1 — Foundations & Constraints of IoT Operating Systems
**Presentation Plan | 3–4 Minutes | ~4 Slides**

---

## Slide 1: What Even *Is* an IoT Device?

### Slide Content
**Title:** The "Things" in IoT

**Bullet Points:**
- Soil sensors buried in fields
- Smart thermostats, door locks, appliances
- Industrial controllers on factory floors
- Traffic monitors, medical implants, vehicle ECUs

**Visual:**
A simple icon grid (3×2) showing 6 device types — each with a small icon and a one-word label:
`Sensor | Thermostat | Lock | Controller | Camera | Wearable`
Use a muted, dark background with small glowing icons to evoke an embedded/hardware feel.

### Script
> "Before we talk about operating systems, let's agree on what we mean by IoT devices. We're not talking about laptops or phones. We're talking about soil sensors buried in a field for two years, a smart thermostat on your wall, an industrial valve controller in a factory, or a traffic monitor on a highway. These are tiny, often unattended, often battery-powered computers — and there are *billions* of them."

---

## Slide 2: The Hardware Reality

### Slide Content
**Title:** Kilobytes, Not Gigabytes

**Two-column comparison table:**

| Typical Laptop     | Typical IoT MCU       |
|--------------------|-----------------------|
| 16 GB RAM          | 64–256 **KB** RAM     |
| 1 TB SSD           | 512 KB – 2 MB flash   |
| 3 GHz, 8 cores     | 16–80 MHz, 1 core     |
| Plugged into wall  | AA battery, 2+ years  |
| You're sitting there | No one is there     |

**Visual:**
A side-by-side scale diagram — on the left, a laptop chip icon labeled "Your laptop." On the right, a tiny MCU die icon labeled "IoT node." Use a dramatic size difference. Beneath the MCU, a small battery icon. Bold callout: **"1,000× less memory."**

### Script
> "Here's the hardware reality. A typical IoT microcontroller has maybe 64 to 256 *kilobytes* of RAM. Your laptop has sixteen *gigabytes* — that's a difference of roughly a hundred thousand times. Storage is similarly tiny. And critically — these devices run on a single AA battery, sometimes for years. There's no power outlet. There's no keyboard. There's no user."

---

## Slide 3: Why Not Just Use Linux?

### Slide Content
**Title:** Why Linux Doesn't Fit

v

**Right side — "IoT device has:"**
- 64–256 KB RAM
- Low-power wireless, intermittent connections
- No display, no user, no IT department
- Must run for *years* unattended

**Visual:**
A simple "square peg, round hole" diagram — a box labeled `Linux (~512 MB min)` trying to fit into a slot labeled `MCU (256 KB RAM)`. Use a red ✗ icon. Below it, a green ✓ icon next to a small box labeled `FreeRTOS / RIOT / Zephyr`.

### Script
> "So why not just run Linux? Linux is great — but it's built for machines with megabytes of RAM at minimum, a rich filesystem, dynamic libraries, and a user who can intervene when things go wrong. An IoT node has *none* of that. Running Linux on a 64 KB MCU is like trying to run Photoshop on a digital wristwatch. The hardware assumptions are completely different. This is why a whole family of purpose-built IoT operating systems exists."

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