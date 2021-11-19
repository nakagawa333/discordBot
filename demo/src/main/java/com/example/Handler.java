package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.entities.Guild;

public class Handler extends ListenerAdapter {

    public static final String BOT_NAME = "Japanese-English-translation";
    public static AudioManager bot_audio = null;
    public static final String bot_user_id = "907621482539135036";
    //public static String bot_id = null;
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!msg.getAuthor().getName().equals(BOT_NAME)) {
            // bot参加
            if (msg.getContentRaw().equals("!join")) {
                if (audioManager.isConnected()) {
                    channel.sendMessage("既に呼ばれてるで").queue();
                    return;
                }

                if(!Objects.isNull(connectedChannel)){
                    audioManager.openAudioConnection(connectedChannel);
                    audioManager.setConnectTimeout(31536000);
                    bot_audio = audioManager;
                    //bot_id = audioManager.getConnectedChannel().getId();
                }
                return;
            }

            // bot退出
            if (msg.getContentRaw().equals("!leave")) {
                if(audioManager.isConnected() && !Objects.isNull(connectedChannel)){
                    audioManager.closeAudioConnection();
                }
                return;
            }

            if (audioManager.isConnected()) {
                channel.sendMessage(msg).queue();
            }
        }
    }

    @Override
    // ユーザーが退出した場合
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {

        if(bot_audio.isConnected()){
            List<Member> members = bot_audio.getConnectedChannel().getMembers();

            if(Objects.isNull(bot_audio)) return;
    
            if (members.size() == 1) {
    
                String id = members.get(0).getUser().getId();
                if(id.equals(bot_user_id)){
                    bot_audio.closeAudioConnection();
                }
            }           
        }
    }
}
