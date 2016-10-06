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
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * The view of the MC architecture
 *
 */
public class View extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private UIContext uiContext;
	private JPanel drawingPanel;
	private JPanel buttonPanel;
	private JButton lineButton;
	private JButton labelButton;
	private JButton polygonButton;
	private JButton ovalButton;
	private JButton selectButton;
	private JButton deleteButton;
	private JButton saveButton;
	private JButton openButton;
	private JButton moveButton; 
    private String fileName;
    // other buttons to be added as needed;
    private static Model model;
    
    /**
     * Returns the UIContext
     * @return the UIContext
     */
    public UIContext getUI() {
    	return uiContext;
    }
    /**
     * Sets the UIContext
     * @param uiContext the UIContext to be used
     */
    private void setUI(UIContext uiContext) {
    	this.uiContext = uiContext;
    }
    /**
     * Sets the mode
     * @param model the model
     */
    public static void setModel(Model model) {
    	View.model = model;
    }
    /**
     * The panel where drawing occurs
     *
     */
    private class DrawingPanel extends JPanel {
    	private MouseListener currentMouseListener;
    	private KeyListener currentKeyListener;
    	private FocusListener currentFocusListener;
    	/**
    	 * Paints the panel
    	 * @param graphics the Graphics object
    	 */
    	@Override
    	public void paintComponent(Graphics graphics) {
    		model.setUI(NewSwingUI.getInstance());
    		super.paintComponent(graphics);
    		(NewSwingUI.getInstance()).setGraphics(graphics);
    		graphics.setColor(Color.BLUE);
    		Enumeration enumeration = model.getItems();
    		while (enumeration.hasMoreElements()) {
    			((Item) enumeration.nextElement()).render();
    		}
    		graphics.setColor(Color.RED);
    		enumeration = model.getSelectedItems();
    		while (enumeration.hasMoreElements()) {
    			((Item) enumeration.nextElement()).render();
    		}
    	}
    	/**
    	 * Makes the given object the listener to mouse events
    	 * @param newListener the new listener
    	 */
    	public void addMouseListener(MouseListener newListener) {
    		removeMouseListener(currentMouseListener);
    		currentMouseListener =  newListener;
    		super.addMouseListener(newListener);
    	}
    	/**
    	 * Makes the given object the listener to keyboard events
    	 * @param newListener the new listener
    	 */
    	public void addKeyListener(KeyListener newListener) {
    		removeKeyListener(currentKeyListener);
    		currentKeyListener =  newListener;
    		super.addKeyListener(newListener);
    	}
    	/**
    	 * When the component gains or loses the keyboard focus,
		 * the relevant method in the listener object is invoked
		 * @param newListener the new listener
    	 */
    	public void addFocusListener(FocusListener newListener) {
    		removeFocusListener(currentFocusListener);
    		currentFocusListener =  newListener;
    		super.addFocusListener(newListener);
    	}
    }
    /**
     * Remembers the file name for storing in the title
     * @param fileName file name
     */
    public void setFileName(String fileName) {
    	this.fileName = fileName;
    	setTitle("Drawing Program 1.1  " + fileName);
    }
    /**
     * returns the file name
     * @return file name
     */
    public String getFileName() {
    	return fileName;
    }
    /**
     * Initializes the view by creating all the widgets
     */
    public View() {
    	super("Drawing Program 1.1  Untitled");
    	fileName = null;
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent event) {
    			System.exit(0);
    		}
    	});
    	drawingPanel = new DrawingPanel();
    	buttonPanel = new JPanel();
    	
    	Container contentpane = getContentPane();
    	contentpane.add(buttonPanel, "North");
    	contentpane.add(drawingPanel);
    	
    	lineButton= new LineButton(this, drawingPanel);
    	labelButton = new LabelButton(this, drawingPanel);
    	polygonButton = new PolygonButton(this, drawingPanel);
    	ovalButton = new OvalButton(this, drawingPanel);
    	selectButton= new SelectButton(this, drawingPanel);
    	moveButton= new MoveButton(this, drawingPanel);
    	
    	deleteButton= new DeleteButton();
    	saveButton= new SaveButton(this);
    	openButton= new OpenButton(this);
    	
    	
    	buttonPanel.add(lineButton);
    	buttonPanel.add(labelButton);
    	buttonPanel.add(polygonButton);
    	buttonPanel.add(ovalButton);
    	buttonPanel.add(selectButton);
    	buttonPanel.add(moveButton);
    	buttonPanel.add(deleteButton);
    	buttonPanel.add(saveButton);
    	buttonPanel.add(openButton);
    	
    	this.setSize(700, 500);
    	this.setLocationRelativeTo(null);
    }
    /**
     * Catches updates from the model
     * @param model the source: it is the model
     * @param dummy: not used
     */
    public void update(Observable model, Object dummy) {
    	drawingPanel.repaint();
    }
    /**
	  * maps a point on the drawing panel to a point
	  * on the figure being created. 
	  * @param point the GUI point
	  * @return translated point
	  */
    public static Point mapPoint(Point point){
    	return point;
    }
}