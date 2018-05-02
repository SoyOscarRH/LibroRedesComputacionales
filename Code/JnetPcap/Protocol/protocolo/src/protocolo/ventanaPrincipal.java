/*======================================================
===============       IMPORTS         ==================
======================================================*/

    package protocolo;

    import java.io.*;
    import java.net.InetAddress;
    import java.net.NetworkInterface;
    import java.nio.ByteBuffer;  
    import java.util.ArrayList;  
    import java.util.Arrays;  
    import java.util.Collections;
    import java.util.Date;
    import java.util.Enumeration;
    import java.util.Iterator;
    import java.util.List;  
    import javax.swing.JFileChooser;
    import javax.swing.JOptionPane;
    import org.jnetpcap.Pcap;  
    import org.jnetpcap.PcapAddr;
    import org.jnetpcap.PcapBpfProgram;
    import org.jnetpcap.PcapIf;  
    import org.jnetpcap.PcapSockAddr;
    import org.jnetpcap.packet.PcapPacket;
    import org.jnetpcap.packet.PcapPacketHandler;


/*======================================================
==========      PRINCIPAL WINDOW      ==================
======================================================*/
public class ventanaPrincipal extends javax.swing.JFrame {


    /*======================================================
    =======       CODE FROM JAVA SWING     =================
    ======================================================*/

    public ventanaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        InterfacesLista = new javax.swing.JComboBox<>();
        seleccionarBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        SourceMACTxt = new javax.swing.JTextField();
        DestinationMACTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        archivoTxt = new javax.swing.JTextField();
        seleccionarArchivo = new javax.swing.JButton();
        enviarBtn = new javax.swing.JButton();
        enviadosTxt = new javax.swing.JLabel();
        recibidosTxt = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        carpetaDestinoTxt = new javax.swing.JTextField();
        seleccionarCarpeta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica 3: Envío de archivos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Interfaces de red:");

        seleccionarBtn.setText("Comenzar a escuchar en la CurrentInterface seleccionada");
        seleccionarBtn.setName(""); // NOI18N
        seleccionarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarBtnMouseClicked(evt);
            }
        });

        jLabel2.setText("MAC origen:");

        SourceMACTxt.setEditable(false);
        SourceMACTxt.setEnabled(false);

        DestinationMACTxt.setEnabled(false);

        jLabel3.setText("MAC destino:");

        jLabel4.setText("Archivo a enviar:");

        archivoTxt.setEditable(false);
        archivoTxt.setEnabled(false);

        seleccionarArchivo.setText("..");
        seleccionarArchivo.setEnabled(false);
        seleccionarArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarArchivoMouseClicked(evt);
            }
        });

        enviarBtn.setText("Enviar");
        enviarBtn.setEnabled(false);
        enviarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviarBtnMouseClicked(evt);
            }
        });

        enviadosTxt.setText("Bytes enviados:");
        enviadosTxt.setEnabled(false);

        recibidosTxt.setText("Bytes recibidos:");
        recibidosTxt.setEnabled(false);

        jLabel5.setText("Recibir archivos en:");

        carpetaDestinoTxt.setEditable(false);

        seleccionarCarpeta.setText("..");
        seleccionarCarpeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarCarpetaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(seleccionarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(enviarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(carpetaDestinoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarCarpeta))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DestinationMACTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InterfacesLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SourceMACTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(archivoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(seleccionarArchivo))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enviadosTxt)
                            .addComponent(recibidosTxt))))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(InterfacesLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SourceMACTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(DestinationMACTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(archivoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionarArchivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(carpetaDestinoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionarCarpeta))
                .addGap(11, 11, 11)
                .addComponent(enviarBtn)
                .addGap(10, 10, 10)
                .addComponent(enviadosTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recibidosTxt)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }



    /*======================================================
    ===============     PROPERTIES        ==================
    ======================================================*/
    
    List<PcapIf> Interfaces = new ArrayList<>();
    PcapIf CurrentInterface;
    Pcap CurrentPcap;

    byte[] HostMAC = new byte[6];
    byte[] SourceMAC = new byte[6];
    byte[] DestinationMAC = new byte[6];

    StringBuilder ErrorData = new StringBuilder();

    Thread hiloPrincipal;


    // === VARIABLES FOR THE FILE ======
    RandomAccessFile BufferFileSource;
    RandomAccessFile BufferFileDestination;

    String SourceFileName = "";
    String DestinationFileName = "";

    int FileSourceSize = 0;
    int FileDestinationSize = 0;

    int NumberOfDivisionOfSourceFile = 0;
    int NumberOfDivisionOfDestinationFile = 0;

    int NumberOfSendParts = 0;
    int NumberOfRecivedParts = 0;

    int SizeOfDivisionInBytes = 700;

    boolean WaitingForAcceptTransfer = false;
    boolean SendingAFile = false;
    boolean ReceivingAFile = false;

    int NumberOfConcurrentFrames = 100;
    volatile int NumberOfSendFrames = 0;





    
    private static String macToString(final byte[] mac) {
        final StringBuilder buf = new StringBuilder();
        for (byte b : mac) {
          if (buf.length() != 0) {
            buf.append(':');
          }
          if (b >= 0 && b < 16) {
            buf.append('0');
          }
          buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
        }
        return buf.toString();
    }
    
    public static byte[] stringToMac(String s) {
        s = s.replace(":", "");
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    private static String ipToString(byte[] ip){
    StringBuilder buf = new StringBuilder();
    for(byte b : ip){
            if(buf.length() != 0){
                buf.append(".");
            }
            buf.append(Integer.toString((b < 0) ? b + 256 : b));
    }
    return buf.toString();
    }
    
    private static void fillEthType(byte[] trama){
        trama[12] = 0x16;
        trama[13] = 0x01;
    }
    
    private static void fillMacs(byte[] trama, byte[] macO, byte[] macD){
        System.arraycopy(macD, 0, trama, 0, 6);
        System.arraycopy(macO, 0, trama, 6, 6);
    }
    
    private static void getMacs(byte[] trama, byte[] macO, byte[] macD){
        System.arraycopy(trama, 6, macO, 0, 6);
        System.arraycopy(trama, 0, macD, 0, 6);
    }
    
    private static void fillFilename(byte[] trama, String nombre){
        for(int i = 0; i < nombre.length(); i++){
            trama[15 + i] = (byte)nombre.charAt(i);
        }
    }
    
    private static String getFilename(byte[] trama){
        String nombre = "";
        for(int i = 15; i <= 270; i++){
            if(trama[i] == 0) break;
            nombre += (char)trama[i];
        }
        return nombre;
    }
    
    private static void fillSize(byte[] trama, int size){
        for(int i = 0; i < 4; i++){
            trama[274 - i] = (byte)((size >> (i * 8)) & 0xFF);
        }
    }
    private static int getSize(byte[] trama){
        int size = 0;
        for(int i = 0; i < 4; i++){
            size |= ((int)(trama[271 + i] & 0xFF)) << (8 * (3 - i));
        }
        return size;
    }
    




    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setLocationRelativeTo(null);
        

        
        //Obtener las Interfaces disponibles
        int Result = Pcap.findAllDevs(Interfaces, ErrorData);
        try{
            
            if (Result == Pcap.NOT_OK || Interfaces.isEmpty()) {

                System.out.printf ("Can't read list of Devices, error is %s",
                    ErrorData.toString());
            }
            else {
                
                for(PcapIf inter : Interfaces){
                    String descripcion = inter.getDescription();
                    if(descripcion == null) descripcion = "";
                    
                    final byte[] MAcAddress = inter.getHardwareAddress();

                    String mac = (MAcAddress != null)? 
                    macToString(MAcAddress) : "No MAC Address";
                    String ip = "";
                    Iterator<PcapAddr> it = inter.getAddresses().iterator();
                    if(it.hasNext()){
                        ip = ipToString(it.next().getAddr().getData());
                    }
                    InterfacesLista.addItem(inter.getName() + " [" + descripcion + "] [" + mac + "] [" + ip + "]");
                }
                
            }
                
        }catch(IOException io){
            //
        }
    }//GEN-LAST:event_formWindowOpened
    
    private void analizarTrama(PcapPacket packet){
        int len = packet.size();
        byte[] trama = packet.getByteArray(0, len);
        if(packet.getUByte(12) == 22 && packet.getUByte(13) == 1){ //0x1601
                    /*System.out.printf("Paquete recibido el %s bytes capturados=%-4d tam original=%-4d\n",
                    new Date(packet.getCaptureHeader().timestampInMillis()),
                    packet.getCaptureHeader().caplen(),  // Length actually captured
                    packet.getCaptureHeader().wirelen()
                    );*/
            byte[] macO = new byte[6];
            byte[] macD = new byte[6];
            getMacs(trama, macO, macD);
            byte accion = trama[14];
            if(!Arrays.equals(macD, HostMAC)) return;
            switch(accion){
                case 0x00:{ //ask
                    System.arraycopy(macO, 0, SourceMAC, 0, 6);
                    DestinationFileName = getFilename(trama);
                    FileDestinationSize = getSize(trama);
                    int opcion = JOptionPane.showConfirmDialog(null, "El usuario con MAC " + macToString(macO) + " te quiere enviar el archivo:\n" + 
                            DestinationFileName + "\nLongitud: " + FileDestinationSize + " bytes\n¿Recibir?", "Transferencia entrante", JOptionPane.YES_NO_OPTION);
                    byte[] nuevaTrama = new byte[1024];
                    fillMacs(nuevaTrama, macD, macO);
                    fillEthType(nuevaTrama);
                    fillFilename(nuevaTrama, DestinationFileName);
                    if(opcion == JOptionPane.YES_OPTION){
                        nuevaTrama[14] = 0x01;
                        ReceivingAFile = true;
                    }else{
                        nuevaTrama[14] = 0x02;
                        DestinationFileName = "";
                        FileDestinationSize = 0;
                        SourceMAC = new byte[6];
                    }
                    enviarTrama(nuevaTrama);
                    break;
                }
                case 0x01:{ //yes
                    if(WaitingForAcceptTransfer && SourceFileName.equals(getFilename(trama))){
                        WaitingForAcceptTransfer = false;
                        SendingAFile = true;
                        enviarBtn.setText("Cancelar");
                        enviadosTxt.setEnabled(true);
                        NumberOfDivisionOfSourceFile = (FileSourceSize % SizeOfDivisionInBytes == 0 ? FileSourceSize / SizeOfDivisionInBytes : FileSourceSize / SizeOfDivisionInBytes + 1);
                        byte [] nuevaTrama = new byte[1024];
                        fillMacs(nuevaTrama, macD, macO);
                        fillFilename(nuevaTrama, SourceFileName);
                        fillEthType(nuevaTrama);
                        nuevaTrama[14] = 0x03;
                        fillSize(nuevaTrama, NumberOfDivisionOfSourceFile);
                        enviarTrama(nuevaTrama);
                    }
                    break;
                }
                case 0x02:{ //no
                    if(WaitingForAcceptTransfer && SourceFileName.equals(getFilename(trama))){
                        WaitingForAcceptTransfer = false;
                        SourceFileName = "";
                        FileSourceSize = 0;
                        try{
                            BufferFileSource.close();
                        }catch(IOException e){
                            //
                        }
                        seleccionarBtn.setEnabled(true);
                        SourceMACTxt.setEnabled(true);
                        DestinationMACTxt.setEnabled(true);
                        archivoTxt.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        enviarBtn.setText("Enviar");
                        JOptionPane.showMessageDialog(null, "El receptor ha rechazado tu transferencia.", "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                        archivoTxt.setText("");
                    }
                    break;
                }
                case 0x03:{ //partSize
                    if(ReceivingAFile && DestinationFileName.equals(getFilename(trama))){
                        NumberOfDivisionOfDestinationFile = getSize(trama);
                        seleccionarBtn.setEnabled(false);
                        SourceMACTxt.setEnabled(false);
                        DestinationMACTxt.setEnabled(false);
                        archivoTxt.setEnabled(false);
                        seleccionarArchivo.setEnabled(false);
                        enviarBtn.setText("Cancelar");
                    }
                    try{
                        BufferFileDestination = new RandomAccessFile(carpetaDestinoTxt.getText() + "\\" + DestinationFileName, "rw");
                        recibidosTxt.setEnabled(true);
                        Thread hilo = new Thread(new Runnable(){
                            @Override
                            public void run(){
                                for(int i = 0; i < NumberOfDivisionOfDestinationFile; i++){
                                    byte [] nuevaTrama = new byte[1024];
                                    fillMacs(nuevaTrama, macD, macO);
                                    fillEthType(nuevaTrama);
                                    fillFilename(nuevaTrama, DestinationFileName);
                                    nuevaTrama[14] = 0x04;
                                    fillSize(nuevaTrama, i);
                                    enviarTrama(nuevaTrama);
                                    NumberOfSendFrames++;
                                    if((i + 1) % NumberOfConcurrentFrames == 0){
                                        while(NumberOfSendFrames > 0){}
                                    }
                                }
                            }
                        });
                        hilo.start();
                    }catch(IOException e){
                        //
                    }
                    break;
                }
                case 0x04:{ //sendContent
                    if(SendingAFile && SourceFileName.equals(getFilename(trama))){
                        int parteActual = getSize(trama);
                        if(parteActual >= NumberOfDivisionOfSourceFile) break;
                        byte [] nuevaTrama = new byte[1024];
                        fillMacs(nuevaTrama, macD, macO);
                        fillEthType(nuevaTrama);
                        fillFilename(nuevaTrama, SourceFileName);
                        nuevaTrama[14] = 0x05;
                        fillSize(nuevaTrama, parteActual);
                        try{
                            //System.out.print("Parte " + parteActual + " ");
                            BufferFileSource.seek(parteActual * SizeOfDivisionInBytes);
                            if(parteActual + 1 == NumberOfDivisionOfSourceFile){
                                BufferFileSource.readFully(nuevaTrama, 275, ((FileSourceSize - 1) % SizeOfDivisionInBytes) + 1);
                                //System.out.println("Enviado: " + (parteActual * SizeOfDivisionInBytes) + " - " + (FileSourceSize - 1));
                            }else{
                                BufferFileSource.readFully(nuevaTrama, 275, SizeOfDivisionInBytes);
                                //System.out.println("Enviado: " + (parteActual * SizeOfDivisionInBytes) + " - " + (SizeOfDivisionInBytes * (parteActual + 1) - 1));
                            }
                            NumberOfSendParts++;
                            enviadosTxt.setText("Bytes enviados: " + (NumberOfSendParts * SizeOfDivisionInBytes) + " / " + FileSourceSize + " (" + ((double)NumberOfSendParts / (double)NumberOfDivisionOfSourceFile * 100) + "%)");
                        }catch(IOException e){
                            //
                        }
                        enviarTrama(nuevaTrama);
                    }
                    break;
                }
                case 0x05:{ //processContent
                    if(ReceivingAFile && DestinationFileName.equals(getFilename(trama))){
                        NumberOfSendFrames--;
                        int parteActual = getSize(trama);
                        try{
                            //System.out.print("Parte " + parteActual + " ");
                            BufferFileDestination.seek(parteActual * SizeOfDivisionInBytes);
                            if(parteActual + 1 == NumberOfDivisionOfDestinationFile){
                                BufferFileDestination.write(trama, 275, ((FileDestinationSize - 1) % SizeOfDivisionInBytes) + 1);
                                //System.out.println("Recibido: " + (parteActual * SizeOfDivisionInBytes) + " - " + (FileDestinationSize - 1));
                            }else{
                                BufferFileDestination.write(trama, 275, SizeOfDivisionInBytes);
                                //System.out.println("Recibido: " + (parteActual * SizeOfDivisionInBytes) + " - " + (SizeOfDivisionInBytes * (parteActual + 1) - 1));
                            }
                        }catch(IOException e){
                            //
                        }
                        NumberOfRecivedParts++;
                        recibidosTxt.setText("Bytes recibidos: " + (NumberOfRecivedParts * SizeOfDivisionInBytes) + " / " + FileDestinationSize + " (" + ((double)NumberOfRecivedParts / (double)NumberOfDivisionOfDestinationFile * 100) + "%)");
                        if(NumberOfRecivedParts == NumberOfDivisionOfDestinationFile){
                            try{
                                BufferFileDestination.close();
                            }catch(IOException e){
                                //
                            }
                            recibidosTxt.setText("Bytes recibidos: ");
                            recibidosTxt.setEnabled(false);
                            ReceivingAFile = false;
                            FileDestinationSize = 0;
                            NumberOfDivisionOfDestinationFile = 0;
                            NumberOfRecivedParts = 0;
                            NumberOfSendFrames = 0;
                            SourceMAC = new byte[6];
                            byte [] nuevaTrama = new byte[1024];
                            fillMacs(nuevaTrama, macD, macO);
                            fillEthType(nuevaTrama);
                            fillFilename(nuevaTrama, DestinationFileName);
                            nuevaTrama[14] = 0x08;
                            enviarTrama(nuevaTrama);
                            DestinationFileName = "";
                            seleccionarBtn.setEnabled(true);
                            SourceMACTxt.setEnabled(true);
                            DestinationMACTxt.setEnabled(true);
                            archivoTxt.setEnabled(true);
                            seleccionarArchivo.setEnabled(true);
                            enviarBtn.setText("Enviar");
                            JOptionPane.showMessageDialog(null, "Has recibido tu archivo.", "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
                }
                case 0x06:{ //senderCancel
                    if(ReceivingAFile && DestinationFileName.equals(getFilename(trama))){
                        try{
                            BufferFileDestination.close();
                        }catch(IOException e){
                            //
                        }
                        recibidosTxt.setText("Bytes recibidos: ");
                        recibidosTxt.setEnabled(false);
                        ReceivingAFile = false;
                        FileDestinationSize = 0;
                        NumberOfDivisionOfDestinationFile = 0;
                        NumberOfRecivedParts = 0;
                        NumberOfSendFrames = 0;
                        SourceMAC = new byte[6];
                        DestinationFileName = "";
                        seleccionarBtn.setEnabled(true);
                        SourceMACTxt.setEnabled(true);
                        DestinationMACTxt.setEnabled(true);
                        archivoTxt.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        enviarBtn.setText("Enviar");
                        JOptionPane.showMessageDialog(null, "El emisor ha cancelado la transferencia.", "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }
                case 0x07:{ //receiverCancel
                    if(SendingAFile && SourceFileName.equals(getFilename(trama))){
                        SendingAFile = false;
                        enviadosTxt.setText("Bytes enviados: ");
                        enviadosTxt.setEnabled(false);
                        FileSourceSize = 0;
                        NumberOfDivisionOfSourceFile = 0;
                        NumberOfSendParts = 0;
                        SourceFileName = "";
                        try{
                            BufferFileSource.close();
                        }catch(IOException e){
                            //
                        }
                        seleccionarBtn.setEnabled(true);
                        SourceMACTxt.setEnabled(true);
                        DestinationMACTxt.setEnabled(true);
                        archivoTxt.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        enviarBtn.setText("Enviar");
                        JOptionPane.showMessageDialog(null, "El receptor ha cancelado la tranferencia.", "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                        archivoTxt.setText("");
                    }
                    break;
                }
                case 0x08:{ //end
                    if(SendingAFile && SourceFileName.equals(getFilename(trama))){
                        SendingAFile = false;
                        enviadosTxt.setText("Bytes enviados: ");
                        enviadosTxt.setEnabled(false);
                        FileSourceSize = 0;
                        NumberOfDivisionOfSourceFile = 0;
                        NumberOfSendParts = 0;
                        SourceFileName = "";
                        try{
                            BufferFileSource.close();
                        }catch(IOException e){
                            //
                        }
                        seleccionarBtn.setEnabled(true);
                        SourceMACTxt.setEnabled(true);
                        DestinationMACTxt.setEnabled(true);
                        archivoTxt.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        enviarBtn.setText("Enviar");
                        JOptionPane.showMessageDialog(null, "Ha finalizado tu transferencia.", "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                        archivoTxt.setText("");
                    }
                    break;
                }
            }
        }
    }
    
    private void enviarTrama(byte [] packet){
        Thread hilo = new Thread(new Runnable(){
            @Override
            public void run(){
                CurrentPcap.sendPacket(packet);
            }
        });
        hilo.start();
    }
    
    private void seleccionarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarBtnMouseClicked
        CurrentInterface = Interfaces.get(InterfacesLista.getSelectedIndex());
        
        if(CurrentPcap != null){
            CurrentPcap.close();
            CurrentPcap = null;
            seleccionarBtn.setText("Comenzar a escuchar en la CurrentInterface seleccionada");
            InterfacesLista.setEnabled(true);
            SourceMACTxt.setText("");
            SourceMACTxt.setEnabled(false);
            DestinationMACTxt.setEnabled(false);
            archivoTxt.setEnabled(false);
            seleccionarArchivo.setEnabled(false);
            enviarBtn.setEnabled(false);
        }else{
            try{
                HostMAC = CurrentInterface.getHardwareAddress();
                CurrentPcap = Pcap.openLive(CurrentInterface.getName(), 64 * 1024, Pcap.MODE_PROMISCUOUS, 1, ErrorData);
                
                System.out.printf ("Can't read list of Devices, error is %s",
                    ErrorData.toString());
                
                PcapBpfProgram filtro = new PcapBpfProgram();
                CurrentPcap.compile(filtro, "ether proto 0x1601", 0, 0);
                
                CurrentPcap.setFilter(filtro);
                PcapPacketHandler<String> jPacketHandler = new PcapPacketHandler<String>(){
                    @Override
                    public void nextPacket(PcapPacket paquete, String txt){
                        analizarTrama(paquete);
                    }
                };
                hiloPrincipal = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        CurrentPcap.loop(Pcap.LOOP_INFINITE, jPacketHandler, "");
                    }
                });
                hiloPrincipal.start();
                seleccionarBtn.setText("Dejar de escuchar en la CurrentInterface seleccionada");
                InterfacesLista.setEnabled(false);
                SourceMACTxt.setText(macToString(HostMAC));
                SourceMACTxt.setEnabled(true);
                DestinationMACTxt.setEnabled(true);
                archivoTxt.setEnabled(true);
                seleccionarArchivo.setEnabled(true);
                enviarBtn.setEnabled(true);
            }catch(IOException io){
                //
            }
        }
    }//GEN-LAST:event_seleccionarBtnMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(CurrentPcap != null){
            CurrentPcap.close();
        }
    }//GEN-LAST:event_formWindowClosing

    private void seleccionarArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarArchivoMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File archivoOrigen = fileChooser.getSelectedFile();
            SourceFileName = archivoOrigen.getName();
            try{
                BufferFileSource = new RandomAccessFile(archivoOrigen, "r");
            }catch(IOException e){
                //
            }
            FileSourceSize = (int)archivoOrigen.length();
            archivoTxt.setText(archivoOrigen.getAbsolutePath());
        }
    }//GEN-LAST:event_seleccionarArchivoMouseClicked

    private void enviarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarBtnMouseClicked
        if(WaitingForAcceptTransfer){
            WaitingForAcceptTransfer = false;
            seleccionarBtn.setEnabled(true);
            SourceMACTxt.setEnabled(true);
            DestinationMACTxt.setEnabled(true);
            archivoTxt.setEnabled(true);
            seleccionarArchivo.setEnabled(true);
            enviarBtn.setText("Enviar");
        }else{
            if(SendingAFile){
                SendingAFile = false;
                byte[] trama = new byte[1024];
                fillEthType(trama);
                fillMacs(trama, HostMAC, DestinationMAC);
                trama[14] = 0x06; //senderCancel
                fillFilename(trama, SourceFileName);
                enviarTrama(trama);
                enviadosTxt.setText("Bytes enviados: ");
                enviadosTxt.setEnabled(false);
                FileSourceSize = 0;
                NumberOfDivisionOfSourceFile = 0;
                NumberOfSendParts = 0;
                SourceFileName = "";
                try{
                    BufferFileSource.close();
                }catch(IOException e){
                    //
                }
                seleccionarBtn.setEnabled(true);
                SourceMACTxt.setEnabled(true);
                DestinationMACTxt.setEnabled(true);
                archivoTxt.setEnabled(true);
                seleccionarArchivo.setEnabled(true);
                enviarBtn.setText("Enviar");
                archivoTxt.setText("");
            }else{
                if(ReceivingAFile){
                    ReceivingAFile = false;
                    byte[] trama = new byte[1024];
                    fillEthType(trama);
                    fillMacs(trama, HostMAC, SourceMAC);
                    trama[14] = 0x07; //receiverCancel
                    fillFilename(trama, DestinationFileName);
                    enviarTrama(trama);
                    try{
                        BufferFileDestination.close();
                    }catch(IOException e){
                        //
                    }
                    recibidosTxt.setText("Bytes recibidos: ");
                    recibidosTxt.setEnabled(false);
                    ReceivingAFile = false;
                    FileDestinationSize = 0;
                    NumberOfDivisionOfDestinationFile = 0;
                    NumberOfRecivedParts = 0;
                    NumberOfSendFrames = 0;
                    SourceMAC = new byte[6];
                    DestinationFileName = "";
                    seleccionarBtn.setEnabled(true);
                    SourceMACTxt.setEnabled(true);
                    DestinationMACTxt.setEnabled(true);
                    archivoTxt.setEnabled(true);
                    seleccionarArchivo.setEnabled(true);
                }else{
                    if(SourceFileName.equals("")) return;
                    WaitingForAcceptTransfer = true;
                    seleccionarBtn.setEnabled(false);
                    SourceMACTxt.setEnabled(false);
                    DestinationMACTxt.setEnabled(false);
                    archivoTxt.setEnabled(false);
                    seleccionarArchivo.setEnabled(false);
                    enviarBtn.setText("Cancelar espera");
                    DestinationMAC = stringToMac(DestinationMACTxt.getText());
                    byte [] trama = new byte[1024];
                    fillMacs(trama, HostMAC, DestinationMAC);
                    fillEthType(trama);
                    trama[14] = 0x00; //ask
                    fillFilename(trama, SourceFileName);
                    fillSize(trama, FileSourceSize);
                    enviarTrama(trama);
                }
            }
        }
    }//GEN-LAST:event_enviarBtnMouseClicked

    private void seleccionarCarpetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarCarpetaMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            carpetaDestinoTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_seleccionarCarpetaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ventanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField archivoTxt;
    private javax.swing.JTextField carpetaDestinoTxt;
    private javax.swing.JLabel enviadosTxt;
    private javax.swing.JButton enviarBtn;
    private javax.swing.JComboBox<String> InterfacesLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField DestinationMACTxt;
    private javax.swing.JTextField SourceMACTxt;
    private javax.swing.JLabel recibidosTxt;
    private javax.swing.JButton seleccionarArchivo;
    private javax.swing.JButton seleccionarBtn;
    private javax.swing.JButton seleccionarCarpeta;
    // End of variables declaration//GEN-END:variables
}
