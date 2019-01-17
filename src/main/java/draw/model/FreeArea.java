package draw.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeArea
{
    @Builder.Default
    List<Point> points = new ArrayList<>();

    @Override
    public String toString()
    {
        StringBuilder stringified = new StringBuilder();

        this.getPoints().forEach( point -> stringified.append( "[" ).append( point.getX() ).append( "; " ).append( point.getY() ).append( "]" ) );

        return stringified.append( "\n" ).toString();
    }
}
