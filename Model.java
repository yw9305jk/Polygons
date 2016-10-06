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
import java.text.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;
/**
 * Class: ICS 372-01
 * @author  Hewan Redie, Kou Vang , Mark Scherr and Txeu Thao
 *          Project Name: Project #3
 *          Last modified: 12/04/2015 
 *          Instructor Habtamu Bogale
 */

/**
 * The model in the MVC pattern.
 * Stores all items organized as two lists.
 *
 */
public class Model extends Observable {
  private Vector itemList;
  private Vector selectedList;
  //  list of "currently selected" items
  private static UIContext uiContext;
  private static View view;
  /**
   * Creates the two lists, one to store items not selected
   * and the other to store selected items
   */
  public Model() {
    itemList = new Vector();
    selectedList = new Vector();
  }
  /**
   * Stores the UI context
   * @param uiContext the UI context to be used
   */
  public static void setUI(UIContext uiContext) {
    Model.uiContext = uiContext;
    Item.setUIContext(uiContext);
  }
  /**
   * Sets the view; only one view can be used
   * @param view the view to be used
   */
  public static void setView(View view) {
    Model.view = view;
  }
  /**
   * Marks an item as selected by moving it to the
   * selceted list.
   * @param item the item that is selected
   */
  public void markSelected(Item item) {
    if (itemList.contains(item)) {
      itemList.remove(item);
      selectedList.add(item);
      System.out.println(item + " selected.");
      updateView();
    }
  }
  /**
  * Marks an item as unselected by moving it from the
  * selceted list.
  * @param item the item that is unselected
  */
  public void unSelect(Item item) {
    if (selectedList.contains(item)) {
      selectedList.remove(item);
      itemList.add(item);
      updateView();
    }
  }
  /**
   * Deletes the selected items.
   */
  public void deleteSelectedItems() {
    selectedList.removeAllElements();
    updateView();
  }
  /**
   * Stores a new item
   * @param item the new item
   */
  public void addItem(Item item) {
    itemList.add(item);
      updateView();
  }
  /**
   * Removes an item
   * @param item the item to be removed
   */
  public void removeItem(Item item) {
    itemList.remove(item);
      updateView();
  }
  /**
   * Returns an enumeration of the unselected items
   * @return enumeration of the unselected items
   */
  public Enumeration getItems() {
    return itemList.elements();
  }
  /**
   * Notifies the view
   */
  public void updateView() {
    setChanged();
    notifyObservers(null);
  }
  /**
   * Returns an enumeration of the selected items
   * @return enumeration of the selected items
   */
  public Enumeration getSelectedItems() {
    return selectedList.elements();
  }
  /** 
   * Saves the items in the specified file
   * @param fileName file to be used for storing
   */
  public void save(String fileName) {
    try {
      FileOutputStream file = new FileOutputStream(fileName);
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(itemList);
      output.writeObject(selectedList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  /** 
   * Retrieves the items in the specified file
   * @param fileName file that contains the items 
   */
  public void retrieve(String fileName) {
    try {
      FileInputStream file = new FileInputStream(fileName);
      ObjectInputStream input = new ObjectInputStream(file);
      itemList = (Vector) input.readObject();
      selectedList = (Vector) input.readObject();
      Item.setUIContext(uiContext);
      updateView();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
}
