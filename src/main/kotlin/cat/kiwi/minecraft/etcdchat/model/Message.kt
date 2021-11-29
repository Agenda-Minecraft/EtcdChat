package cat.kiwi.minecraft.etcdchat.model

import com.google.gson.Gson

data class Message(
    val type: String,
    val meta: MessageType,
    val content: MCMessage,
) {
}

data class PrivateMessage(
    val source: String,
    val target: String
) : MessageType()

data class PublicMessage(val boardCast: Boolean) : MessageType()

open class MessageType {}

fun Message.toJson() = Gson().toJson(this, Message::class.java)
