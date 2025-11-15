package org.example.decorator;

public class BassBoost extends EffectDecorator
{
    public BassBoost(AudioStream stream){
        super(stream);
    }


    @Override
    public void play() {
        System.out.println("[Effect] Bass Boost applied ");
        super.play();
    }
}
