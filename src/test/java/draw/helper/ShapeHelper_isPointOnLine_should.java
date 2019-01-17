package draw.helper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Point;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_isPointOnLine_should
{
    public Object[] pointOnHorizontalLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 8 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 10 ).y( 1 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointOnHorizontalLineParameters")
    public void return_true_if_point_is_on_horizontal_line( Point point )
    {
        // given
        Shape line = Shape.builder().startX( 2 ).startY( 1 ).endX( 10 ).endY( 1 ).isHorizontal( true ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnLine( line, point );

        // then
        assertThat( isPointOnLine ).isTrue();
    }

    public Object[] pointOnVerticalLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 4 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 7 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 8 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointOnVerticalLineParameters")
    public void return_true_if_point_is_on_vertical_line( Point point )
    {
        // given
        Shape line = Shape.builder().startX( 2 ).startY( 1 ).endX( 2 ).endY( 8 ).isHorizontal( false ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnLine( line, point );

        // then
        assertThat( isPointOnLine ).isTrue();
    }

    public Object[] pointNotOnHorizontalLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 11 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 6 ).y( 7 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnHorizontalLineParameters")
    public void return_false_if_point_is_on_not_on_horizontal_line( Point point )
    {
        // given
        Shape line = Shape.builder().startX( 2 ).startY( 1 ).endX( 10 ).endY( 1 ).isHorizontal( true ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnLine( line, point );

        // then
        assertThat( isPointOnLine ).isFalse();
    }

    public Object[] pointNotOnVerticalLineParameters()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 3 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 9 ).build() },
                new Object[] { Point.builder().x( 6 ).y( 7 ).build() }
        };
    }

    @Test
    @Parameters(method = "pointNotOnVerticalLineParameters")
    public void return_true_if_point_is_not_on_vertical_line( Point point )
    {
        // given
        Shape line = Shape.builder().startX( 2 ).startY( 1 ).endX( 2 ).endY( 8 ).isHorizontal( false ).build();

        // when
        boolean isPointOnLine = ShapeHelper.isPointOnLine( line, point );

        // then
        assertThat( isPointOnLine ).isFalse();
    }
}
