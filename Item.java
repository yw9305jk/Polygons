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
import java.io.*;
import java.awt.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * Superclass for all types of drawable objects.
 *
 */
public abstract class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	protected static UIContext uiContext;
	
  /**
   * Stores reference to the type of UI.
   * @param uiContext reference to the 
   */
	public static void setUIContext(UIContext uiContext) {
		Item.uiContext = uiContext;
	}
	/**
	 * Checks whether a point falls within the figure.
	 * @param point the point
	 * @return true iff the point is in the figure
	 */
	public abstract boolean includes(Point point);
	/**
	 * Calculates the distance between two points
	 * @param point1 one of the two points
	 * @param point2 one of the two points
	 * @return distance between the points
	 */
	protected double distance(Point point1, Point point2) {
		double xDifference = point1.getX() - point2.getX();
		double yDifference = point1.getY() - point2.getY();
		return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
	}
    /**
     * this performs a move by using values pass by the parameter .
     * 
     * @param x shift x direction
     * @param y shift y direction 
     */
    public abstract void moveBy(double x, double y);
	/**
	 * Draw the figure on the UI
	 */
	public  void render() {
		uiContext.draw(this);
	}
}