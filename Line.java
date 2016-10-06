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
import java.awt.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * Represents a line
 *
 */
public class Line extends Item {
  private Point point1;
  private Point point2;
  private Boolean selected = false;
  
  /**
   * Creates a line with the given endpoints
   * @param point1 one endpoint
   * @param point2 another endpoint
   */
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  /**
   * Creates a line with no specific endpoints
   */
  public Line() {
  }
  /**
   * Checks whether the given point falls within the line
   * @return true iff the given point is close to one of the endpoints
   */
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  /**
   * Displays the line
   */
  public void render() {
    uiContext.draw(this);
  }
  /**
   * Sets one of the endpoints
   * @param point an endpoint
   */
  public void setPoint1(Point point) {
    point1 = point;
  }
  /**
   * Sets one of the endpoints
   * @param point an endpoint
   */
  public void setPoint2(Point point) {
    point2 = point;
  }
  /**
   * Returns one of the endpoints
   * @return an endpoint
   */
  public Point getPoint1() {
    return point1;
  }
  /**
   * Returns one of the endpoints
   * @return an endpoint
   */
  public Point getPoint2() {
    return point2;
  }
  /**
   * Returns a string representation of the line
   * @return a string representation
   */
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
  /** 
   * Method to move the line
   * @param double x, double y coordinates to move
   */
  public void moveBy(double x, double y) {
          point1.translate((int)x, (int)y);
          point2.translate((int)x, (int)y);
}

}