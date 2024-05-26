package me.emmy.alley.party.command.impl;

import me.emmy.alley.Alley;
import me.emmy.alley.locale.ErrorMessage;
import me.emmy.alley.party.Party;
import me.emmy.alley.party.PartyRequest;
import me.emmy.alley.utils.chat.CC;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Alley
 * Date: 25/05/2024 - 18:27
 */

public class PartyInviteCommand extends BaseCommand {
    @Override
    @Command(name = "party.invite")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (command.length() < 1) {
            player.sendMessage(CC.translate("&cUsage: /party invite (player)"));
            return;
        }

        //TODO: Check if the target player allows party invites (in future when profiles are done)

        String target = args[0];
        Player targetPlayer = Bukkit.getPlayer(target);

        if (targetPlayer == null) {
            player.sendMessage(CC.translate(ErrorMessage.PLAYER_NOT_ONLINE.replace("{player}", target)));
            return;
        }

        if (targetPlayer == command.getPlayer()) {
            player.sendMessage(CC.translate("&cYou can't invite yourself."));
            return;
        }

        Party party = Alley.getInstance().getPartyRepository().getPartyByMember(command.getPlayer().getUniqueId());
        Alley.getInstance().getPartyRequest().sendRequest(party, targetPlayer);
    }
}