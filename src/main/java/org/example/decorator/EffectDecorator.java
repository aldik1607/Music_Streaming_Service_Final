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
}
