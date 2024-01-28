package com.podcast.aggregator.pa_v1.controller;

import com.podcast.aggregator.pa_v1.entities.youtube.Channel;
import com.podcast.aggregator.pa_v1.services.ChannelPersistanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/channel")
@CrossOrigin("*")
public class ChannelController {

    @Autowired
    private ChannelPersistanceService channelPersistanceService;

    @RequestMapping("/getAll")
    public List<Channel> getAll(){
        return channelPersistanceService.getAllChannels();
    }

    @RequestMapping("/get/{channelId}")
    public Channel getChannelById(@PathVariable String channelId){
        return channelPersistanceService.getChannelById(channelId);
    }

    @RequestMapping("/add")
    public Channel addChannel(@RequestParam String channelId,
                              @RequestParam String name){
        return channelPersistanceService.saveChannel(channelId, name);
    }

    @RequestMapping("/update")
    public Channel updateChannel(@RequestParam String channelId,
                                 @RequestParam String name){
        Channel channel = channelPersistanceService.getChannelById(channelId);
        channel.setName(name);
        return channelPersistanceService.updateChannel(channel);
    }

    @RequestMapping("/delete")
    public void deleteChannel(@RequestParam String channelId){
        channelPersistanceService.deleteChannel(channelId);
    }

}
