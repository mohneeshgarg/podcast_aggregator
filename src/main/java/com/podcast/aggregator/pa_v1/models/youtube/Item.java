package com.podcast.aggregator.pa_v1.models.youtube;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private Id id;
    private Snippet snippet;
    private ContentDetail contentDetails;
    private Statistics statistics;
}
