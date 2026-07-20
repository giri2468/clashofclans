package com.clashcoach.player;
import java.util.List;
/** Provider-independent player data used by the application layer. */
public final class PlayerData {
    private final String name; private final int townHallLevel; private final int experienceLevel; private final int trophies; private final List<Hero> heroes;
    public PlayerData(String name, int townHallLevel, int experienceLevel, int trophies, List<Hero> heroes) { this.name=name;this.townHallLevel=townHallLevel;this.experienceLevel=experienceLevel;this.trophies=trophies;this.heroes=heroes; }
    public String getName(){return name;} public int getTownHallLevel(){return townHallLevel;} public int getExperienceLevel(){return experienceLevel;} public int getTrophies(){return trophies;} public List<Hero> getHeroes(){return heroes;}
    public static final class Hero { private String name; private int level; public Hero(){} public Hero(String name,int level){this.name=name;this.level=level;} public String getName(){return name;} public int getLevel(){return level;} public void setName(String name){this.name=name;} public void setLevel(int level){this.level=level;} }
}
