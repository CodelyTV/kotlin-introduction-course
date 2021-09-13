package com.codely.demo.cat

sealed class CatCreationException(override val message: String?): IllegalArgumentException(message)

class InvalidName(name: String?): CatCreationException("<$name> is not a valid name")
class InvalidOrigin(origin: String?): CatCreationException("<$origin > is not a valid origin")
class InvalidColor(color: String?): CatCreationException("<$color> is not a valid color")
