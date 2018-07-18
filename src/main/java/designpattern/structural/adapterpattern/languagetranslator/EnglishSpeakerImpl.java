package designpattern.structural.adapterpattern.languagetranslator;

public class EnglishSpeakerImpl implements EnglishSpeaker {
  String name;

  public EnglishSpeakerImpl(String name) {
    this.name = name;
  }

  @Override
  public String speakEnglish(String sentence) {
    System.out.println("Speaking English: "+sentence);
    return sentence;
  }

  @Override
  public String listenEnglish(String sentence) {
    System.out.println("Listening English: "+sentence);
    return sentence;
  }
}
