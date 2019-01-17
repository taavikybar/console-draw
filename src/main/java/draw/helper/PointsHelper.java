package draw.helper;

import java.util.ArrayList;
import java.util.List;

import draw.controller.CanvasController;
import draw.model.Point;

class PointsHelper
{
    static List<Point> calculateFreePoints( CanvasController canvasController )
    {
        List<Point> freePoints = new ArrayList<>();

        int height = canvasController.canvas.getHeight();
        int width = canvasController.canvas.getWidth();

        for ( int y = 1; y <= height; y++ )
        {
            for ( int x = 1; x <= width; x++ )
            {
                Point point = Point.builder().x( x ).y( y ).build();

                boolean isPointOnOrInsideShape = canvasController.shapes.stream()
                        .anyMatch( shape -> ShapeHelper.isPointOnOrInsideShape( shape, point ) );

                if ( !isPointOnOrInsideShape )
                {
                    freePoints.add( point );
                }
            }
        }

        freePoints.forEach( point -> setConnectingPoints( point, freePoints ) );

        return freePoints;
    }

    private static void setConnectingPoints( Point point, List<Point> allFreePoints )
    {
        allFreePoints.stream()
                .filter( p -> p.isConnecting( point ) )
                .forEach( p -> point.getConnectingPoints().add( p ) );
    }
}
