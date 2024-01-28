package com.podcast.aggregator.pa_v1.entities.youtube;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel {

    @Id
    private String id;

    private String name;

    private LocalDateTime createdAt;

    public Channel(String id, String name){
        this.id = id;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
