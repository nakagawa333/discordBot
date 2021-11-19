package com.example;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;


//import java.lag.*;
/**
 * Hello world!
 *
 */
public class App 
{

  private static final String BASE_URL= "https://www2.deepl.com/jsonrpc";
  private static final String API_KEY = "8558654b-5c4e-c8ab-9517-c538c7b8a0e5:fx";
  private static final String TOKEN = "OTA3NjIxNDgyNTM5MTM1MDM2.YYp2YA.01nAfpmebCRkebE3cXLJc_hZEKA";
  
  // public static final DiscordClient client = DiscordClient.create("OTA3NjIxNDgyNTM5MTM1MDM2.YYp2YA.01nAfpmebCRkebE3cXLJc_hZEKA");
  // public static final GatewayDiscordClient gateway = client.login().block();

    public static void main( String[] args )
    {

      try{
        System.out.println("Hello");
        JDA jda = JDABuilder.createDefault(TOKEN).build();
        jda.addEventListener(new Handler());

        
      } catch(Exception e){
        e.printStackTrace();
      }

      // gateway.on(MessageCreateEvent.class).subscribe(event -> {
      //     Message message = event.getMessage();
      //     if(!message.getData().author().username().equals("Japanese-English-translation")){
      //       if(message.getContent().equals("!join")){
      //         GatewayDiscordClient gate = message.getAuthor().get().getClient();

      //         ChannelData cha = client.getChannelService().getChannel(message.getChannelId().asLong()).block();
      //         Snowflake id = message.getGuildId().get();

      //         VoiceChannel channel = new VoiceChannel(gateway,cha);
      //         final AudioPlayer provider = GuildAudioManager.of(channel.getGuildId()).getProvider();
      //         final VoiceConnection connection = channel.join(spec -> spec.setProvider(provider)).block();
      //         //VoiceConnection connection = gate.getVoiceConnectionRegistry().getVoiceConnection(id).block();

      //         channel.join();
      //         System.out.println(provider);
      //         // if(connection != null){

      //         // }
      //         return;
      //       }
      //       MessageChannel channel = message.getChannel().block();
      //       channel.createMessage("こんにちは!").block();
      //     }
      //   });

      //   gateway.onDisconnect().block();
    }
}