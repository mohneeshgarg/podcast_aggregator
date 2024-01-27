package com.podcast.aggregator.pa_v1.repository;

import com.podcast.aggregator.pa_v1.entities.youtube.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    @Query(value = "select * from video where tags_enabled=false", nativeQuery = true)
    List<Video> findAllUntaggedVideos();

    @Query(value = "select * from video where tags_enabled=true", nativeQuery = true)
    List<Video> findAllTaggedVideos();

}
