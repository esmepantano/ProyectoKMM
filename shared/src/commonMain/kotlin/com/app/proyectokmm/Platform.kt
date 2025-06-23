package com.app.proyectokmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform