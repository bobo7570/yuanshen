package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "kick", usage = "kick <player>",
        description = "Kicks the specified player from the server (WIP)", permission = "server.kick")
public final class KickCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        int target = Integer.parseInt(args.get(0));

        Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
        if (targetPlayer == null) {
            CommandHandler.sendMessage(sender, "Player not found.");
            return;
        }

        if (sender != null) {
            CommandHandler.sendMessage(sender, String.format("Player [%s:%s] has kicked player [%s:%s]", sender.getAccount().getPlayerUid(), sender.getAccount().getUsername(), target, targetPlayer.getAccount().getUsername()));
        }
        CommandHandler.sendMessage(sender, String.format("Kicking player [%s:%s]", target, targetPlayer.getAccount().getUsername()));

        targetPlayer.getSession().close();
    }
}
