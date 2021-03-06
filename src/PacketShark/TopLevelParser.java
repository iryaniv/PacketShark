package PacketShark;

public class TopLevelParser {
    public TopLevelParser() {

    }

    public LibpcapPacket parse(LibpcapPacket p) {
        EthernetPacket eth = new EthernetPacket(p);
        p.nextLevel = eth;
        p = p.nextLevel;
        if (eth.packetType == 2048) {
            IPv4Packet ip = new IPv4Packet(eth);
            p.nextLevel = ip;
            p = p.nextLevel;
            if (ip.protocol == 1) {
                IcmpPacket icmp = new IcmpPacket(ip);
                p.nextLevel = icmp;
                p = p.nextLevel;
            }
            else if(ip.protocol == 6){
                    TcpPacket tcp = new TcpPacket(ip);
                    p.nextLevel = tcp;
                    p = p.nextLevel;
                    if (tcp.destinationPort == 80 || tcp.sourcePort == 80){
                        try {
                            HttpPacket http = new HttpPacket(tcp);
                            p.nextLevel = http;
                            p = p.nextLevel;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                else if(ip.protocol == 17) {
                    UdpPacket udp = new UdpPacket(ip);
                    p.nextLevel = udp;
                    p = p.nextLevel;
                    if (udp.destinationPort == 53) {
                        try {
                            DnsRequestOverUdpPacket dns = new DnsRequestOverUdpPacket(udp);
                            p.nextLevel = dns;
                            p = p.nextLevel;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            else if (eth.packetType == 2054) {
                ArpPacket arp = new ArpPacket(p);
                p.nextLevel = arp;
                p = p.nextLevel;
            }
        return p;
        }
    }
