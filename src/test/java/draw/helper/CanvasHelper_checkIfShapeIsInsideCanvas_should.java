package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Canvas;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CanvasHelper_checkIfShapeIsInsideCanvas_should
{
    private static final Shape shape1 = Shape.builder().startX( 11 ).startY( 1 ).endX( 12 ).endY( 9 ).build();

    private static final Shape shape2 = Shape.builder().startX( 1 ).startY( 11 ).endX( 2 ).endY( 12 ).build();

    private static final Shape shape3 = Shape.builder().startX( 1 ).startY( 2 ).endX( 11 ).endY( 3 ).build();

    private static final Shape shape4 = Shape.builder().startX( 1 ).startY( 2 ).endX( 2 ).endY( 12 ).build();

    private static final Shape shape5 = Shape.builder().startX( 1 ).startY( 2 ).endX( 2 ).endY( 2 ).build();

    private static final Canvas canvas = Canvas.builder().width( 10 ).height( 10 ).build();

    @Test
    public void not_throw_error_if_canvas_is_set_has_width_and_height()
    {
        CanvasHelper.checkIfCanvasIsSet( canvas );
    }

    public Object[] shapeParameters()
    {
        return new Object[] {
                new Object[] { shape1 },
                new Object[] { shape2 },
                new Object[] { shape3 },
                new Object[] { shape4 }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "shapeParameters")
    public void throw_runtime_exception_if_shape_is_outside_of_canvas( Shape shape )
    {
        CanvasHelper.checkIfShapeIsInsideCanvas( shape, canvas );
    }

    @Test
    public void not_throw_error_if_shape_is_not_outside_of_canvas()
    {
        // given
        CanvasHelper.checkIfShapeIsInsideCanvas( shape5, canvas );
    }
}
