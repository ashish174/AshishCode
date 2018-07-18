package designpattern.structural.adapterpattern.languagetranslator;

public class EnglishtoHindiTranslator implements Translator {
  EnglishSpeaker englishSpeaker;

  public EnglishtoHindiTranslator(EnglishSpeaker englishSpeaker) {
    this.englishSpeaker = englishSpeaker;
  }

  @Override
  public String translate(String sentence) {
    return "Hindi Translation :"+sentence;
  }
}
