package xyz.sqlskid.skirtframework.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputListener {

    void onKeyPress(KeyEvent event);
    void onKeyRelease(KeyEvent event);
    void onKeyType(KeyEvent event);
    void onMouseClick(MouseEvent event);
    void onMousePress(MouseEvent event);
    void onMouseRelease(MouseEvent event);
    void onMouseEnter(MouseEvent event);
    void onMouseExit(MouseEvent event);
    void onMouseDrag(MouseEvent event);
    void onMouseMove(MouseEvent event);
}
