package cat.kiwi.minecraft.etcdchat

import cat.kiwi.minecraft.etcdchat.listener.Events
import cat.kiwi.minecraft.etcdchat.repository.EtcdClient
import org.bukkit.plugin.java.JavaPlugin

class EtcdChat: JavaPlugin() {
    companion object {
        lateinit var instance: EtcdChat
    }

    override fun onEnable() {
        logger.info("EtcdChat is enabled!")
        instance = this
        try {
            Config.readConfig()
        } catch (e: Exception) {
            logger.info("Cannot read the configuration file!")
            e.printStackTrace()
        }

        saveDefaultConfig()
        try {
             EtcdClient.init(Config.etcdAddress)
        }catch (e: Exception) {
           logger.warning("Cannot connect to etcd daemon(s)!")
        }
        server.pluginManager.registerEvents(Events(), this)
    }

    override fun onDisable() {
        logger.info("EtcdChat is disabled!")
    }

}