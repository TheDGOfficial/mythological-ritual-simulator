package dev.practice.ritual.command;

import dev.practice.ritual.ritual.GriffinRarity;
import dev.practice.ritual.ritual.MythoKind;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public final class ForceSpawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("§cUsage: /forcespawn <mythokind>");
            return true;
        }

        try {
            MythoKind kind = MythoKind.valueOf(args[0].toUpperCase());
            GriffinRarity.forcedNextSpawn = kind;
            sender.sendMessage("§aNext spawn forced to §e" + kind.display + "§a.");
        } catch (IllegalArgumentException e) {
            sender.sendMessage("§cInvalid mytho kind: §e" + args[0]);
            sender.sendMessage("§7Valid values: §f" + String.join(", ", Arrays.stream(MythoKind.values())
                    .map(Enum::name)
                    .toList()));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) return List.of();

        String input = args.length == 0 ? "" : args[0].toLowerCase();

        return Arrays.stream(MythoKind.values())
                .map(Enum::name)
                .filter(name -> name.toLowerCase().startsWith(input))
                .toList();
    }
}
