# PacketShark
Java libpcap packet parsing library.
This lib was created to parse pcaps easily and doing stuff with the packets themself.
In the following  example i just print the packets to the stdout.

Simple Usage:

```java
import PacketShark.*;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PcapReader reader = new PcapReader();
        try {
            List<LibpcapPacket> packets = reader.read("..\\pcaps\\dns.pcap");
            TopLevelParser parser = new TopLevelParser();
            for (LibpcapPacket p : packets) {
                LibpcapPacket a = parser.parse(p);
                System.out.println(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
Output example:
```
Ethernet.SourceMac: 54:75:D0:BA:52:2A Ethernet.DestinationMac: 00:26:22:CB:10:17 Ethernet.PacketType: 2048
IP.Version: 4 IP.IHL: 5 IP.DSCP: 0 IP.ECN: 0 IP.Length: 1420  IP.ID: 59565 IP.Flags: [] IP.FragmentOffset: 0 IP.TTL: 54 IP.Protcol: 6 IP.Source: 74.125.127.101 IP.Destination: 172.30.1.125
Tcp.SourcePort: 80 Tcp.DestinationPort: 35863 Tcp.SeqNum: 1027058232 Tcp.AckNum: 3049148734 Tcp.DataOffset: 8 Tcp.WindowSize: 205 Tcp.Flags: [ACK] Tcp.UrgPointer: 0

Ethernet.SourceMac: 54:75:D0:BA:52:2A Ethernet.DestinationMac: 00:26:22:CB:10:17 Ethernet.PacketType: 2048
IP.Version: 4 IP.IHL: 5 IP.DSCP: 0 IP.ECN: 0 IP.Length: 1420  IP.ID: 59566 IP.Flags: [] IP.FragmentOffset: 0 IP.TTL: 54 IP.Protcol: 6 IP.Source: 74.125.127.101 IP.Destination: 172.30.1.125
Tcp.SourcePort: 80 Tcp.DestinationPort: 35863 Tcp.SeqNum: 1027059600 Tcp.AckNum: 3049148734 Tcp.DataOffset: 8 Tcp.WindowSize: 205 Tcp.Flags: [ACK] Tcp.UrgPointer: 0

```
