package me.emmy.alley.kit.command.impl.manage;

import me.emmy.alley.Alley;
import me.emmy.alley.utils.chat.CC;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

/**
 * @author Emmy
 * @project Practice
 * @date 28/04/2024 - 22:07
 */
public class KitListCommand extends BaseCommand {
    @Override
    @Command(name = "kit.list", permission = "alley.admin")
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();

        sender.sendMessage("");
        sender.sendMessage(CC.translate("     &b&lKit List &f(" + Alley.getInstance().getKitRepository().getKits().size() + "&f)"));
        if (Alley.getInstance().getKitRepository().getKits().isEmpty()) {
            sender.sendMessage(CC.translate("      &f● &cNo Kits available."));
        }
        Alley.getInstance().getKitRepository().getKits().forEach(kit -> sender.sendMessage(CC.translate("      &f● &b" + kit.getDisplayName() + " &f(" + (kit.isEnabled() ? "&aEnabled" : "&cDisabled") + "&f)")));
        sender.sendMessage("");
    }
}