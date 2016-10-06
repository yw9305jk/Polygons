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
import javax.swing.*;
import java.awt.event.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * Creates a button for selecting items.
 *
 */
public class SelectButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  /**
   * Creates the button
   * @param jFrame frame where the button lives
   * @param jPanel the panel where the items are
   */
  public SelectButton(View jFrame, JPanel jPanel) {
    super("Select");
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
  }
  /**
   * Handles the click by listening to mouse clicks
   * @param event the click event
   */
  public void actionPerformed(ActionEvent event) {
    drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
  }
  /**
   * Handles mouse clicks for selecting items
   *
   */
  private class MouseHandler extends MouseAdapter {
	  /**
	   * Handles the clicks
	   * @param event the click event
	   */
    public void mouseClicked(MouseEvent event) {
      Controller.instance().selectItem(View.mapPoint(event.getPoint()));
      drawingPanel.removeMouseListener(this);
    }
  }
}
