package draw.helper;

import draw.controller.CanvasController;
import draw.model.Canvas;
import draw.model.Point;
import draw.model.Shape;

public class CanvasHelper
{
    public static void checkIfCanvasIsSet( Canvas canvas )
    {
        if ( canvas == null )
        {
            throw new RuntimeException( "canvas is not set" );
        }

        if ( canvas.getWidth() == 0 || canvas.getHeight() == 0 )
        {
            throw new RuntimeException( "canvas size is not set" );
        }
    }

    public static void checkIfShapeIsInsideCanvas( Shape shape, Canvas canvas )
    {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if ( shape.getStartX() > width )
        {
            String error = String.format( "shape is out of bounds, x start %d is greater than canvas width %d", shape.getStartX(), width );
            throw new RuntimeException( error );
        }
        if ( shape.getStartY() > height )
        {
            String error = String.format( "shape is out of bounds, y start %d is greater than canvas height %d", shape.getEndY(), height );
            throw new RuntimeException( error );
        }
        if ( shape.getEndX() > width )
        {
            String error = String.format( "shape is out of bounds, x end %d is greater than canvas width %d", shape.getEndX(), width );
            throw new RuntimeException( error );
        }
        if ( shape.getEndY() > height )
        {
            String error = String.format( "shape is out of bounds, y end %d is greater than canvas height %d", shape.getEndY(), height );
            throw new RuntimeException( error );
        }
    }

    public static void checkIfAllShapesAreInsideCanvas( CanvasController canvasController, Canvas canvas )
    {
        try
        {
            canvasController.shapes
                    .forEach( s -> checkIfShapeIsInsideCanvas( s, canvas ) );
        }
        catch ( RuntimeException e )
        {
            throw new RuntimeException( "cannot resize canvas, some shapes would be cropped" );
        }
    }

    public static void checkIfPointIsInsideCanvas( Canvas canvas, Point point )
    {
        if ( point.getX() > canvas.getWidth() || point.getY() > canvas.getHeight() )
        {
            throw new RuntimeException( "point is outside of canvas" );
        }
    }

}
