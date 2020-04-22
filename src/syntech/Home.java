/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntech;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.objdetect.Objdetect.CASCADE_SCALE_IMAGE;
import org.opencv.videoio.VideoCapture;


/**
 *
 * @author ccs
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    
    String source = "C:\\javacv resource\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt2.xml"; //paste here
  //  String source = "C:\\Users\\ccs\\Desktop\\trafic\\vehicle_detection_haarcascades-master\\cars.xml";
    CascadeClassifier faceDetector = new CascadeClassifier(source);
    
    
    public Home() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblnumber = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblnumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblnumber.setText("No of face");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(lblnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        
        (new Thread(){
            @Override
            public void run(){
                VideoCapture capture = new VideoCapture(0);//0 mean your default web cam
         //   VideoCapture capture = new VideoCapture("C:\\Users\\ccs\\Desktop\\videoplayback.mp4");
                MatOfRect rostros = new MatOfRect();
                MatOfByte mem = new MatOfByte();
                
                Mat frame = new Mat();
                Mat frame_gray = new Mat();
                
                Rect[] facesArray;      
                Graphics g;
                BufferedImage buff = null;

                
                while(capture.read(frame)){
                    if(frame.empty()){
                        System.out.println("No detection");
                        break;
                    }else{
                        try {
                            g = jPanel1.getGraphics();
                            Imgproc.cvtColor(frame, frame_gray, Imgproc.COLOR_BGR2GRAY);
                            Imgproc.equalizeHist(frame_gray, frame_gray);
                            double w = frame.width();
                            double h = frame.height();
                            faceDetector.detectMultiScale(frame_gray, rostros, 1.1, 2, CASCADE_SCALE_IMAGE, new Size(30, 30), new Size(w, h) );
                            facesArray = rostros.toArray();
                            System.out.println("No of faces: "+facesArray.length);
                         
                            for (Rect facesArray1 : facesArray) {
                                Point center = new Point(facesArray1.x + facesArray1.width * 0.5, facesArray1.y + facesArray1.height * 0.5);
                                Imgproc.ellipse(frame, center, new Size(facesArray1.width * 0.5, facesArray1.height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);
                                Mat faceROI = frame_gray.submat(facesArray1);
                                Imgproc.rectangle(frame, new Point(facesArray1.x, facesArray1.y), new Point(facesArray1.x + facesArray1.width, facesArray1.y + facesArray1.height), new Scalar(123, 213, 23, 220));
                                //  Imgproc.putText(frame, "Width: "+faceROI.width()+" Height: "+faceROI.height()+" X = "+facesArray[i].x+
                                //         " Y = "+facesArray[i].y, new Point(facesArray[i].x, facesArray[i].y-20), 1, 1, new Scalar(255,255,255));
                                Imgproc.putText(frame, "This is human ", new Point(facesArray1.x, facesArray1.y - 20), 1, 1, new Scalar(255,255,255));
                            }
                           
                            int no= facesArray.length;
                            lblnumber.setText(String.valueOf(no));
                            
                             Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            buff = (BufferedImage) im;
                            if(g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null)){
                            }
                            
                            
                            
                        } catch (IOException ex) {
                   
                        }
                    }
                    
                }
            }
            
        }).start();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblnumber;
    // End of variables declaration//GEN-END:variables
}
