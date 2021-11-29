package cat.kiwi.minecraft.etcdchat.listener

import cat.kiwi.minecraft.etcdchat.Config
import cat.kiwi.minecraft.etcdchat.EtcdChat
import cat.kiwi.minecraft.etcdchat.model.tagPublic
import cat.kiwi.minecraft.etcdchat.model.toJson
import cat.kiwi.minecraft.etcdchat.repository.EtcdClient
import cat.kiwi.minecraft.etcdchat.util.applyFormatCode
import cat.kiwi.minecraft.etcdchat.util.toByteSequence
import cat.kiwi.minecraft.etcdchat.util.toMessage
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class Events: Listener {
    init {
       etcdWatcher()
    }
    @EventHandler(ignoreCancelled = true)
    fun onAsyncPlayerChatEvent(e: AsyncPlayerChatEvent) {
        val finalMsg = PlaceholderAPI.setPlaceholders(e.player, Config.defaultPattern) + e.message
        EtcdClient.client.kvClient.put(
            Config.etcdRoute.toByteSequence(),
            finalMsg.applyFormatCode().tagPublic().toJson().toByteSequence()
        )
        e.isCancelled = true
    }
    private fun etcdWatcherTask() {
        EtcdClient.client.watchClient.watch(Config.etcdRoute.toByteSequence()) {
            it.events.forEach { event ->
                val msg = event.keyValue.value.toString().toMessage()
                when(msg.type) {
                    "public" -> {
                            Bukkit.getOnlinePlayers().forEach {
                                it.sendMessage(msg.content.message)
                        }
                    }
                    "private" -> {

                    }
                }
            }
            it.events[0].keyValue.value
        }
    }
    private fun etcdWatcher() {
        Bukkit.getServer().scheduler.runTaskLaterAsynchronously(
            EtcdChat.instance,
            ::etcdWatcherTask,
            20L
        )
    }
}