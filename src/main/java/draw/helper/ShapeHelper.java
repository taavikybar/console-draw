package draw.helper;

import java.util.List;

import draw.model.Point;
import draw.model.Shape;

public class ShapeHelper
{

    static boolean isPointOnLine( Shape shape, Point point )
    {
        int x = point.getX();
        int y = point.getY();

        if ( shape.isHorizontal() )
        {
            boolean isPointOnSameColumns = shape.getStartX() <= x && shape.getEndX() >= x;
            boolean isPointOnSameRow = shape.getStartY() == y;

            return isPointOnSameColumns && isPointOnSameRow;
        }
        else
        {
            boolean isPointOnSameColumn = shape.getStartX() == x;
            boolean isPointOnSameRows = shape.getStartY() <= y && shape.getEndY() >= y;

            return isPointOnSameColumn && isPointOnSameRows;
        }
    }

    static boolean isPointOnRectangleEdge( Shape shape, Point point )
    {
        int x = point.getX();
        int y = point.getY();

        boolean isPointOnSameColumnAsVerticalEdges = x == shape.getStartX() || x == shape.getEndX();
        boolean isPointInsideShapeRows = y >= shape.getStartY() && y <= shape.getEndY();
        boolean isPointOnVerticalEdge = isPointOnSameColumnAsVerticalEdges && isPointInsideShapeRows;

        boolean isPointOnSameRowAsHorizontalEdges = y == shape.getStartY() || y == shape.getEndY();
        boolean isPointInsideShapeCols = x >= shape.getStartX() && x <= shape.getEndX();
        boolean isPointOnHorizontalEdge = isPointOnSameRowAsHorizontalEdges && isPointInsideShapeCols;

        return isPointOnVerticalEdge || isPointOnHorizontalEdge;
    }

    public static void checkIfPointIsOnLineOrEdge( List<Shape> shapes, Point point )
    {
        boolean isPointOnLineOrEdge = shapes.stream()
                .anyMatch( s -> isPointOnLine( s, point ) || isPointOnRectangleEdge( s, point ) );

        if ( isPointOnLineOrEdge )
        {
            throw new RuntimeException( "point is on line or rectangle edge" );
        }
    }

    public static boolean isPointInsideRectangle( Shape shape, Point point )
    {
        int x = point.getX();
        int y = point.getY();

        boolean isPointInsideVertically = y > shape.getStartY() && y < shape.getEndY();
        boolean isPointInsideHorizontally = x > shape.getStartX() && x < shape.getEndX();

        return isPointInsideVertically && isPointInsideHorizontally;
    }

    static boolean isPointOnOrInsideShape( Shape shape, Point point )
    {
        return isPointOnLine( shape, point )
                || isPointOnRectangleEdge( shape, point )
                || isPointInsideRectangle( shape, point );
    }

    public static void checkIfShapeIsOverlappingAnyOtherShape( Shape shape, List<Shape> shapes )
    {
        boolean isOverlapping = shapes.stream()
                .anyMatch( shape::isOverlapping );

        if ( isOverlapping )
        {
            throw new RuntimeException( "cannot create shape, shape overlaps another shape" );
        }
    }
}
