package PacketShark;

import java.util.Arrays;

public class UdpPacket extends IPv4Packet{
    public int sourcePort;
    public int destinationPort;
    public int udpLength;
    public byte[] udpChecksum;

    public UdpPacket(LibpcapPacket packet) {
        super(packet);
        this.sourcePort = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 0, 2));
        this.destinationPort = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 2, 4));
        this.udpLength = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 4, 6));
        this.udpChecksum = Arrays.copyOfRange(this.data, 6, 8);
        this.data = Arrays.copyOfRange(this.data, 8, this.data.length);
    }

    @Override
    public String toString() {
        return String.format("%s\nUdp.SourcePort: %d Udp.DestinationPort: %d Udp.Length: %d",super.toString(), this.sourcePort, this.destinationPort, this.udpLength);
    }
}
