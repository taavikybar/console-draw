package draw.controller;

import java.util.Arrays;

import draw.builder.CanvasBuilder;
import draw.builder.FillOptionsBuilder;
import draw.builder.ShapeBuilder;
import draw.constants.Commands;
import draw.helper.TerminalHelper;

public class CommandController
{
    private static final CanvasController CanvasController = new CanvasController();

    public static void processCommand( String[] commands )
    {
        String mainCommand = commands[0];
        String[] subCommands = Arrays.copyOfRange( commands, 1, commands.length );

        try
        {
            switch ( mainCommand.toUpperCase() )
            {
            case Commands.DRAW_CANVAS:
                processDrawCanvasCommand( subCommands );
                break;
            case Commands.DRAW_LINE:
                processDrawLineCommand( subCommands );
                break;
            case Commands.DRAW_RECTANGLE:
                processDrawRectangleCommand( subCommands );
                break;
            case Commands.FILL_AREA:
                processFillAreaCommand( subCommands );
                break;
            case Commands.CLEAR:
                CanvasController.clear();
                CanvasController.draw();
                break;
            case Commands.HELP:
            case Commands.HELP_SHORT:
                printHelp();
                break;
            case Commands.QUIT:
                System.exit( 0 );
                break;
            default:
                throw new RuntimeException( "command not recognized, type help or H for all commands" );
            }
        }
        catch ( NullPointerException e )
        {
            TerminalHelper.print( "an error has occurred, type X to clear or Q to quit" );
            e.printStackTrace();
        }
        catch ( RuntimeException e )
        {
            TerminalHelper.print( e.getMessage() );
        }
    }

    private static void processDrawCanvasCommand( String[] subCommands )
    {
        checkSubCommands( subCommands, 2 );
        CanvasController.setCanvas( CanvasBuilder.buildCanvas( subCommands[0], subCommands[1] ) );
        CanvasController.draw();
    }

    private static void processDrawLineCommand( String[] subCommands )
    {
        checkSubCommands( subCommands, 4 );
        CanvasController.addShape( ShapeBuilder.buildLine( subCommands[0], subCommands[1], subCommands[2], subCommands[3] ) );
        CanvasController.draw();
    }

    private static void processDrawRectangleCommand( String[] subCommands )
    {
        checkSubCommands( subCommands, 4 );
        CanvasController.addShape( ShapeBuilder.buildRectangle( subCommands[0], subCommands[1], subCommands[2], subCommands[3] ) );
        CanvasController.draw();
    }

    private static void processFillAreaCommand( String[] subCommands )
    {
        checkSubCommands( subCommands, 3 );
        CanvasController.addFillOptions( FillOptionsBuilder.buildFillOptions( subCommands[0], subCommands[1], subCommands[2] ) );
        CanvasController.draw();
    }

    private static void checkSubCommands( String[] subCommands, int amount )
    {
        if ( subCommands.length != amount )
        {
            throw new RuntimeException( String.format( "wrong number of arguments given, expected %d", amount ) );
        }
    }

    private static void printHelp()
    {
        TerminalHelper.print( "Available commands are: \n\n"
                + "C w h -> create canvas with given size \n\n"
                + "L x1 y1 x2 y2 -> \ncreate line on the canvas by defining start and end points \n\n"
                + "R x1 y1 x2 y2 -> \ncreate rectangle on the canvas by defining start and end points \n\n"
                + "B x y c -> \nfill all the connected areas of given point with the given character \n\n"
                + "X -> clear the canvas \n"
                + "Q -> quit the program \n" );
    }
}
