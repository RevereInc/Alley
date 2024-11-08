package dev.revere.alley.game.match.impl;

import dev.revere.alley.arena.Arena;
import dev.revere.alley.kit.Kit;
import dev.revere.alley.game.match.player.GameParticipant;
import dev.revere.alley.game.match.player.impl.MatchGamePlayerImpl;
import dev.revere.alley.queue.Queue;

/**
 * @author Remi
 * @project Alley
 * @date 5/21/2024
 */
public class MatchBedRegularImpl extends MatchRegularImpl {

    /**
     * Constructor for the BedMatchImpl class.
     *
     * @param kit          The kit of the match.
     * @param arena        The arena of the match.
     * @param participantA The first participant.
     * @param participantB The second participant.
     */
    public MatchBedRegularImpl(Queue queue, Kit kit, Arena arena, boolean ranked, GameParticipant<MatchGamePlayerImpl> participantA, GameParticipant<MatchGamePlayerImpl> participantB) {
        super(queue, kit, arena, ranked, participantA, participantB);
    }
}