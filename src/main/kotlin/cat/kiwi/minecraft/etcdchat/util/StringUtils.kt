package cat.kiwi.minecraft.etcdchat.util

import cat.kiwi.minecraft.etcdchat.model.MCMessage
import cat.kiwi.minecraft.etcdchat.model.Message
import com.google.gson.Gson
import io.etcd.jetcd.ByteSequence
import net.md_5.bungee.api.ChatColor


private val hexColorPattern: Regex = "[&][#][0-9A-Fa-f]{6}".toRegex()
private const val chars = "23456789ABCDEFHKMNPQRSTUVWXYZ+$%"

fun String.toMessage(): Message {
    return Gson().fromJson(this, Message::class.java)
}

fun String.applyFormatCode() =
    MCMessage(ChatColor.translateAlternateColorCodes('&', convertConvenientHexColorCode(this)))

fun String.convertConvenientHexColorCode(message: String): String {
    val splittedMessage = message.split(hexColorPattern)
    val colorCodeIterator = hexColorPattern.findAll(message).iterator()
    val messageBuilder = StringBuilder()

    for (part in splittedMessage) {
        messageBuilder.append(part)
        if (colorCodeIterator.hasNext()) {
            messageBuilder.append(convertHexColorCode(colorCodeIterator.next().value))
        }
    }
    return messageBuilder.toString()
}

private fun convertHexColorCode(hexColorCode: String): String {
    val mainCode = hexColorCode.substring(2, 8)
    val codeBuilder = StringBuilder("&x")
    for (char in mainCode) {
        codeBuilder.append("&").append(char)
    }
    return codeBuilder.toString()
}

fun String.toByteSequence() = ByteSequence.from(this.toByteArray())
