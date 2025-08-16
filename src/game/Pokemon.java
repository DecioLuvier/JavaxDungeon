package game;

import game.datas.Move;
import game.datas.Pokedex;
import game.datas.Type;

public class Pokemon {
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

    private Pokedex stats;
    private Trainer trainer;
    private int hp, attack, defense, score;
	private int currentHP, currentMP, currentCD, level, exp;

    public Pokemon(Pokedex stats) {
        this.stats = stats;
        this.currentHP = 0;
        this.currentMP = 0;
        this.currentCD = 0;
		this.level = 0;
		this.trainer = null;
		addExperience(0);
    } 

    public int getCurrentCD() { return currentCD; }
	public int getCurrentHP() { return currentHP; }
	public int getCurrentMP() { return currentMP; }
	public Trainer getTrainer() { return trainer; }
    public Pokedex getStats() { return stats; }
    public int getScore() { return score; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getLevel() { return level; }
    public void setCurrentCD(int currentCD) { this.currentCD = currentCD; }
    public void setCurrentHP(int currentHP) { this.currentHP = currentHP; }
    public void setCurrentMP(int currentMP) { this.currentMP = currentMP; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    public void setLevel(int newLevel) {
        this.level = newLevel;
        double levelScalar = LEVEL_SCALARS[newLevel - 1];
        this.hp = (int) Math.round((stats.getBaseHp()) * levelScalar);
        this.currentHP = this.hp;
        this.attack = (int) Math.round((stats.getBaseAttack()) * levelScalar);
        this.defense = (int) Math.round((stats.getBaseDefense()) * levelScalar);
        this.score = (int) Math.round(((stats.getBaseAttack()) * 
                                        Math.sqrt(stats.getBaseDefense()) * 
                                        Math.sqrt(stats.getBaseHp()) * 
                                        Math.pow(levelScalar, 2)) / 10);

		checkEvolution();
    }

    private void checkEvolution() {
		if(stats.getPokemonToEvolve() != null && level >= stats.getLevelToEvolve()){
            this.stats = stats.getPokemonToEvolve();
			setLevel(level);
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

    public static int calculateDamage(Pokemon attacker, Pokemon target, Move move) {
        final int power = move.getPower();
        final int attackStat = attacker.getAttack();
        final int defenseStat = target.getDefense();
        final double modifier = calculateTypeMultiplier(move.getType(), target.getStats().getMainType(), target.getStats().getSecondaryType());
        double stab = 1.0;
        if (move.getType().equals(attacker.getStats().getMainType()) || move.getType().equals(attacker.getStats().getSecondaryType())) 
            stab = 1.2;
        double trainerBonus = 1.0;
        if (target.getTrainer() != null) 
            trainerBonus = 1.3;
        return (int) (0.5 * power * ((double) attackStat / defenseStat) * modifier * stab * trainerBonus) + 1;
    }

    public static int calculateXP(Pokemon pokemon) {
        final int baseXP = pokemon.getStats().getBaseExp();
        final int level = pokemon.getLevel();
        double trainerBonus = 1.0;
        if (pokemon.getTrainer() != null) 
            trainerBonus = 1.5;
        return (int) Math.round((baseXP * level / 7.0) * trainerBonus);
    }

    public static double calculateTypeMultiplier(Type attack, Type defenderMain, Type defenderSecondary) {
        double multiplierMain = 1.0;
        double multiplierSecondary = 1.0;
        if (defenderMain != null) 
            multiplierMain = defenderMain.getResistances().getOrDefault(attack.getName(), 1.0);
        if (defenderSecondary != null) 
            multiplierSecondary = defenderSecondary.getResistances().getOrDefault(attack.getName(), 1.0);
        return multiplierMain * multiplierSecondary;
    }

    public static double calculateCaptureChance(Pokemon pokemon) {
        final int HPmax = pokemon.getHp();
        final int HPcurrent = pokemon.getCurrentHP();
        final double catchRate = pokemon.getStats().getCatchRate();
        return Math.min(1.0, (((3.0 * HPmax - 2.0 * HPcurrent) / (3.0 * HPmax)) * catchRate) / 255.0);
    }
}