package designpattern.structural.adapterpattern.languagetranslator;

public class TestTranslator {
  public static void main(String[] args) {
    EnglishSpeaker englishSpeaker = new EnglishSpeakerImpl("James");
    HindiSpeaker hindiSpeaker = new HindiSpeakerImpl("Ashish");
    EnglishtoHindiTranslator englishtoHindiTranslator = new EnglishtoHindiTranslator(englishSpeaker);
    HinditoEnglishTranslator hinditoEnglishTranslator = new HinditoEnglishTranslator(hindiSpeaker);
    englishSpeaker.listenEnglish(
        hinditoEnglishTranslator.translate(hindiSpeaker.speakHindi("Namaskar")));
    hindiSpeaker.listenHindi(englishtoHindiTranslator.translate(englishSpeaker.speakEnglish("GoodMorning Bud")));

  }
}
