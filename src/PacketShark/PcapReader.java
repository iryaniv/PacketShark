package PacketShark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PcapReader {
    final int TCPDUMP_MAGIC = 0xa1b2c3d4;
    final int TCPDUMP_MAGIC_NANO = 0xa1b23c4d;
    final int PMUDPCT_MAGIC = 0xd4c3b2a1;
    final int PMUDPCT_MAGIC_NANO = 0x4d3cb2a1;

    public PcapReader() {
    }

    public List<LibpcapPacket> read(String path) throws IOException {
        List<LibpcapPacket> basicPackets = new ArrayList<LibpcapPacket>();
        Path filePath = Paths.get(path);
        byte[] data = Files.readAllBytes(filePath);
        byte[] pcapHeader = Arrays.copyOfRange(data, 0, 24);
        byte[] packets = Arrays.copyOfRange(data, 24, data.length);
        int offset = 0;
        while (offset + 16 < packets.length) {
            byte[] timeStampSeconds = Arrays.copyOfRange(packets,offset, offset+4);
            byte[] timeStampMicroseconds = Arrays.copyOfRange(packets,offset+4,offset+8);
            byte[] frameLength = Arrays.copyOfRange(packets, offset+8, offset+12);
            byte[] frameActualLength = Arrays.copyOfRange(packets, offset+12, offset+16);
            int packetLength = BytesUtils.bytesToInt(frameActualLength);
            byte[] packetData = Arrays.copyOfRange(packets, offset+16, offset+16+packetLength);
            basicPackets.add(new LibpcapPacket(BytesUtils.bytesToInt(timeStampSeconds), BytesUtils.bytesToInt(timeStampMicroseconds), BytesUtils.bytesToInt(frameLength), packetLength, packetData));
            offset += 16 + packetLength;
        }
        return basicPackets;
    }

}
