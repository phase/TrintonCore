package me.killazombiecow.trintoncore;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Core extends JavaPlugin implements Listener {

	static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Trinton" + ChatColor.DARK_GRAY + "] ";
	private HashMap < Player, Integer > cooldownTime;
	private HashMap < Player, BukkitRunnable > cooldownTask;

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		cooldownTime = new HashMap < Player, Integer > ();
		cooldownTask = new HashMap < Player, BukkitRunnable > ();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(final CommandSender sender, Command command, String alias, String[] args) {
		if ((alias.equalsIgnoreCase("tc")) || (alias.equalsIgnoreCase("help"))) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GRAY + "-=-=-=-=-=-=-=-" + ChatColor.RED + "TC Help" + ChatColor.GRAY + "-=-=-=-=-=-=-=-");

				sender.sendMessage(ChatColor.RED + "/tc" + ChatColor.GRAY + " Brings up this menu");

				sender.sendMessage(ChatColor.RED + "/tc ranks" + ChatColor.GRAY + " List all TC ranks");

				sender.sendMessage(ChatColor.RED + "/tc staff" + ChatColor.GRAY + " List all TC staff");

				sender.sendMessage(ChatColor.RED + "/tc clear" + ChatColor.GRAY + " Clear your chat");

				sender.sendMessage(ChatColor.RED + "/tc author" + ChatColor.GRAY + " Get Plugin Information");

				sender.sendMessage(ChatColor.RED + "/tc commands" + ChatColor.GRAY + " View all the commands you have access to");

				sender.sendMessage(ChatColor.RED + "/tc donate" + ChatColor.GRAY + " Want to donate? Use this command!");

				sender.sendMessage(ChatColor.RED + "/tc website" + ChatColor.GRAY + " Get the website URL");

				sender.sendMessage(" ");
			} else if (args[0].equalsIgnoreCase("commands")) {
				if (args.length == 1) {
					sender.sendMessage(" ");

					sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Commands you have access to:");
					if (sender.hasPermission("tc.ocommands"))
						sender.sendMessage(ChatColor.RED + "/tc commands owner" + ChatColor.GRAY + " List of Owner Commands");
					if (sender.hasPermission("tc.acommands"))
						sender.sendMessage(ChatColor.RED + "/tc commands admin" + ChatColor.GRAY + " List of Admin Commands");
					if (sender.hasPermission("tc.mcommands"))
						sender.sendMessage(ChatColor.RED + "/tc commands mod" + ChatColor.GRAY + " List of Moderator Commands");
					if (sender.hasPermission("tc.bcommands"))
						sender.hasPermission(ChatColor.RED + "/tc commands builder" + ChatColor.GRAY + " List of Builder Commands");
					if (sender.hasPermission("tc.hcommands"))
						sender.sendMessage(ChatColor.RED + "/tc commands helper" + ChatColor.GRAY + " List of Helper Commands");
					if (sender.hasPermission("tc.gcommands"))
						sender.sendMessage(ChatColor.RED + "/tc commands guide" + ChatColor.GRAY + " List of Guide Commands");
					sender.sendMessage(ChatColor.RED + "/tc commands vip+" + ChatColor.GRAY + " List of VIP+ Commands");

					sender.sendMessage(ChatColor.RED + "/tc commands vip" + ChatColor.GRAY + " List of VIP Commands");

					sender.sendMessage(ChatColor.RED + "/tc commands member" + ChatColor.GRAY + " List of Default Commands");

					sender.sendMessage(" ");
				}
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("owner")) {
						if (sender.hasPermission("tc.ocommands")) {
							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Owner Commands:");

							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.RED + "/tc reload" + ChatColor.GRAY + " Reload TCcore");

							sender.sendMessage(" ");
						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permission to view these commands!");
						}
					}
					if (args[1].equalsIgnoreCase("admin")) {
						if (sender.hasPermission("tc.acommands")) {
							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Admin Commands");

							sender.sendMessage(ChatColor.RED + "/ban" + ChatColor.GRAY + " Ban Player");

							sender.sendMessage(ChatColor.RED + "/banip" + ChatColor.GRAY + " Ban a player's IP");

							sender.sendMessage(ChatColor.RED + "/tempban" + ChatColor.GRAY + " Temporary ban a player");

							sender.sendMessage(ChatColor.RED + "/gamemode" + ChatColor.GRAY + " Change your gamemode");

							sender.sendMessage(" ");
						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permission to view these commands!");
						}
					}
					if (args[1].equalsIgnoreCase("mod")) {
						if (sender.hasPermission("tc.mcommands")) {
							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Mod Commands");

							sender.sendMessage(ChatColor.RED + "/broadcast" + ChatColor.GRAY + " Broadcast to the entire server");

							sender.sendMessage(ChatColor.RED + "/fly" + ChatColor.GRAY + " Toggle fly mode");

							sender.sendMessage(ChatColor.RED + "/tempban" + ChatColor.GRAY + " Temporarily ban a player");

							sender.sendMessage(ChatColor.RED + "/tp" + ChatColor.GRAY + " Teleport to a player");

							sender.sendMessage(ChatColor.RED + "/tc gclear" + ChatColor.GRAY + " Clear chat globally");

							sender.sendMessage(" ");
						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permission to view these commands!");
						}
					}
					if (args[1].equalsIgnoreCase("builder")) {
						if (sender.hasPermission("tc.bcommands")) {
							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Builder Commands");

							sender.sendMessage(ChatColor.RED + "/gamemode" + ChatColor.GRAY + " Change your gamemode");

							sender.sendMessage(ChatColor.RED + "/speed" + ChatColor.GRAY + " Set fly and walk speed");

							sender.sendMessage(ChatColor.RED + "/tp" + ChatColor.GRAY + " Teleport to a player");

							sender.sendMessage(ChatColor.RED + "/setwarp" + ChatColor.GRAY + " Set a warp point");

							sender.sendMessage(ChatColor.RED + "/fly" + ChatColor.GRAY + " Toggle fly mode");

							sender.sendMessage(ChatColor.RED + "//wand" + ChatColor.GRAY + " Get and use worldedit wand");
						}
					}
					if (args[1].equalsIgnoreCase("helper")) {
						if (sender.hasPermission("tc.hcommands")) {
							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Helper Commands");

							sender.sendMessage(ChatColor.RED + "/me" + ChatColor.GRAY + " Need to announce something? Use this");

							sender.sendMessage(ChatColor.RED + "/mute" + ChatColor.GRAY + " Mute a player");

							sender.sendMessage(ChatColor.RED + "/kick" + ChatColor.GRAY + " Kick a player");

							sender.sendMessage(" ");
						}
					}
					if (args[1].equalsIgnoreCase("guide")) {
						if (sender.hasPermission("tc.gcommands")) {
							sender.sendMessage(" ");

							sender.sendMessage(ChatColor.RED + "/fly" + ChatColor.GRAY + " Toggle fly state");

							sender.sendMessage(ChatColor.RED + "/tphere" + ChatColor.GRAY + " Teleport a player to you (Use only when needed");

							sender.sendMessage(ChatColor.RED + "/tp" + ChatColor.GRAY + "Teleport to a player (Use only when needed)");

							sender.sendMessage(" ");
						}
					}
					if (args[1].equalsIgnoreCase("vip+")) {
						sender.sendMessage(" ");

						sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "VIP+ Commands");

						sender.sendMessage(ChatColor.RED + "/say" + ChatColor.GRAY + " Talk in a different format");

						sender.sendMessage(ChatColor.RED + "/nick" + ChatColor.GRAY + " Change your nick name (With Color)");

						sender.sendMessage(ChatColor.RED + "/pv" + ChatColor.GRAY + " Open your player vault (6 more)");

						sender.sendMessage(ChatColor.RED + "/da" + ChatColor.GRAY + " Turn on your MLG sweg disco armor");
						
						sender.sendMessage(ChatColor.RED + "/tc shoutcast" + ChatColor.GRAY + " Shout to the entire server");

						sender.sendMessage(ChatColor.GRAY + "And all the commands from VIP");

						sender.sendMessage(ChatColor.GRAY + "Want a full list of all your features? Visit our website at http://trintoncraft.com");

						sender.sendMessage(" ");
					}
					if (args[1].equalsIgnoreCase("vip")) {
						sender.sendMessage(" ");

						sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "VIP Commands");

						sender.sendMessage(ChatColor.RED + "/disguise" + ChatColor.GRAY + " Main command for Disguises");

						sender.sendMessage(ChatColor.RED + "/cc" + ChatColor.GRAY + " Change your chat color!");

						sender.sendMessage(ChatColor.RED + "/pet" + ChatColor.GRAY + " Choose a pet");

						sender.sendMessage(ChatColor.RED + "/ping" + ChatColor.GRAY + " Ping an online player to get thier attention");

						sender.sendMessage(ChatColor.RED + "/trail" + ChatColor.GRAY + " Choose a trail");

						sender.sendMessage(ChatColor.GRAY + "Want a full list of all your features? Visit our website at http://trintoncraft.com");

						sender.sendMessage(" ");
					}
					if (args[1].equalsIgnoreCase("member")) {
						sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Default Commands");

						sender.sendMessage(ChatColor.RED + "/auc" + ChatColor.GRAY + " Default command for Auctions");

						sender.sendMessage(ChatColor.RED + "/shop" + ChatColor.GRAY + " Open the shop GUI");

						sender.sendMessage(ChatColor.RED + "/balance or /money" + ChatColor.GRAY + " Check your money");

						sender.sendMessage(ChatColor.RED + "/help" + ChatColor.GRAY + " Brings up TrintonCore help menu");

						sender.sendMessage(ChatColor.RED + "/kit" + ChatColor.GRAY + " Choose a kit or view your kits with this");

						sender.sendMessage(ChatColor.RED + "/rankup" + ChatColor.GRAY + " When you meet the rankup requirements type this, you can view the rank prices with '/tc ranks'");

						sender.sendMessage(ChatColor.RED + "/pay" + ChatColor.GRAY + " Send money to another player");

						sender.sendMessage(ChatColor.RED + "/emotd" + ChatColor.GRAY + " View the latest news about Trinton");

						sender.sendMessage(ChatColor.RED + "/jobs" + ChatColor.GRAY + " Choose a job to earn money");

						sender.sendMessage(ChatColor.RED + "/warp" + ChatColor.GRAY + " Teleport to a warp point");

						sender.sendMessage(ChatColor.RED + "/pv" + ChatColor.GRAY + " Open your player vault (Used for storing valuable items)");

						sender.sendMessage(ChatColor.RED + "/tpa" + ChatColor.GRAY + " Teleport to another player");

						sender.sendMessage(ChatColor.RED + "/tpaccept" + ChatColor.GRAY + " Accept a teleport request");

						sender.sendMessage(ChatColor.RED + "/home" + ChatColor.GRAY + " Teleport to your home point");

						sender.sendMessage(ChatColor.RED + "/sethome" + ChatColor.GRAY + " Set your home point");

						sender.sendMessage(ChatColor.RED + "/tc pkilla" + ChatColor.GRAY + " Get killazombiecow's attention");

						sender.sendMessage(ChatColor.RED + "/tc pjay" + ChatColor.GRAY + " Get OhLookItsJay's attention");

					}
				}
			} else if (args[0].equalsIgnoreCase("website")) {
				sender.sendMessage(prefix + ChatColor.GRAY + "The website URL is: " + getConfig().getString("Website.URL"));
			} else if (args[0].equalsIgnoreCase("donate")) {
				sender.sendMessage(prefix + ChatColor.GRAY + "You can donate at: " + getConfig().getString("Website.Donate-URL"));
			} else if (args[0].equalsIgnoreCase("shoutcast")) {
				if (sender.hasPermission("tc.vipplus")) {
					final Player p = (Player) sender;
					if (cooldownTime.containsKey(p)) {
						sender.sendMessage(ChatColor.RED + "You cannot use this command for another " + cooldownTime.get(p) + " seconds");
						return true;
					}
					if (args.length == 1) {
						sender.sendMessage(ChatColor.RED + "You must provide a message to broadcast!");
						return true;
					}
					StringBuilder message = new StringBuilder();
					if (args.length > 1) {
						message.append(args[1]);
						for (int i = 2; i < args.length; i++) {
							message.append(" ");
							message.append(args[i]);
						}
						Bukkit.broadcastMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "[" + sender.getName() + "]" + ChatColor.RED + " shouts " + ChatColor.GRAY + "" + ChatColor.BOLD + message);
						
						cooldownTime.put(p, 5);
						cooldownTask.put(p, new BukkitRunnable() {
							public void run() {
								cooldownTime.put(p, cooldownTime.get(p) - 1);
								if (cooldownTime.get(p) == 0) {
									cooldownTime.remove(p);
									cooldownTask.remove(p);
									cancel();
								}
							}
						});
						cooldownTask.get(p).runTaskTimer(this, 20, 20);

						return true;
					}
				} 
			} else if (args[0].equalsIgnoreCase("pkilla")) {
				try {
					final Player target = Bukkit.getPlayer("killazombiecow");
					new BukkitRunnable() {
						public void run() {
							target.playSound(target.getLocation(), Sound.NOTE_PLING, 50.0F, 5.0F);

							target.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + sender.getName() + ChatColor.RED + " wants your attention!" + ChatColor.RED + " <<");
						}
					}.runTaskLater(this, 20L);
				} catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Sorry, but killazombiecow is not online and cannot be pinged!");
				}
				sender.sendMessage(ChatColor.GREEN + "The owner, killazombiecow has been pinged, he will get back to you shortly.");
			} else if (args[0].equalsIgnoreCase("pjay")) {
				try {
					final Player target = Bukkit.getPlayer("OhLookItsJay");
					new BukkitRunnable() {
						public void run() {
							target.playSound(target.getLocation(), Sound.NOTE_PLING, 50.0F, 5.0F);

							target.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + sender.getName() + ChatColor.RED + " wants your attention!" + ChatColor.RED + " <<");
						}
					}.runTaskLater(this, 20L);
				} catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Sorry, but OhLookItsJay is not online and cannot be pinged!");
				}
				sender.sendMessage(ChatColor.GREEN + "The owner, OhLookItsJay has been pinged, he will get back to you shortly.");
			} else {
				if (args[0].equalsIgnoreCase("author")) {
					PluginDescriptionFile pdf = getDescription();

					sender.sendMessage(ChatColor.GRAY + "TrintonCore Plugin Info");

					sender.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.RED + "killazombiecow");

					sender.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.RED + pdf.getVersion());
				} else if (args[0].equalsIgnoreCase("ping")) {
					if ((sender.hasPermission("tc.ping"))){
						if (args.length != 2) {
							sender.sendMessage(ChatColor.RED + "Error! Not enough or too many arguments! Usage: /tc ping [player]");
						}
						try {
							final Player target = Bukkit.getPlayer(args[1]);

							new BukkitRunnable() {
								public void run() {
									target.playSound(target.getLocation(), Sound.NOTE_PLING, 50.0F, 5.0F);

									target.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GREEN + " wants your attention!");
								}
							}.runTaskLater(this, 20L);
						} catch (Exception ex) {
							sender.sendMessage(ChatColor.RED + "Sorry, but that player is not online and cannot be notified!");
						}
						sender.sendMessage(ChatColor.GREEN + args[1] + " has been pinged, they should get back to you shortly.");
					} else {
						sender.sendMessage(ChatColor.RED + "You must donate for this feature!");
					}
				} else if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("tc.reload")) {
						reloadConfig();

						saveConfig();

						sender.sendMessage(ChatColor.GREEN + "TrintonCore reloaded");
					} else {
						sender.sendMessage(ChatColor.RED + "You don't have permission to type " + ChatColor.GREEN + "/" + ChatColor.GREEN + args[0]);
					}
				}
				if (args[0].equalsIgnoreCase("ranks")) {
					sender.sendMessage(ChatColor.GRAY + "TrintonCraft Ranks:");

					sender.sendMessage(ChatColor.RED + "1. Citizen");

					sender.sendMessage(ChatColor.RED + "2. " + getConfig().getString("Ranks.Knight.Name") + " | " + getConfig().getString("Ranks.Knight.Cost"));

					sender.sendMessage(ChatColor.RED + "3. " + getConfig().getString("Ranks.Duke.Name") + " | " + getConfig().getString("Ranks.Duke.Cost"));

					sender.sendMessage(ChatColor.RED + "4. " + getConfig().getString("Ranks.Baron.Name") + " | " + getConfig().getString("Ranks.Baron.Cost"));

					sender.sendMessage(ChatColor.RED + "5. " + getConfig().getString("Ranks.Regent.Name") + " | " + getConfig().getString("Ranks.Regent.Cost"));

					sender.sendMessage(ChatColor.RED + "6. " + getConfig().getString("Ranks.Prince.Name") + " |" + getConfig().getString("Ranks.Prince.Cost"));

					sender.sendMessage(ChatColor.RED + "7. " + getConfig().getString("Ranks.King.Name") + " | " + getConfig().getString("Ranks.King.Cost"));

					sender.sendMessage(ChatColor.RED + "8. " + getConfig().getString("Ranks.Emperor.Name") + " | " + getConfig().getString("Ranks.Emperor.Cost"));

					sender.sendMessage(ChatColor.RED + "9. " + getConfig().getString("Ranks.GrandMaster.Name") + " | " + getConfig().getString("Ranks.GrandMaster.Cost"));

					sender.sendMessage(ChatColor.RED + "10. " + getConfig().getString("Ranks.Overlord.Name") + " | " + getConfig().getString("Ranks.Overlord.Cost"));
				} else if (args[0].equalsIgnoreCase("staff")) {
					if (args.length == 1) {

						sender.sendMessage(ChatColor.GRAY + "Staff List");

						sender.sendMessage(ChatColor.RED + "/tc staff owners" + ChatColor.GRAY + " List of all owners");

						sender.sendMessage(ChatColor.RED + "/tc staff devs" + ChatColor.GRAY + " List of all developers");

						sender.sendMessage(ChatColor.RED + "/tc staff admins" + ChatColor.GRAY + " List of all admins");

						sender.sendMessage(ChatColor.RED + "/tc staff mods" + ChatColor.GRAY + " List of all moderators");

						sender.sendMessage(ChatColor.RED + "/tc staff builders" + ChatColor.GRAY + " List of all builders");

						sender.sendMessage(ChatColor.RED + "/tc staff helpers" + ChatColor.GRAY + " List of all helpers");

					}

					if (args.length == 2) {

						if (args[1].equalsIgnoreCase("owners")) {

							sender.sendMessage(ChatColor.RED + "Owner: Killazombiecow");

							sender.sendMessage(ChatColor.RED + "Co-Owner: " + getConfig().getString("Staff.co-owner"));
						}

						if (args[1].equalsIgnoreCase("devs")) {
							
							sender.sendMessage(ChatColor.RED + "Developers: " + ChatColor.RED + getConfig().getStringList("Developers"));

						}

						if (args[1].equalsIgnoreCase("admins")) {
							
							sender.sendMessage(ChatColor.GOLD + "Admins: " + getConfig().getStringList("Admins"));

						}

						if (args[1].equalsIgnoreCase("mods")) {
							
							sender.sendMessage(ChatColor.DARK_PURPLE + "Mods: " + getConfig().getStringList("Moderators"));
							
						}

						if (args[1].equalsIgnoreCase("builders")) {
							
							sender.sendMessage(ChatColor.DARK_GREEN + "Builders: " + getConfig().getStringList("Builders"));

						}

						if (args[1].equalsIgnoreCase("helpers")) {
							
							sender.sendMessage(ChatColor.YELLOW + "Helpers: " + getConfig().getStringList("Helpers"));

						}

					}
				} else if (args[0].equalsIgnoreCase("clear")) {
					for (int i = 0; i < 100; i++) {
						sender.sendMessage("");
					}
					sender.sendMessage(prefix + ChatColor.GRAY + getConfig().getString("General.self-cleared"));
				} else if (args[0].equalsIgnoreCase("gclear")) {
					if (sender.hasPermission("tc.gclear")) {
						for (int i = 0; i < 100; i++) {
							Bukkit.broadcastMessage(" ");
						}
						Bukkit.broadcastMessage(ChatColor.RED + "Chat cleared by " + ChatColor.BOLD + sender.getName());
					} else if (!sender.hasPermission("tc.gclear")) {
						sender.sendMessage(ChatColor.RED + "You don't have permission to type " + ChatColor.GREEN + "/" + ChatColor.GREEN + args[0]);
					}
				}
			}
		}
		return false;
	}
}
