package draw.builder;

import draw.model.Shape;

public class ShapeBuilder
{
    public static Shape buildLine( String startXString, String startYString, String endXString, String endYString )
    {
        return buildShape( startXString, startYString, endXString, endYString, false );
    }

    public static Shape buildRectangle( String startXString, String startYString, String endXString, String endYString )
    {
        return buildShape( startXString, startYString, endXString, endYString, true );
    }

    private static Shape buildShape( String startXString, String startYString, String endXString, String endYString, boolean isRectangle )
    {
        try
        {
            int startX = Integer.parseInt( startXString );
            int startY = Integer.parseInt( startYString );
            int endX = Integer.parseInt( endXString );
            int endY = Integer.parseInt( endYString );
            boolean isHorizontal = startY == endY;
            boolean isVertical = startX == endX;

            if ( startX < 1 || startY < 1 || endX < 1 || endY < 1 )
            {
                throw new RuntimeException( "coordinate value has to be at least 1" );
            }

            if ( startX > endX || startY > endY )
            {
                throw new RuntimeException( "start point cannot be after end point" );
            }

            if ( !isRectangle && !isHorizontal && !isVertical )
            {
                throw new RuntimeException( "line cannot be diagonal" );
            }

            return Shape.builder()
                    .startX( startX )
                    .startY( startY )
                    .endX( endX )
                    .endY( endY )
                    .isHorizontal( isHorizontal )
                    .isRectangle( isRectangle )
                    .build();
        }
        catch ( NumberFormatException e )
        {
            throw new RuntimeException( "given arguments are not numbers" );
        }
    }
}
