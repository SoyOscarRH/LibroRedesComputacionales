
/*======================================================
===============       IMPORTS         ==================
======================================================*/

import java.util.*;
import java.io.*;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;



/*======================================================
===============       CHECK SUM       ==================
======================================================*/
public class Checksumv2 {
    

    /*======================================================
    ============   CALCULATE CHECK SUM    ==================
    ======================================================*/
    public static long CalculateChecksum (byte[] Data) {
        
        int length = Data.length;
        int i = 0;

        long sum = 0;
        long data;

        while (length > 1) {
            
            data = (((Data[i] << 8) & 0xFF00) | ((Data[i + 1]) & 0xFF));
            sum += data;
            
            if ((sum & 0xFFFF0000) > 0) {
                sum = sum & 0xFFFF;
                sum += 1;
            }

            i += 2;
            length -= 2;
        }

        if (length > 0) {
            sum += (Data[i] << 8 & 0xFF00);
            
            if ((sum & 0xFFFF0000) > 0) {
                sum = sum & 0xFFFF;
                sum += 1;
            }
        }

        sum = ~sum;
        sum = sum & 0xFFFF;
        
        return sum;

    }


    /*======================================================
    =========    FROM BYTE[] => MAC STRING   ===============
    ======================================================*/
    private static String MACAsString (final byte[] MAcAddress) {
        
        final StringBuilder Data = new StringBuilder();
        
        for (byte Byte: MAcAddress) {
            
            if (Data.length() != 0) Data.append (':');

            if (Byte >= 0 && Byte < 16) Data.append ('0');

            Data.append (Integer.toHexString((Byte < 0) ? Byte + 256 : Byte).toUpperCase());
        }

        return Data.toString();
    
    }


    /*======================================================
    =========   SELECT DEVICE FROM CONSOLE   ===============
    ======================================================*/
    static PcapIf SelectDevicesByConsole() {

        int DEFAULT_DEVICE = 10;

        List <PcapIf> AllDevs = new ArrayList <PcapIf>(); 
        StringBuilder ErrorData = new StringBuilder(); 
        PcapIf SelectedDevice;
        
        int Result = Pcap.findAllDevs(AllDevs, ErrorData);

        if (Result == Pcap.NOT_OK || AllDevs.isEmpty()) {
            
            System.err.printf ("Can't read list of Devices, error is %s",
                ErrorData.toString());
            
            return null;
        }

        System.out.println("============================");
        System.out.println("==  Network Devices found ==");
        System.out.println("============================");

        try {
            
            int i = 0;
            for (PcapIf Device: AllDevs) {
                
                String Description = "No Description available";
                if (Device.getDescription() != null) 
                    Description = Device.getDescription();
                Description = "";

                final byte[] MAcAddress = Device.getHardwareAddress();

                String StrMAcAddress = (MAcAddress != null)? 
                    MACAsString(MAcAddress) : "No MAC Address";

                System.out.printf("  #%d: Name [%s] ", i, Device.getName());
                System.out.printf("MAC:[%s]\n", StrMAcAddress);

                i++;

            }

            SelectedDevice = AllDevs.get(DEFAULT_DEVICE);

            String InfoSelected = (SelectedDevice.getDescription() != null)? 
                SelectedDevice.getDescription() : SelectedDevice.getName();

            System.out.printf("\n\nChoosing '%s':\n\n\n", InfoSelected);

        }
        catch (IOException e) {
            e.printStackTrace ();
        }

        SelectedDevice = AllDevs.get(DEFAULT_DEVICE);


        return SelectedDevice;

    }




    /*================================================================
    =================             MAIN             ===================
    ================================================================*/
    public static void main (String[] Args) {
    
        StringBuilder ErrorData = new StringBuilder(); 
        PcapIf Device = SelectDevicesByConsole();

        if (Device == null) return; 

        /*=====================================
        =========    START THE PCAP   =========
        =======================================
        Remember:
            - SnapshotLength 
                Refers to the amount of actual data captured
                from each packet passing through the specified network interface.

                64*1024 = 65536 bytes
            */

        int SnapshotLength  = 64 * 1024;                // Capture all packets, no trucation
        int Flags           = Pcap.MODE_PROMISCUOUS;    // capture all packets
        int Timeout         = 10 * 1000;                // 10 seconds in millis
        
        Pcap PcapInstance = Pcap.openLive(
            Device.getName(),
            SnapshotLength,
            Flags,
            Timeout,
            ErrorData
        );

        if (PcapInstance == null) {
            System.err.printf("Error while opening device: " + ErrorData.toString());
            return;
        }


        /*=====================================
        =========       FILTER        =========
        =====================================*/
        PcapBpfProgram Filter = new PcapBpfProgram();

        String Expression = "";             // "port 80";
        int Optimize      = 0;              // 1 means true, 0 means false
        int Netmask       = 0;              // Netmask value

        int Result = PcapInstance.compile(
            Filter,
            Expression,
            Optimize,
            Netmask
        );
        
        if (Result != Pcap.OK) 
            System.out.println("Filter error: " + PcapInstance.getErr());

        PcapInstance.setFilter(Filter);



        /*=====================================
        =====    CREATE PACKET HANDLER    =====
        =====================================*/
        PcapPacketHandler<String> JPacketHandler = new PcapPacketHandler<String>() {

            public void nextPacket(PcapPacket Packet, String User) {
                String MACo = "";
                String MACd = "";
                String aux = "";
                int tipo = 1;

                System.out.println("=============================================");
                System.out.println("============    PACKET    ===================");
                System.out.println("=============================================\n\n");



                System.out.printf("\n\tReceived Packet at %s",new Date(Packet.getCaptureHeader().timestampInMillis()));
                System.out.printf("\n\tCapture Length = %-4d", Packet.getCaptureHeader().caplen());
                System.out.printf("\n\tOriginal Sizeh = %-4d", Packet.getCaptureHeader().wirelen());
                System.out.printf("\n\tUSer           = %s\n", User);

                /******Desencapsulado********/
                for (int i = 0; i < Packet.size (); i++)
                {
                    System.out.printf("%02X ", Packet.getUByte (i));
                    if (i % 16 == 15)
                        System.out.println("");
                    if (i < 6)
                    {
                        aux = String.format("%02X ", Packet.getUByte (i));
                        MACo += aux;
                    }
                    if (i >= 6 && i < 12)
                    {   
                        aux = String.format("%02X ", Packet.getUByte (i));
                        MACd += aux;
                    }
                }
                tipo = Packet.getUByte (12) * 256 + Packet.getUByte (13);
                System.out.println ("\n");
                System.out.printf("MACo = " + MACo + " MACd = " + MACd);
                System.out.printf(" Tipo = %04X\n",tipo);

                if((tipo & 0xFFFF) == 0x0800)
                {
                    int len = Packet.size();
                    int p_size = 0;
                    byte[] trama = Packet.getByteArray(0, len);
                    System.out.println("IPv4");
                    int ip_size = (trama[14] & 0x0F)*4; 
                    byte[] ip = new byte[ip_size];
                    System.arraycopy(trama,14,ip,0,ip_size);
                    //ip[10]= 0x00;
                    //ip[11]= 0x00;
                    p_size=(((ip[2] << 8) & 0xFF00) | ((ip[3]) & 0xFF));
                    long ip_checksum=CalculateChecksum(ip);
                    System.out.printf("El complemento a uno de la suma de IPv4: %04X\n", ip_checksum);
                    if(len>(13+ip_size))
                    {
                        if(ip[9]== 0x06)
                        {
                            System.out.println("TCP");
                            byte[] encabezado= new byte[12];
                            for (int i=0;i<8 ;i++ ) {
                                encabezado[i]=ip[ip_size-8+i];
                            }
                            int tcp_size= p_size-ip_size;
                            encabezado[8]=0x00;
                            encabezado[9]=0x06;
                            encabezado[10]=(byte)(tcp_size & 0x0000FF00);
                            encabezado[11]=(byte)(tcp_size & 0x000000FF);
                            byte[] tcp = new byte[tcp_size+12];
                            System.arraycopy(encabezado,0,tcp,0,12);
                            System.arraycopy(trama,14+ip_size ,tcp,12,tcp_size);
                            //tcp[28]=0x00;
                            //tcp[29]=0x00;
                            long tcp_checksum = CalculateChecksum(tcp);
                            System.out.printf("El complemento a uno de la suma de TCP: %04X\n", tcp_checksum);
                        }
                        if(ip[9]== 0x11)
                        {
                            System.out.println("UDP");
                            byte[] encabezado= new byte[12];
                            for (int i=0;i<8 ;i++ ) {
                                encabezado[i]=ip[ip_size-8+i];
                            }
                            int udp_size= p_size-ip_size;
                            encabezado[8]=0x00;
                            encabezado[9]=0x11;
                            encabezado[10]=(byte)(udp_size & 0x0000FF00);
                            encabezado[11]=(byte)(udp_size & 0x000000FF);
                            byte[] udp = new byte[udp_size+12];
                            System.arraycopy(encabezado,0,udp,0,12);
                            System.arraycopy(trama,14+ip_size ,udp,12,udp_size);
                            //udp[18]=0x00;
                            //udp[19]=0x00;
                            long udp_checksum = CalculateChecksum(udp);
                            System.out.printf("El complemento a uno de la suma de UDP: %04X\n", udp_checksum);
                        }
                    }

                }

                System.out.println ("\n\nEncabezado: " + Packet.toHexdump ());
            }

        };



        /*=====================================
        =====          DO A LOOP          =====
        =====================================
        
            Remember:
                Fourth we enter the loop and tell it to capture 10 packets. The loop
                method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
                is needed by JScanner. The scanner scans the packet Datafer and decodes
                the headers. The mapping is done automatically, although a variation on
                the loop method exists that allows the programmer to sepecify exactly
                which protocol ID to use as the data link type for this pcap interface.  
    
        */
        PcapInstance.loop(10, JPacketHandler, "jNetPcap rocks!");

        PcapInstance.close();
    }

}
