package draw.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Point
{
    private int x;

    private int y;

    @Builder.Default
    private List<Point> connectingPoints = new ArrayList<>();

    @Override
    public int hashCode() {
        return this.x;
    }

    @Override
    public boolean equals( Object point )
    {
        if ( !( point instanceof Point ) )
        {
            return false;
        }

        return this.x == ( (Point) point ).x && this.y == ( (Point) point ).y;
    }

    @Override
    public String toString()
    {
        StringBuilder connectingPointsAsString = new StringBuilder( "(" );

        for ( Point p : this.connectingPoints )
        {
            connectingPointsAsString.append( "[" ).append( p.x ).append( ", " ).append( p.y ).append( "], " );
        }

        connectingPointsAsString.append( ")" );

        return "[" + this.x + ", " + this.y + "] ->" + connectingPointsAsString.toString() + "\n";
    }

    public boolean isConnecting( Point point )
    {
        boolean pointIsNextToOnXAxis = Math.abs( this.x - point.getX() ) == 1;
        boolean pointIsSameOnXAxis = this.x == point.getX();
        boolean pointIsNextToOnYAxis = Math.abs( this.y - point.getY() ) == 1;
        boolean pointIsSameOnYAxis = this.y == point.getY();

        return ( pointIsNextToOnXAxis && pointIsSameOnYAxis ) || ( pointIsNextToOnYAxis && pointIsSameOnXAxis );
    }
}
