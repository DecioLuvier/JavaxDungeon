package game.data;

import engine.graphic.SpriteSheet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Pokedex {
    private final String id;
    private final String name;
    private final Type mainType;
    private final Type secondaryType;
    private final int baseHp;
    private final int baseAttack;
    private final int baseDefense;
    private final Move fastMove;
    private final Move chargedMove;
    private final int levelToEvolve;
    private final Pokedex pokemonToEvolve;
    private final int catchRate;
    private final int baseExp;
    private final SpriteSheet spriteSheet;

    private Pokedex(String id, String name, Type mainType, Type secondaryType, int baseHp,
                   int baseAttack, int baseDefense, Move fastMove, Move chargedMove,
                   int levelToEvolve, Pokedex pokemonToEvolve, int catchRate, int baseExp,
                   int spriteCount) {
        this.id = id;
        this.name = name;
        this.mainType = mainType;
        this.secondaryType = secondaryType;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.fastMove = fastMove;
        this.chargedMove = chargedMove;
        this.levelToEvolve = levelToEvolve;
        this.pokemonToEvolve = pokemonToEvolve;
        this.catchRate = catchRate;
        this.baseExp = baseExp;
        this.spriteSheet = new SpriteSheet("images/pokemons/" + id + ".png", spriteCount, 6, 0, 0);
    }

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public Type getMainType() { 
        return mainType; 
    }

    public Type getSecondaryType() { 
        return secondaryType; 
    }

    public int getBaseHp() {
        return baseHp; 
    }

    public int getBaseAttack() { 
        return baseAttack; 
    }

    public int getBaseDefense() { 
        return baseDefense; 
    }

    public Move getFastMove() { 
        return fastMove; 
    }

    public Move getChargedMove() { 
        return chargedMove; 
    }

    public int getLevelToEvolve() { 
        return levelToEvolve;
    }

    public Pokedex getPokemonToEvolve() { 
        return pokemonToEvolve; 
    }

    public int getCatchRate() { 
        return catchRate; 
    }

    public int getBaseExp() { 
        return baseExp; 
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public static Pokedex get(String Name) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Pokemons.xml"));
            doc.getDocumentElement().normalize();
            NodeList pokemonList = doc.getElementsByTagName("pokemon");

            for (int i = 0; i < pokemonList.getLength(); i++) {
                Element pokemon = (Element) pokemonList.item(i);
                
                if (pokemon.getAttribute("name").equals(Name)) {
                    return new Pokedex(
                        pokemon.getAttribute("id"),
                        pokemon.getAttribute("name"),
                        Type.get(pokemon.getAttribute("mainType")),
                        Type.get(pokemon.getAttribute("secondaryType")),
                        Integer.parseInt(pokemon.getAttribute("baseHp")),
                        Integer.parseInt(pokemon.getAttribute("baseAttack")),
                        Integer.parseInt(pokemon.getAttribute("baseDefense")),
                        Move.get(pokemon.getAttribute("fastMove")),
                        Move.get(pokemon.getAttribute("chargedMove")),
                        Integer.parseInt(pokemon.getAttribute("levelToEvolve")),
                        Pokedex.get(pokemon.getAttribute("pokemonToEvolve")),
                        Integer.parseInt(pokemon.getAttribute("catchRate")),
                        Integer.parseInt(pokemon.getAttribute("baseExp")),
                        Integer.parseInt(pokemon.getAttribute("spriteCount"))
                    );
                }
            }

            System.err.println("Pokémon with name " + Name + " not found.");
            return null;

        } catch (Exception e) {
            System.err.println("Error loading Pokedex from XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}