package cat.kiwi.minecraft.etcdchat.repository

import io.etcd.jetcd.Client

object EtcdClient {
    lateinit var client: Client
    fun init(address: String) {
        client = Client.builder().target(address).build()
    }

}