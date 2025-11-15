    package org.example.decorator;

    public class BassBoost extends EffectDecorator
    {
        public BassBoost(AudioStream stream){
            super(stream);
        }


        @Override
        public void play() {
            System.out.println("[Effect] BASS BOOST!!! ");
            super.play();
        }
        @Override
        public String processLyrics(String lyrics) {
            return super.processLyrics(lyrics).toUpperCase();
        }
    }
