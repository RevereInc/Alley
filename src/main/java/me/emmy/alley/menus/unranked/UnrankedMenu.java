package me.emmy.alley.menus.unranked;

import me.emmy.alley.Alley;
import me.emmy.alley.queue.Queue;
import me.emmy.alley.utils.menu.Button;
import me.emmy.alley.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emmy
 * Project: FlowerCore
 * Discord: dsc.gg/emmiesa
 */
public class UnrankedMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Unranked Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (Queue queue : Alley.getInstance().getQueueRepository().getQueues()) {
            buttons.put(queue.getKit().getUnrankedslot(), new UnrankedButton(queue));
        }

        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 5;
    }
}