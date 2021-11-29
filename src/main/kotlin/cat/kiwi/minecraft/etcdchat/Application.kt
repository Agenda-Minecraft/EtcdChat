package cat.kiwi.minecraft.etcdchat

import cat.kiwi.minecraft.etcdchat.model.*
import cat.kiwi.minecraft.etcdchat.utils.applyFormatCode
import cat.kiwi.minecraft.etcdchat.utils.toMessage
import com.google.gson.Gson
import io.etcd.jetcd.ByteSequence
import io.etcd.jetcd.Client


class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val msgJson1 = "&fpri".applyFormatCode().tagPrivate("s","t").toJson()
            val msgJson2 = "&fpub".applyFormatCode().tagPublic().toJson()

            println(msgJson1)
            println(msgJson2)

            val umJson1 = msgJson1.toMessage()
            val umJson2 = msgJson2.toMessage()

            when(umJson1.type) {
                "public" -> println("PrivateMessage: $umJson1")
                "private" -> println("PublicMessage $umJson1")
            }

            when(umJson2.type) {
                "public" -> println("PrivateMessage: $umJson2 ")
                "private" -> println("PublicMessage $umJson2")
            }




//            val client: Client = Client.builder().target("ip:///127.0.0.1:12379").build()
//            val kvClient = client.kvClient
//            println(msg)
//            kvClient.put("foo".toByteSequence(),"bar".toByteSequence())
//            println(kvClient.get("foo".toByteSequence()).get())

        }
    }
}