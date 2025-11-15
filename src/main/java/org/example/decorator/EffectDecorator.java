package org.example.decorator;

public abstract class EffectDecorator implements AudioStream {
    protected AudioStream wrappedStream;
    public EffectDecorator (AudioStream wrappedStream)
    {
        this.wrappedStream = wrappedStream;
    }
    @Override
    public void play()
    {
        wrappedStream.play();
    }
    @Override
    public String processLyrics(String lyrics) {
        return wrappedStream.processLyrics(lyrics);
    }
}
