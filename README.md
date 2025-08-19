![POKEMON](images/Pokemon_logo.png)

# ***Programmers***
- [Lucas Lemes](https://github.com/L3mSv) 
- [Décio Luvier](https://github.com/DecioLuvier) 

 # ***Introduction***

Pokémon is a franchise from The Pokémon Company, created in 1995 by 
Satoshi Tajiri. The game centers on fictional creatures called “Pokémon”. These 
creatures can be captured and trained by humans to fight against each other 
like a sport.
    

<p align="left">
  <img src="images/pikachu.png" width="200" align="right">
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

<marquee behavior="scroll" direction="right" scrollamount="10">
  <img src="gif/pikachuWalk.gif" width="120">
</marquee>

Run the program through an IDE with JAVA extension or compile the .java files and create a bytecode file run on it.

# ***Examples of use***

## **Initial Screen**

<table>
  <tr>
    <td width="60%">
    <p>
  The home screen features a retro Game Boy-inspired layout, with blue borders and digital buttons reminiscent of the classic handheld console. In the center, the Pokémon UFPEL Version logo is prominently displayed, accompanied by a mysterious purple Pokémon silhouette, conveying an air of mystery and challenge.

  At the bottom of the screen, the main menu offers four options:

  - <b>New Game</b>: Starts a new journey.

  - <b>Continue Game</b>: Resumes saved progress.

  - <b>Custom Level</b>: Allows you to play custom levels (disabled by default).

  - <b>Level Builder</b>: Level creation mode (also disabled initially).

  The design evokes the aesthetics of the franchise's classic games, conveying nostalgia and authenticity to players right from the start.
  </p>
    </td>
    <td width="40%">
      <img src="images/BeginScreen.png" alt="BeginScreen" width="200">
    </td>
  </tr>
</table>

## **Grid Screen** 

<table>
  <tr>
    <td width="40%">
      <img src="images/GridScreen.png" alt="GridScreen" width="200">
    </td>
    <td width="60%">
      <p>
        This is the game board screen. The board itself is represented by blocks where there may or may not be a hidden Pokémon. This will be the dynamic of the game where the player will be competing against the computer to find all the Pokémon hidden on the board and, at the end, comparing the number of Pokémon caught between the player and the computer to decide the winner.
        <br><br>
        There are also two other information sections, located at the top is the player statistics screen and on the side is the user's pokedex.
      </p>
    </td>
  </tr>
</table>


## **Player Status**

<table>
  <tr>
    <td width="60%">
    <p>
  This is the player status window in it shows the amount of pokemons captured by both the player and the computer, in addition to this data there are also three specific icons 
  <br></br>
  💾 : Save the game <br>
  ⭐️ : Receive a tip <br>
  ↩️ : Back to main menu <br>
  </p>
    </td>
    <td width="40%">
      <img src="images/PlayerStatusScreen.png" alt="PlayerStatus" width="200">
    </td>
  </tr>
</table>

## **Pokedex**

<table>
  <tr>
    <td width="60%">
    <p>
    This is the player's pokedex tab in it appear the player's pokemons having a maximum amount of 6 pokemons and can change their order in your team being the pokemon that is more to the top of pokedex your main pokemon and that will start in battle 
    </p>
    </td>
    <td width="40%">
      <img src="images/PokemonInfoScreen.png" alt="PokemonInfo" width="50">
    </td>
  </tr>
</table>

# **Battle** 

<table>
  <tr>
    <td width="60%">
    <p>
  When you find a pokemon on the board you will be directed to battle, it is practically the same as a classic pokemon game having the options of FIGHT, PKM, BALL and RUN, the only difference compared to the battles of the classic games and that the fight does not occur in turns but sequentially live making the player choose his options during the battle 

  <b>FIGHT</b> : Choose the pokemon skill <br>

  <b>PKM</b>: Pokemon exchange <br>

  <b>BALL</b>: Play pokeball to capture wild pokemon <br>

  <b>RUN</b> : Out of battle <br>
    </p>
    </td>
    <td width="40%">
      <img src="images/BattleScreen.png" alt="Battle" width="200">
    </td>
  </tr>
</table>




# ***Difficulties***

The greatest difficulty of the work was the structuring based on a design pattern 

# ***Conclusion***

Finally this work provides a program in pokemon theme for the community to have fun and analyze the structured code, using concepts learned in OOP classes efficiently in order to optimize the program and especially work with the main concept of the JAVA language "code reuse".

For those of you playing, feel free to explore the game's features and the code itself!

![endImage](images/endImage.jpg)