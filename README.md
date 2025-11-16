# 🎵 Console Music Player (Java)  
A fully-featured console-based music player application built using **multiple classic design patterns**:  
**Builder, Strategy, Observer, Decorator, Facade, Adapter**.

This project demonstrates clean architecture, modular design, separation of concerns, and extensibility.  
It includes playlists, playback strategies, lyrics loading, audio effects, observers, and a facade-based unified API.

***

# 📌 Table of Contents
- Features  
- Project Structure  
- Design Patterns Used  
- How It Works  
- Class Diagrams (UML)  
- Console Menu Overview  
- Running the Application  
- Example Output  

***

# ⭐ Features

✔ Multiple playlists  
✔ Play / Pause / Next  
✔ 3 Playback strategies  
✔ Runtime audio effects (Decorator)  
✔ Lyrics loading with Adapter  
✔ Facade for simplified playback  
✔ Observer notifications (UI + Analytics)  
✔ Builder for Track creation  

***

# 📁 Project Structure

src/main/java/org/example
│
├── adapter/
│     ├── LyricsAdapter
│     ├── LyricsProvider
│     └── ExternalLyricsService
│
├── builder/
│     ├── Builder
│     └── TrackBuilder
│
├── decorator/
│     ├── AudioStream
│     ├── EffectDecorator
│     ├── BaseStream
│     ├── BassBoost
│     ├── Echo
│     └── Reverb
│
├── facade/
│     ├── MusicFacade
│     ├── PlayerService
│     ├── PlaylistManager
│     └── SongLoader
│
├── menu/
│     └── ConsoleMenu
│
├── models/
│     ├── Track
│     └── Playlist
│
├── observer/
│     ├── PlayerObserver
│     ├── PlayerSubject
│     ├── UiListener
│     └── AnalyticsListener
│
├── strategy/
│     ├── Player
│     ├── PlayBackStrategy
│     ├── SequentialStrategy
│     ├── ShuffleStrategy
│     └── RepeatStrategy
│
└── Application.java

***

# 🧩 Design Patterns Used

## 🧱 Builder
Used for constructing `Track` objects step-by-step using `TrackBuilder`.

Avoids telescoping constructors and allows controlled construction of complex objects.

***

## 🎚 Strategy
Three playback strategies:

- Sequential  
- Shuffle  
- Repeat  

Allows switching playback logic at runtime.

***

## 👀 Observer
Observers:  
- UiListener  
- AnalyticsListener  

Player notifies them about:  
- play  
- pause  
- track changed

Decouples UI and analytics from core logic.

***

## 🎛 Decorator
Applies dynamic audio/lyrics effects:

- BassBoost  
- Echo  
- Reverb  

Chain example:

BaseStream → BassBoost → Echo → Reverb

***

## 🎹 Facade
`MusicFacade` unifies:

- PlayerService  
- SongLoader  
- PlaylistManager  
- LyricsAdapter  

Simplifies interaction with subsystems.

***

## 🔌 Adapter
Converts:

"Queen - Bohemian Rhapsody"
→
lyrics/Queen-BohemianRhapsody.txt

Allows switching to real APIs later.

***

# ⚙ How It Works

## ▶ Playback Flow

ConsoleMenu → Player → Observers

## 🎵 Play with Effects Flow

ConsoleMenu  
↓  
buildEffects() → decorators  
↓  
MusicFacade.playTrack()  
↓  
PlayerService.play() → stream.play()

## 📝 Lyrics Flow

ConsoleMenu  
↓  
LyricsAdapter.getLyrics()  
↓  
ExternalLyricsService.fetchLyricText()  
↓  
Decorator.processLyrics()

***

# 🧱 Class Diagrams (UML)

Decorator Pattern:
AudioStream ← EffectDecorator ← (BassBoost, Echo, Reverb) ← BaseStream

Strategy Pattern:
PlayBackStrategy ← (Sequential, Shuffle, Repeat)
Player → uses PlayBackStrategy

Facade Pattern:
MusicFacade → (PlayerService, SongLoader, PlaylistManager)

Adapter Pattern:
LyricsProvider ← LyricsAdapter ← ExternalLyricsService

***

# 🖥 Console Menu Overview

1. Play  
2. Pause  
3. Next  
4. Change Strategy  
5. Select Track  
6. Change Playlist  
7. Show Current Track  
8. Show All Playlists  
9. Create New Playlist  
10. Play with Effects  
11. Choose Effects  
12. Show Lyrics  
0. Exit  

***

# 🚀 Running the Application

IntelliJ IDEA: Run Application.java  
CLI:

mvn clean package  
java -jar target/music-app.jar

***

# 🎬 Example Output

=== PLAYBACK START ===  
PlayerService: Starting playback...  
--- EFFECTS APPLIED ---  
[Effect] Echo  
[Effect] Reverb  
Playing track: Queen - Bohemian Rhapsody  
--- PLAYBACK END ---  

=== LYRICS (WITH EFFECTS) ===  
Mama, just killed a man... man...  
Put a gun against his head... head...

***

# 🏁 Conclusion

This project demonstrates:

- Clean architecture  
- Strong OOP design  
- Multiple design patterns working together  
- Extendable and maintainable structure  

Perfect for academic defense and portfolio.

