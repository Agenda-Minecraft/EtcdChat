package cat.kiwi.minecraft.etcdchat.model

import com.google.gson.Gson


data class MCMessage(
    val message: String,
)

fun MCMessage.tagPrivate(source: String, target: String) = Message("private", PrivateMessage(source, target), this)
fun MCMessage.tagPublic() = Message("public", PublicMessage(true), this)
