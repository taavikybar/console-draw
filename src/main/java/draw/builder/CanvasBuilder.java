package draw.builder;

import draw.model.Canvas;

public class CanvasBuilder
{
    public static Canvas buildCanvas( String widthString, String heightString )
    {
        try
        {
            int width = Integer.parseInt( widthString );
            int height = Integer.parseInt( heightString );

            if ( width < 1 || height < 1 )
            {
                throw new RuntimeException( "canvas width and height have to be at least 1" );
            }

            if ( width > 40 || height > 40 )
            {
                throw new RuntimeException( "canvas width and height cannot be more than 40" );
            }

            return Canvas.builder()
                    .width( width )
                    .height( height )
                    .build();
        }
        catch ( NumberFormatException e )
        {
            throw new RuntimeException( "given arguments are not numbers" );
        }
    }
}
