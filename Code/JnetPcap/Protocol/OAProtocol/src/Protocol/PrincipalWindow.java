/**
 * ===================================================================================
 * =====================          OA PROTOCOL       ==================================
 * ===================================================================================
 * 
 * @author Oscar RH, Alan Ontiveros, Arturo Rivas
 */

package Protocol;


/**
 * ========================================================
 * ====================   IMPORTS     =====================
 * ========================================================
 */
    import java.io.*;
    import java.util.ArrayList;  
    import java.util.Arrays;  
    import java.util.List;  
    import javax.swing.JFileChooser;
    import javax.swing.JOptionPane;
    import org.jnetpcap.Pcap;  
    import org.jnetpcap.PcapBpfProgram;
    import org.jnetpcap.PcapIf;  
    import org.jnetpcap.packet.PcapPacket;
    import org.jnetpcap.packet.PcapPacketHandler;





/**
 * ========================================================
 * ===============   USER INTERFACE     ===================
 * ========================================================
 */
public class PrincipalWindow extends javax.swing.JFrame {

    public PrincipalWindow() {
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SelectInterfaceButton = new javax.swing.JButton();
        ListOfNetworkView = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DestinationDirectoryView = new javax.swing.JTextField();
        seleccionarCarpeta = new javax.swing.JButton();
        SendText = new javax.swing.JLabel();
        RecivedText = new javax.swing.JLabel();
        FileView = new javax.swing.JTextField();
        SourceMACText = new javax.swing.JTextField();
        DestinationMACText = new javax.swing.JTextField();
        seleccionarArchivo = new javax.swing.JButton();
        SendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Send Files");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(38, 50, 56));
        jPanel1.setForeground(new java.awt.Color(38, 50, 56));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Network Interfaces:");

        SelectInterfaceButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        SelectInterfaceButton.setLabel("Start to Listen for Packages");
        SelectInterfaceButton.setName(""); // NOI18N
        SelectInterfaceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectInterfaceButtonMouseClicked(evt);
            }
        });
        SelectInterfaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectInterfaceButtonActionPerformed(evt);
            }
        });

        ListOfNetworkView.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Your MAC:");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Destination MAC:");

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("File to Send:");

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Where to Recived:");

        DestinationDirectoryView.setEditable(false);

        seleccionarCarpeta.setText("..");
        seleccionarCarpeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarCarpetaMouseClicked(evt);
            }
        });

        SendText.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        SendText.setForeground(java.awt.Color.white);
        SendText.setText("Send Bytes:");
        SendText.setEnabled(false);

        RecivedText.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        RecivedText.setForeground(java.awt.Color.white);
        RecivedText.setText("Recived Bytes:");
        RecivedText.setEnabled(false);

        FileView.setEditable(false);
        FileView.setEnabled(false);

        SourceMACText.setEditable(false);
        SourceMACText.setEnabled(false);

        DestinationMACText.setEnabled(false);

        seleccionarArchivo.setText("..");
        seleccionarArchivo.setEnabled(false);
        seleccionarArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarArchivoMouseClicked(evt);
            }
        });

        SendButton.setFont(new java.awt.Font("Roboto Mono", 1, 18)); // NOI18N
        SendButton.setText("Send");
        SendButton.setEnabled(false);
        SendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SendButtonMouseClicked(evt);
            }
        });
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SendText)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(80, 80, 80))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(DestinationDirectoryView, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarCarpeta))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(SourceMACText, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(FileView, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(seleccionarArchivo))
                                .addComponent(DestinationMACText, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SelectInterfaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ListOfNetworkView, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(RecivedText))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListOfNetworkView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SelectInterfaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SourceMACText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(DestinationMACText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(FileView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionarArchivo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DestinationDirectoryView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionarCarpeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(SendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(SendText)
                .addGap(18, 18, 18)
                .addComponent(RecivedText)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
    * ==============================================================
    * =====================   VARIABLES    =========================
    * ==============================================================
    */
    
    List<PcapIf> NetworkInterfaces = new ArrayList<>();
    PcapIf CurrentNetworkInterface;
    Pcap CurrentPCap;

    byte[] HostMAC = new byte[6];
    byte[] SourceMAC = new byte[6];
    byte[] DestinationMAC = new byte[6];

    StringBuilder ErrorData = new StringBuilder();

    Thread PrincipalThread;

    RandomAccessFile ByteFileSource;
    RandomAccessFile ByteFileDestination;

    String NameSourceFile = "";
    String NameDestinationFile = "";

    int SourceFileSize = 0;
    int DestinationFileSize = 0;

    int NumberOfPartsOfSourceFile = 0;
    int NumberOfPartsOfDestinationFile = 0;

    int FrameSize = 700;
    int NumberOfPartsSend = 0;
    int NumberOfPartsRecived = 0;

    boolean Waiting = false;
    boolean Sending = false;
    boolean Receiving = false;

    int SimultaneousFrames = 10;
    volatile int NumberOfSendFrames = 0;



  

    
    
    
    /*==============================================================
    ==================     START UP      ===========================
    ============================================================= */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {

        setLocationRelativeTo(null);
        
        Pcap.findAllDevs(NetworkInterfaces, ErrorData);
        
        try{
            for(PcapIf Interface : NetworkInterfaces) {
                final byte[] MAcAddress = Interface.getHardwareAddress();

                String MACAddresString = (MAcAddress != null)? 
                    Protocol.MACToString(MAcAddress): 
                    "No MAC Address";

                ListOfNetworkView.addItem(Interface.getName() + " <" + MACAddresString + ">");
            }

        }
        catch(IOException io) {}

    }

    /*==============================================================
    =================      CLOSE UP      ===========================
    ============================================================= */
    private void formWindowClosing(java.awt.event.WindowEvent Event) {
        if (CurrentPCap != null) CurrentPCap.close();
    }

 
    /*===============================================================
    ==========     START LISTENING FOR PACKETS    ===================
    ============================================================== */
    private void SelectInterfaceButtonMouseClicked(java.awt.event.MouseEvent Event) {

        CurrentNetworkInterface = NetworkInterfaces.get(ListOfNetworkView.getSelectedIndex());

        if(CurrentPCap != null) {
            CurrentPCap.close();
            CurrentPCap = null;
            
            SelectInterfaceButton.setText("Start to Listen");

            SourceMACText.setText("");
            ListOfNetworkView.setEnabled(true);
            DestinationMACText.setEnabled(false);
            seleccionarArchivo.setEnabled(false);
            FileView.setEnabled(false);
            SourceMACText.setEnabled(false);
            SendButton.setEnabled(false);
        } 
        else {
            
            try{
                
                HostMAC = CurrentNetworkInterface.getHardwareAddress();
                
                CurrentPCap = Pcap.openLive(
                    CurrentNetworkInterface.getName(), 
                    64 * 1024, 
                    Pcap.MODE_PROMISCUOUS,
                    1,
                    ErrorData
                );

                PcapBpfProgram Filter = new PcapBpfProgram();
                
                CurrentPCap.compile(Filter, "ether proto 0x1601", 0, 0);
                CurrentPCap.setFilter(Filter);
                
                PcapPacketHandler<String> jPacketHandler = new PcapPacketHandler<String>(){
                    public void nextPacket(PcapPacket paquete, String txt){
                        AnalyzeFrame(paquete);
                    }
                };

                PrincipalThread = new Thread(new Runnable() {
                    public void run(){
                        CurrentPCap.loop(Pcap.LOOP_INFINITE, jPacketHandler, "");
                    }
                });

                PrincipalThread.start();
                SelectInterfaceButton.setText("Stop Listening");
                SourceMACText.setText(Protocol.MACToString(HostMAC));
                SourceMACText.setEnabled(true);
                FileView.setEnabled(true);
                SendButton.setEnabled(true);
                ListOfNetworkView.setEnabled(false);
                DestinationMACText.setEnabled(true);
                seleccionarArchivo.setEnabled(true);
            }
            catch(IOException io) {}
        }
    }


    /*===============================================================
    ==========     START LISTENING FOR PACKETS    ===================
    ============================================================== */
    private void AnalyzeFrame(PcapPacket Packet) {

        byte[] Frame = Packet.getByteArray(0, Packet.size());

        if(Packet.getUByte(12) == 22 && Packet.getUByte(13) == 1) {
            
            byte[] CurrentSourceMAC = new byte[6];
            byte[] CurrentDestinationMAC = new byte[6];
            
            Protocol.GetMACToFrame(Frame, CurrentSourceMAC, CurrentDestinationMAC);
            byte Action = Frame[14];

            if(!Arrays.equals(CurrentDestinationMAC, HostMAC)) return;

            switch(Action) {

                // ========  ASK  ====================
                case 0x00: {
                    
                    System.arraycopy(CurrentSourceMAC, 0, SourceMAC, 0, 6);
                    NameDestinationFile = Protocol.GetFilename(Frame);
                    DestinationFileSize = Protocol.GetFrameSize(Frame);

                    String Text = 
                                    "User with MAC " + Protocol.MACToString(CurrentSourceMAC) +
                                    " wants to send you the file:\n" + NameDestinationFile + 
                                    "\nSize: " + DestinationFileSize + " bytes\nAccept?";

                    int Option = JOptionPane.showConfirmDialog(null, Text, "Transfer", JOptionPane.YES_NO_OPTION);

                    byte[] NewFrame = new byte[1024];
                    Protocol.FillMACToFrame(NewFrame, CurrentDestinationMAC, CurrentSourceMAC);
                    Protocol.FillEthernetType(NewFrame);

                    Protocol.FillFilename(NewFrame, NameDestinationFile);
                    
                    if(Option == JOptionPane.YES_OPTION){
                        NewFrame[14] = 0x01;
                        Receiving = true;
                    }
                    else {
                        NewFrame[14] = 0x02;
                        NameDestinationFile = "";
                        DestinationFileSize = 0;
                        SourceMAC = new byte[6];
                    }

                    SendFrame(NewFrame);
                    break;

                }

                // ========  YES  ====================
                case 0x01: {
                    
                    if(Waiting && NameSourceFile.equals(Protocol.GetFilename(Frame))) {
                        Waiting = false;
                        Sending = true;

                        SendButton.setText("Cancel");
                        SendText.setEnabled(true);

                        NumberOfPartsOfSourceFile = 
                            (SourceFileSize % FrameSize == 0 ?
                                SourceFileSize / FrameSize:
                                SourceFileSize / FrameSize + 1);

                        byte [] NewFrame = new byte[1024];
                        Protocol.FillMACToFrame(NewFrame, CurrentDestinationMAC, CurrentSourceMAC);
                        Protocol.FillFilename(NewFrame, NameSourceFile);
                        Protocol.FillEthernetType(NewFrame);
                        NewFrame[14] = 0x03;
                        Protocol.FillFrameSize(NewFrame, NumberOfPartsOfSourceFile);
                        SendFrame(NewFrame);
                    }

                    break;

                }

                // ========  NO  ====================
                case 0x02: {
                    if(Waiting && NameSourceFile.equals(Protocol.GetFilename(Frame))){
                        Waiting = false;
                        NameSourceFile = "";
                        SourceFileSize = 0;
                        
                        try{
                            ByteFileSource.close();
                        }
                        catch(IOException e){}

                        SourceMACText.setEnabled(true);
                        FileView.setEnabled(true);
                        DestinationMACText.setEnabled(true);
                        SelectInterfaceButton.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        SendButton.setText("Send");
                        JOptionPane.showMessageDialog(
                            null,
                            "Your transfer have been cancel",
                            "Transfer",
                            JOptionPane.INFORMATION_MESSAGE
                        );

                        FileView.setText("");
                    }
                    break;

                }

                // ========  PART SIZE  ====================
                case 0x03:{
                    if(Receiving && NameDestinationFile.equals(Protocol.GetFilename(Frame))){
                        NumberOfPartsOfDestinationFile = Protocol.GetFrameSize(Frame);
                        SourceMACText.setEnabled(false);
                        FileView.setEnabled(false);
                        DestinationMACText.setEnabled(false);
                        SelectInterfaceButton.setEnabled(false);
                        seleccionarArchivo.setEnabled(false);
                        SendButton.setText("Cancel");
                    }

                    try{
                        ByteFileDestination = new RandomAccessFile(DestinationDirectoryView.getText() + "/" + NameDestinationFile, "rw");
                        RecivedText.setEnabled(true);


                        Thread NewThread = new Thread(() -> {
                            for(int i = 0; i < NumberOfPartsOfDestinationFile; i++){
                                byte [] NewFrame = new byte[1024];
                                Protocol.FillMACToFrame(NewFrame, CurrentDestinationMAC, CurrentSourceMAC);
                                Protocol.FillEthernetType(NewFrame);
                                Protocol.FillFilename(NewFrame, NameDestinationFile);
                                NewFrame[14] = 0x04;
                                Protocol.FillFrameSize(NewFrame, i);
                                SendFrame(NewFrame);
                                NumberOfSendFrames++;
                                if((i + 1) % SimultaneousFrames == 0) {
                                    while(NumberOfSendFrames > 0){}
                                }
                            }
                        });

                        NewThread.start();

                    }
                    catch(IOException e){}
                    break;

                }

                // ========  SEND CONTENT  ====================
                case 0x04: {
                    if(Sending && NameSourceFile.equals(Protocol.GetFilename(Frame))){
                        
                        int CurrentPart = Protocol.GetFrameSize(Frame);
                        if(CurrentPart >= NumberOfPartsOfSourceFile) break;

                        byte [] NewFrame = new byte[1024];
                        Protocol.FillMACToFrame(NewFrame, CurrentDestinationMAC, CurrentSourceMAC);
                        Protocol.FillEthernetType(NewFrame);
                        Protocol.FillFilename(NewFrame, NameSourceFile);
                        NewFrame[14] = 0x05;
                        Protocol.FillFrameSize(NewFrame, CurrentPart);

                        try{
                            ByteFileSource.seek(CurrentPart * FrameSize);
                            
                            if(CurrentPart + 1 == NumberOfPartsOfSourceFile)
                                ByteFileSource.readFully(NewFrame, 275, ((SourceFileSize - 1) % FrameSize) + 1);
                            else
                                ByteFileSource.readFully(NewFrame, 275, FrameSize);

                            NumberOfPartsSend++;
                            SendText.setText(
                                "Send Bytes: " + (NumberOfPartsSend * FrameSize) + " / " + SourceFileSize + 
                                " (" + ((double)NumberOfPartsSend / (double)NumberOfPartsOfSourceFile * 100) + "%)");
                        }
                        catch(IOException e){}
                        
                        SendFrame(NewFrame);
                    }
                    break;

                }

                // ========  PROCESS CONTENT  ====================
                case 0x05: {
                    
                    if(Receiving && NameDestinationFile.equals(Protocol.GetFilename(Frame))){
                        
                        NumberOfSendFrames--;
                        int CurrentPart = Protocol.GetFrameSize(Frame);
                        
                        try{
                            ByteFileDestination.seek(CurrentPart * FrameSize);
                            if(CurrentPart + 1 == NumberOfPartsOfDestinationFile)
                                ByteFileDestination.write(Frame, 275, ((DestinationFileSize - 1) % FrameSize) + 1);
                            else 
                                ByteFileDestination.write(Frame, 275, FrameSize);
                        }

                        catch(IOException e){}

                        NumberOfPartsRecived++;

                        RecivedText.setText(
                            "Bytes Recived: " + (NumberOfPartsRecived * FrameSize) + " / " + DestinationFileSize + 
                            " (" + ((double)NumberOfPartsRecived / (double)NumberOfPartsOfDestinationFile * 100) + "%)");

                        if(NumberOfPartsRecived == NumberOfPartsOfDestinationFile){
                            
                            try{
                                ByteFileDestination.close();
                            }
                            catch(IOException e){}
                            
                            RecivedText.setText("Bytes Recived: ");
                            RecivedText.setEnabled(false);
                            Receiving = false;
                            DestinationFileSize = 0;
                            NumberOfPartsOfDestinationFile = 0;
                            NumberOfPartsRecived = 0;
                            NumberOfSendFrames = 0;

                            SourceMAC = new byte[6];
                            byte [] NewFrame = new byte[1024];
                            Protocol.FillMACToFrame(NewFrame, CurrentDestinationMAC, CurrentSourceMAC);
                            Protocol.FillEthernetType(NewFrame);
                            Protocol.FillFilename(NewFrame, NameDestinationFile);
                            NewFrame[14] = 0x08;
                            SendFrame(NewFrame);
                            NameDestinationFile = "";

                            SourceMACText.setEnabled(true);
                            FileView.setEnabled(true);
                            seleccionarArchivo.setEnabled(true);
                            DestinationMACText.setEnabled(true);
                            SelectInterfaceButton.setEnabled(true);
                            SendButton.setText("Send");
                            JOptionPane.showMessageDialog(null, "Transfer complete", "Transfer", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
                }

                // ========  SENDER CANCEL  ====================
                case 0x06: {
                    if(Receiving && NameDestinationFile.equals(Protocol.GetFilename(Frame))){
                        try{
                            ByteFileDestination.close();
                        }catch(IOException e){}

                        RecivedText.setText("Bytes Recived: ");
                        RecivedText.setEnabled(false);
                        Receiving = false;
                        DestinationFileSize = 0;
                        NumberOfPartsOfDestinationFile = 0;
                        NumberOfPartsRecived = 0;
                        NumberOfSendFrames = 0;
                        SourceMAC = new byte[6];
                        NameDestinationFile = "";
                        SelectInterfaceButton.setEnabled(true);
                        SourceMACText.setEnabled(true);
                        DestinationMACText.setEnabled(true);
                        FileView.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        SendButton.setText("Send");
                        JOptionPane.showMessageDialog(
                            null,
                            "Your transfer have been cancel.",
                            "Transfer",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                }

                // ========  RECEIVER CANCEL  ====================
                case 0x07:{
                    if(Sending && NameSourceFile.equals(Protocol.GetFilename(Frame))){
                        Sending = false;
                        SendText.setText("Bytes Send: ");
                        SendText.setEnabled(false);
                        SourceFileSize = 0;
                        NumberOfPartsOfSourceFile = 0;
                        NumberOfPartsSend = 0;
                        NameSourceFile = "";

                        try{
                            ByteFileSource.close();
                        }catch(IOException e){}

                        SelectInterfaceButton.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        DestinationMACText.setEnabled(true);
                        SourceMACText.setEnabled(true);
                        FileView.setEnabled(true);
                        SendButton.setText("Send");

                        JOptionPane.showMessageDialog(null, "Transfer cancel", "Transfer", JOptionPane.INFORMATION_MESSAGE);
                        FileView.setText("");
                    }
                    break;
                }

                // ========  END  ====================
                case 0x08: {

                    if(Sending && NameSourceFile.equals(Protocol.GetFilename(Frame))){
                        Sending = false;
                        SendText.setText("Bytes Send: ");
                        SendText.setEnabled(false);
                        SourceFileSize = 0;
                        NumberOfPartsOfSourceFile = 0;
                        NumberOfPartsSend = 0;
                        NameSourceFile = "";
                        
                        try{
                            ByteFileSource.close();
                        }catch(IOException e){}

                        FileView.setEnabled(true);
                        SourceMACText.setEnabled(true);
                        SelectInterfaceButton.setEnabled(true);
                        DestinationMACText.setEnabled(true);
                        seleccionarArchivo.setEnabled(true);
                        SendButton.setText("Send");
                        JOptionPane.showMessageDialog(
                            null, 
                            "Complete Transfer",
                            "Transfer",
                            JOptionPane.INFORMATION_MESSAGE);
                        FileView.setText("");
                    }
                    break;
                }
            }
        }
    }

    private void SendFrame(byte [] Packet) {
        Thread NewThread = new Thread(new Runnable(){
            public void run(){
                CurrentPCap.sendPacket(Packet);
            }
        });
        NewThread.start();
    }
   







    private void seleccionarArchivoMouseClicked(java.awt.event.MouseEvent evt) {                                                
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File archivoOrigen = fileChooser.getSelectedFile();
            NameSourceFile = archivoOrigen.getName();
            try{
                ByteFileSource = new RandomAccessFile(archivoOrigen, "r");
            }catch(IOException e){
                //
            }
            SourceFileSize = (int)archivoOrigen.length();
            FileView.setText(archivoOrigen.getAbsolutePath());
        }
    }

    private void SendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SendButtonMouseClicked
        if(Waiting){
            Waiting = false;
            SelectInterfaceButton.setEnabled(true);
            SourceMACText.setEnabled(true);
            DestinationMACText.setEnabled(true);
            FileView.setEnabled(true);
            seleccionarArchivo.setEnabled(true);
            SendButton.setText("Send");
        }else{
            if(Sending){
                Sending = false;
                byte[] Frame = new byte[1024];
                Protocol.FillEthernetType(Frame);
                Protocol.FillMACToFrame(Frame, HostMAC, DestinationMAC);
                Frame[14] = 0x06; //senderCancel
                Protocol.FillFilename(Frame, NameSourceFile);
                SendFrame(Frame);
                SendText.setText("Bytes Send: ");
                SendText.setEnabled(false);
                SourceFileSize = 0;
                NumberOfPartsOfSourceFile = 0;
                NumberOfPartsSend = 0;
                NameSourceFile = "";
                try{
                    ByteFileSource.close();
                }catch(IOException e){
                    //
                }
                SelectInterfaceButton.setEnabled(true);
                SourceMACText.setEnabled(true);
                DestinationMACText.setEnabled(true);
                FileView.setEnabled(true);
                seleccionarArchivo.setEnabled(true);
                SendButton.setText("Send");
                FileView.setText("");
            }else{
                if(Receiving){
                    Receiving = false;
                    byte[] Frame = new byte[1024];
                    Protocol.FillEthernetType(Frame);
                    Protocol.FillMACToFrame(Frame, HostMAC, SourceMAC);
                    Frame[14] = 0x07; //receiverCancel
                    Protocol.FillFilename(Frame, NameDestinationFile);
                    SendFrame(Frame);
                    try{
                        ByteFileDestination.close();
                    }catch(IOException e){
                        //
                    }
                    RecivedText.setText("Bytes Recived: ");
                    RecivedText.setEnabled(false);
                    Receiving = false;
                    DestinationFileSize = 0;
                    NumberOfPartsOfDestinationFile = 0;
                    NumberOfPartsRecived = 0;
                    NumberOfSendFrames = 0;
                    SourceMAC = new byte[6];
                    NameDestinationFile = "";
                    SelectInterfaceButton.setEnabled(true);
                    SourceMACText.setEnabled(true);
                    DestinationMACText.setEnabled(true);
                    FileView.setEnabled(true);
                    seleccionarArchivo.setEnabled(true);
                }else{
                    if(NameSourceFile.equals("")) return;
                    Waiting = true;
                    SelectInterfaceButton.setEnabled(false);
                    SourceMACText.setEnabled(false);
                    DestinationMACText.setEnabled(false);
                    FileView.setEnabled(false);
                    seleccionarArchivo.setEnabled(false);
                    SendButton.setText("Cancel Confirm");
                    DestinationMAC = Protocol.StringToMAC(DestinationMACText.getText());
                    byte [] Frame = new byte[1024];
                    Protocol.FillMACToFrame(Frame, HostMAC, DestinationMAC);
                    Protocol.FillEthernetType(Frame);
                    Frame[14] = 0x00; //ask
                    Protocol.FillFilename(Frame, NameSourceFile);
                    Protocol.FillFrameSize(Frame, SourceFileSize);
                    SendFrame(Frame);
                }
            }
        }
    }//GEN-LAST:event_SendButtonMouseClicked

    private void seleccionarCarpetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarCarpetaMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            DestinationDirectoryView.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_seleccionarCarpetaMouseClicked

    private void SelectInterfaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectInterfaceButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectInterfaceButtonActionPerformed

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SendButtonActionPerformed

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
                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {   
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
                 } 
            }

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PrincipalWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DestinationDirectoryView;
    private javax.swing.JTextField DestinationMACText;
    private javax.swing.JTextField FileView;
    private javax.swing.JComboBox<String> ListOfNetworkView;
    private javax.swing.JLabel RecivedText;
    private javax.swing.JButton SelectInterfaceButton;
    private javax.swing.JButton SendButton;
    private javax.swing.JLabel SendText;
    private javax.swing.JTextField SourceMACText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton seleccionarArchivo;
    private javax.swing.JButton seleccionarCarpeta;
    // End of variables declaration//GEN-END:variables
}
