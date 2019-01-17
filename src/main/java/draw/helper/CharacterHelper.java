package draw.helper;

import draw.constants.Characters;
import draw.controller.CanvasController;
import draw.model.FillOptions;
import draw.model.FreeArea;
import draw.model.Point;

class CharacterHelper
{
    static String getCharacter( CanvasController canvasController, int row, int col )
    {
        Point currentPoint = Point.builder().x( col ).y( row ).build();

        boolean pointIsOnLine = canvasController.getLines().stream()
                .anyMatch( l -> ShapeHelper.isPointOnLine( l, currentPoint ) );

        boolean pointIsOnRectangleEdge = canvasController.getRectangles().stream()
                .anyMatch( r -> ShapeHelper.isPointOnRectangleEdge( r, currentPoint ) );

        if ( pointIsOnLine || pointIsOnRectangleEdge )
        {
            return Characters.LINE;
        }

        boolean isPointIsInsideRectangle = canvasController.getRectangles().stream()
                .anyMatch( r -> ShapeHelper.isPointInsideRectangle( r, currentPoint ) );

        if ( isPointIsInsideRectangle )
        {
            return canvasController.fillOptions.stream()
                    .filter( o -> o.getRectangleToBeFilled() != null )
                    .filter( o -> ShapeHelper.isPointInsideRectangle( o.getRectangleToBeFilled(), currentPoint ) )
                    .map( FillOptions::getFill )
                    .findFirst()
                    .orElse( Characters.EMPTY );
        }

        FreeArea freeAreaWherePointBelongsTo = FreeAreaHelper.getFreeAreaWherePointBelongsTo( canvasController, currentPoint );

        if ( freeAreaWherePointBelongsTo != null )
        {
            return canvasController.fillOptions.stream()
                    .filter( o -> FreeAreaHelper.doesPointBelongToFreeArea( freeAreaWherePointBelongsTo, o.getPoint() ) )
                    .findFirst()
                    .map( FillOptions::getFill )
                    .orElse( Characters.EMPTY );
        }

        return Characters.EMPTY;
    }
}
