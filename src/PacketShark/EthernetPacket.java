package PacketShark;

import java.util.Arrays;

public class EthernetPacket extends LibpcapPacket {
    public String srcMac;
    public String dstMac;
    public int packetType;
    protected byte[] data;


    public EthernetPacket(LibpcapPacket p) {
        super(p.timeStampSeconds, p.timeStampMicroSeconds, p.frameLength, p.frameActualLength, p.data);
        byte[] packetType = Arrays.copyOfRange(p.data, 12, 14);
        this.data = Arrays.copyOfRange(p.data, 14, p.data.length);
        this.dstMac = BytesUtils.bytesToMacStr(p.data, 0);
        this.srcMac = BytesUtils.bytesToMacStr(p.data, 6);
        this.packetType = BytesUtils.bytesToIntBig(packetType);
    }

    @Override
    public String toString() {
        return String.format("Ethernet.SourceMac: %s Ethernet.DestinationMac: %s Ethernet.PacketType: %d", this.srcMac, this.dstMac, this.packetType);
    }
}
