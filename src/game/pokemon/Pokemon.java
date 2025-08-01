package game.pokemon;

import java.util.Random;

import engine.Manager;
import engine.actors.VisualActor;
import engine.graphic.Animation;
import game.trainer.Trainer;

public class Pokemon extends VisualActor {
	// pokemongo.fandom.com/wiki/Combat_Power
    private static final double[] LEVEL_SCALARS = { 
        0.094000000, 0.166397870, 0.215732470, 0.255720050, 0.290249880, //01-05
        0.321087600, 0.349212680, 0.375235590, 0.399567280, 0.422500010, //05-10
        0.443107550, 0.462798390, 0.481684950, 0.499858440, 0.517393950, //10-15
        0.534354330, 0.550792690, 0.566754520, 0.582278910, 0.597400010, //15-20
        0.612157290, 0.626567130, 0.640652950, 0.654435630, 0.667934000, //20-25
        0.681164920, 0.694143650, 0.706884210, 0.719399090, 0.731700000, //25-30
        0.743789430, 0.755685510, 0.767397170, 0.778932750, 0.790300010, //30-35
        0.801496010, 0.812534010, 0.823416010, 0.834144010, 0.844720010, //35-40
        0.795300010, 0.800300010, 0.805300010, 0.810300010, 0.815300010, //40-45
        0.820300010, 0.825300010, 0.830300010, 0.835300010, 0.840300010  //45-50
    };
	//Individual Values
    private Pokedex stats;
    private final int ivHp;
    private final int ivAttack;
    private final int ivDefense;
    // Stored calculated stats
    private int hp;
    private int attack;
    private int defense;
    private int score;
	//
    private int level;
    private int exp;
	// Combat related
	private Trainer trainer;
	private int currentHP; // The amount of health it currently has
	private int currentMP; // The amount of energy it has to perform charged attacks
	private int currentCD; // The number of turns it needs to wait to perform an action

    public Pokemon(Manager manager, Pokedex stats) {
        super(new Animation(stats.getSpriteSheet(), 1, 1));
        this.stats = stats;

        Random random = manager.getRandom();
        this.ivHp = random.nextInt(16);      
        this.ivAttack = random.nextInt(16);  
        this.ivDefense = random.nextInt(16); 

        this.currentHP = 0;
        this.currentMP = 0;
        this.currentCD = 0;
		this.level = 0;
		this.trainer = null;

		addExperience(0);
    } 

    public int getCurrentCD() {
        return currentCD;
    }

	public int getCurrentHP() {
		return currentHP;
	}

	public int getCurrentMP() {
		return currentMP;
	}

    public void setCurrentCD(int currentCD) {
        this.currentCD = currentCD;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setCurrentMP(int currentMP) {
        this.currentMP = currentMP;
    }

	public Trainer getTrainer() {
		return trainer;
	}

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Pokedex getStats() {
        return stats;
    }

    public int getScore() {
        return score;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
        double levelScalar = LEVEL_SCALARS[newLevel - 1];
        this.hp = (int) Math.round((stats.getBaseHp() + ivHp) * levelScalar);
        this.currentHP = this.hp;
        this.attack = (int) Math.round((stats.getBaseAttack() + ivAttack) * levelScalar);
        this.defense = (int) Math.round((stats.getBaseDefense() + ivDefense) * levelScalar);
        this.score = (int) Math.round(((stats.getBaseAttack() + ivAttack) * 
                                              Math.sqrt(stats.getBaseDefense() + ivDefense) * 
                                              Math.sqrt(stats.getBaseHp() + ivHp) * 
                                              Math.pow(levelScalar, 2)) / 10);

		checkEvolution();
    }

    private void checkEvolution() {
		if(stats.getPokemonToEvolve() != null && level >= stats.getLevelToEvolve()){
            this.stats = stats.getPokemonToEvolve();
			setLevel(level);
            setAnimation(new Animation(stats.getSpriteSheet(), 1, 1));
        }
    }

	// Medium Fast - bulbapedia.bulbagarden.net/wiki/exp
    public void addExperience(int exp) {
        this.exp += exp;
        int expForNextLevel = level * level * level; 
        while (this.exp >= expForNextLevel && level < LEVEL_SCALARS.length) {
            setLevel(level + 1);
            expForNextLevel = level * level * level;
        }
    }
}