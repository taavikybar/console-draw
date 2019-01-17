package draw.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import draw.controller.CanvasController;
import draw.model.FreeArea;
import draw.model.Point;

public class FreeAreaHelper
{
    static FreeArea getFreeAreaWherePointBelongsTo( CanvasController canvasController, Point point )
    {
        return canvasController.freeAreas.stream()
                .filter( area -> doesPointBelongToFreeArea( area, point ) )
                .findFirst()
                .orElse( null );
    }

    static boolean doesPointBelongToFreeArea( FreeArea freeArea, Point point )
    {
        return freeArea.getPoints().stream()
                .anyMatch( p -> p.equals( point ) );
    }

    public static void calculateFreeAreas( CanvasController canvasController )
    {
        canvasController.freePoints = PointsHelper.calculateFreePoints( canvasController );

        List<FreeArea> freeAreas = new ArrayList<>();
        List<Point> freePoints = new ArrayList<>( canvasController.freePoints );

        addPointsToFreeArea( null, freePoints, freeAreas );

        canvasController.freeAreas = freeAreas;
    }

    private static void addPointsToFreeArea( Point point, List<Point> freePoints, List<FreeArea> freeAreas )
    {
        if ( point != null )
        {
            FreeArea freeAreaToAddTo = getFreeAreaWherePointBelongs( point, freeAreas );

            addToFreeArea( point, freeAreaToAddTo, freePoints );
        }

        Iterator<Point> freePointsIterator = freePoints.iterator();

        if ( freePointsIterator.hasNext() )
        {
            addPointsToFreeArea( freePointsIterator.next(), freePoints, freeAreas );
        }
    }

    private static void addToFreeArea( Point point, FreeArea freeAreaToAddTo, List<Point> freePoints )
    {
        if ( freePoints.contains( point ) )
        {
            freeAreaToAddTo.getPoints().add( point );

            freePoints.remove( point );

            point.getConnectingPoints().forEach( p -> addToFreeArea( p, freeAreaToAddTo, freePoints ) );
        }
    }

    private static FreeArea getFreeAreaWherePointBelongs( Point point, List<FreeArea> freeAreas )
    {
        FreeArea freeArea = freeAreas.stream()
                .filter( a -> doesAreaContainConnectingPoint( a, point ) )
                .findFirst()
                .orElse( null );

        if ( freeArea == null )
        {
            freeArea = FreeArea.builder().build();
            freeAreas.add( freeArea );
        }

        return freeArea;
    }

    private static boolean doesAreaContainConnectingPoint( FreeArea freeArea, Point point )
    {
        return freeArea.getPoints().stream()
                .anyMatch( p -> p.isConnecting( point ) );
    }

}
