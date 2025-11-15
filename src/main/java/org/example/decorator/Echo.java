package org.example.decorator;

public class Echo extends EffectDecorator
{
    public Echo(AudioStream stream)
    {
        super(stream);
    }


    @Override
    public void play ()
    {
        System.out.println("[Effect] Echo applied :  ");
        super.play();

    }
    @Override
    public String processLyrics(String lyrics)
    {
        return super.processLyrics(lyrics)
                .replaceAll("(\\b\\w+\\b)", "$1oooo");
    }
}
