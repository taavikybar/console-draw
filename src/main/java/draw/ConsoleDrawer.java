package draw;

import draw.constants.Characters;
import draw.controller.CommandController;
import draw.helper.TerminalHelper;

public class ConsoleDrawer
{
    public static void main( String[] args )
    {
        askForCommand( "Welcome!\nType help or H for list of commands or enter command:" );
    }

    private static void askForCommand( String message )
    {
        String command = TerminalHelper.getInput( message );

        if ( !command.isEmpty() )
        {
            CommandController.processCommand( command.split( Characters.COMMAND_DELIMITER ) );
        }

        askForCommand( "enter command:" );
    }
}
