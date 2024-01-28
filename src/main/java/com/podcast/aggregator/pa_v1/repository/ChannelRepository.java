package com.podcast.aggregator.pa_v1.repository;

import com.podcast.aggregator.pa_v1.entities.youtube.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
