package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_checkIfPointIsOnLineOrEdge_should extends TestBase
{
    public Object[] pointOnLineOrEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 8 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 10 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 3 ).y( 3 ).build() }
        };
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "pointOnLineOrEdgeParameters")
    public void throw_runtime_exception_if_point_is_on_line_or_edge( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }

    public Object[] pointNotOnLineOrEdgeParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 11 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 6 ).y( 7 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 5 ).build() },
                new Object[] { Point.builder().x( 1 ).y( 4 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnLineOrEdgeParameters")
    public void not_throw_runtime_exception_if_point_is_not_on_line_or_edge( Point point )
    {
        ShapeHelper.checkIfPointIsOnLineOrEdge( getShapes(), point );
    }
}
