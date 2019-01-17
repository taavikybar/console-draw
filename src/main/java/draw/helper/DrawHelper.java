package draw.helper;

import draw.controller.CanvasController;
import draw.constants.Characters;
import draw.model.Canvas;

public class DrawHelper
{
    public static void draw( CanvasController canvasController )
    {
        Canvas canvas = canvasController.canvas;

        if (canvas == null) {
            throw new RuntimeException( "canvas is not set" );
        }

        int height = canvas.getHeight();
        int width = canvas.getWidth();

        for ( int row = 0; row <= height + 1; row++ )
        {
            StringBuilder toPrint = new StringBuilder();
            for ( int col = 0; col <= width + 1; col++ )
            {
                if ( row == 0 || row == height + 1 )
                {
                    toPrint.append( Characters.HORIZONTAL_EDGE );
                }
                else if ( col == 0 || col == width + 1 )
                {
                    toPrint.append( Characters.VERTICAL_EDGE );
                }
                else
                {
                    toPrint.append( CharacterHelper.getCharacter( canvasController, row, col ) );
                }
            }
            TerminalHelper.print( toPrint.toString() );
        }
    }
}
