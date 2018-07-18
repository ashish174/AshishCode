package designpattern.structural.adapterpattern.languagetranslator;

public class HinditoEnglishTranslator implements Translator {
  HindiSpeaker hindiSpeaker;

  public HinditoEnglishTranslator(HindiSpeaker hindiSpeaker) {
    this.hindiSpeaker = hindiSpeaker;
  }

  @Override
  public String translate(String sentence) {
    return "English Translation :"+sentence;
  }
}
