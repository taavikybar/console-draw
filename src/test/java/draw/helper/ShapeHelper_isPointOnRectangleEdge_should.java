package draw.helper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Point;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_isPointOnRectangleEdge_should extends TestBase
{

    public Object[] pointOnEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 1 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 3 ).y( 3 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointOnEdgeParameters")
    public void return_true_if_point_is_on_rectangle_edge( Point point )
    {
        // given
        Shape rectangle = Shape.builder().startX( 1 ).startY( 1 ).endX( 3 ).endY( 3 ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnRectangleEdge( rectangle, point );

        // then
        assertThat( isPointOnLine ).isTrue();
    }

    public Object[] pointNotOnEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 5 ).build() },
                new Object[] { Point.builder().x( 1 ).y( 4 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnEdgeParameters")
    public void return_true_if_point_is_not_on_rectangle_edge( Point point )
    {
        // given
        Shape rectangle = Shape.builder().startX( 1 ).startY( 1 ).endX( 3 ).endY( 3 ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnRectangleEdge( rectangle, point );

        // then
        assertThat( isPointOnLine ).isFalse();
    }

    public Object[] pointOnLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 10 ).y( 1 ).build() }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "pointOnLineParameters")
    public void throw_runtime_exception_if_point_is_on_line( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }

    public Object[] pointNotOnLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 11 ).y( 1 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnLineParameters")
    public void not_throw_runtime_exception_if_point_is_not_on_line( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }

    public Object[] pointOnRectangleEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 3 ).y( 5 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 9 ).y( 10 ).build() },
                new Object[] { Point.builder().x( 10 ).y( 10 ).build() }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "pointOnRectangleEdgeParameters")
    public void throw_runtime_exception_if_point_is_on_rectangle_edge( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }

    public Object[] pointNotOnRectangleEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 4 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 7 ).y( 7 ).build() },
                new Object[] { Point.builder().x( 9 ).y( 9 ).build() },
                new Object[] { Point.builder().x( 11 ).y( 11 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnRectangleEdgeParameters")
    public void not_throw_runtime_exception_if_point_is_not_on_rectangle_edge( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }

}
