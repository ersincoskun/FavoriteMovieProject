package com.ersincoskun.taskapp.model

data class Response(
    val page: Long,
    val results: MutableList<Result>
)