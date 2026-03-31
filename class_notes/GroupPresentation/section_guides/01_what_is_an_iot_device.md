# Section 1: What Is an IoT Device?

## Overview

This section defines IoT devices for a reader who may not know what they are. It should cover: what IoT stands for, the sense-compute-communicate loop, concrete examples of devices, and a brief mention of the spectrum from tiny sensor motes to gateways. This is purely definitional — don't get into constraints or OS design yet.

**Recommended length: ~2-4 sentences**

Pure setup. The reader just needs to know what these devices are and that they exist at massive scale. Don't linger — get the definition down and move to why they're different.

---

## Information to Use

IoT stands for Internet of Things — physical devices embedded with sensors, software, and network connectivity that collect and exchange data automatically with little to no human interaction. The core loop that defines every IoT device is: sense something about the world, do some minimal computation on it, and communicate that data somewhere. The devices doing this are everywhere and number in the billions: soil sensors buried in agricultural fields, smart thermostats and door locks in homes, industrial valve controllers on factory floors, traffic monitors on highways, medical implants inside patients, and vehicle ECUs. These are not scaled-down laptops — they are a fundamentally different class of computing device. It is worth noting that IoT devices span a spectrum: tiny end-nodes like sensor motes and bare-metal microcontrollers sit at the most constrained end, while more capable edge devices and gateways occupy the upper tier. Each tier may use different classes of operating system, a distinction that matters for the rest of the report.

---

## References

### Baeldung. (n.d.). Operating systems for Internet of Things. *Baeldung on Computer Science*. https://www.baeldung.com/cs/os-internet-of-things
- Defines an IoT OS as a specialized software layer that abstracts hardware, schedules tasks, manages memory, and provides networking/device APIs for constrained devices
- Covers the role of an IoT OS in the stack between tiny hardware and cloud/backend

### GeeksforGeeks. (n.d.). IoT operating systems. *GeeksforGeeks*. https://www.geeksforgeeks.org/iot-operating-systems/
- General definition of IoT devices and their operating systems
- Covers the basic sense-compute-communicate model

### Devopedia. (n.d.). IoT operating systems. *Devopedia*. https://devopedia.org/iot-operating-systems
- Distinguishes between tiers of IoT devices (tiny end-nodes vs. more capable edge/gateway devices)
- Notes that each tier often uses different classes of OS

### Fiveable. (n.d.). Major IoT operating systems. *Fiveable*. https://fiveable.me/lists/major-iot-operating-systems
- Provides examples across the IoT device spectrum
- Covers the range from sensor motes to gateway-class devices
