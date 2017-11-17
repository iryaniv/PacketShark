package PacketShark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class TcpPacket extends IPv4Packet{
    // TODO: Implement Tcp Options when dataoffset > 5
    public int sourcePort;
    public int destinationPort;
    public Long sequenceNumber;
    public Long acknowledgeNumber;
    public int dataOffset;
    public List<String> flags;
    public int windowSize;
    public byte[] tcpChecksum;
    public int urgentPointer;

    public TcpPacket(LibpcapPacket packet) {
        super(packet);
        BitSet dataOffsetByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 12,13));
        BitSet flagsByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 13,14));
        this.sourcePort = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 0, 2));
        this.destinationPort = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 2, 4));
        this.sequenceNumber = BytesUtils.bytesToLongBig(Arrays.copyOfRange(this.data, 4, 8));
        this.acknowledgeNumber = BytesUtils.bytesToLongBig(Arrays.copyOfRange(this.data, 8, 12));
        this.dataOffset = BytesUtils.bitsToInt(dataOffsetByteBitSet.get(4,8));
        this.windowSize = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 14,16));
        getFlags(dataOffsetByteBitSet, flagsByteBitSet);
        this.tcpChecksum = Arrays.copyOfRange(this.data, 16, 18);
        this.urgentPointer = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 18,20));
        if (this.dataOffset > 5)
            this.data = Arrays.copyOfRange(this.data, this.dataOffset * 4, this.data.length);
        else
            this.data = Arrays.copyOfRange(this.data, 20, this.data.length);
    }

    private void getFlags(BitSet dataOffsetByteBitSet, BitSet flagsByteBitSet) {
        flags = new ArrayList<>();
        if (dataOffsetByteBitSet.get(0))
            flags.add("SN");
        if (flagsByteBitSet.get(0))
            flags.add("FIN");
        if (flagsByteBitSet.get(1))
            flags.add("SYN");
        if (flagsByteBitSet.get(2))
            flags.add("RST");
        if (flagsByteBitSet.get(3))
            flags.add("PSH");
        if (flagsByteBitSet.get(4))
            flags.add("ACK");
        if (flagsByteBitSet.get(5))
            flags.add("URG");
        if (flagsByteBitSet.get(6))
            flags.add("UCE");
        if (flagsByteBitSet.get(7))
            flags.add("CWR");

    }
    @Override
    public String toString() {
        return String.format("%s\nTcp.SourcePort: %d Tcp.DestinationPort: %d Tcp.SeqNum: %d Tcp.AckNum: %d Tcp.DataOffset: %d Tcp.WindowSize: %d Tcp.Flags: %s Tcp.UrgPointer: %d"
                ,super.toString(), this.sourcePort, this.destinationPort, this.sequenceNumber, this.acknowledgeNumber, this.dataOffset, this.windowSize, this.flags.toString(), this.urgentPointer);
    }
}
