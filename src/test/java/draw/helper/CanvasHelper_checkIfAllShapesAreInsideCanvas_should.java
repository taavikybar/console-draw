package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.controller.CanvasController;
import draw.model.Canvas;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CanvasHelper_checkIfAllShapesAreInsideCanvas_should
{
    private static final Shape shape1 = Shape.builder().startX( 11 ).startY( 1 ).endX( 12 ).endY( 9 ).build();

    private static final Shape shape2 = Shape.builder().startX( 1 ).startY( 11 ).endX( 2 ).endY( 12 ).build();

    private static final Shape shape3 = Shape.builder().startX( 1 ).startY( 2 ).endX( 2 ).endY( 2 ).build();

    private static final Shape shape4 = Shape.builder().startX( 5 ).startY( 6 ).endX( 9 ).endY( 8 ).build();

    private static final Canvas canvas = Canvas.builder().width( 10 ).height( 10 ).build();

    @Test(expected = RuntimeException.class)
    public void throw_runtime_exception_if_at_least_one_shape_is_outside_of_canvas()
    {
        // given
        CanvasController canvasController = new CanvasController();
        canvasController.shapes.add( shape1 );
        canvasController.shapes.add( shape2 );

        // when
        CanvasHelper.checkIfAllShapesAreInsideCanvas( canvasController, canvas );
    }

    @Test
    public void not_throw_error_if_all_shapes_are_inside_of_canvas()
    {
        // given
        CanvasController canvasController = new CanvasController();
        canvasController.shapes.add( shape3 );
        canvasController.shapes.add( shape4 );

        // when
        CanvasHelper.checkIfAllShapesAreInsideCanvas( canvasController, canvas );
    }
}
