/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MasterPanel.java
 *
 * Created on 4-Apr-2012, 6:52:17 PM
 */
package webcamstudio.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.SpinnerNumberModel;
import webcamstudio.FullScreen;
import webcamstudio.WebcamStudio;
import webcamstudio.channels.MasterChannels;
import webcamstudio.mixers.Frame;
import webcamstudio.mixers.MasterMixer;
import webcamstudio.mixers.SystemPlayer;
import webcamstudio.streams.SourceChannel;
import webcamstudio.streams.SourceText;
import webcamstudio.streams.Stream;
import webcamstudio.util.Tools;

/**
 *
 * @author patrick (modified by karl)
 */
public class MasterPanel extends javax.swing.JPanel implements MasterMixer.SinkListener, FullScreen.Listener {

    protected Viewer viewer = new Viewer();
    private SystemPlayer player = null;
    private final MasterMixer mixer = MasterMixer.getInstance();
    MasterChannels master = MasterChannels.getInstance();
    final static public Dimension PANEL_SIZE = new Dimension(150, 400);
    ArrayList<Stream> streamM = MasterChannels.getInstance().getStreams();   
    Stream stream = null;
    SourceText sTx = null;
    boolean lockRatio = false;

    /** Creates new form MasterPanel */
    public MasterPanel() {
        initComponents();
        spinFPS.setModel(new SpinnerNumberModel(5, 5, 30, 5));
        spinWidth.setValue(mixer.getWidth());
        spinHeight.setValue(mixer.getHeight());
        this.setVisible(true);
        viewer.setOpaque(true);
        panelPreview.add(viewer, BorderLayout.CENTER);
        player = SystemPlayer.getInstance(viewer);
        mixer.register(this);
        spinFPS.setValue(MasterMixer.getInstance().getRate());
        panChannels.add(new ChannelPanel(), BorderLayout.CENTER);
        final MasterPanel instanceSinkMP = this;
        FullScreen.setListenerFS(instanceSinkMP);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPreview = new javax.swing.JPanel();
        tabMixers = new javax.swing.JTabbedPane();
        panChannels = new javax.swing.JPanel();
        panMixer = new javax.swing.JPanel();
        lblWidth = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        spinWidth = new javax.swing.JSpinner();
        spinHeight = new javax.swing.JSpinner();
        btnApply = new javax.swing.JButton();
        lblHeight1 = new javax.swing.JLabel();
        spinFPS = new javax.swing.JSpinner();
        btnApplyToStreams = new javax.swing.JButton();
        btnFullScreen = new javax.swing.JButton();
        tglLockRatio = new javax.swing.JToggleButton();
        tglSound = new javax.swing.JToggleButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("webcamstudio/Languages"); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PREVIEW"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(257, 465));
        setLayout(new java.awt.BorderLayout());

        panelPreview.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelPreview.setMaximumSize(new java.awt.Dimension(180, 120));
        panelPreview.setMinimumSize(new java.awt.Dimension(180, 120));
        panelPreview.setName("panelPreview"); // NOI18N
        panelPreview.setPreferredSize(new java.awt.Dimension(180, 120));
        panelPreview.setLayout(new java.awt.BorderLayout());
        add(panelPreview, java.awt.BorderLayout.NORTH);

        tabMixers.setName("tabMixers"); // NOI18N
        tabMixers.setPreferredSize(new java.awt.Dimension(257, 353));

        panChannels.setName("panChannels"); // NOI18N
        panChannels.setLayout(new java.awt.BorderLayout());
        tabMixers.addTab(bundle.getString("CHANNELS"), panChannels); // NOI18N

        panMixer.setName("panMixer"); // NOI18N

        lblWidth.setText(bundle.getString("WIDTH")); // NOI18N
        lblWidth.setName("lblWidth"); // NOI18N

        lblHeight.setText(bundle.getString("HEIGHT")); // NOI18N
        lblHeight.setName("lblHeight"); // NOI18N

        spinWidth.setName("spinWidth"); // NOI18N
        spinWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinWidthStateChanged(evt);
            }
        });

        spinHeight.setName("spinHeight"); // NOI18N

        btnApply.setText(bundle.getString("APPLY")); // NOI18N
        btnApply.setToolTipText("Apply/Reset Mixer Settings");
        btnApply.setName("btnApply"); // NOI18N
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        lblHeight1.setText(bundle.getString("FRAMERATE")); // NOI18N
        lblHeight1.setName("lblHeight1"); // NOI18N

        spinFPS.setName("spinFPS"); // NOI18N

        btnApplyToStreams.setText("Apply to Streams");
        btnApplyToStreams.setToolTipText("Apply Mixer Settings Proportionally to all Streams.");
        btnApplyToStreams.setName("btnApplyToStreams"); // NOI18N
        btnApplyToStreams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyToStreamsActionPerformed(evt);
            }
        });

        btnFullScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/webcamstudio/resources/tango/view-fullscreen.png"))); // NOI18N
        btnFullScreen.setToolTipText("WebcamStudio FullScreen View");
        btnFullScreen.setMinimumSize(new java.awt.Dimension(0, 0));
        btnFullScreen.setName("btnFullScreen"); // NOI18N
        btnFullScreen.setPreferredSize(new java.awt.Dimension(20, 20));
        btnFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFullScreenActionPerformed(evt);
            }
        });

        tglLockRatio.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        tglLockRatio.setText("Lock Mixer Aspect Ratio");
        tglLockRatio.setName("tglLockRatio"); // NOI18N
        tglLockRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglLockRatioActionPerformed(evt);
            }
        });

        tglSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/webcamstudio/resources/tango/audio-card.png"))); // NOI18N
        tglSound.setToolTipText("Java Sound AudioSystem Out");
        tglSound.setName("tglSound"); // NOI18N
        tglSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglSoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panMixerLayout = new javax.swing.GroupLayout(panMixer);
        panMixer.setLayout(panMixerLayout);
        panMixerLayout.setHorizontalGroup(
            panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMixerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panMixerLayout.createSequentialGroup()
                        .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHeight1)
                            .addComponent(lblWidth)
                            .addComponent(lblHeight))
                        .addGap(14, 14, 14)
                        .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spinFPS, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(spinHeight)
                            .addComponent(spinWidth)))
                    .addComponent(btnApplyToStreams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMixerLayout.createSequentialGroup()
                        .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tglLockRatio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFullScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(tglSound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panMixerLayout.setVerticalGroup(
            panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMixerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWidth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinFPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHeight1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglLockRatio)
                    .addComponent(tglSound, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMixerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnApply)
                    .addComponent(btnFullScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApplyToStreams)
                .addGap(69, 69, 69))
        );

        tabMixers.addTab(bundle.getString("MIXER"), panMixer); // NOI18N

        add(tabMixers, java.awt.BorderLayout.CENTER);
        tabMixers.getAccessibleContext().setAccessibleName(bundle.getString("MIXER")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    public void releaseTglButton(){
    }    public void applyLoadedMixer(){
        int w = (Integer) spinWidth.getValue();
        int h = (Integer) spinHeight.getValue();
        mixer.stop();
        mixer.setWidth(w);
        mixer.setHeight(h);
        mixer.setRate((Integer) spinFPS.getValue());
        MasterMixer.getInstance().start();
    }
    
    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        SystemPlayer.getInstance(null).stop();
        Tools.sleep(30);
        MasterChannels.getInstance().stopAllStream();
        for (Stream s : streamM){
            s.updateStatus();
        }
        Tools.sleep(30);
        int w = (Integer) spinWidth.getValue();
        int h = (Integer) spinHeight.getValue();
        mixer.stop();
        mixer.setWidth(w);
        mixer.setHeight(h);
        mixer.setRate((Integer) spinFPS.getValue());
        MasterMixer.getInstance().start();
        for (Stream s : streamM){
            String streamName =s.getClass().getName();
            System.out.println("StreamName: "+streamName);
            if (streamName.contains("SinkFile") || streamName.contains("SinkUDP")){
                System.out.println("Sink New Size: "+w+"x"+h);
                s.setWidth(w);
                s.setHeight(h);
                s.updateStatus();
            }
        }
        ResourceMonitorLabel label = new ResourceMonitorLabel(System.currentTimeMillis()+10000, "New Mixer Settings Applied");
        ResourceMonitor.getInstance().addMessage(label);
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnApplyToStreamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyToStreamsActionPerformed
        ArrayList<Stream> allStreams = MasterChannels.getInstance().getStreams();
        int wi = mixer.getWidth();
        int he = mixer.getHeight();
        int oldCW;
        int oldCH;
        for (Stream oneStream : allStreams) {
            if (oneStream instanceof SourceText) {
                sTx = (SourceText) oneStream;
                oldCW = sTx.getTextCW();
                oldCH = sTx.getTextCH();
            } else {
                oldCW = oneStream.getCaptureWidth();
                oldCH = oneStream.getCaptureHeight();
            }
//            System.out.println("oldCW: "+oldCW);
//            System.out.println("oldCH: "+oldCH);
            int oldW = oneStream.getWidth();
//            System.out.println("oldW: "+oldW);
            int oldH = oneStream.getHeight();
//            System.out.println("oldH: "+oldH);
            int oldX = oneStream.getX();
//            System.out.println("oldX: "+oldX);
            int oldY = oneStream.getY();
//            System.out.println("oldY: "+oldY);
            int newW = (oldW * wi) / oldCW;
//            System.out.println("newW: "+newW);
            int newH = (oldH * he) / oldCH;
//            System.out.println("newH: "+newH);
            int newX = (oldX * wi) / oldCW;
//            System.out.println("newX: "+newX);
            int newY = (oldY * he) / oldCH;
//            System.out.println("newY: "+newY);
            if (oneStream instanceof SourceText) {
                oneStream.setWidth(newW);
                oneStream.setHeight(newH);
                oneStream.setX(newX);
                oneStream.setY(newY);
                oneStream.setCaptureWidth(newW);
                oneStream.setCaptureHeight(newH);
                sTx.setTextCW(wi);
                sTx.setTextCH(he);
                oneStream.updateStatus();
                for (SourceChannel ssc : oneStream.getChannels()) {
                    ssc.setWidth(newW);
                    ssc.setHeight(newH);
                    ssc.setX(newX);
                    ssc.setY(newY);
                    ssc.setCapWidth(newW);
                    ssc.setCapHeight(newH);
                }
            } else {
                oneStream.setWidth(newW);
                oneStream.setHeight(newH);
                oneStream.setX(newX);
                oneStream.setY(newY);
                oneStream.setCaptureWidth(wi);
                oneStream.setCaptureHeight(he);
                oneStream.updateStatus();
                for (SourceChannel ssc : oneStream.getChannels()) {
                    ssc.setWidth(newW);
                    ssc.setHeight(newH);
                    ssc.setX(newX);
                    ssc.setY(newY);
                    ssc.setCapWidth(wi);
                    ssc.setCapHeight(he);
                }
            }
        }
        ResourceMonitorLabel label = new ResourceMonitorLabel(System.currentTimeMillis()+10000, "Mixer Settings Applied To All Streams");
        ResourceMonitor.getInstance().addMessage(label);
    }//GEN-LAST:event_btnApplyToStreamsActionPerformed

    private void btnFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFullScreenActionPerformed
        FullScreen window = new FullScreen();
        StreamFullScreen frame = new StreamFullScreen(viewer);
        window.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            frame.setSelected(true);
            frame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(WebcamStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.setAlwaysOnTop(true);
        window.setVisible(true);
    }//GEN-LAST:event_btnFullScreenActionPerformed

    private void tglLockRatioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglLockRatioActionPerformed
        if (tglLockRatio.isSelected()){
            spinHeight.setEnabled(false);
            lockRatio = true;
        } else {
            spinHeight.setEnabled(true);
            lockRatio = false;
        }
    }//GEN-LAST:event_tglLockRatioActionPerformed

    private void spinWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinWidthStateChanged
        int oldW = mixer.getWidth();
        int oldH = mixer.getHeight();
        int w = (Integer) spinWidth.getValue();
        int h;
        if (tglLockRatio.isSelected()){
            h = (oldH * w) / oldW; 
            spinHeight.setValue(h);
        }
    }//GEN-LAST:event_spinWidthStateChanged

    private void tglSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglSoundActionPerformed
        if (tglSound.isSelected()) {
            try {
                player.play();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(MasterPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            player.stop();
        }
    }//GEN-LAST:event_tglSoundActionPerformed
    @Override
    public void resetViewer(ActionEvent evt){
        viewer.setOpaque(true);
        panelPreview.add(viewer, BorderLayout.CENTER);
        player = SystemPlayer.getInstance(viewer);
        this.repaint();
        this.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnApplyToStreams;
    private javax.swing.JButton btnFullScreen;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblHeight1;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JPanel panChannels;
    private javax.swing.JPanel panMixer;
    private javax.swing.JPanel panelPreview;
    public static javax.swing.JSpinner spinFPS;
    public static javax.swing.JSpinner spinHeight;
    public static javax.swing.JSpinner spinWidth;
    private javax.swing.JTabbedPane tabMixers;
    private javax.swing.JToggleButton tglLockRatio;
    private javax.swing.JToggleButton tglSound;
    // End of variables declaration//GEN-END:variables

    @Override
    public void newFrame(Frame frame) {
        player.addFrame(frame);
    }
}
