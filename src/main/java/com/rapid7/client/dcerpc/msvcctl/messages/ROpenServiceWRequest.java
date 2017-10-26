package com.rapid7.client.dcerpc.msvcctl.messages;

import com.rapid7.client.dcerpc.io.PacketOutput;
import com.rapid7.client.dcerpc.messages.RequestCall;
import com.rapid7.client.dcerpc.msrrp.messages.HandleResponse;
import com.rapid7.client.dcerpc.msrrp.objects.ContextHandle;
import java.io.IOException;

public class ROpenServiceWRequest extends RequestCall<HandleResponse>
{

    private final static short OP_NUM = 16;
    private final static int FULL_ACCESS = 0x000F003F;
    private final String serviceName;
    private final ContextHandle serviceManagerHandle;

    public ROpenServiceWRequest(ContextHandle serviceManagerHandle, String serviceName)
    {
        super(OP_NUM);
        this.serviceManagerHandle = serviceManagerHandle;
        this.serviceName = serviceName;
    }

    @Override public void marshal(PacketOutput packetOut)
        throws IOException
    {
        packetOut.write(serviceManagerHandle.getBytes());
        if (serviceName != null)packetOut.writeString(serviceName, true);
        else packetOut.writeNull();
        packetOut.writeInt(FULL_ACCESS);
    }

    @Override public HandleResponse getResponseObject()
    {
        return new HandleResponse();
    }
}
