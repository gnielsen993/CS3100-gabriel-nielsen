# Section 2: Built to Run Alone

## Overview

This section explains what makes IoT devices fundamentally different from traditional computers: their autonomy and the hardware reality that comes with it. Cover the deployment conditions (unattended, remote, no maintenance), the concrete hardware comparison (laptop vs. MCU specs), and why adding more hardware isn't the answer. This is where the reader should feel the gap between what they know (laptops) and what IoT actually looks like.

**Recommended length: ~1 short paragraph (4-6 sentences)**

This is buildup, not the main event. The laptop-vs-MCU comparison is your strongest concrete moment — use it to make the reader *feel* the gap, then move on. This sets up the "why" that the constraints section delivers.

---

## Information to Use

IoT devices are built to operate completely on their own. A soil sensor gets buried in a field, a controller gets installed in a pipeline, a monitor gets bolted to a bridge — and then nobody touches them for months or years. There is no user logging in, no IT admin pushing updates, no one nearby to reboot it. This autonomy is the defining characteristic and it drives every hardware decision. A typical IoT microcontroller has 64 to 256 kilobytes of RAM, 512 KB to 2 MB of flash storage, a single core running at 16 to 80 MHz, and runs on a pair of AA batteries expected to last two or more years. Compare that to a typical laptop with 16 GB of RAM, a terabyte of SSD storage, a multi-gigahertz multi-core processor, and a constant wall power connection. The natural question is why not add more hardware — but more hardware means more power draw, more heat, more cost per unit, and a device that can no longer survive unattended for years. The constraints are not accidental limitations; they exist because the deployment conditions demand them.

---

## References

### Musaddiq, A., Zikria, Y. B., Hahm, O., Yu, H., Bashir, A. K., & Kim, S. W. (2018). A survey on resource management in IoT operating systems. *IEEE Access*, 6, 8459-8482. https://www.diva-portal.org/smash/get/diva2:1480277/FULLTEXT01.pdf
- Specific hardware specs: RAM ranges (64-256 KB), flash (512 KB-2 MB), clock speeds (16-80 MHz)
- Radio communication and active CPU time are the two largest power consumers
- Battery-powered systems expected to run months or years without replacement

### GeeksforGeeks. (n.d.). IoT operating systems. *GeeksforGeeks*. https://www.geeksforgeeks.org/iot-operating-systems/
- General contrast between IoT device specs and traditional computing devices
- Energy constraints of battery-powered deployments

### Devopedia. (n.d.). IoT operating systems. *Devopedia*. https://devopedia.org/iot-operating-systems
- Deployment conditions: remote, inaccessible, unattended for long periods
- Physical access for maintenance is difficult or impossible
