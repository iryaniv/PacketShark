package PacketShark;

import java.util.Arrays;

public class IcmpPacket extends IPv4Packet {

    public int icmpType;
    public int icmpCode;
    public byte[] icmpChecksum;

    public IcmpPacket(LibpcapPacket packet) {
        super(packet);
        this.icmpType = (int)this.data[0] & 0xff;
        this.icmpCode = (int)this.data[1] & 0xff;
        this.icmpChecksum = Arrays.copyOfRange(this.data, 2,4);
        this.data = Arrays.copyOfRange(this.data, 4, this.data.length);
    }

    @Override
    public String toString() {
        return String.format("%s\nIcmpType: %d IcmpCode: %d",super.toString(), this.icmpType, this.icmpCode);
    }
}
