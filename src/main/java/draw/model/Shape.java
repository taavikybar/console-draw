package draw.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Shape
{
    private int startX;

    private int endX;

    private int startY;

    private int endY;

    private boolean isHorizontal;

    private boolean isRectangle;

    public boolean isOverlapping( Shape shape )
    {
        boolean isXStartOverlapping = shape.startX >= this.startX && shape.startX <= this.endX;
        boolean isXEndOverlapping = shape.endX >= this.startX && shape.endX <= this.endX;

        boolean isYStartOverlapping = shape.startY >= this.startY && shape.startY <= this.endY;
        boolean isYEndOverlapping = shape.endY >= this.startY && shape.endY <= this.endY;

        boolean isStartingOverlappingAndEndingBigger = isXStartOverlapping && shape.startY <= this.startY && shape.endY >= this.endY;

        boolean isShapeBiggerAndCoversWhole = shape.startX <= this.startX
                && shape.startY <= this.startY
                && shape.endX >= this.endX
                && shape.endY >= this.endY;

        return ( isXStartOverlapping && isYStartOverlapping )
                || ( isXEndOverlapping && isYEndOverlapping )
                || isStartingOverlappingAndEndingBigger
                || isShapeBiggerAndCoversWhole;
    }
}
