package com.harismexis.cosmos.workshared.extensions

fun String?.extractYouTubeVideoId(): String? {
    if (this == null) return null
    if (!this.contains("youtube")) return null
    val embed = "/embed/"
    val startIndex: Int = this.lastIndexOf(embed) + embed.length
    if (startIndex <= 0) return null
    val endIndex: Int = this.indexOf("?")
    if (endIndex <= 0) return null
    return this.substring(startIndex, endIndex)
}