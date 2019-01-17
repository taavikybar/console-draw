package draw.helper;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class TerminalHelper
{
    private final static TextIO textIO = TextIoFactory.getTextIO();

    private final static TextTerminal textTerminal = textIO.getTextTerminal();

    public static String getInput(String text)
    {
        return textIO.newStringInputReader()
                .read( text );
    }

    public static void print( String text )
    {
        textTerminal.println( text );
    }
}
