package com.podcast.aggregator.pa_v1.util;

import com.podcast.aggregator.pa_v1.clients.YoutubeClient;
import com.podcast.aggregator.pa_v1.models.youtube.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@Slf4j
public class YoutubeDataUtil {

    @Autowired
    private YoutubeClient youtubeClient;

    private List<Item> allVideos;

    public void fetchAllVideosFromYoutube(){
        List<Item> allVideosUsingChannelIds = youtubeClient.getAllVideos();
        List<String> videoIds = allVideosUsingChannelIds.stream().map(video-> video.getId().getVideoId()).collect(Collectors.toList());
        allVideos = youtubeClient.getDataUsingVideoIds(videoIds);
        log.info("fetched all the data!");
    }
}
