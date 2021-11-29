package cat.kiwi.minecraft.etcdchat

import java.util.*

object Config {
    var etcdAddress = ""
    var defaultPattern = ""
    var etcdRoute = ""
    var tag = UUID.randomUUID().toString()
    fun readConfig() = with(EtcdChat.instance.config) {
        options().copyDefaults(true)

        etcdAddress = getString("etcd-address")!!
        defaultPattern = getString("default-pattern")!!
        etcdRoute = getString("etcd-route")!!

    }
}