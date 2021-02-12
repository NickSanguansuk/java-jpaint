package application.observers;

import application.Point;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeRepository implements ISubject {
    // Data
    private static List<IShape> mainShapeList = new ArrayList<>();
    private static List<IShape> selectedShapeList = new ArrayList<>();

    private List<IObserver> observerList = new ArrayList<>();

    // Constructors
    public ShapeRepository() {
    }

    // Methods
    // public Methods
    @Override
    public void registerObserver(IObserver _observer) {
        this.observerList.add(_observer);
    }

    @Override
    public void removeObserver(IObserver _observer) {
        this.observerList.remove(_observer);
    }

    public void add(IShape _shape)
    {
        this.mainShapeList.add(_shape);

        this.reDrawAllShapes();

        System.out.println("add() - mainShapeList size: " + this.mainShapeList.size());
    }

    public void remove(IShape _shape) {
        if (this.mainShapeList.contains(_shape)) {
            this.mainShapeList.remove(_shape);
        }

        this.reDrawAllShapes();

        System.out.println("remove() - mainShapeList size: " + this.mainShapeList.size());
    }

    // static
    public static void setMainShapeList(List<IShape> _shapeList) {
        ShapeRepository.mainShapeList.clear();

        for (IShape s : _shapeList) {
            ShapeRepository.mainShapeList.add(s);
        }
    }

    // static
    public static List<IShape> getMainShapeList() {
        return ShapeRepository.mainShapeList;
    }

    // static
    public static void setSelectedShapeList(List<IShape> _shapeList) {
        ShapeRepository.selectedShapeList.clear();

        for (IShape s : _shapeList) {
            ShapeRepository.selectedShapeList.add(s);
        }
    }

    // static
    public static List<IShape> getSelectedShapeList() {
        return ShapeRepository.selectedShapeList;
    }

    public void updateMainShapeListForMove(int _deltaX, int _deltaY) {
        // This loop will also update the mainShapeList
        for (IShape s : this.selectedShapeList) {
            s.translateAllPoint(_deltaX, _deltaY);
        }

        reDrawAllShapes();

        System.out.println("updateForMove() - mainShapeList size: " + this.mainShapeList.size());
        System.out.println("updateForMove() - selectedShapeList size: " + this.selectedShapeList.size());
    }

    public void updateSelectedShapeListForCollision(Point _topLeftCollision, Point _bottomRightCollision) {
        this.clearSelectedShapeList();

        for (IShape s : this.mainShapeList) {
            if (this.checkCollision(s, _topLeftCollision, _bottomRightCollision)) {
                System.out.println("Collided");
                this.selectedShapeList.add(s);
            } else {
                System.out.println("Not collided");
            }
        }

        System.out.println("updateForCollision() - selectedShapeList size: " + this.selectedShapeList.size());
    }

    public void reDrawAllShapes() {
        this.notifyObservers();
    }

    // private Methods
    private void notifyObservers() {
        for (IObserver observer : this.observerList) {
            observer.update();
        }
    }

    private void clearSelectedShapeList() {
        this.selectedShapeList.clear();
    }

    private boolean checkCollision(IShape shape, Point _topLeftCollision, Point _bottomRightCollision) {
        return shape.getTopLeftPoint().getX() < _bottomRightCollision.getX()
                && shape.getBottomRightPoint().getX() > _topLeftCollision.getX()
                && shape.getTopLeftPoint().getY() < _bottomRightCollision.getY()
                && shape.getBottomRightPoint().getY() > _topLeftCollision.getY();
    }
}
