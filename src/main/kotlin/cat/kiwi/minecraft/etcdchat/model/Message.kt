package cat.kiwi.minecraft.etcdchat.model

import com.google.gson.Gson

data class Message(
    val type: String,
    val meta: MessageMeta,
    val content: MCMessage,
) {
}


open class MessageMeta(
    val source: String,
    val target: String,
    val fromServer: String

)

fun Message.toJson(): String = Gson().toJson(this, Message::class.java)
