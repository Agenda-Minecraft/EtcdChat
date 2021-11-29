package cat.kiwi.minecraft.etcdchat.model

import cat.kiwi.minecraft.etcdchat.Config
import cat.kiwi.minecraft.etcdchat.Config.tag


data class MCMessage(
    val message: String,
)

fun MCMessage.tagPrivate(source: String, target: String) = Message("private", MessageMeta(source, target,Config.tag), this)
fun MCMessage.tagPublic() = Message("public", MessageMeta("public", "public", Config.tag), this)
