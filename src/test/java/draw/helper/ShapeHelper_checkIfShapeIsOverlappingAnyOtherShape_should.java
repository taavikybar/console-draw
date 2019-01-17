package draw.helper;

import org.junit.Test;
import org.junit.runner.RunWith;

import draw.model.Shape;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ShapeHelper_checkIfShapeIsOverlappingAnyOtherShape_should extends TestBase
{
    public Object[] shapeIsOverlappingParams()
    {
        return getParams(
                Shape.builder().startX( 1 ).startY( 1 ).endX( 3 ).endY( 3 ).build(),
                Shape.builder().startX( 4 ).startY( 2 ).endX( 12 ).endY( 12 ).build(),
                Shape.builder().startX( 4 ).startY( 2 ).endX( 8 ).endY( 8 ).build(),
                Shape.builder().startX( 2 ).startY( 2 ).endX( 3 ).endY( 3 ).build(),
                Shape.builder().startX( 3 ).startY( 1 ).endX( 12 ).endY( 12 ).build(),
                Shape.builder().startX( 1 ).startY( 1 ).endX( 12 ).endY( 12 ).build()
        );
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "shapeIsOverlappingParams")
    public void throw_runtime_exception_if_two_shapes_are_not_overlapping( Shape shape )
    {
        ShapeHelper.checkIfShapeIsOverlappingAnyOtherShape( shape, getShapes() );
    }

    public Object[] shapeIsNotOverlappingParams()
    {
        return getParams(
                Shape.builder().startX( 11 ).startY( 11 ).endX( 12).endY( 12 ).build(),
                Shape.builder().startX( 1 ).startY( 1 ).endX( 1 ).endY( 5 ).build(),
                Shape.builder().startX( 13 ).startY( 14 ).endX( 20 ).endY( 20 ).build()
        );
    }

    @Test
    @Parameters(method = "shapeIsNotOverlappingParams")
    public void not_throw_runtime_exception_if_two_shapes_are_not_overlapping( Shape shape )
    {
        ShapeHelper.checkIfShapeIsOverlappingAnyOtherShape( shape, getShapes() );
    }
}
