package com.example.diplomproject;
import javafx.scene.canvas.GraphicsContext;

public interface Action {
    String getName();
    void getRectangleInfo(double getX, double getY);
    void setRectangleInfo(double setX, double setY);
    void getInputValue(boolean[] input);
    void calculateOutput();
    void printFormula();
    boolean returnOutputValue();
    void drawElement(GraphicsContext gc, double canvasWidth, double canvasHeight);

}
