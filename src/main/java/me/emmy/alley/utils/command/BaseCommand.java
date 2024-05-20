package me.emmy.alley.utils.command;

import me.emmy.alley.Alley;

public abstract class BaseCommand {

    public Alley main = Alley.getInstance();

    public BaseCommand() {
        main.getFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);

}