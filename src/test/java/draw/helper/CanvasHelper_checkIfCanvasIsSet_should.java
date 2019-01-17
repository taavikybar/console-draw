package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Canvas;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CanvasHelper_checkIfCanvasIsSet_should
{
    public Object[] canvasParameters()
    {
        return new Object[] {
                new Object[] { null },
                new Object[] { Canvas.builder().build() },
                new Object[] { Canvas.builder().height( 10 ).build() },
                new Object[] { Canvas.builder().width( 10 ).build() }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "canvasParameters")
    public void throw_runtime_exception_if_canvas_is_not_set_or_width_or_height_is_zero( Canvas canvas )
    {
        CanvasHelper.checkIfCanvasIsSet( canvas );
    }

}
