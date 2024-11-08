package dev.revere.alley.game.tournament.command.impl;

import dev.revere.alley.api.command.BaseCommand;
import dev.revere.alley.api.command.Command;
import dev.revere.alley.api.command.CommandArgs;
import dev.revere.alley.game.tournament.TournamentLogger;
import dev.revere.alley.locale.ErrorMessage;
import dev.revere.alley.util.chat.CC;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Alley
 * @date 15/06/2024 - 18:32
 */
public class TournamentJoinCommand extends BaseCommand {
    @Command(name = "tournament.join")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        /*TournamentRepository tournamentRepository = Alley.getInstance().getTournamentRepository();
        if (!tournamentRepository.isRunning()) {
            player.sendMessage(CC.translate("&cThere is no tournament running."));
            return;
        }

        if (tournamentRepository.getPlayers().size() >= tournamentRepository.getMaxPlayers()) {
            player.sendMessage(CC.translate("&cThe tournament is full."));
            return;
        }

        if (tournamentRepository.isPlayerInTournament(player)) {
            player.sendMessage(CC.translate("&cYou have already joined the tournament."));
            return;
        }

        Alley.getInstance().getHotbarRepository().applyHotbarItems(player, HotbarType.TOURNAMENT);
        tournamentRepository.getPlayers().add(player);*/

        player.sendMessage(CC.translate(ErrorMessage.DEBUG_CMD));
        //TournamentLogger.broadcastPlayerJoin(player);
    }
}