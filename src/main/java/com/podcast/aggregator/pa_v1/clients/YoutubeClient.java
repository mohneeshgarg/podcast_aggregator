package com.podcast.aggregator.pa_v1.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.podcast.aggregator.pa_v1.models.youtube.Item;
import com.podcast.aggregator.pa_v1.models.youtube.YoutubeV3ApiResponse;
import com.podcast.aggregator.pa_v1.models.youtube.properties.YoutubeProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class YoutubeClient {

    @Autowired
    YoutubeProperties youtubeProperties;

    @Autowired
    private RestTemplate restTemplate;


    public YoutubeV3ApiResponse getVideosUsingChannelId(String channelId){
        String url = youtubeProperties.getUrl()+"search?key="+youtubeProperties.getKey()+"&channelId="+channelId+"&part=snippet,id&order=date&maxResults="+youtubeProperties.getMaxResults();
        YoutubeV3ApiResponse response = restTemplate.getForObject(url, YoutubeV3ApiResponse.class);
        log.info("Response list size from youtube {}", response.getItems().size());
        return response;
    }

    public List<Item> getAllVideos(){
        Map<String, YoutubeProperties.MetaData> channels =  youtubeProperties.getChannel();
        List<Item> allVideos = new ArrayList<>();
        for(String channel: channels.keySet()){
            YoutubeProperties.MetaData metaData = channels.get(channel);
            allVideos.addAll(Optional.ofNullable(getVideosUsingChannelId(metaData.getId()).getItems()).orElse(null));
        }
        log.info("Total videos {}", allVideos.size());
        return allVideos;
    }

    public List<Item> getDataUsingVideoIds(List<String> videoIds){

        List<List<String>> batches = Lists.partition(videoIds, 50);
        log.info("Total batches {}", batches.size());
        List<Item> allVideos = new ArrayList<>();
        for(List<String> currentBatch: batches){
            String ids = StringUtils.join(currentBatch, ",");
            log.info("current batch ids {}", ids);
            String url = youtubeProperties.getUrl()+"videos?key="+youtubeProperties.getKey()+"&id="+ids+"&part=snippet,contentDetails,statistics,id&fields=items(id,snippet(publishedAt,channelId,title,thumbnails,channelTitle,tags,categoryId,description),contentDetails,statistics)";
            YoutubeV3ApiResponse videoMetaData = restTemplate.getForObject(url, YoutubeV3ApiResponse.class);
            filterShorts(videoMetaData);
            allVideos.addAll(Optional.ofNullable(videoMetaData.getItems()).orElse(null));
        }
        log.info("Total size after filtering {}", allVideos.size());
        return allVideos;
    }

    public void filterShorts(YoutubeV3ApiResponse videoMetaData){
        videoMetaData.setItems(videoMetaData.getItems().
                stream().filter(item-> convertDuration(item.getContentDetails().getDuration())>=600).
                collect(Collectors.toList()));
    }

    public Integer convertDuration(String duration){
        duration = duration.substring(2);
        Integer totalTime = 0;
        String temp = "";
        for(char c: duration.toCharArray()){
            if(c=='H' || c=='M' || c=='S'){
                if(c=='H')
                    totalTime+= Integer.parseInt(temp)*3600;
                else if(c=='M')
                    totalTime += Integer.parseInt(temp)*60;
                else
                    totalTime += Integer.parseInt(temp);
                temp = "";
            } else{
                temp += c;
            }
        }
        return totalTime;
    }
}
