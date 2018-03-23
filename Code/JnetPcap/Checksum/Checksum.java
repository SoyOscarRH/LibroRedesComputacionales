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


public class Checksum
{
	public static long calculateChecksum(byte[] buf) {
		int length = buf.length;
		int i = 0;

		long sum = 0;
		long data;

    	// Handle all pairs
		while (length > 1) {
      		// Corrected to include @Andy's edits and various comments on Stack Overflow
			data = (((buf[i] << 8) & 0xFF00) | ((buf[i + 1]) & 0xFF));
			sum += data;
      		// 1's complement carry bit correction in 16-bits (detecting sign extension)
			if ((sum & 0xFFFF0000) > 0) {
				sum = sum & 0xFFFF;
				sum += 1;
			}

			i += 2;
			length -= 2;
		}

    	// Handle remaining byte in odd length buffers
		if (length > 0) {
      		// Corrected to include @Andy's edits and various comments on Stack Overflow
			sum += (buf[i] << 8 & 0xFF00);
      		// 1's complement carry bit correction in 16-bits (detecting sign extension)
			if ((sum & 0xFFFF0000) > 0) {
				sum = sum & 0xFFFF;
				sum += 1;
			}
		}

    	// Final 1's complement value correction to 16-bits
		sum = ~sum;
		sum = sum & 0xFFFF;
		return sum;

	}

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
	private static String asString (final byte[]mac)
	{
		final StringBuilder buf = new StringBuilder ();
		for (byte b:mac)
		{
			if (buf.length () != 0)
			{
				buf.append (':');
			}
			if (b >= 0 && b < 16)
			{
				buf.append ('0');
			}
			buf.append (Integer.toHexString ((b < 0) ? b + 256 : b).
				toUpperCase ());
		}

		return buf.toString ();
	}

	public static void main (String[]args)
	{
    List < PcapIf > alldevs = new ArrayList < PcapIf > ();	// Will be filled with NICs
    StringBuilder errbuf = new StringBuilder ();	// For any error msgs

		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs (alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty ())
		{
			System.err.printf ("Can't read list of devices, error is %s",
				errbuf.toString ());
			return;
		}

		System.out.println ("Network devices found:");

		int i = 0;
		try
		{
			for (PcapIf device:alldevs)
			{
				String description =
				(device.getDescription () != null) ? device.getDescription ()
				: "No description available";
				final byte[] mac = device.getHardwareAddress ();
				String dir_mac =
				(mac == null) ? "No tiene direccion MAC" : asString (mac);
				System.out.printf ("#%d: %s [%s] MAC:[%s]\n", i++,
					device.getName (), description, dir_mac);

	}			//for

      PcapIf device = alldevs.get (9);	// We know we have atleast 1 device
      System.out.printf ("\nChoosing '%s' on your behalf:\n",
      	(device.getDescription () !=
      		null) ? device.getDescription () : device.
      	getName ());

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
      /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
      64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

      int snaplen = 64 * 1024;	// Capture all packets, no trucation
      int flags = Pcap.MODE_PROMISCUOUS;	// capture all packets
      int timeout = 10 * 1000;	// 10 seconds in millis
      Pcap pcap =
      Pcap.openLive(device.getName (), snaplen, flags, timeout, errbuf);

      if (pcap == null)
      {
      	System.err.printf("Error while opening device for capture: "
      		+ errbuf.toString());
      	return;
	  }			//if

	  /********F I L T R O********/
	  PcapBpfProgram filter = new PcapBpfProgram();
      String expression = "";	// "port 80";
      int optimize = 0;		// 1 means true, 0 means false
      int netmask = 0;
      int r2 = pcap.compile(filter, expression, optimize, netmask);
      if (r2 != Pcap.OK)
      {
      	System.out.println("Filter error: " + pcap.getErr ());
	  }			//if
	  pcap.setFilter(filter);
	  /****************/


		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler < String > jpacketHandler =
		new PcapPacketHandler < String > ()
		{

			public void nextPacket (PcapPacket packet, String user)
			{
				String MACo = "";
				String MACd = "";
				String aux = "";
				int tipo = 1;
	  			System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n", new Date (packet.getCaptureHeader ().timestampInMillis ()), packet.getCaptureHeader ().caplen (),	// Length actually captured
			     packet.getCaptureHeader().wirelen(),	// Original length
			     user	// User supplied object
			     );
	  			/******Desencapsulado********/
	  			for (int i = 0; i < packet.size (); i++)
	  			{
	  				System.out.printf("%02X ", packet.getUByte (i));
	  				if (i % 16 == 15)
	  					System.out.println("");
	  				if (i < 6)
	  				{
	  					aux = String.format("%02X ", packet.getUByte (i));
	  					MACo += aux;
	  				}
	  				if (i >= 6 && i < 12)
	  				{	
	  					aux = String.format("%02X ", packet.getUByte (i));
	  					MACd += aux;
	  				}
	  			}
	  			tipo = packet.getUByte (12) * 256 + packet.getUByte (13);
	  			System.out.println ("\n");
	  			System.out.printf("MACo = " + MACo + " MACd = " + MACd);
	  			System.out.printf(" Tipo = %04X\n",tipo);

	  			if((tipo & 0xFFFF) == 0x0800)
	  			{
	  				int len = packet.size();
	  				int p_size = 0;
	  				byte[] trama = packet.getByteArray(0, len);
	  				System.out.println("IPv4");
	  				int ip_size = (trama[14] & 0x0F)*4; 
	  				byte[] ip = new byte[ip_size];
	  				System.arraycopy(trama,14,ip,0,ip_size);
	  				//ip[10]= 0x00;
	  				//ip[11]= 0x00;
	  				p_size=(((ip[2] << 8) & 0xFF00) | ((ip[3]) & 0xFF));
	  				long ip_checksum=calculateChecksum(ip);
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
	  						long tcp_checksum = calculateChecksum(tcp);
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
	  						long udp_checksum = calculateChecksum(udp);
	  						System.out.printf("El complemento a uno de la suma de UDP: %04X\n", udp_checksum);
	  					}
	  				}

	  			}

	  			System.out.println ("\n\nEncabezado: " + packet.toHexdump ());
	  		}
	  	};


		/***************************************************************************
		 * Fourth we enter the loop and tell it to capture 10 packets. The loop
		 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
		 * is needed by JScanner. The scanner scans the packet buffer and decodes
		 * the headers. The mapping is done automatically, although a variation on
		 * the loop method exists that allows the programmer to sepecify exactly
		 * which protocol ID to use as the data link type for this pcap interface.
		 **************************************************************************/
		pcap.loop (10, jpacketHandler, "jNetPcap rocks!");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close ();
	}
	catch (IOException e)
	{
		e.printStackTrace ();
	}
}
}
