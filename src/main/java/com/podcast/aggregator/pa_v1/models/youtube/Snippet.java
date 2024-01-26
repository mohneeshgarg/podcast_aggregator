package com.podcast.aggregator.pa_v1.models.youtube;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Snippet {

    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private Map<String, ThumbnailProperties> thumbnails;
    private String channelTitle;
    private String publishTime;
    private List<String> tags;

    @Data
    public static class ThumbnailProperties{
        private String url;
        private String height;
        private String width;
    }
}
