package dce;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import shared.*;

public class MainMenu {
  MenuBar menuBar;
  View view;
  public MainMenu(View v) {
    view=v;
    prepMainMenu();
    } /* end Board() */

  public MenuBar getMenuBar() {
    return menuBar;
    }
  public void prepMainMenu() {
    MenuItem m;
    Menu menu;
    Menu b,c;
    menuBar=new MenuBar();

    // File menu
    menu=new Menu("File");
    m=new MenuItem("New");
    m.addActionListener(view);
    menu.add(m);
    menu.addSeparator();
    m=new MenuItem("Load");
    m.addActionListener(view);
    menu.add(m);
    menu.addSeparator();
    m=new MenuItem("Save");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Save As...");
    m.addActionListener(view);
    menu.add(m);
    menu.addSeparator();
    m=new MenuItem("Exit");
    m.addActionListener(view);
    menu.add(m);
    menuBar.add(menu);

    // Circuit menu
    menu=new Menu("Circuit");
    m=new MenuItem("Run");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Status");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Check");
    m.addActionListener(view);
    m.setActionCommand("check");
    menu.add(m);
    m=new MenuItem("Scope");
    m.addActionListener(view);
    menu.add(m);
    menuBar.add(menu);

    // Part menu
    menu=readMenu("Part","PartMenu.txt","C");
    m=new MenuItem("Label");
    m.addActionListener(view);
    menu.add(m);
    menuBar.add(menu);

    // Data menu
    menu=readMenu("Data","DataMenu.txt","D");
    menuBar.add(menu);

    // Wire menu
    menu=new Menu("Wire");
    m=new MenuItem("White");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Black");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Blue");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Red");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Magenta");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Green");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Yellow");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Orange");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Pink");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Cyan");
    m.addActionListener(view);
    m.setActionCommand("cyan");
    menu.add(m);
    m=new MenuItem("Gray");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Dark Red");
    m.addActionListener(view);
    m.setActionCommand("dark Red");
    menu.add(m);
    m=new MenuItem("Dark Green");
    m.addActionListener(view);
    m.setActionCommand("dark Green");
    menu.add(m);
    m=new MenuItem("Dark Blue");
    m.addActionListener(view);
    m.setActionCommand("dark Blue");
    menu.add(m);
    m=new MenuItem("Dark Cyan");
    m.addActionListener(view);
    m.setActionCommand("dark Cyan");
    menu.add(m);
    m=new MenuItem("Dark Yellow");
    m.addActionListener(view);
    m.setActionCommand("dark Yellow");
    menu.add(m);
    menuBar.add(menu);

    // Set menu
    menu=new Menu("Set");
    // Background submenu
    b=new Menu("Background");
    m=new MenuItem("BG Normal");
    m.addActionListener(view);
    b.add(m);
    m=new MenuItem("BG Dark");
    m.addActionListener(view);
    b.add(m);
    m=new MenuItem("BG Light");
    m.addActionListener(view);
    b.add(m);
    menu.add(b);
    // Zoom submenu
    b=new Menu("Zoom");
    m=new MenuItem("Zoom Normal");
    m.addActionListener(view);
    b.add(m);
    menu.add(b);
    menuBar.add(menu);

//    window.setMenuBar(menuBar);
    // Help menu
    menu=new Menu("Help");
    m=new MenuItem("General Help");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Parts by Category");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Parts Sorted");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("Rom Files");
    m.addActionListener(view);
    menu.add(m);
    m=new MenuItem("PLD Files");
    m.addActionListener(view);
    menu.add(m);
    // DCE Tutorial submenu
    b=new Menu("DCE Tutorial");
    m=new MenuItem("First Circuit");
    m.addActionListener(view);
    b.add(m);
    m=new MenuItem("Second Circuit");
    m.addActionListener(view);
    b.add(m);
    m=new MenuItem("Third Circuit");
    m.addActionListener(view);
    b.add(m);
    menu.add(b);
    // Electronics Tutorial submenu
    b=new Menu("Electronics Tutorial");
    // Electronics subsubmenu
    c=new Menu("Electronics");
    m=new MenuItem("Electricity");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Resistors");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Capacitors");
    m.addActionListener(view);
    m.setActionCommand("capacitors");
    c.add(m);
    m=new MenuItem("Inductors");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Diodes");
    m.addActionListener(view);
    m.setActionCommand("diodes");
    c.add(m);
    m=new MenuItem("Transistors");
    m.addActionListener(view);
    c.add(m);
    b.add(c);
    // Digital Electronics subsubmenu
    c=new Menu("Digital Electronics");
    m=new MenuItem("Basics");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Building Blocks");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Flip Flops");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Counters");
    m.addActionListener(view);
    m.setActionCommand("counters");
    c.add(m);
    m=new MenuItem("Shift Registers");
    m.addActionListener(view);
    c.add(m);
    m=new MenuItem("Decoders");
    m.addActionListener(view);
    m.setActionCommand("decoders");
    c.add(m);
    m=new MenuItem("Multiplexers");
    m.addActionListener(view);
    c.add(m);
    b.add(c);
    menu.add(b);
    m=new MenuItem("About");
    m.addActionListener(view);
    menu.add(m);
    menuBar.add(menu);
    }

  private Menu readMenu(String title,String filename,String prefix) {
    Menu[] menus=new Menu[10];
    int    menuCount,j;
    Menu parts=new Menu(title);
    MenuItem m;
    StringBuffer buffer=new StringBuffer(0);
    StringBuffer buffer2=new StringBuffer(0);
    StringBuffer buffer3=new StringBuffer(0);
    try {
      MyBufferedInputStream file=
        new MyBufferedInputStream(getClass().getResourceAsStream(filename));
      menuCount=0;
      while (file.ready()) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        j=0;
        while (j<buffer.length() && buffer.charAt(j)==' ') j++;
        buffer2.setLength(0);
        while (j<buffer.length() && buffer.charAt(j)!=' ')
          buffer2.append(buffer.charAt(j++));
        if ("MENU".equals(buffer2.toString())) {
          j++;
          buffer2.setLength(0);
          while (j<buffer.length() && buffer.charAt(j)==' ') j++;
          buffer2.setLength(0);
          while (j<buffer.length() && buffer.charAt(j)!='{')
            buffer2.append(buffer.charAt(j++));
          menus[menuCount++]=new Menu(buffer2.toString());
          }
        else if ("ITEM".equals(buffer2.toString())) {
          j++;
          buffer2.setLength(0);
          while (j<buffer.length() && buffer.charAt(j)==' ') j++;
          buffer2.setLength(0);
          while (j<buffer.length() && buffer.charAt(j)!=' ')
            buffer2.append(buffer.charAt(j++));
          while (j<buffer.length() && buffer.charAt(j)==' ') j++;
          buffer3.setLength(0);
          buffer3.append(buffer.toString().substring(j,buffer.length()));
          m=new MenuItem(buffer3.toString());
          m.addActionListener(view);
          m.setActionCommand(prefix+buffer2.toString());
          if (menuCount==0) parts.add(m); else menus[menuCount-1].add(m);
          }
        else if ("----".equals(buffer2.toString())) {
          if (menuCount>0)
            menus[menuCount-1].addSeparator();
          else parts.addSeparator();
          }
        else if ("}".equals(buffer2.toString())) {
          if (menuCount>0) {
            menuCount--;
            if (menuCount==0) parts.add(menus[0]);
              else menus[menuCount-1].add(menus[menuCount]);
            }
            else System.out.println("Menu Format Error");
          }
        }
      file.close();
      } catch (IOException e) {
      }
    return parts;
    }
  }
