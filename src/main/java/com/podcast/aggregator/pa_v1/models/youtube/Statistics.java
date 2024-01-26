package com.podcast.aggregator.pa_v1.models.youtube;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statistics {
    private String viewCount;
    private String likeCount;
    private String commentCount;
    private String favouriteCount;
}
