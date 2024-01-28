package com.podcast.aggregator.pa_v1.controller;

import com.podcast.aggregator.pa_v1.entities.youtube.VideoTags;
import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import com.podcast.aggregator.pa_v1.services.VideoTagsPersistanceService;
import com.podcast.aggregator.pa_v1.services.VideoDataPersistanceService;
import com.podcast.aggregator.pa_v1.services.YoutubeService;
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

    @Autowired
    YoutubeService youtubeService;


    @RequestMapping(value = "/getAllTaggedVideos", method = RequestMethod.GET)
    public List<Video> getAllTaggedVideos(){
        log.info("Got request to get the whole data!");
        return videoDataPersistanceService.getAllTaggedVideos();
    }

    @RequestMapping(value = "/addTags/{videoId}", method = RequestMethod.POST)
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

    @RequestMapping(value = "/getAllUntaggedVideos", method = RequestMethod.GET)
    public List<Video> getAllUntaggedVideos(){
        return videoDataPersistanceService.getAllUntaggedVideos();
    }

    @RequestMapping(value = "/getAllVideosByTag/{tag}", method = RequestMethod.GET)
    public List<Video> getAllVideosUsingTag(@PathVariable String tag){
        return videoDataPersistanceService.findAllVideosByTagName(tag);
    }

    @RequestMapping(value = "/delete/tags/{videoId}", method = RequestMethod.DELETE)
    public String deleteTags(@PathVariable String videoId,
                             @RequestParam String tags){
        Set<String> tagSet = Set.of(tags.split(","));
        videoDataPersistanceService.deleteTagFromVideo(videoId, tagSet);
        return "Deleted tag from the videos!";
    }

    @RequestMapping(value = "/tags/all", method = RequestMethod.GET)
    public List<String> getAllTags(){
        return youtubeService.getAllTheTags();
    }
}
