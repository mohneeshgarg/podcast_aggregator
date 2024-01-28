package com.podcast.aggregator.pa_v1.entities.youtube;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(name="video_publishedAt", columnList = "publishedAt"),
                @Index(name="video_tags_enabled", columnList = "tagsEnabled")
        }
)
public class Video {

    @Id
    public String id;

    public String channelId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> snippet;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> contentDetails;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> statistics;

    private LocalDateTime publishedAt;

    private boolean tagsEnabled;

    private Set<String> applicableTags;
}
