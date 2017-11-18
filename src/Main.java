import PacketShark.*;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PcapReader reader = new PcapReader();
        try {
            List<LibpcapPacket> packets = reader.read("C:\\Users\\Yaniv\\IdeaProjects\\PacketShark\\pcaps\\dns.pcap");
            TopParser parser = new TopParser();
            for (LibpcapPacket p : packets) {
                LibpcapPacket a = parser.parse(p);
                System.out.println(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
