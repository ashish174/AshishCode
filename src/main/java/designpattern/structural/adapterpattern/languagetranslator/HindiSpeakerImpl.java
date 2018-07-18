package designpattern.structural.adapterpattern.languagetranslator;

public class HindiSpeakerImpl implements HindiSpeaker {

  String name;

  public HindiSpeakerImpl(String name) {
    this.name = name;
  }

  @Override
  public String speakHindi(String sentence) {
    System.out.println("Speaking Hindi: "+sentence);
    return sentence;
  }

  @Override
  public String listenHindi(String sentence) {
    System.out.println("Listening Hindi: "+sentence);
    return sentence;
  }
}
