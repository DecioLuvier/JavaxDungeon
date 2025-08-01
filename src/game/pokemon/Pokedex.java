package game.pokemon;

import engine.graphic.SpriteSheet;

public enum Pokedex {
    VENUSAUR(new SpriteSheet("src/game/pokemon/sprites/3.png", 14, 6, 0, 0), "VENUSAUR", Type.GRASS, Type.POISON, 190, 198, 189, Move.VINE_WHIP, Move.SLUDGE_BOMB, 0, null, 45 ,263),
    IVYSAUR(new SpriteSheet("src/game/pokemon/sprites/2.png", 10, 6, 0, 0), "IVYSAUR", Type.GRASS, Type.POISON, 155, 151, 143, Move.VINE_WHIP, Move.SLUDGE_BOMB, 32, Pokedex.VENUSAUR, 45 ,142),
    BULBASAUR(new SpriteSheet("src/game/pokemon/sprites/1.png", 14, 6, 0, 0), "BULBASAUR", Type.GRASS, Type.POISON, 128, 118, 111, Move.VINE_WHIP, Move.SLUDGE_BOMB, 16, Pokedex.IVYSAUR, 45 ,64),
    CHARIZARD(new SpriteSheet("src/game/pokemon/sprites/6.png", 11, 6, 0, 0), "CHARIZARD", Type.FIRE, Type.FLYING, 188, 223, 176, Move.WING_ATTACK, Move.BLAST_BURN, 0, null, 45 ,267),
    CHARMELEON(new SpriteSheet("src/game/pokemon/sprites/5.png", 16, 6, 0, 0), "CHARMELEON", Type.FIRE, Type.NONE, 151, 158, 129, Move.FIRE_FANG, Move.FLAMETHROWER, 36, Pokedex.CHARIZARD, 45 ,142),
    CHARMANDER(new SpriteSheet("src/game/pokemon/sprites/4.png", 12, 6, 0, 0), "CHARMANDER", Type.FIRE, Type.NONE, 118, 116, 96, Move.SCRATCH, Move.FLAMETHROWER, 16, Pokedex.CHARMELEON, 45 ,62),
    BLASTOISE(new SpriteSheet("src/game/pokemon/sprites/9.png", 10, 6, 0, 0), "BLASTOISE", Type.WATER, Type.NONE, 188, 171, 207, Move.WATER_GUN, Move.HYDRO_CANNON, 0, null, 45 ,265),
    WARTORTLE(new SpriteSheet("src/game/pokemon/sprites/8.png", 13, 6, 0, 0), "WARTORTLE", Type.WATER, null, 153, 144, 171, Move.WATER_GUN, Move.HYDRO_PUMP, 36, Pokedex.BLASTOISE, 45 ,142),
    SQUIRTLE(new SpriteSheet("src/game/pokemon/sprites/7.png", 14, 6, 0, 0), "SQUIRTLE", Type.WATER, Type.NONE, 127, 112, 142, Move.TACKLE, Move.AQUA_TAIL, 16, Pokedex.WARTORTLE, 45 ,63),
    BUTTERFREE(new SpriteSheet("src/game/pokemon/sprites/12.png", 13, 6, 0, 0), "BUTTERFREE", Type.BUG, Type.FLYING, 155, 167, 137, Move.BUG_BITE, Move.BUG_BUZZ, 0, null, 45 ,198),
    METAPOD(new SpriteSheet("src/game/pokemon/sprites/11.png", 11, 6, 0, 0), "METAPOD", Type.BUG, Type.NONE, 137, 45, 94, Move.BUG_BITE, Move.STRUGGLE, 10, Pokedex.BUTTERFREE, 120 ,72),
    CATERPIE(new SpriteSheet("src/game/pokemon/sprites/10.png", 13, 6, 0, 0), "CATERPIE", Type.BUG, Type.NONE, 128, 55, 62, Move.BUG_BITE, Move.STRUGGLE, 7, Pokedex.METAPOD, 255 ,39),
    BEEDRILL(new SpriteSheet("src/game/pokemon/sprites/15.png", 55, 6, 0, 0), "BEEDRILL", Type.BUG, Type.POISON, 163, 169, 130, Move.BUG_BITE, Move.SLUDGE_BOMB, 0, null, 45 ,198),
    KAKUNA(new SpriteSheet("src/game/pokemon/sprites/14.png", 7, 6, 0, 0), "KAKUNA", Type.BUG, Type.POISON, 128, 46, 86, Move.BUG_BITE, Move.STRUGGLE, 10, Pokedex.BEEDRILL, 120 ,72),
    WEEDLE(new SpriteSheet("src/game/pokemon/sprites/13.png", 23, 6, 0, 0), "WEEDLE", Type.BUG, Type.POISON, 120, 63, 55, Move.BUG_BITE, Move.STRUGGLE, 7, Pokedex.KAKUNA, 255 ,39),
    PIDGEOT(new SpriteSheet("src/game/pokemon/sprites/18.png", 16, 6, 0, 0), "PIDGEOT", Type.NORMAL, Type.FLYING, 195, 166, 154, Move.GUST, Move.AERIAL_ACE, 0, null, 45 ,240),
    PIDGEOTTO(new SpriteSheet("src/game/pokemon/sprites/17.png", 14, 6, 0, 0), "PIDGEOTTO", Type.NORMAL, Type.FLYING, 149, 117, 108, Move.STEEL_WING, Move.AERIAL_ACE, 36, Pokedex.PIDGEOT, 120 ,122),
    PIDGEY(new SpriteSheet("src/game/pokemon/sprites/16.png", 23, 6, 0, 0), "PIDGEY", Type.NORMAL, Type.FLYING, 120, 85, 73, Move.TACKLE, Move.AERIAL_ACE, 18, Pokedex.PIDGEOTTO, 255 ,50),
    RATICATE(new SpriteSheet("src/game/pokemon/sprites/20.png", 15, 6, 0, 0), "RATICATE", Type.NORMAL, Type.NONE, 146, 161, 139, Move.TACKLE, Move.HYPER_FANG, 0, null, 127 ,145),
    RATTATA(new SpriteSheet("src/game/pokemon/sprites/19.png", 16, 6, 0, 0), "RATTATA", Type.NORMAL, Type.NONE, 102, 103, 70, Move.TACKLE, Move.DIG, 20, Pokedex.RATICATE, 255 ,51),
    FEAROW(new SpriteSheet("src/game/pokemon/sprites/22.png", 16, 6, 0, 0), "FEAROW", Type.NORMAL, Type.FLYING, 163, 182, 133, Move.PECK, Move.FLY, 0, null, 90 ,155),
    SPEAROW(new SpriteSheet("src/game/pokemon/sprites/21.png", 21, 6, 0, 0), "SPEAROW", Type.NORMAL, Type.FLYING, 120, 112, 61, Move.PECK, Move.DRILL_PECK, 20, Pokedex.FEAROW, 255 ,52),
    ARBOK(new SpriteSheet("src/game/pokemon/sprites/24.png", 26, 6, 0, 0), "ARBOK", Type.POISON, Type.NONE, 155, 167, 153, Move.ACID, Move.GUNK_SHOT, 0, null, 90 ,157),
    EKANS(new SpriteSheet("src/game/pokemon/sprites/23.png", 38, 6, 0, 0), "EKANS", Type.POISON, Type.NONE, 111, 110, 97, Move.POISON_STING, Move.SLUDGE_BOMB, 22, Pokedex.ARBOK, 255 ,58),
    RAICHU(new SpriteSheet("src/game/pokemon/sprites/26.png", 11, 6, 0, 0), "RAICHU", Type.ELECTRIC, Type.NONE, 155, 193, 151, Move.THUNDER_SHOCK, Move.THUNDERBOLT, 0, null, 75 ,243),
    PIKACHU(new SpriteSheet("src/game/pokemon/sprites/25.png", 22, 6, 0, 0), "PIKACHU", Type.ELECTRIC, Type.NONE, 111, 112, 96, Move.THUNDER_SHOCK, Move.WILD_CHARGE, 0, Pokedex.RAICHU, 190 ,112),
    SANDSLASH(new SpriteSheet("src/game/pokemon/sprites/28.png", 13, 6, 0, 0), "SANDSLASH", Type.GROUND, Type.NONE, 181, 182, 200, Move.MUD_SHOT, Move.SCORCHING_SANDS, 0, null, 90 ,158),
    SANDSHREW(new SpriteSheet("src/game/pokemon/sprites/27.png", 19, 6, 0, 0), "SANDSHREW", Type.GROUND, Type.NONE, 137, 126, 145, Move.SAND_ATTACK, Move.DIG, 22, Pokedex.SANDSLASH, 255 ,60),
    NIDOQUEEN(new SpriteSheet("src/game/pokemon/sprites/31.png", 15, 6, 0, 0), "NIDOQUEEN", Type.POISON, Type.GROUND, 207, 182, 173, Move.POISON_STING, Move.EARTH_POWER, 0, null, 45 ,253),
    NIDORINA(new SpriteSheet("src/game/pokemon/sprites/30.png", 15, 6, 0, 0), "NIDORINA", Type.POISON, Type.NONE, 172, 117, 120, Move.POISON_STING, Move.SLUDGE_BOMB, 0, Pokedex.NIDOQUEEN, 120 ,128),
    NIDORAN_F(new SpriteSheet("src/game/pokemon/sprites/29.png", 15, 6, 0, 0), "NIDORAN_F", Type.POISON, Type.NONE, 146, 86, 94, Move.POISON_STING, Move.SLUDGE_BOMB, 16, Pokedex.NIDORINA, 235 ,55),
    NIDOKING(new SpriteSheet("src/game/pokemon/sprites/34.png", 11, 6, 0, 0), "NIDOKING", Type.POISON, Type.GROUND, 191, 204, 156, Move.POISON_STING, Move.EARTH_POWER, 0, null, 45 ,253),
    NIDORINO(new SpriteSheet("src/game/pokemon/sprites/33.png", 21, 6, 0, 0), "NIDORINO", Type.POISON, Type.NONE, 156, 137, 111, Move.POISON_JAB, Move.SLUDGE_BOMB, 0, Pokedex.NIDOKING, 120 ,128),
    NIDORAN_M(new SpriteSheet("src/game/pokemon/sprites/32.png", 15, 6, 0, 0), "NIDORAN_M", Type.POISON, Type.NONE, 130, 105, 76, Move.POISON_STING, Move.SLUDGE_BOMB, 16, Pokedex.NIDORINO, 235 ,55),
    CLEFABLE(new SpriteSheet("src/game/pokemon/sprites/36.png", 11, 6, 0, 0), "CLEFABLE", Type.FAIRY, Type.NONE, 216, 178, 162, Move.POUND, Move.METEOR_MASH, 0, null, 25 ,242),
    CLEFAIRY(new SpriteSheet("src/game/pokemon/sprites/35.png", 18, 6, 0, 0), "CLEFAIRY", Type.FAIRY, Type.NONE, 172, 107, 108, Move.POUND, Move.MOONBLAST, 0, Pokedex.CLEFABLE, 150 ,113),
    NINETALES(new SpriteSheet("src/game/pokemon/sprites/38.png", 15, 6, 0, 0), "NINETALES", Type.FIRE, Type.NONE, 177, 169, 190, Move.FIRE_SPIN, Move.OVERHEAT, 0, null, 75 ,177),
    VULPIX(new SpriteSheet("src/game/pokemon/sprites/37.png", 17, 6, 0, 0), "VULPIX", Type.FIRE, Type.NONE, 116, 96, 109, Move.QUICK_ATTACK, Move.FLAMETHROWER, 0, Pokedex.NINETALES, 190 ,60),
    WIGGLYTUFF(new SpriteSheet("src/game/pokemon/sprites/40.png", 14, 6, 0, 0), "WIGGLYTUFF", Type.NORMAL, Type.FAIRY, 295, 156, 90, Move.POUND, Move.PLAY_ROUGH, 0, null, 50 ,218),
    JIGGLYPUFF(new SpriteSheet("src/game/pokemon/sprites/39.png", 17, 6, 0, 0), "JIGGLYPUFF", Type.NORMAL, Type.FAIRY, 251, 80, 70, Move.POUND, Move.BODY_SLAM, 0, Pokedex.WIGGLYTUFF, 170 ,95),
    GOLBAT(new SpriteSheet("src/game/pokemon/sprites/42.png", 17, 6, 0, 0), "GOLBAT", Type.POISON, Type.FLYING, 181, 161, 150, Move.WING_ATTACK, Move.SHADOW_BALL, 0, null, 90 ,159),
    ZUBAT(new SpriteSheet("src/game/pokemon/sprites/41.png", 17, 6, 0, 0), "ZUBAT", Type.POISON, Type.FLYING, 120, 83, 73, Move.BITE, Move.POISON_FANG, 22, Pokedex.GOLBAT, 255 ,49),
    VILEPLUME(new SpriteSheet("src/game/pokemon/sprites/45.png", 16, 6, 0, 0), "VILEPLUME", Type.GRASS, Type.POISON, 181, 202, 170, Move.ACID, Move.SLUDGE_BOMB, 0, null, 45 ,245),
    GLOOM(new SpriteSheet("src/game/pokemon/sprites/44.png", 15, 6, 0, 0), "GLOOM", Type.GRASS, Type.POISON, 155, 153, 136, Move.ACID, Move.SLUDGE_BOMB, 0, Pokedex.VILEPLUME, 120 ,138),
    ODDISH(new SpriteSheet("src/game/pokemon/sprites/43.png", 14, 6, 0, 0), "ODDISH", Type.GRASS, Type.POISON, 128, 131, 112, Move.ACID, Move.SLUDGE_BOMB, 21, Pokedex.GLOOM, 255 ,64),
    PARASECT(new SpriteSheet("src/game/pokemon/sprites/47.png", 20, 6, 0, 0), "PARASECT", Type.BUG, Type.GRASS, 155, 165, 146, Move.FURY_CUTTER, Move.SOLAR_BEAM, 0, null, 75 ,142),
    PARAS(new SpriteSheet("src/game/pokemon/sprites/46.png", 11, 6, 0, 0), "PARAS", Type.BUG, Type.GRASS, 111, 121, 99, Move.BUG_BITE, Move.SEED_BOMB, 24, Pokedex.PARASECT, 190 ,57),
    VENOMOTH(new SpriteSheet("src/game/pokemon/sprites/49.png", 19, 6, 0, 0), "VENOMOTH", Type.BUG, Type.POISON, 172, 179, 143, Move.BUG_BITE, Move.BUG_BUZZ, 0, null, 75 ,158),
    VENONAT(new SpriteSheet("src/game/pokemon/sprites/48.png", 21, 6, 0, 0), "VENONAT", Type.BUG, Type.POISON, 155, 100, 100, Move.BUG_BITE, Move.SIGNAL_BEAM, 31, Pokedex.VENOMOTH, 190 ,61),
    DUGTRIO(new SpriteSheet("src/game/pokemon/sprites/51.png", 26, 6, 0, 0), "DUGTRIO", Type.GROUND, Type.NONE, 111, 167, 136, Move.MUD_SLAP, Move.MUD_BOMB, 0, null, 50 ,149),
    DIGLETT(new SpriteSheet("src/game/pokemon/sprites/50.png", 11, 6, 0, 0), "DIGLETT", Type.GROUND, Type.NONE, 67, 109, 78, Move.MUD_SLAP, Move.DIG, 26, Pokedex.DUGTRIO, 255 ,53),
    PERSIAN(new SpriteSheet("src/game/pokemon/sprites/53.png", 14, 6, 0, 0), "PERSIAN", Type.NORMAL, Type.NONE, 163, 150, 136, Move.SCRATCH, Move.FOUL_PLAY, 0, null, 90 ,154),
    MEOWTH(new SpriteSheet("src/game/pokemon/sprites/52.png", 11, 6, 0, 0), "MEOWTH", Type.NORMAL, Type.NONE, 120, 92, 78, Move.SCRATCH, Move.BODY_SLAM, 28, Pokedex.PERSIAN, 255 ,58),
    GOLDUCK(new SpriteSheet("src/game/pokemon/sprites/55.png", 18, 6, 0, 0), "GOLDUCK", Type.WATER, Type.NONE, 190, 191, 162, Move.WATER_GUN, Move.HYDRO_PUMP, 0, null, 75 ,175),
    PSYDUCK(new SpriteSheet("src/game/pokemon/sprites/54.png", 11, 6, 0, 0), "PSYDUCK", Type.WATER, Type.NONE, 137, 122, 95, Move.WATER_GUN, Move.AQUA_TAIL, 33, Pokedex.GOLDUCK, 190 ,64),
    PRIMEAPE(new SpriteSheet("src/game/pokemon/sprites/57.png", 22, 6, 0, 0), "PRIMEAPE", Type.FIGHTING, Type.NONE, 163, 207, 138, Move.COUNTER, Move.RAGE_FIST, 0, null, 75 ,159),
    MANKEY(new SpriteSheet("src/game/pokemon/sprites/56.png", 15, 6, 0, 0), "MANKEY", Type.FIGHTING, Type.NONE, 120, 148, 82, Move.KARATE_CHOP, Move.BRICK_BREAK, 28, Pokedex.PRIMEAPE, 190 ,61),
    ARCANINE(new SpriteSheet("src/game/pokemon/sprites/59.png", 16, 6, 0, 0), "ARCANINE", Type.FIRE, Type.NONE, 207, 227, 166, Move.FIRE_FANG, Move.WILD_CHARGE, 0, null, 75 ,194),
    GROWLITHE(new SpriteSheet("src/game/pokemon/sprites/58.png", 14, 6, 0, 0), "GROWLITHE", Type.FIRE, Type.NONE, 146, 136, 93, Move.BITE, Move.FLAMETHROWER, 0, Pokedex.ARCANINE, 190 ,70),
    POLIWRATH(new SpriteSheet("src/game/pokemon/sprites/62.png", 12, 6, 0, 0), "POLIWRATH", Type.WATER, Type.FIGHTING, 207, 182, 184, Move.COUNTER, Move.DYNAMIC_PUNCH, 0, null, 45 ,255),
    POLIWHIRL(new SpriteSheet("src/game/pokemon/sprites/61.png",  17, 6, 0, 0), "POLIWHIRL", Type.WATER, Type.NONE, 163, 130, 123, Move.BUBBLE, Move.BUBBLE_BEAM, 0, Pokedex.POLIWRATH, 120 ,135),
    POLIWAG(new SpriteSheet("src/game/pokemon/sprites/60.png", 13, 6, 0, 0), "POLIWAG", Type.WATER, Type.NONE, 120, 101, 82, Move.BUBBLE, Move.BUBBLE_BEAM, 25, Pokedex.POLIWHIRL, 255 ,60),
    ALAKAZAM(new SpriteSheet("src/game/pokemon/sprites/65.png", 12, 6, 0, 0), "ALAKAZAM", Type.PSYCHIC, Type.NONE, 146, 271, 167, Move.CONFUSION, Move.PSYCHIC, 0, null, 50 ,250),
    KADABRA(new SpriteSheet("src/game/pokemon/sprites/64.png", 16, 6, 0, 0), "KADABRA", Type.PSYCHIC, Type.NONE, 120, 232, 117, Move.CONFUSION, Move.SHADOW_BALL, 0, Pokedex.ALAKAZAM, 100 ,140),
    ABRA(new SpriteSheet("src/game/pokemon/sprites/63.png", 18, 6, 0, 0), "ABRA", Type.PSYCHIC, Type.NONE, 93, 195, 82, Move.ZEN_HEADBUTT, Move.PSYSHOCK, 16, Pokedex.KADABRA, 200 ,62),
    MACHAMP(new SpriteSheet("src/game/pokemon/sprites/68.png", 20, 6, 0, 0), "MACHAMP", Type.FIGHTING, Type.NONE, 207, 234, 159, Move.COUNTER, Move.DYNAMIC_PUNCH, 0, null, 45 ,253),
    MACHOKE(new SpriteSheet("src/game/pokemon/sprites/67.png", 13, 6, 0, 0), "MACHOKE", Type.FIGHTING, Type.NONE, 190, 177, 125, Move.KARATE_CHOP, Move.DYNAMIC_PUNCH, 0, Pokedex.MACHAMP, 90 ,142),
    MACHOP(new SpriteSheet("src/game/pokemon/sprites/66.png", 11, 6, 0, 0), "MACHOP", Type.FIGHTING, Type.NONE, 172, 137, 88, Move.LOW_KICK, Move.BRICK_BREAK, 28, Pokedex.MACHOKE, 180 ,61),
    VICTREEBEL(new SpriteSheet("src/game/pokemon/sprites/71.png", 13, 6, 0, 0), "VICTREEBEL", Type.GRASS, Type.POISON, 190, 207, 135, Move.MAGICAL_LEAF, Move.SLUDGE_BOMB, 0, null, 45 ,245),
    WEEPINBELL(new SpriteSheet("src/game/pokemon/sprites/70.png", 21, 6, 0, 0), "WEEPINBELL", Type.GRASS, Type.POISON, 163, 172, 92, Move.ACID, Move.POWER_WHIP, 0, Pokedex.VICTREEBEL, 120 ,137),
    BELLSPROUT(new SpriteSheet("src/game/pokemon/sprites/69.png", 12, 6, 0, 0), "BELLSPROUT", Type.GRASS, Type.POISON, 137, 139, 61, Move.VINE_WHIP, Move.WRAP_NORMAL, 21, Pokedex.WEEPINBELL, 255 ,60),
    TENTACRUEL(new SpriteSheet("src/game/pokemon/sprites/73.png", 20, 6, 0, 0), "TENTACRUEL", Type.WATER, Type.POISON, 190, 166, 209, Move.POISON_JAB, Move.HYDRO_PUMP, 0, null, 60 ,180),
    TENTACOOL(new SpriteSheet("src/game/pokemon/sprites/72.png", 10, 6, 0, 0), "TENTACOOL", Type.WATER, Type.POISON, 120, 97, 149, Move.BUBBLE, Move.BUBBLE_BEAM, 30, Pokedex.TENTACRUEL, 190 ,67),
    GOLEM(new SpriteSheet("src/game/pokemon/sprites/76.png", 10, 6, 0, 0), "GOLEM", Type.ROCK, Type.GROUND, 190, 211, 198, Move.ROCK_THROW, Move.EARTHQUAKE, 0, null, 45 ,248),
    GRAVELER(new SpriteSheet("src/game/pokemon/sprites/75.png", 14, 6, 0, 0), "GRAVELER", Type.ROCK, Type.GROUND, 146, 164, 164, Move.ROCK_THROW, Move.ROCK_SLIDE, 0, Pokedex.GOLEM, 120 ,137),
    GEODUDE(new SpriteSheet("src/game/pokemon/sprites/74.png", 15, 6, 0, 0), "GEODUDE", Type.ROCK, Type.GROUND, 120, 132, 132, Move.ROCK_THROW, Move.ROCK_SLIDE, 25, Pokedex.GRAVELER, 255 ,60),
    RAPIDASH(new SpriteSheet("src/game/pokemon/sprites/78.png", 21, 6, 0, 0), "RAPIDASH", Type.FIRE, Type.NONE, 163, 207, 162, Move.FIRE_SPIN, Move.WILD_CHARGE, 0, null, 60 ,175),
    PONYTA(new SpriteSheet("src/game/pokemon/sprites/77.png", 14, 6, 0, 0), "PONYTA", Type.FIRE, Type.NONE, 137, 170, 127, Move.EMBER, Move.FLAMETHROWER, 40, Pokedex.RAPIDASH, 190 ,82),
    SLOWBRO(new SpriteSheet("src/game/pokemon/sprites/80.png", 16, 6, 0, 0), "SLOWBRO", Type.WATER, Type.PSYCHIC, 216, 177, 180, Move.CONFUSION, Move.PSYCHIC, 0, null, 75 ,172),
    SLOWPOKE(new SpriteSheet("src/game/pokemon/sprites/79.png", 6, 6, 0, 0), "SLOWPOKE", Type.WATER, Type.PSYCHIC, 207, 109, 98, Move.WATER_GUN, Move.PSYCHIC, 37, Pokedex.SLOWBRO, 190 ,63),
    MAGNETON(new SpriteSheet("src/game/pokemon/sprites/82.png", 18, 6, 0, 0), "MAGNETON", Type.ELECTRIC, Type.STEEL, 137, 223, 169, Move.THUNDER_SHOCK, Move.ZAP_CANNON, 0, null, 60 ,163),
    MAGNEMITE(new SpriteSheet("src/game/pokemon/sprites/81.png", 13, 6, 0, 0), "MAGNEMITE", Type.ELECTRIC, Type.STEEL, 93, 165, 121, Move.THUNDER_SHOCK, Move.MAGNET_BOMB, 30, Pokedex.MAGNETON, 190 ,65),
    FARFETCH(new SpriteSheet("src/game/pokemon/sprites/83.png", 9, 6, 0, 0), "FARFETCH", Type.NORMAL, Type.FLYING, 141, 124, 115, Move.CUT, Move.LEAF_BLADE, 0, null, 45 ,132),
    DODRIO(new SpriteSheet("src/game/pokemon/sprites/85.png", 11, 6, 0, 0), "DODRIO", Type.NORMAL, Type.FLYING, 155, 218, 140, Move.STEEL_WING, Move.DRILL_PECK, 0, null, 45 ,165),
    DODUO(new SpriteSheet("src/game/pokemon/sprites/84.png", 13, 6, 0, 0), "DODUO", Type.NORMAL, Type.FLYING, 111, 158, 83, Move.QUICK_ATTACK, Move.DRILL_PECK, 31, Pokedex.DODRIO, 190 ,62),
    DEWGONG(new SpriteSheet("src/game/pokemon/sprites/87.png", 15, 6, 0, 0), "DEWGONG", Type.WATER, Type.ICE, 207, 139, 177, Move.FROST_BREATH, Move.BLIZZARD, 0, null, 75 ,166),
    SEEL(new SpriteSheet("src/game/pokemon/sprites/86.png", 12, 6, 0, 0), "SEEL", Type.WATER, Type.NONE, 163, 85, 121, Move.WATER_GUN, Move.AQUA_TAIL, 34, Pokedex.DEWGONG, 190 ,65),
    MUK(new SpriteSheet("src/game/pokemon/sprites/89.png", 12, 6, 0, 0), "MUK", Type.POISON, Type.NONE, 233, 190, 172, Move.POISON_JAB, Move.GUNK_SHOT, 0, null, 75 ,175),
    GRIMER(new SpriteSheet("src/game/pokemon/sprites/88.png", 11, 6, 0, 0), "GRIMER", Type.POISON, Type.NONE, 190, 135, 90, Move.POISON_JAB, Move.SLUDGE_BOMB, 38, Pokedex.MUK, 190 ,65),
    CLOYSTER(new SpriteSheet("src/game/pokemon/sprites/91.png", 8, 6, 0, 0), "CLOYSTER", Type.WATER, Type.ICE, 137, 186, 256, Move.FROST_BREATH, Move.RAZOR_SHELL, 0, null, 60 ,184),
    SHELLDER(new SpriteSheet("src/game/pokemon/sprites/90.png", 14, 6, 0, 0), "SHELLDER", Type.WATER, Type.NONE, 102, 116, 168, Move.TACKLE, Move.RAZOR_SHELL, 0, Pokedex.CLOYSTER, 190 ,61),
    GENGAR(new SpriteSheet("src/game/pokemon/sprites/94.png", 18, 6, 0, 0), "GENGAR", Type.GHOST, Type.POISON, 155, 261, 149, Move.LICK, Move.SHADOW_BALL, 0, null, 45 ,250),
    HAUNTER(new SpriteSheet("src/game/pokemon/sprites/93.png", 9, 6, 0, 0), "HAUNTER", Type.GHOST, Type.POISON, 128, 223, 107, Move.LICK, Move.SHADOW_BALL, 0, Pokedex.GENGAR, 90 ,142),
    GASTLY(new SpriteSheet("src/game/pokemon/sprites/92.png", 11, 6, 0, 0), "GASTLY", Type.GHOST, Type.POISON, 102, 186, 67, Move.LICK, Move.DARK_PULSE, 25, Pokedex.HAUNTER, 190 ,62),
    ONIX(new SpriteSheet("src/game/pokemon/sprites/95.png", 17, 6, 0, 0), "ONIX", Type.ROCK, Type.GROUND, 111, 85, 232, Move.ROCK_THROW, Move.BREAKING_SWIPE, 0, null, 45 ,77),
    HYPNO(new SpriteSheet("src/game/pokemon/sprites/97.png", 14, 6, 0, 0), "HYPNO", Type.PSYCHIC, Type.NONE, 198, 144, 193, Move.CONFUSION, Move.PSYCHIC, 0, null, 75 ,169),
    DROWZEE(new SpriteSheet("src/game/pokemon/sprites/96.png", 17, 6, 0, 0), "DROWZEE", Type.PSYCHIC, Type.NONE, 155, 89, 136, Move.POUND, Move.PSYCHIC, 26, Pokedex.HYPNO, 190 ,66),
    KINGLER(new SpriteSheet("src/game/pokemon/sprites/99.png", 18, 6, 0, 0), "KINGLER", Type.WATER, Type.NONE, 146, 240, 214, Move.WATER_GUN, Move.CRABHAMMER, 0, null, 60 ,166),
    KRABBY(new SpriteSheet("src/game/pokemon/sprites/98.png", 15, 6, 0, 0), "KRABBY", Type.WATER, Type.NONE, 102, 181, 156, Move.WATER_GUN, Move.RAZOR_SHELL, 28, Pokedex.KINGLER, 225 ,65),
    ELECTRODE(new SpriteSheet("src/game/pokemon/sprites/101.png", 5, 6, 0, 0), "ELECTRODE", Type.ELECTRIC, Type.NONE, 155, 173, 173, Move.SPARK, Move.THUNDERBOLT, 0, null, 60 ,172),
    VOLTORB(new SpriteSheet("src/game/pokemon/sprites/100.png", 10, 6, 0, 0), "VOLTORB", Type.ELECTRIC, Type.NONE, 120, 109, 111, Move.SPARK, Move.DISCHARGE, 30, Pokedex.ELECTRODE, 190 ,66),
    EXEGGUTOR(new SpriteSheet("src/game/pokemon/sprites/103.png", 16, 6, 0, 0), "EXEGGUTOR", Type.GRASS, Type.PSYCHIC, 216, 233, 149, Move.CONFUSION, Move.PSYCHIC, 0, null, 45 ,186),
    EXEGGCUTE(new SpriteSheet("src/game/pokemon/sprites/102.png", 22, 6, 0, 0), "EXEGGCUTE", Type.GRASS, Type.PSYCHIC, 155, 107, 125, Move.CONFUSION, Move.PSYCHIC, 0, Pokedex.EXEGGUTOR, 90 ,65),
    MAROWAK(new SpriteSheet("src/game/pokemon/sprites/105.png", 18, 6, 0, 0), "MAROWAK", Type.GROUND, Type.NONE, 155, 144, 186, Move.MUD_SLAP, Move.EARTHQUAKE, 0, null, 75 ,149),
    CUBONE(new SpriteSheet("src/game/pokemon/sprites/104.png", 15, 6, 0, 0), "CUBONE", Type.GROUND, Type.NONE, 137, 90, 144, Move.MUD_SLAP, Move.BONE_CLUB, 28, Pokedex.MAROWAK, 190 ,64),
    HITMONCHAN(new SpriteSheet("src/game/pokemon/sprites/107.png", 21, 6, 0, 0), "HITMONCHAN", Type.FIGHTING, Type.NONE, 137, 193, 197, Move.COUNTER, Move.POWER_UP_PUNCH, 0, null, 45 ,159),
    HITMONLEE(new SpriteSheet("src/game/pokemon/sprites/106.png", 16, 6, 0, 0), "HITMONLEE", Type.FIGHTING, Type.NONE, 137, 224, 181, Move.DOUBLE_KICK, Move.BLAZE_KICK, 0, null, 45 ,159),
    LICKITUNG(new SpriteSheet("src/game/pokemon/sprites/108.png", 11, 6, 0, 0), "LICKITUNG", Type.NORMAL, Type.NONE, 207, 108, 137, Move.LICK, Move.HYPER_BEAM, 0, null, 45 ,77),
    WEEZING(new SpriteSheet("src/game/pokemon/sprites/110.png", 17, 6, 0, 0), "WEEZING", Type.POISON, Type.NONE, 163, 174, 197, Move.ROLLOUT, Move.SLUDGE_BOMB, 0, null, 60 ,172),
    KOFFING(new SpriteSheet("src/game/pokemon/sprites/109.png", 9, 6, 0, 0), "KOFFING", Type.POISON, Type.NONE, 120, 119, 141, Move.TACKLE, Move.SLUDGE_BOMB, 35, Pokedex.WEEZING, 190 ,68),
    RHYDON(new SpriteSheet("src/game/pokemon/sprites/112.png", 29, 6, 0, 0), "RHYDON", Type.GROUND, Type.ROCK, 233, 222, 171, Move.MUD_SLAP, Move.BREAKING_SWIPE, 0, null, 60 ,170),
    RHYHORN(new SpriteSheet("src/game/pokemon/sprites/111.png", 11, 6, 0, 0), "RHYHORN", Type.GROUND, Type.ROCK, 190, 140, 127, Move.MUD_SLAP, Move.BULLDOZE, 42, Pokedex.RHYDON, 120 ,69),
    CHANSEY(new SpriteSheet("src/game/pokemon/sprites/113.png", 14, 6, 0, 0), "CHANSEY", Type.NORMAL, Type.NONE, 487, 60, 128, Move.POUND, Move.HYPER_BEAM, 0, null, 30 ,395),
    TANGELA(new SpriteSheet("src/game/pokemon/sprites/114.png", 9, 6, 0, 0), "TANGELA", Type.GRASS, Type.NONE, 163, 183, 169, Move.VINE_WHIP, Move.POWER_WHIP, 0, null, 45 ,87),
    KANGASKHAN(new SpriteSheet("src/game/pokemon/sprites/115.png", 15, 6, 0, 0), "KANGASKHAN", Type.NORMAL, Type.NONE, 233, 181, 165, Move.LOW_KICK, Move.OUTRAGE, 0, null, 45 ,172),
    SEADRA(new SpriteSheet("src/game/pokemon/sprites/117.png", 13, 6, 0, 0), "SEADRA", Type.WATER, Type.NONE, 146, 187, 156, Move.WATER_GUN, Move.HYDRO_PUMP, 0, null, 75 ,154),
    HORSEA(new SpriteSheet("src/game/pokemon/sprites/116.png", 14, 6, 0, 0), "HORSEA", Type.WATER, Type.NONE, 102, 129, 103, Move.WATER_GUN, Move.BUBBLE_BEAM, 32, Pokedex.SEADRA, 225 ,59),
    SEAKING(new SpriteSheet("src/game/pokemon/sprites/119.png", 16, 6, 0, 0), "SEAKING", Type.WATER, Type.NONE, 190, 175, 147, Move.WATERFALL, Move.DRILL_RUN, 0, null, 60 ,158),
    GOLDEEN(new SpriteSheet("src/game/pokemon/sprites/118.png", 15, 6, 0, 0), "GOLDEEN", Type.WATER, Type.NONE, 128, 123, 110, Move.MUD_SHOT, Move.AQUA_TAIL, 33, Pokedex.SEAKING, 225 ,64),
    STARMIE(new SpriteSheet("src/game/pokemon/sprites/121.png", 15, 6, 0, 0), "STARMIE", Type.WATER, Type.PSYCHIC, 155, 210, 184, Move.PSYWAVE, Move.PSYCHIC, 0, null, 60 ,182),
    STARYU(new SpriteSheet("src/game/pokemon/sprites/120.png", 15, 6, 0, 0), "STARYU", Type.WATER, Type.NONE, 102, 137, 112, Move.WATER_GUN, Move.BUBBLE_BEAM, 0, Pokedex.STARMIE, 225 ,68),
    MR_MIME(new SpriteSheet("src/game/pokemon/sprites/122.png", 14, 6, 0, 0), "MR_MIME", Type.PSYCHIC, Type.FAIRY, 120, 192, 205, Move.PSYWAVE, Move.PSYCHIC, 0, null, 45 ,161),
    SCYTHER(new SpriteSheet("src/game/pokemon/sprites/123.png", 34, 6, 0, 0), "SCYTHER", Type.BUG, Type.FLYING, 172, 218, 170, Move.FURY_CUTTER, Move.BUG_BUZZ, 0, null, 45 ,100),
    JYNX(new SpriteSheet("src/game/pokemon/sprites/124.png", 15, 6, 0, 0), "JYNX", Type.ICE, Type.PSYCHIC, 163, 223, 151, Move.CONFUSION, Move.AVALANCHE, 0, null, 45 ,159),
    ELECTABUZZ(new SpriteSheet("src/game/pokemon/sprites/125.png", 16, 6, 0, 0), "ELECTABUZZ", Type.ELECTRIC, Type.NONE , 163, 198, 158, Move.THUNDER_SHOCK, Move.THUNDERBOLT, 0, null, 45 ,172),
    MAGMAR(new SpriteSheet("src/game/pokemon/sprites/126.png", 15, 6, 0, 0), "MAGMAR", Type.FIRE, Type.NONE, 163, 206, 154, Move.KARATE_CHOP, Move.FLAMETHROWER, 0, null, 45 ,173),
    PINSIR(new SpriteSheet("src/game/pokemon/sprites/127.png", 14, 6, 0, 0), "PINSIR", Type.BUG, Type.NONE, 163, 238, 182, Move.FURY_CUTTER, Move.X_SCISSOR, 0, null, 45 ,175),
    TAUROS(new SpriteSheet("src/game/pokemon/sprites/128.png", 18, 6, 0, 0), "TAUROS", Type.NORMAL, Type.NONE, 181, 198, 183, Move.TACKLE, Move.EARTHQUAKE, 0, null, 45 ,172),
    GYARADOS(new SpriteSheet("src/game/pokemon/sprites/130.png", 9, 6, 0, 0), "GYARADOS", Type.WATER, Type.FLYING, 216, 237, 186, Move.WATERFALL, Move.HYDRO_PUMP, 0, null, 45 ,189),
    MAGIKARP(new SpriteSheet("src/game/pokemon/sprites/129.png", 11, 6, 0, 0), "MAGIKARP", Type.WATER, Type.NONE, 85, 29, 85, Move.SPLASH, Move.STRUGGLE, 20, Pokedex.GYARADOS, 255 ,40),
    LAPRAS(new SpriteSheet("src/game/pokemon/sprites/131.png", 12, 6, 0, 0), "LAPRAS", Type.WATER, Type.ICE, 277, 165, 174, Move.WATER_GUN, Move.SPARKLING_ARIA, 0, null, 45 ,187),
    DITTO(new SpriteSheet("src/game/pokemon/sprites/132.png", 15, 6, 0, 0), "DITTO", Type.NORMAL, Type.NONE, 134, 91, 91, Move.TRANSFORM, Move.STRUGGLE, 0, null, 35 ,101),
    FLAREON(new SpriteSheet("src/game/pokemon/sprites/136.png", 13, 6, 0, 0), "FLAREON", Type.FIRE, Type.NONE, 163, 246, 179, Move.FIRE_SPIN, Move.OVERHEAT, 0, null, 45 ,184),
    JOLTEON(new SpriteSheet("src/game/pokemon/sprites/135.png", 15, 6, 0, 0), "JOLTEON", Type.ELECTRIC, Type.NONE, 163, 232, 182, Move.THUNDER_SHOCK, Move.THUNDERBOLT, 0, null, 45 ,184),
    VAPOREON(new SpriteSheet("src/game/pokemon/sprites/134.png", 14, 6, 0, 0), "VAPOREON", Type.WATER, Type.NONE, 277, 205, 161, Move.WATER_GUN, Move.HYDRO_PUMP, 0, null, 45 ,184),
    EEVEE(new SpriteSheet("src/game/pokemon/sprites/133.png", 17, 6, 0, 0), "EEVEE", Type.NORMAL, Type.NONE, 146, 104, 121, Move.TACKLE, Move.LAST_RESORT, 0, Pokedex.VAPOREON, 45 ,65),
    PORYGON(new SpriteSheet("src/game/pokemon/sprites/137.png", 15, 6, 0, 0), "PORYGON", Type.NORMAL, Type.NONE, 163, 153, 136, Move.TACKLE, Move.HYPER_BEAM, 0, null, 45 ,79),
    OMASTAR(new SpriteSheet("src/game/pokemon/sprites/139.png", 9, 6, 0, 0), "OMASTAR", Type.ROCK, Type.WATER, 172, 207, 201, Move.ROCK_THROW, Move.ROCK_SLIDE, 0, null, 45 ,173),
    OMANYTE(new SpriteSheet("src/game/pokemon/sprites/138.png", 9, 6, 0, 0), "OMANYTE", Type.ROCK, Type.WATER, 111, 155, 153, Move.WATER_GUN, Move.ROCK_BLAST, 40, Pokedex.OMASTAR, 45 ,71),
    KABUTOPS(new SpriteSheet("src/game/pokemon/sprites/141.png", 13, 6, 0, 0), "KABUTOPS", Type.ROCK, Type.WATER, 155, 220, 186, Move.WATERFALL, Move.STONE_EDGE, 0, null, 45 ,173),
    KABUTO(new SpriteSheet("src/game/pokemon/sprites/140.png", 11, 6, 0, 0), "KABUTO", Type.ROCK, Type.WATER, 102, 148, 140, Move.SCRATCH, Move.ANCIENT_POWER, 40, Pokedex.KABUTOPS, 45 ,71),
    AERODACTYL(new SpriteSheet("src/game/pokemon/sprites/142.png", 13, 6, 0, 0), "AERODACTYL", Type.ROCK, Type.FLYING, 190, 221, 159, Move.ROCK_THROW, Move.ROCK_SLIDE, 0, null, 45 ,180),
    SNORLAX(new SpriteSheet("src/game/pokemon/sprites/143.png", 13, 6, 0, 0), "SNORLAX", Type.NORMAL, Type.NONE, 330, 190, 169, Move.LICK, Move.HYPER_BEAM, 0, null, 25 ,189),
    MOLTRES(new SpriteSheet("src/game/pokemon/sprites/146.png", 21, 6, 0, 0), "MOLTRES", Type.FIRE, Type.FLYING, 207, 251, 181, Move.FIRE_SPIN, Move.SKY_ATTACK, 0, null, 3 ,290),
    ZAPDOS(new SpriteSheet("src/game/pokemon/sprites/145.png", 13, 6, 0, 0), "ZAPDOS", Type.ELECTRIC, Type.FLYING, 207, 253, 185, Move.THUNDER_SHOCK, Move.DRILL_PECK, 0, null, 3 ,290),
    ARTICUNO(new SpriteSheet("src/game/pokemon/sprites/144.png", 16, 6, 0, 0), "ARTICUNO", Type.ICE, Type.FLYING, 207, 192, 236, Move.FROST_BREATH, Move.TRIPLE_AXEL, 0, null, 3 ,290),
    DRAGONITE(new SpriteSheet("src/game/pokemon/sprites/149.png", 18, 6, 0, 0), "DRAGONITE", Type.DRAGON, Type.FLYING, 209, 263, 198, Move.DRAGON_TAIL, Move.DRACO_METEOR, 0, null, 45 ,300),
    DRAGONAIR(new SpriteSheet("src/game/pokemon/sprites/148.png", 18, 6, 0, 0), "DRAGONAIR", Type.DRAGON, Type.NONE, 156, 163, 135, Move.DRAGON_BREATH, Move.DRAGON_PULSE, 55, Pokedex.DRAGONITE, 45 ,147),
    DRATINI(new SpriteSheet("src/game/pokemon/sprites/147.png", 11, 6, 0, 0), "DRATINI", Type.DRAGON, Type.NONE, 121, 119, 91, Move.DRAGON_BREATH, Move.AQUA_TAIL, 30, Pokedex.DRAGONAIR, 45 ,60),
    MEW(new SpriteSheet("src/game/pokemon/sprites/151.png", 23, 6, 0, 0), "MEW", Type.PSYCHIC, Type.NONE, 225, 210, 210, Move.SNARL, Move.PSYCHIC, 0, null, 45 ,300),
    MEWTWO(new SpriteSheet("src/game/pokemon/sprites/150.png", 25, 6, 0, 0), "MEWTWO", Type.PSYCHIC, Type.NONE, 214, 300, 182, Move.CONFUSION, Move.PSYSTRIKE, 0, null, 3 ,340);
    
    private final SpriteSheet spriteSheet;
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

    Pokedex(SpriteSheet spriteSheet, String name, Type mainType, Type secondaryType, int baseHp, int baseAttack, int baseDefense, Move fastMove, Move chargedMove, int levelToEvolve, Pokedex pokemonToEvolve, int catchRate, int baseExp) {
        this.spriteSheet = spriteSheet;
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
    }

    public int getCatchRate() {
        return catchRate;
    }

    public int getBaseExp() {
        return baseExp;
    }

    public Move getChargedMove() {
        return chargedMove;
    }

    public Move getFastMove() {
        return fastMove;
    }

    public Pokedex getPokemonToEvolve() {
        return pokemonToEvolve;
    }

    public int getLevelToEvolve() {
        return levelToEvolve;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
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
}