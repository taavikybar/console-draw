package draw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import draw.helper.CanvasHelper;
import draw.helper.DrawHelper;
import draw.helper.FreeAreaHelper;
import draw.helper.ShapeHelper;
import draw.model.Canvas;
import draw.model.FillOptions;
import draw.model.FreeArea;
import draw.model.Point;
import draw.model.Shape;

public class CanvasController
{
    public Canvas canvas;

    public List<Shape> shapes = new ArrayList<>();

    public List<FillOptions> fillOptions = new ArrayList<>();

    public List<FreeArea> freeAreas = new ArrayList<>();

    public List<Point> freePoints = new ArrayList<>();

    public List<Shape> getRectangles()
    {
        return this.shapes.stream()
                .filter( Shape::isRectangle )
                .collect( Collectors.toList() );
    }

    public List<Shape> getLines()
    {
        return this.shapes.stream()
                .filter( s -> !s.isRectangle() )
                .collect( Collectors.toList() );
    }

    void setCanvas( Canvas canvas )
    {
        CanvasHelper.checkIfAllShapesAreInsideCanvas( this, canvas );
        this.canvas = canvas;
        FreeAreaHelper.calculateFreeAreas( this );
    }

    void addShape( Shape shape )
    {
        CanvasHelper.checkIfCanvasIsSet( this.canvas );
        CanvasHelper.checkIfShapeIsInsideCanvas( shape, this.canvas );
        ShapeHelper.checkIfShapeIsOverlappingAnyOtherShape( shape, this.shapes );

        this.shapes.add( shape );
        FreeAreaHelper.calculateFreeAreas( this );
    }

    void addFillOptions( FillOptions fillOptions )
    {
        CanvasHelper.checkIfCanvasIsSet( this.canvas );
        CanvasHelper.checkIfPointIsInsideCanvas( this.canvas, fillOptions.getPoint() );
        ShapeHelper.checkIfPointIsOnLineOrEdge( this.shapes, fillOptions.getPoint() );

        Shape rectangleToBeFilled = this.getRectangles().stream()
                .filter( r -> ShapeHelper.isPointInsideRectangle( r, fillOptions.getPoint() ) )
                .findFirst()
                .orElse( null );

        fillOptions.setRectangleToBeFilled( rectangleToBeFilled );

        this.fillOptions.add( fillOptions );
    }

    void draw()
    {
        DrawHelper.draw( this );
    }

    void clear()
    {
        this.shapes = new ArrayList<>();
        this.fillOptions = new ArrayList<>();
    }
}
