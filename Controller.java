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
import java.awt.Point;
import java.util.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * The controller orchestrates the drawing program. It receives
 * requests from the user via the view and then transmits them appropriately
 * to the model.
 * 
 */
public class Controller {
  private static Model model;
  private Line line;
  private Label label;
  private Polygon polygon;
  private Oval oval;
  private static Controller controller;
  private int pointCount;
  private ArrayList pointList;
  private Point startPoint;
  /**
   * For singleton
   */
  private Controller() {
  }
  /**
   * Returns the instance of the controller
   * @return the instance
   */
  public static Controller instance() {
    if (controller == null) {
      controller = new Controller();
    }
    return controller;
  }
  /**
   * Sets the reference to the model
   * @param model the model
   */
  public static void setModel(Model model) {
    Controller.model = model;
  }
  /**
   * Constructs a line and sends the info to the model.
   * 
   */
  public void makeLine() {
    line = new Line();
    pointCount = 0;
    model.addItem(line);
  }
  /**
   * Stores one of the line endpoints.
   * @param point one of the two points
   */
  public void setLinePoint(Point point) {
    System.out.println("Line Click: " + (pointCount + 1));
    if (++pointCount == 1) {
      line.setPoint1(point);
    } else  {
      line.setPoint2(point);
    }
    model.updateView();
  }
  /**
   * Creates a label and informs the model.
   * @param point the start point
   */
  public void makeLabel(Point point) {
    pointCount = 0;
    System.out.println("Label Click: " + (pointCount + 1));
    label = new Label(point);
    model.addItem(label);
  }
  /**
   * Receives a character and accumulates it.
   * The model is asked to update the view.
   * @param character the typed in character
   */
  public void addCharacter(char character) {
    label.addCharacter(character);
    model.updateView();
  }
  /**
   * A command to remove a character. The model
   * will then update the view.
   * 
   */
  public void removeCharacter() {
    label.removeCharacter();
    model.updateView();
  }
  /**
   * Constructs a polygon and sends the info to the model
   * @param point the point
   */
  public void makePolygon() {
      polygon = new Polygon();
      pointCount = 0;
      model.addItem(polygon);;
  }
  /**
   * Sets the line for the polygons using clicks
   * @param point the points for the polygon
   */
  public void setPolygonLine(Point point) {
      System.out.println("Polygon Click: " + (pointCount + 1));
      polygon.setPoint(point);
      pointList = polygon.getPoint();
      int polygonSize = pointList.size();
      Line polygonLine;
      
      //Increment the pointCount, if it is at 1 initialize the first point
      if (++pointCount == 1){
          Point point1 = (Point) pointList.get(0);
      }
      
      //If the pointCount is at 2, draw the first line for the first two points
      else if (pointCount == 2){
          Point point1 = (Point) pointList.get(0);
          Point point2 = (Point) pointList.get(1);
          polygonLine = new Line(point1, point2);
          polygon.addLine(polygonLine);
      }
      
      //Else draw the line for the current point and the previous point
      else{
          Point point1 = (Point) pointList.get(polygonSize-2);
          Point point2 = (Point) pointList.get(polygonSize-1);
          polygonLine = new Line(point1, point2);
          polygon.addLine(polygonLine);
      }
      model.updateView();
  }
  /**
   * Constructs an oval and sends the info to the model.
   */
  public void makeOval(){
      oval = new Oval();
      pointCount = 0;
      model.addItem(oval);
  }
  /**
   * Stores one of the oval endpoints.
   * @param point
   */
  public void setOvalPoint(Point point){
      System.out.println("Oval click: " + (pointCount + 1));
      if ( ++pointCount == 1 ) {
          oval.setPoint1(point);
      } else {
          oval.setPoint2(point);
      }
      model.updateView();
  }
  /**
   * Given a point, see if any of the items contains it.
   * @param point the point
   */
  public void selectItem(Point point) {
    pointCount = 0;
    System.out.println("Select Click: " + (pointCount + 1));
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        break;
      }
    }
  }
  /**
   * Processes the command to delete the selected items.
   */
  public  void deleteItems() {
    model.deleteSelectedItems();
  }
  /**
   * Processes the command to open a file
   * @param fileName the name of the file
   */
  public void openFile(String fileName) {
    model.retrieve(fileName);
  }
  /**
   * 
   * Processes the command to close a file
   * @param fileName the name of the file
   */
  public void saveFile(String fileName) {
    model.save(fileName);
  }
  /**
   * Gets the selected item
   * @param point the point of the selected item
   * @return item 
   */
  public Item getSelectedItem(Point point) {
      Enumeration enumeration = model.getSelectedItems();
      
      while (enumeration.hasMoreElements()) {
          Item item = (Item)(enumeration.nextElement());
          if (item.includes(point)) {
          return item;
          }
      }
      return null;
  }
  /**
   *This method performs moving an item 
   * @param StartPoint, EndPoint, selectedItem - The start point, end point, and selected item
   */
  public void moveSelectedItem(Point StartPoint, Point EndPoint, Item selectedItem) {
      double x, y;
    
      //Item found perform the operation 
      if(selectedItem != null) {
        
          x = EndPoint.getX() - StartPoint.getX();
          y = EndPoint.getY() - StartPoint.getY();
          selectedItem.moveBy(x, y);
          model.updateView();
      }
  }
  /**
   * Saves the location of the first click in a move attempt.
   * @param point the location of the first click.
   */
  public void setFirstPoint(Point point){
      startPoint = point;
  }
  /**
   * Unselects the items
   */
  public void unselectItems() {
      Enumeration enumeration = model.getSelectedItems();
      while (enumeration.hasMoreElements()) {
          Item item = (Item) (enumeration.nextElement());
          model.unSelect(item);
      }
  }
}
