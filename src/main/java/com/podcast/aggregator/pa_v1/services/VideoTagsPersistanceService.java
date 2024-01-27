package com.podcast.aggregator.pa_v1.services;

import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import com.podcast.aggregator.pa_v1.entities.youtube.VideoTags;
import com.podcast.aggregator.pa_v1.repository.VideoTagsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class VideoTagsPersistanceService {

    @Autowired
    private VideoTagsRepository videoTagsRepository;

    @Transactional
    public VideoTags saveTag(String tag, String videoId){
        VideoTags tags = VideoTags.builder()
                .tag(tag)
                .videoId(videoId)
                .build();
        return videoTagsRepository.save(tags);
    }

    public List<VideoTags> getAllTags(){
        return videoTagsRepository.findAll();
    }

    public VideoTags findTagById(Long tagId){
        return videoTagsRepository.findById(tagId).get();
    }
    @Transactional
    public void deleteTags(String tag, String videoId){
        videoTagsRepository.deleteByTagAndVideoId(tag, videoId);
    }
    public List<VideoTags> findAllVideoTagRecordsUsingTag(String tag){
        return videoTagsRepository.findAllByTag(tag);
    }
}
