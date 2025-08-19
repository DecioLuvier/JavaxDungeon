![POKEMON](images/Pokemon_logo.png)

# ***Programmers***
- [Lucas Lemes](https://github.com/L3mSv) 
- [Décio Luvier](https://github.com/DecioLuvier) 

 # ***Introduction***

Pokémon is a franchise from The Pokémon Company, created in 1995 by 
Satoshi Tajiri. The game centers on fictional creatures called “Pokémon”. These 
creatures can be captured and trained by humans to fight against each other 
like a sport.
    

<div style="display: flex; align-items: center; gap: 10px;">
  <div style="flex: 1;">
    <p>
      The work consists of implementing the Pokémon game using the <strong>Java language</strong>.
    </p>
    <p>
    The game that must be implemented is a variant of the original game, as it has specific characteristics 
    and different. The Pokémon will be dispersed in a rectangular area divided into cells, similar to a table.  A cell may or may not contain a Pokémon. This area will also have a subdivision for 
    represent the specific regions of existing Pokémon types. In each play, the player must choose 
    a cell in search of Pokémon. If a wild Pokémon is found, the Poké Ball will be used to attempt 
    capture it. If the Pokémon found is from another Trainer, a battle will be held between them. In this 
    version of the game, the winning Pokémon also gains experience points. These points will be updated every 
    play to indicate the winning player so far. The game ends when all available Pokémon 
    have been captured. In the first case, the winning player will be the one who has the Pokémon team with the most 
    experience score.
    </p>

  </div>
  <div style="flex: 1;">
    <img src="images/pikachu.png" alt="Pikachu" style="max-width: 100%;">
  </div>
</div>


# ***Class Diagram*** 
This is the resulting class diagram of the program, designed using the PlantUML tool

![ClassDiagram](images/ClassDiagram.png)

>[!NOTE]
>If you are interested in viewing this diagram on your machine, you will need to 
>download the tool from the [PlantUML](plantuml.com) website or if you are using 
>Vscode IDE, it provides 
>a plantUML extension that already does the job, but I still recommend taking a look
>at the website to see what the code syntax is like at least.

# ***Concepts Used***

✅ - **Composition** <br/>
✅ - **Inheritance** <br/>
✅ - **Builder Pattern Design** <br/>
✅ - **Singleton Pattern Design** <br/>
✅ - **Polimorfism**  <br/>
✅ - **ArrayList** <br/>
✅ - **Encapsulation** <br/>
✅ - **Interface** <br/>
✅ - **Exception** <br/>
✅ - **Abstract Class** <br/> 
✅ - **Concurrent programming** <br/>

# ***Run the Program***

# ***Examples of use***

## **Initial Screen**
<div style="display: flex; align-items: center; gap: 10px;">
  <div style="flex: 1;">
  <p>
  The home screen features a retro Game Boy-inspired layout, with blue borders and digital buttons reminiscent of the classic handheld console. In the center, the Pokémon UFPEL Version logo is prominently displayed, accompanied by a mysterious purple Pokémon silhouette, conveying an air of mystery and challenge.

  At the bottom of the screen, the main menu offers four options:

  - <b>New Game</b>: Starts a new journey.

  - <b>Continue Game</b>: Resumes saved progress.

  - <b>Custom Level</b>: Allows you to play custom levels (disabled by default).

  - <b>Level Builder</b>: Level creation mode (also disabled initially).

  The design evokes the aesthetics of the franchise's classic games, conveying nostalgia and authenticity to players right from the start.
  </p>

  </div>
  <div style="flex: 1;">
    <img src="images/BeginScreen.png" alt="Initial Screen" style="max-width: 100%;">
  </div>
</div>

## **Grid Screen** 

<div style="display: flex; align-items: center; gap: 10px; flex-direction: row-reverse;">
  <div style="flex: 1;">
    <p>
    This is the game board screen. The board itself is represented by blocks where there may or may not be a hidden Pokémon. This will be the dynamic of the game where the player will be competing against the computer to find all the Pokémon hidden on the board and, at the end, comparing the number of Pokémon caught between the player and the computer to decide the winner.
      <br><br>
      There are also two other information sections, located at the top is the player statistics screen and on the side is the user's pokedex.
    </p>
  </div>
  <div style="flex: 1;">
    <img src="images/GridScreen.png" alt="GridScreen" style="max-width: 100%;">
  </div>
</div>

## **Player Status**

<div style="display: flex; align-items: center; gap: 10px;">
  <div style="flex: 1;">
    <p>
    This is the game board screen. The board itself is represented by blocks where there may or may not be a hidden Pokémon. This will be the dynamic of the game where the player will be competing against the computer to find all the Pokémon hidden on the board and, at the end, comparing the number of Pokémon caught between the player and the computer to decide the winner.
      <br><br>
      There are also two other information sections, located at the top is the player statistics screen and on the side is the user's pokedex.
    </p>
  </div>
  <div style="flex: 1;">
    <img src="images/PlayerStatusScreen.png" alt="Player Status Screen" style="max-width: 100%;">
  </div>
</div>

## **Pokedex**

<div style="display: flex; align-items: center; gap: 10px; flex-direction: row-reverse;">
  <div style="flex: 1;">
    <p>
    This is the game board screen. The board itself is represented by blocks where there may or may not be a hidden Pokémon. This will be the dynamic of the game where the player will be competing against the computer to find all the Pokémon hidden on the board and, at the end, comparing the number of Pokémon caught between the player and the computer to decide the winner.
      <br><br>
      There are also two other information sections, located at the top is the player statistics screen and on the side is the user's pokedex.
    </p>
  </div>
  <div style="flex: 1;">
    <img src="images/PokemonInfoScreen.png" alt="Pokedex Screen" style="max-width: 100%;">
  </div>
</div>

# **Battle** 

<div style="display: flex; align-items: center; gap: 10px;">
  <div style="flex: 1;">
    <p>
    This is the game board screen. The board itself is represented by blocks where there may or may not be a hidden Pokémon. This will be the dynamic of the game where the player will be competing against the computer to find all the Pokémon hidden on the board and, at the end, comparing the number of Pokémon caught between the player and the computer to decide the winner.
      <br><br>
      There are also two other information sections, located at the top is the player statistics screen and on the side is the user's pokedex.
    </p>
  </div>
  <div style="flex: 1;">
    <img src="images/BattleScreen.png" alt="Battle Screen" style="max-width: 100%;">
  </div>
</div>




# ***Difficulties***

# ***Conclusion***