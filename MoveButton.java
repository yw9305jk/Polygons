/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * This class creates a button to move shapes
 *
 */
public class MoveButton extends JButton implements ActionListener { 
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private MouseHandler motionHandler;
    private Point startPoint;
    private Item selectedItem;
 
    /**
     * Constructor that Creates a MoveButton.
     * @param jFrame and JPanel
     */
    public MoveButton(View jFrame, JPanel jPanel) {
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
    }    
    
    /**
     * Perform functionality when the button is clicked.
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
        drawingPanel.addMouseMotionListener(motionHandler = new MouseHandler());
    }
    /**
     * This functionality will move the selected item. 
     * mouse pressed, dragged and released. 
     */
    
    private class MouseHandler extends MouseAdapter {
    	/** This method will find the Item the user has clicked on
         * if there is shape at the place the mouse clicked then it will start dragging it
         */
        public void mousePressed(MouseEvent event) {
            Controller.instance().setFirstPoint(View.mapPoint(event.getPoint()));
            startPoint = event.getPoint();
            selectedItem = Controller.instance().getSelectedItem(event.getPoint());
        }
        /** This method will move the item while the mouse is being
         * dragged
         */
        public void mouseDragged(MouseEvent event) {
            Controller.instance().moveSelectedItem(startPoint, event.getPoint(), selectedItem);
            startPoint = event.getPoint(); 
        }
        /**
         * User has released the mouse.  Move the dragged shape, then set
         * shapeBeingDragged to null to indicate that dragging is over.
         * If the shape lies completely outside the canvas, remove it
         * from the list of Item.
         * Remove listener to the next action 
         */
        public void mouseReleased(MouseEvent event) {
            drawingPanel.removeMouseListener(this);
            drawingPanel.removeMouseMotionListener(motionHandler);
        }
        
    }     
    
}
