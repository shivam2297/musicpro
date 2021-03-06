/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trunk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import org.openide.util.ImageUtilities;

/**
 *
 * @author DELL
 */
public class stave extends javax.swing.JInternalFrame implements MouseListener{

    /** Creates new form stave */
    public stave() {
        initComponents();
        addMouseListener(this);
        
    }
       
    
      public void paint(Graphics g) {
        
        String imagePath = "res/G-clef.png";

        Image imgClef = ImageUtilities.loadImage(imagePath, true);

        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);

        // draw lines
        for (int i = 0; i < 5; i++) {
            
            g2.draw(new Line2D.Double(leftMargin, header + i * interspace, getWidth() - rightMargin, header + i * interspace));
        }
        int clefCenter = 60;

        // draw Clef
        g2.drawImage(imgClef, leftMargin, (header + 3 * interspace) - clefCenter, this);
        //g2.drawImage(imgClef, 0, 10, (ImageObserver) this);
        
        
        octave = notePitch/12;
        
        //drawnotes
        int pitchPos = header - noteCenter + (interspace / 2) * 3 + ((6 - octave) * ((interspace / 2) * 7)) - noteOffset[notePitch % 12];
        
        //g.drawImage(image,x,pitchPos,this);
        drawNote(g2, pitchPos,x);
        x += 20;

    }
      
      public void drawNote(Graphics2D g2, int y, int x) {
        /*if (symbolPrefix != null) {
            g2.drawImage(symbolPrefix, x - 6, (int) (y + 1.5 * interspace), this);
            x += 3;
        }*/
        System.out.println("x="+x);
        g2.drawImage(image, x,  y, this);
       
    }
      
      @Override
    public void mousePressed(MouseEvent e) {
        if (e.getY() < 0 || e.getY() > (header + interspace * 4 + footer)) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            // find note
            Double dpitch = refPitch + (header + refPos - e.getY()) / (interspace / 2);
            notePitch = dpitch.intValue();

            if (notePitch > refPitch) {
                int devPitch = notePitch - refPitch;
                if (devPitch < 15) // size of Array blackNotes
                {
                    notePitch += blackNotesAbove[devPitch];
                } else {
                    //StatusDisplayer.getDefault().setStatusText("Out of range");
                }
            } else {
                int devPitch = refPitch - notePitch;
                if (devPitch < 15) // size of Array blackNotes
                {
                    notePitch -= blackNotesBeneath[devPitch];
                } else {
                    //StatusDisplayer.getDefault().setStatusText("Out of range");
                }
            }
            System.out.println("event happened");
            repaint();
          
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

     private 
            int notePitch;
            static int x = 40;
            int interspace = 10;
            int header = 100, leftMargin = 20, rightMargin = 20, footer = 30;
            String img="res/quarter-note.png";
        String downpath = "res/quarter-note-down.png";
        Image image=ImageUtilities.loadImage(img,true);
        Image notedown = ImageUtilities.loadImage(downpath);
        Image clef = ImageUtilities.loadImage("G-clef.png");
        int noteCenter = 27;
        int octave;
        int[] noteOffset = {0, 0, interspace / 2, interspace / 2, interspace, (interspace / 2) * 3, (interspace / 2) * 3, interspace * 2, interspace * 2, (interspace / 2) * 5, (interspace / 2) * 5, interspace * 3};;
        private int[] blackNotesAbove = {0, 1, 2, 2, 3, 4, 5, 5, 6, 7, 7, 8, 9, 10, 10};
        private int[] blackNotesBeneath = {0, 0, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10};
        int refPitch = 72;
        double refPos = interspace * 1.5;

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
