package PacketShark;

import java.util.Arrays;

public class ArpPacket extends EthernetPacket {
    private static final int ARP_ETHER_TYPE = 2054;
    public int hardwareType;
    public int protocolType;
    public int hardwareSize;
    public int protocolSize;
    public int opcode;
    public String senderMac;
    public String senderIp;
    public String targetMac;
    public String targetIp;

    public ArpPacket(LibpcapPacket p) {
        super(p);
        this.hardwareType = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data,0,2));
        this.protocolType = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data,2,4));
        this.hardwareSize = this.data[4] & 0xff;
        this.protocolSize = this.data[5] & 0xff;
        this.opcode = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data,6,8));
        this.senderMac = BytesUtils.bytesToMacStr(this.data, 9);
        this.senderIp = BytesUtils.bytesToIpStr(this.data, 14);
        this.targetMac = BytesUtils.bytesToMacStr(this.data, 18);
        this.targetIp = BytesUtils.bytesToIpStr(this.data, 24);
    }

    @Override
    public String toString() {
        return String.format("%s\nHardwareType: %d ProtocolType: %d HardwareSize: %d ProtocolSize: %d " +
                "Opcode: %d SenderMac: %s SenderIp: %s TargetMac: %s TargetIp: %s",super.toString(), this.hardwareType,
                this.protocolType, this.hardwareSize, this.protocolSize, this.opcode, this.senderMac, this.senderIp,
                this.targetMac, this.targetIp);
    }
}
