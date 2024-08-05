package top.earthvillage.fakechatplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeChatPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        System.out.println("腐竹的恶搞插件已加载~OHHHH~~~");
        this.getCommand("说话").setExecutor(this);
        this.getCommand("说话").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("说话")) {
            if (args.length < 2) {
                sender.sendMessage("使用方法: /说话 <名字> <文字>");
                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage("玩家不在");
                return false;
            }

            // 获取消息内容，同时将args 中的所有元素使用空格 " " 连接成一个单独的字符串（如果说的话里有空格的话）
            //String message = String.join(" ", args).substring(args[0].length()).trim();
            String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));  //拼接除了talk以外剩下的参数

            // 以玩家身份发送消息
            targetPlayer.chat(message);

            return true;
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("说话")) {
            if (args.length == 1) {
                // 返回在线玩家列表
                List<String> playerNames = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerNames.add(player.getName());
                }
                return playerNames;
            } else if (args.length == 2) {
                // 在第二个参数位置提示输入文字
                List<String> suggestions = new ArrayList<>();
                suggestions.add("请输入消息内容");
                suggestions.add("支持空格隔开的多词消息");
                suggestions.add("例如：你 好  世 界");
                return suggestions;
            }
        }
        return Collections.emptyList();
    }
}
