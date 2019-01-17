package draw.helper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Point;
import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_isPointOnOrInsideShape_should
{
    private static final Shape rectangle = Shape.builder().startX( 2 ).startY( 2 ).endX( 6 ).endY( 8 ).build();

    public Object[] pointOnOrInsideShape()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 2 ).y( 2 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 4 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 4 ).build() },
                new Object[] { Point.builder().x( 5 ).y( 7 ).build() },
                new Object[] { Point.builder().x( 4 ).y( 8 ).build() },
                new Object[] { Point.builder().x( 6 ).y( 8 ).build() },
        };
    }

    @Test
    @Parameters(method = "pointOnOrInsideShape")
    public void return_true_if_point_is_on_or_inside_shape( Point point )
    {
        // when
        boolean isPointInsideRectangle = ShapeHelper.isPointOnOrInsideShape( rectangle, point );

        // then
        assertThat( isPointInsideRectangle ).isTrue();
    }

    public Object[] pointNotOnOrInsideShape()
    {
        return new Object[] {
                new Object[] { Point.builder().x( 1 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 2 ).y( 1 ).build() },
                new Object[] { Point.builder().x( 7 ).y( 9 ).build() },
        };
    }

    @Test
    @Parameters(method = "pointNotOnOrInsideShape")
    public void return_false_if_point_is_not_on_or_inside_shape( Point point )
    {
        // when
        boolean isPointInsideRectangle = ShapeHelper.isPointOnOrInsideShape( rectangle, point );

        // then
        assertThat( isPointInsideRectangle ).isFalse();
    }
}
