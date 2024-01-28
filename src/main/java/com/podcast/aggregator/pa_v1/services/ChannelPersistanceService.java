package com.podcast.aggregator.pa_v1.services;

import com.podcast.aggregator.pa_v1.entities.youtube.Channel;
import com.podcast.aggregator.pa_v1.repository.ChannelRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ChannelPersistanceService {

    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> getAllChannels(){
        return channelRepository.findAll();
    }

    public Channel getChannelById(String id){
        return Optional.ofNullable(channelRepository.findById(id).get()).orElse(null);
    }

    @Transactional
    public Channel saveChannel(String id, String name){
        Channel channel = new Channel(id, name);
        channelRepository.save(channel);
        return channel;
    }

    @Transactional
    public Channel updateChannel(Channel channel){
        return channelRepository.save(channel);
    }

    @Transactional
    public void deleteChannel(String id){
        channelRepository.deleteById(id);
    }
}
