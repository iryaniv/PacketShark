package PacketShark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class DnsRequestOverUdpPacket extends UdpPacket {

    public int transactionId;
    public List<String> flags; // TODO: parse the dns flags.
    public int QDCount;
    public int ANCount;
    public int NSCount;
    public int ARCount;



    public DnsRequestOverUdpPacket(LibpcapPacket packet) {
        super(packet);
        this.transactionId = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 0, 2));
//        getFlags();
        this.QDCount = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 4, 6));
        this.ANCount = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 6, 8));
        this.NSCount = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 8, 10));
        this.ARCount = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 10, 12));

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
        return String.format("%s\nDns.TransactionId: %d, Dns.Flags: %s Dns.QDCount: %d Dns.ANCount: %d Dns.NSCount: %d Dns.ARCount: %d" ,
                super.toString(), this.transactionId,
                this.flags, this.QDCount,
                this.ANCount, this.NSCount, this.ARCount);
    }
}
