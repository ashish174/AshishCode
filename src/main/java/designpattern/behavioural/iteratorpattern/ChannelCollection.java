package designpattern.behavioural.iteratorpattern;

public interface ChannelCollection {
  public void addChannel(Channel channel);
  public void removeChannel(Channel channel);
  public ChannelIterator iterator(ChannelTypeEnum TYPE);
}
