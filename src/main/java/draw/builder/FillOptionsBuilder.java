package draw.builder;

import draw.model.FillOptions;
import draw.model.Point;

public class FillOptionsBuilder
{
    public static FillOptions buildFillOptions( String xString, String yString, String fill )
    {
        try
        {
            int x = Integer.parseInt( xString );
            int y = Integer.parseInt( yString );

            if ( x < 1 || y < 1 )
            {
                throw new RuntimeException( "coordinate value has to be at least 1" );
            }

            if ( fill.isEmpty() )
            {
                throw new RuntimeException( "no fill character given" );
            }

            Point point = Point.builder().x( x ).y( y ).build();

            return FillOptions.builder()
                    .point( point )
                    .fill( fill )
                    .build();
        }
        catch ( NumberFormatException e )
        {
            throw new RuntimeException( "given arguments are not numbers" );
        }
    }
}
