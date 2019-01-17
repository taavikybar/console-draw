package draw.helper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Point;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_isPointInsideRectangle_should
{
    private static final Shape rectangle = Shape.builder().startX( 2 ).startY( 2 ).endX( 6 ).endY( 8 ).build();

    public Object[] pointInsideRectangle()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 3 ).y( 3 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 3 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 7 ).build() },
        };
    }

    @Test
    @Parameters(method = "pointInsideRectangle")
    public void return_true_if_point_is_inside_rectangle( Point point )
    {
        // when
        boolean isPointInsideRectangle = ShapeHelper.isPointInsideRectangle( rectangle, point );

        // then
        assertThat( isPointInsideRectangle ).isTrue();
    }

    public Object[] pointNotInsideRectangle()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 7 ).y( 9 ).build() },
        };
    }

    @Test
    @Parameters(method = "pointNotInsideRectangle")
    public void return_false_if_point_is_not_inside_rectangle( Point point )
    {
        // when
        boolean isPointInsideRectangle = ShapeHelper.isPointInsideRectangle( rectangle, point );

        // then
        assertThat( isPointInsideRectangle ).isFalse();
    }
}
