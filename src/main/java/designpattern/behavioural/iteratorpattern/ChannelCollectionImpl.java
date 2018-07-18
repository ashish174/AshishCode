package designpattern.behavioural.iteratorpattern;

import java.util.ArrayList;
import java.util.List;

/*
Notice the inner class implementation of iterator interface
so that the implementation can’t be used by any other collection.
Same approach is followed by collection classes also and all of them have inner
class implementation of Iterator interface.
*/

public class ChannelCollectionImpl implements ChannelCollection{
  private List<Channel> channelsList;

  public ChannelCollectionImpl() {
    channelsList = new ArrayList<>();
  }

  public void addChannel(Channel channel) {
    this.channelsList.add(channel);
  }

  public void removeChannel(Channel channel) {
    this.channelsList.remove(channel);
  }

  @Override
  public ChannelIterator iterator(ChannelTypeEnum type) {
    return new ChannelIteratorImpl(type, this.channelsList);
  }

  private class ChannelIteratorImpl implements ChannelIterator{
    private ChannelTypeEnum TYPE;
    private List<Channel> channelList;
    private int position;

    public ChannelIteratorImpl(ChannelTypeEnum TYPE, List<Channel> channelList) {
      this.TYPE = TYPE;
      this.channelList = channelList;
    }

    @Override
    public boolean hasNext() {
      while (position < channelList.size()){
        Channel channel = channelList.get(position);
        if((TYPE.equals(ChannelTypeEnum.ALL)) || channel.getTYPE().equals(TYPE)){
          return true;
        } else{
          position++;
        }
      }
      return false;
    }

    @Override
    public Channel next() {
      Channel channel = channelList.get(position);
      position++;
      return channel;
    }
  }
}
