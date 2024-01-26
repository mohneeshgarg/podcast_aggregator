package com.podcast.aggregator.pa_v1.controller;

import com.podcast.aggregator.pa_v1.clients.YoutubeClient;
import com.podcast.aggregator.pa_v1.models.youtube.Item;
import com.podcast.aggregator.pa_v1.models.youtube.YoutubeV3ApiResponse;
import com.podcast.aggregator.pa_v1.models.youtube.properties.YoutubeProperties;
import com.podcast.aggregator.pa_v1.util.YoutubeDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class YoutubeController {

    @Autowired
    private YoutubeDataUtil youtubeDataUtil;

    @RequestMapping("/")
    public String home(){
        return "this is home page";
    }

    @RequestMapping("/getData")
    public List<Item> getData(){
        log.info("got request to get the whole data!");
        return youtubeDataUtil.getAllVideos();
    }
}
