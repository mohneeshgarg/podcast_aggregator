package com.podcast.aggregator.pa_v1.models.youtube;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentDetail {

    private String duration;
    private String dimension;
    private String definition;
    private String caption;
}
