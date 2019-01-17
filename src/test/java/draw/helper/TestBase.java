package draw.helper;

import java.util.ArrayList;
import java.util.List;

import draw.model.Shape;

class TestBase
{
    List<Shape> getShapes()
    {
        Shape shape1 = Shape.builder().startX( 2 ).startY( 1 ).endX( 10 ).endY( 1 ).build();
        Shape shape2 = Shape.builder().startX( 3 ).startY( 1 ).endX( 10 ).endY( 10 ).build();
        List<Shape> shapes = new ArrayList<>();
        shapes.add( shape1 );
        shapes.add( shape2 );
        return shapes;
    }

    Object[] getParams( Object... params )
    {
        Object[] returnParams = new Object[params.length];

        for ( int i = 0; i < params.length; i++ )
        {
            returnParams[i] = new Object[] { params[i] };
        }

        return returnParams;
    }
}
