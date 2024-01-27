package com.podcast.aggregator.pa_v1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.podcast.aggregator.pa_v1.clients.YoutubeClient;
import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import com.podcast.aggregator.pa_v1.models.youtube.Item;
import com.podcast.aggregator.pa_v1.models.youtube.properties.YoutubeProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class YoutubeService {
    @Autowired
    private YoutubeClient youtubeClient;
    @Autowired
    private VideoDataPersistanceService videoDataPersistanceService;

    public void dumpVideoDataIntoDB(){
        List<Item> allVideosUsingChannelIds = youtubeClient.getAllVideos();
        List<String> videoIds = allVideosUsingChannelIds.stream().map(video-> video.getId().getVideoId()).collect(Collectors.toList());
        List<Item> allVideos = youtubeClient.getDataUsingVideoIds(videoIds);
        log.info("fetched all the data, now starting saving into db!");
        allVideos.forEach(item -> {
            try {
                videoDataPersistanceService.persistVideoData(item);
            } catch (JsonProcessingException e) {
                log.info("Got exception for video id {} while persisting", item.getId().getVideoId());
                throw new RuntimeException(e);
            }
        });
    }

}
