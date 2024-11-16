package dev.revere.alley.party.menu.event.impl.split;

import dev.revere.alley.Alley;
import dev.revere.alley.api.menu.Button;
import dev.revere.alley.api.menu.Menu;
import dev.revere.alley.arena.Arena;
import dev.revere.alley.game.match.AbstractMatch;
import dev.revere.alley.game.match.impl.MatchLivesRegularImpl;
import dev.revere.alley.game.match.impl.MatchRegularImpl;
import dev.revere.alley.game.match.player.participant.GameParticipant;
import dev.revere.alley.game.match.player.impl.MatchGamePlayerImpl;
import dev.revere.alley.game.match.player.participant.TeamGameParticipant;
import dev.revere.alley.kit.Kit;
import dev.revere.alley.kit.settings.impl.KitSettingLivesImpl;
import dev.revere.alley.locale.Locale;
import dev.revere.alley.party.Party;
import dev.revere.alley.queue.Queue;
import dev.revere.alley.util.chat.CC;
import dev.revere.alley.util.item.ItemBuilder;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Emmy
 * @project Alley
 * @date 08/10/2024 - 18:38
 */
@AllArgsConstructor
public class PartyEventSplitMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "&c&lStill in development";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        Alley.getInstance().getKitRepository().getKits()
                .stream()
                .filter(Kit::isEnabled)
                .forEach(kit -> buttons.put(buttons.size(), new PartyEventSplitButton(kit)))
        ;

        this.addGlass(buttons, 15);

        return buttons;
    }

    @Override
    public int getSize() {
        return 3 * 9;
    }

    @AllArgsConstructor
    private static class PartyEventSplitButton extends Button {
        private Kit kit;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(kit.getIcon()).name("&b&l" + kit.getName()).durability(kit.getIconData()).hideMeta()
                    .lore(
                            "&7" + kit.getDescription(),
                            "",
                            "&aClick to select this kit."
                    )
                    .hideMeta().build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (clickType != ClickType.LEFT) return;

            if (player.hasPermission("alley.party.arena.selector")) {
                new PartyEventSplitArenaSelectorMenu(this.kit).openMenu(player);
                return;
            }

            Party party = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId()).getParty();
            if (party == null) {
                player.closeInventory();
                player.sendMessage(CC.translate(Locale.NOT_IN_PARTY.getMessage()));
                return;
            }

            Player playerA = party.getLeader();
            Player playerB = Bukkit.getPlayer(party.getMembers().get(1));

            MatchGamePlayerImpl gamePlayerA = new MatchGamePlayerImpl(playerA.getUniqueId(), playerA.getName());
            MatchGamePlayerImpl gamePlayerB = new MatchGamePlayerImpl(playerB.getUniqueId(), playerB.getName());

            GameParticipant<MatchGamePlayerImpl> participantA = new TeamGameParticipant<>(gamePlayerA);
            GameParticipant<MatchGamePlayerImpl> participantB = new TeamGameParticipant<>(gamePlayerB);

            List<Player> players = party.getMembers().stream().map(Bukkit::getPlayer).collect(Collectors.toList());
            Collections.shuffle(players);

            for (Player player1 : players) {
                if (player1.equals(playerA) || player1.equals(playerB)) continue;
                MatchGamePlayerImpl gamePlayer = new MatchGamePlayerImpl(player1.getUniqueId(), player1.getName());

                if (players.indexOf(player1) % 2 == 0) {
                    participantA.getPlayers().add(gamePlayer);
                } else {
                    participantB.getPlayers().add(gamePlayer);
                }
            }

            Arena arena = Alley.getInstance().getArenaRepository().getRandomArena(this.kit);

            for (Queue queue : Alley.getInstance().getQueueRepository().getQueues()) {
                if (queue.getKit().equals(this.kit) && !queue.isRanked()) {
                    if (queue.getKit().isSettingEnabled(KitSettingLivesImpl.class)) {
                        AbstractMatch match = new MatchLivesRegularImpl(queue, this.kit, arena, false, participantA, participantB);
                        match.startMatch();
                    } else {
                        AbstractMatch match = new MatchRegularImpl(queue, this.kit, arena, false, participantA, participantB);
                        match.startMatch();
                    }
                }
            }
        }
    }
}