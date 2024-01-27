package com.podcast.aggregator.pa_v1.entities.youtube;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="video_tags")
public class VideoTags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;

    private String videoId;

    public VideoTags(String tag){
        this.tag = tag;
    }
}
