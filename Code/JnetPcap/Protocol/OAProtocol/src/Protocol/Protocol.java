/**
 * ===================================================================================
 * =====================          OA PROTOCOL       ==================================
 * ===================================================================================
 * 
 * @author Oscar RH, Alan Ontiveros, Arturo Rivas
 */

package Protocol;


public class Protocol {

    /*==================================================================
    ===============        HELPER FUNCTIONS      =======================
    ==================================================================*/
    
        static String MACToString(final byte[] MAcAddress) {
            
            final StringBuilder Data = new StringBuilder();

            for (byte Byte : MAcAddress) {
                
                if (Data.length() != 0) Data.append(':');

                if (Byte >= 0 && Byte < 16) Data.append('0');

                Data.append(Integer.toHexString((Byte < 0) ? Byte + 256 : Byte).toUpperCase());
            }
            return Data.toString();
        }


        static byte[] StringToMAC(String MAcAddressString) {
            
            MAcAddressString = MAcAddressString.replace(":", "");
            byte[] MAcAddress = new byte[MAcAddressString.length() / 2];

            for (int i = 0; i < MAcAddressString.length(); i += 2) {
                MAcAddress[i / 2] 
                    = (byte) (
                        (
                        Character.digit(MAcAddressString.charAt(i), 16) << 4) 
                        + 
                        Character.digit(MAcAddressString.charAt(i + 1), 16)
                    );
            }

            return MAcAddress;
        }
        

        static String IPToString(byte[] IPAddress) {
          	
            StringBuilder Data = new StringBuilder();

          	for(byte Byte : IPAddress) {
                if(Data.length() != 0)  Data.append(".");
                Data.append(Integer.toString((Byte < 0) ? Byte + 256 : Byte));
          	}

          	return Data.toString();
        }

        
        static void FillEthernetType(byte[] Frame) {
            Frame[12] = 0x16;
            Frame[13] = 0x01;
        }
        

        static void FillFilename(byte[] Frame, String FileName) {
            for(int i = 0; i < FileName.length(); i++) {
                Frame[15 + i] = (byte) FileName.charAt(i);
            }
        }
        

        static String GetFilename(byte[] Frame) {
            String FileName = "";
            
            for(int i = 15; i <= 270; i++) {
                if (Frame[i] == 0) break;
                FileName += (char)Frame[i];
            }

            return FileName;
        }
        

        static void FillFrameSize(byte[] Frame, int Size) {
            for(int i = 0; i < 4; i++) {
                Frame[274 - i] = (byte) ((Size >> (i * 8)) & 0xFF);
            }
        }


        static int GetFrameSize(byte[] Frame) {
            int Size = 0;
            
            for(int i = 0; i < 4; i++) 
                Size |= ((int)(Frame[271 + i] & 0xFF)) << (8 * (3 - i));

            return Size;
        }


        static void FillMACToFrame(byte[] Frame, byte[] SourceMAC, byte[] DestinationMAC) {
            System.arraycopy(DestinationMAC, 0, Frame, 0, 6);
            System.arraycopy(SourceMAC, 0, Frame, 6, 6);
        }

        
        static void GetMACToFrame(byte[] Frame, byte[] SourceMAC, byte[] DestinationMAC) {
            System.arraycopy(Frame, 6, SourceMAC, 0, 6);
            System.arraycopy(Frame, 0, DestinationMAC, 0, 6);
        }
        

        
    
    /*==================================================================
    =============      CONTROLLER FUNCTIONS      =======================
    ==================================================================*/





    
}
