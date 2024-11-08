package dev.revere.alley.game.ffa;

import lombok.Getter;
import lombok.Setter;
import dev.revere.alley.arena.Arena;
import dev.revere.alley.game.ffa.enums.EnumFFAState;
import dev.revere.alley.kit.Kit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Remi
 * @project Alley
 * @date 5/27/2024
 */
@Getter
@Setter
public abstract class AbstractFFAMatch {
    private EnumFFAState state = EnumFFAState.SPAWN;

    private final String name;
    private final Arena arena;
    private final Kit kit;
    private int maxPlayers;
    private List<Player> players;

    /**
     * Constructor for the AbstractFFAMatch class.
     *
     * @param name The name of the match
     * @param arena The arena the match is being played in
     * @param kit The kit the players are using
     * @param maxPlayers The maximum amount of players allowed in the match
     */
    public AbstractFFAMatch(String name, Arena arena, Kit kit, int maxPlayers) {
        this.name = name;
        this.arena = arena;
        this.kit = kit;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
    }

    public abstract void join(Player player);
    public abstract void setupPlayer(Player player);
    public abstract void handleDeath(Player player, Player killer);
    public abstract void handleRespawn(Player player);
    public abstract void leave(Player player);
}