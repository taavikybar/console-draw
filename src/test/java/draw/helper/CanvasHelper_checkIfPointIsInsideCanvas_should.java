package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Canvas;
import draw.model.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CanvasHelper_checkIfPointIsInsideCanvas_should
{
    private static final Canvas canvas = Canvas.builder().width( 10 ).height( 10 ).build();

    public Object[] pointParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 11 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 1 ).y( 11 ).build() }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "pointParameters")
    public void throw_runtime_exception_if_point_is_outside_of_canvas( Point point )
    {
        CanvasHelper.checkIfPointIsInsideCanvas( canvas, point );
    }

    @Test
    public void not_throw_error_if_point_is_inside_of_canvas()
    {
        // given
        Point point = Point.builder().x( 2 ).y( 1 ).build();

        // when
        CanvasHelper.checkIfPointIsInsideCanvas( canvas, point );
    }
}
