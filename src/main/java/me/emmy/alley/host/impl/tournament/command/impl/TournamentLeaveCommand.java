package me.emmy.alley.host.impl.tournament.command.impl;

import me.emmy.alley.Alley;
import me.emmy.alley.host.impl.tournament.TournamentRepository;
import me.emmy.alley.host.impl.tournament.TournamentLogger;
import me.emmy.alley.hotbar.enums.HotbarType;
import me.emmy.alley.utils.chat.CC;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Alley
 * @date 15/06/2024 - 19:07
 */
public class TournamentLeaveCommand extends BaseCommand {
    @Override
    @Command(name = "tournament.leave")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        TournamentRepository tournamentRepository = Alley.getInstance().getTournamentRepository();

        if (!tournamentRepository.isRunning()) {
            player.sendMessage("There is no tournament running.");
            return;
        }

        if (!tournamentRepository.isPlayerInTournament(player)) {
            player.sendMessage("You are not in the tournament.");
            return;
        }

        Alley.getInstance().getHotbarRepository().applyHotbarItems(player, HotbarType.LOBBY);
        tournamentRepository.getPlayers().remove(player);
        player.sendMessage(CC.translate("&cYou have left the tournament."));
        TournamentLogger.broadcastPlayerLeave(player);
    }
}
