package com.podcast.aggregator.pa_v1.models.youtube;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeV3ApiResponse {

    private List<Item> items;
}
