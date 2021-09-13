package com.codely.demo.cat

import java.lang.IllegalArgumentException

sealed class CatCreationException(override val message: String?) : IllegalArgumentException(message)

class InvalidName(name: String?) : CatCreationException("<$name> is not a valid name")
class InvalidOrigin(origin: String?) : CatCreationException("<$origin > is not a valid origin")
class InvalidColor(color: String?) : CatCreationException("<$color> is not a valid color")
class InvalidVaccinated(vaccinated: String?) : CatCreationException("<$vaccinated> is not a valid vaccinated value use true or false instead")
class InvalidId(id: String?) : CatCreationException("<$id> is not a valid uuid value")
class InvalidBirthDate(birthDate: String?) : CatCreationException("<$birthDate> is not a valid date, the date should follow this format <yyy-mm-dd>")
