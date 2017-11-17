package PacketShark;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpPacket extends TcpPacket {
    public List<Map<String, String>> headers;
    public String textualData;
    public String textualHeader;

    public HttpPacket(LibpcapPacket packet) throws Exception {
        super(packet);
        if (this.data.length > 0) {
            headers = new ArrayList<>();
            this.textualData = new String(this.data, "Utf-8");
        }
        else
            throw new Exception();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
