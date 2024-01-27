package com.podcast.aggregator.pa_v1.repository;

import com.podcast.aggregator.pa_v1.entities.youtube.VideoTags;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoTagsRepository extends JpaRepository<VideoTags, Long> {

    @Query(value = "select * from video_tags where tag= ?1", nativeQuery = true)
    List<VideoTags> findAllByTag(String tag);

    @Transactional
    @Modifying
    @Query(value = "delete from video_tags where tag= ?1 and video_id= ?2", nativeQuery = true)
    void deleteByTagAndVideoId(String tag, String videoId);
}
