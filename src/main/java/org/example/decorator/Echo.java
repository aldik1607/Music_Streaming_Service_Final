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
}
