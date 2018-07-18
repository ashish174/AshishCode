package designpattern.behavioural.iteratorpattern;


import designpattern.structural.proxypattern.CommandExecutor;
import designpattern.structural.proxypattern.CommandExecutorProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
used to provide a standard way to traverse through a group of Objects.
Provides a way to access the elements of an aggregate object without exposing its underlying represenation.
widely used in Java Collection Framework where Iterator interface provides methods for traversing through a collection.
Iterator pattern is not only about traversing through a collection, we can provide different kind of iterators based on our requirements.
Iterator design pattern hides the actual implementation of traversal
*/

public class IteratorPatternTest {
  private static Logger logger = LogManager.getLogger(IteratorPatternTest.class);
  public static void main(String[] args) {

    ChannelCollection channels = populateChannels();
    ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
    System.out.println("All Channels List : ");
    while (baseIterator.hasNext()){
      Channel channel = baseIterator.next();
      System.out.println(channel.toString()+" ");
    }

    System.out.println("Hindi Channels List : ");
    ChannelIterator hindiIterator = channels.iterator(ChannelTypeEnum.HINDI);
    while (hindiIterator.hasNext()){
      Channel channel = hindiIterator.next();
      System.out.println(channel.toString()+" ");
    }

    System.out.println("French Channels List : ");
    ChannelIterator frenchIterator = channels.iterator(ChannelTypeEnum.FRENCH);
    while (frenchIterator.hasNext()){
      Channel channel = frenchIterator.next();
      System.out.println(channel.toString()+" ");
    }

  }

  private static ChannelCollection populateChannels(){
    ChannelCollection channels = new ChannelCollectionImpl();
    channels.addChannel(new Channel(93.0, ChannelTypeEnum.ENGLISH));
    channels.addChannel(new Channel(93.5, ChannelTypeEnum.HINDI));
    channels.addChannel(new Channel(94.0, ChannelTypeEnum.FRENCH));
    channels.addChannel(new Channel(94.5, ChannelTypeEnum.ENGLISH));
    channels.addChannel(new Channel(95.0, ChannelTypeEnum.HINDI));
    channels.addChannel(new Channel(95.5, ChannelTypeEnum.FRENCH));
    channels.addChannel(new Channel(96.0, ChannelTypeEnum.ENGLISH));
    channels.addChannel(new Channel(96.5, ChannelTypeEnum.HINDI));
    channels.addChannel(new Channel(97.0, ChannelTypeEnum.HINDI));
    return channels;

  }
}
