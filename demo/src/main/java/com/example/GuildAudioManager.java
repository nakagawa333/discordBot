package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;

import discord4j.common.util.Snowflake;

public class GuildAudioManager  {
  public static final AudioPlayerManager PLAYER_MANAGER;
  static {
    PLAYER_MANAGER = new DefaultAudioPlayerManager();
    // This is an optimization strategy that Discord4J can utilize to minimize allocations
    PLAYER_MANAGER.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
    AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER);
    AudioSourceManagers.registerLocalSource(PLAYER_MANAGER);
  }
  private static final Map<Snowflake, GuildAudioManager> MANAGERS = new ConcurrentHashMap<>();

  public static GuildAudioManager of(final Snowflake id) {
    return MANAGERS.computeIfAbsent(id, ignored -> new GuildAudioManager());
  }

  private final AudioPlayer player;
  private final AudioTrackScheduler scheduler;
  private final LavaPlayerAudioProvider provider;

  private GuildAudioManager() {
    player = PLAYER_MANAGER.createPlayer();
    scheduler = new AudioTrackScheduler(player);
    provider = new LavaPlayerAudioProvider(player);

    player.addListener(scheduler);
  }

  public AudioPlayer getProvider(){
    return player;
  }

  public static void setProvider(AudioPlayer pro){

  }
}
