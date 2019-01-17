package draw.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FillOptions
{
    private Point point;

    private String fill;

    private Shape rectangleToBeFilled;
}
