package application.shapes;

import application.Point;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

abstract class Shape implements IShape {
    // Data
    protected PaintCanvasBase paintCanvas;
    protected Point pointTopLeft;
    protected int width;
    protected int height;
    protected ShapeColor primaryColor;
    protected ShapeColor secondaryColor;
    protected ShapeShadingType shadingType;
    protected MouseMode mouseMode;
}