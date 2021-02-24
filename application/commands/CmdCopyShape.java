package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class CmdCopyShape implements ICommand {
    // Data
    private final ShapeRepository shapeRepo;
    private final int offset;

    // Constructors
    public CmdCopyShape(ShapeRepository _shapeRepo) {
        this.shapeRepo = _shapeRepo;
        this.offset = 60;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCopyShape");

        List<IShape> tmpList = new ArrayList<>();

        for (IShape s : this.shapeRepo.getSelectedShapeList()) {
            ShapeInfo shapeInfo = new ShapeInfo(s.getShapeInfo());
            IShape shape;

            if (shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
                shape = ShapeFactory.createShapeEllipse(shapeInfo);
            } else if (shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
                shape = ShapeFactory.createShapeRectangle(shapeInfo);
            } else {
                shape = ShapeFactory.createShapeTriangle(shapeInfo);
            }

            shape.translateAllPoint(this.offset, this.offset);

            tmpList.add(shape);
        }

        this.shapeRepo.setClipboardShapeList(tmpList);

        System.out.print("____________ - ");
        this.shapeRepo.printSizeOfAllList();
    }
}
