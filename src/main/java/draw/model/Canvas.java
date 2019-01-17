package draw.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Canvas
{
    @Builder.Default
    private int width = 0;

    @Builder.Default
    private int height = 0;
}
