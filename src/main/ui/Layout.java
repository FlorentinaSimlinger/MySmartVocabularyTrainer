package ui;

import javafx.scene.control.Label;

public abstract class Layout {
    Label label;
    Button button

    //für verschiedene layouts: abstract class machen mit subclasses HBOX, VBOX etc. ODER Type als Feld
    // wenn nested: array von layouts einfügen

    //profileLayout extends Layout

    public Layout(String label, String button, String button){
        //beinhaltet sachen die alle layouts haben
    }

    public Layout(){

    }

    public void addButton(String buttonString) {

    }

    public void onSearch(...) {
        //event on mouse click
    }
}
