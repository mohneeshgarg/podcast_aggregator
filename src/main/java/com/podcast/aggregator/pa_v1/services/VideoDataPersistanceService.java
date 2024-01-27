package com.podcast.aggregator.pa_v1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.podcast.aggregator.pa_v1.beans.ObjectMapperFactory;
import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import com.podcast.aggregator.pa_v1.entities.youtube.VideoTags;
import com.podcast.aggregator.pa_v1.models.youtube.Item;
import com.podcast.aggregator.pa_v1.repository.VideoRepository;
import com.podcast.aggregator.pa_v1.repository.VideoTagsRepository;
import com.podcast.aggregator.pa_v1.util.DateUtils;
import com.podcast.aggregator.pa_v1.util.JsonUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class VideoDataPersistanceService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoTagsPersistanceService videoTagsPersistanceService;

    public void persistVideoData(Item item) throws JsonProcessingException {
        Video video = Video.builder()
                .id(item.getId().getVideoId())
                .channelId(item.getSnippet().getChannelId())
                .snippet(JsonUtil.objectToMap(item.getSnippet()))
                .contentDetails(JsonUtil.objectToMap(item.getContentDetails()))
                .publishedAt(DateUtils.stringToDate(item.getSnippet().getPublishedAt()))
                .statistics(JsonUtil.objectToMap(item.getStatistics()))
                .applicableTags(new HashSet<>())
                .build();
        log.info("started to save video {}", video);
        videoRepository.save(video);
    }


    public Video getVideoUsingId(String id){
        return videoRepository.findById(id).get();
    }

    @Transactional
    public Video saveOrUpdateVideo(Video video){
        if(video.getApplicableTags()!=null && video.getApplicableTags().size() > 0){
            video.getApplicableTags().forEach(tag->{
                videoTagsPersistanceService.saveTag(tag, video.getId());
            });
        }
        return videoRepository.save(video);
    }

    @Transactional
    public void deleteVideo(String videoId){
        Video video = getVideoUsingId(videoId);
        video.getApplicableTags().forEach(tag->{
            videoTagsPersistanceService.deleteTags(tag, videoId);
        });
        videoRepository.deleteById(videoId);
    }

    public List<Video> getAllUntaggedVideos(){
        return videoRepository.findAllUntaggedVideos();
    }

    public List<Video> getAllTaggedVideos(){
        return videoRepository.findAllTaggedVideos();
    }

    public List<Video> findAllVideosByTagName(String tag){
        List<VideoTags> videoTagRecords =  videoTagsPersistanceService.findAllVideoTagRecordsUsingTag(tag);
        List<Video> videoList = new ArrayList<>();
        videoTagRecords.forEach(record->{
            videoList.add(Optional.ofNullable(videoRepository.findById(record.getVideoId()).get()).orElse(null));
        });
        return videoList;
    }

    @Transactional
    public void deleteTagFromVideo(String videoId, Set<String> tagSet){
        Video video = videoRepository.findById(videoId).get();
        Set<String> existingTags = video.getApplicableTags();
        existingTags.removeAll(tagSet);
        if(existingTags.size()==0)
            video.setTagsEnabled(false);
        video.setApplicableTags(existingTags);
        videoRepository.save(video);
        tagSet.forEach(tag->{
            videoTagsPersistanceService.deleteTags(tag, videoId);
        });
    }
}
