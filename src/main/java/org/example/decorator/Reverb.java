package org.example.decorator;

public class Reverb extends EffectDecorator
{
    public Reverb(AudioStream stream)
    {
        super(stream);

    }


    @Override
    public void play()
    {
        System.out.println("[Effect] Reverb played :  ");
        super.play();
    }
    @Override
    public String processLyrics(String lyrics)
    {
        return super.processLyrics(lyrics)
                .replaceAll("(\\b\\w+\\b)", "$1...");
    }
}
