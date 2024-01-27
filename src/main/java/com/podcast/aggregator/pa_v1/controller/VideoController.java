package com.podcast.aggregator.pa_v1.controller;

import com.podcast.aggregator.pa_v1.entities.youtube.VideoTags;
import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import com.podcast.aggregator.pa_v1.services.VideoTagsPersistanceService;
import com.podcast.aggregator.pa_v1.services.VideoDataPersistanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/videos")
@CrossOrigin("*")
public class VideoController {

    @Autowired
    VideoDataPersistanceService  videoDataPersistanceService;


    @RequestMapping("/getAllTaggedVideos")
    public List<Video> getAllTaggedVideos(){
        log.info("Got request to get the whole data!");
        return videoDataPersistanceService.getAllTaggedVideos();
    }

    @RequestMapping("/addTags/{videoId}")
    public String assign(@PathVariable("videoId") String videoId,
                         @RequestParam("tags") String tags){
        Video video = videoDataPersistanceService.getVideoUsingId(videoId);
        Set<String> existingTags = video.getApplicableTags();
        Set<String> tagSet = Set.of(tags.split(","));
        existingTags.addAll(tagSet);
        video.setApplicableTags(existingTags);
        video.setTagsEnabled(true);
        videoDataPersistanceService.saveOrUpdateVideo(video);
        return "Tag assigned successfully";
    }

    @DeleteMapping("/delete/{videoId}")
    public String deleteVideo(@PathVariable("videoId") String videoId){
        videoDataPersistanceService.deleteVideo(videoId);
        return "Video deleted successfully";
    }

    @RequestMapping("/videos/getAllUntaggedVideos")
    public List<Video> getAllUntaggedVideos(){
        return videoDataPersistanceService.getAllUntaggedVideos();
    }

    @RequestMapping("/getAllVideosByTag/{tag}")
    public List<Video> getAllVideosUsingTag(@PathVariable String tag){
        return videoDataPersistanceService.findAllVideosByTagName(tag);
    }

    @RequestMapping("/delete/tags/{videoId}")
    public String deleteTags(@PathVariable String videoId,
                             @RequestParam String tags){
        Set<String> tagSet = Set.of(tags.split(","));
        videoDataPersistanceService.deleteTagFromVideo(videoId, tagSet);
        return "Deleted tag from the videos!";
    }
}
