package com.app.proyectokmm

class IOSPlatform: Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()