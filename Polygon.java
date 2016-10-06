/**
 * 
 * @author Txeu Thao, Kou Yang , Hewan Redie, Mark Scherr
 * 
 * 
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.awt.*;
import java.util.ArrayList;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * Represents a polygon
 *
 */
public class Polygon extends Item {
  private ArrayList<Point> pointList = new ArrayList<Point>(); //Array to hold the points
  private ArrayList<Line> polygonList = new ArrayList<Line>(); //Array to hold the lines
  private Line line;
  private Point point1;
  private Point point2;
  private Boolean includes = false;
  
  /**
   * Creates a polygon with no specific endpoints
   */
  public Polygon() {
  }
  /**
   * Checks whether the given point falls within the polygon endpoints
   * @return true iff the given point is close to one of the endpoints
   */
  public boolean includes(Point point) {
      //Name the outer loop
      outerloop:
      for (int i = 0; i < pointList.size(); i++){
          if(distance(point, pointList.get(i)) < 10.0){
              includes = true;      
              if (includes == true){
                  //If point is found, break out of loop
                  break outerloop;
              }
          }
          else{
              includes = false;
          }
      }
      return includes;
  }
  /**
   * Displays the polygon
   */
  public void render() {
    uiContext.draw(this);
  }
  /**
   * Adds the point to the pointList array
   * @param point adds the point
   */
  public void setPoint(Point point) {
    pointList.add(point);
  }
  /**
   * Returns the ArrayList pointList
   * @return an ArrayList of points, pointList
   */
  public ArrayList getPoint() {
    return pointList;
  }
  /**
   * Sets one of the lines
   * @param line a line
   */
  public void setLine(Line line1) {
    line = line1;
  }
  /**
   * Adds a line to the polygonList
   * @param line a line
   */
  public void addLine(Line line) {
    polygonList.add(line);
  }
  /**
   * Returns the polygonList
   * @return an ArrayList of lines
   */
  public ArrayList getPolygonList() {
    return polygonList;
  }
  /**
   * Returns a string representation of the polygon
   * @return a string representation
   */
  public String toString() {
    return "Polygon with lines " + polygonList;
  }
  /**
   * Moves the polygon to the x and y coordinate
   * @param double x, double y the coordinates to move
   */
  public void moveBy(double x, double y) {
      //Iterates through the pointList and moves each point to x and y
      for (int i = 0; i < pointList.size(); i++)
	  pointList.get(i).translate((int)x, (int)y);
      }
}