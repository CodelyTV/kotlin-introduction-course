package com.codely.demo.cat

import com.codely.demo.cat.Cat.Id
import com.codely.demo.cat.Cat.Name
import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CatCreator(
    private val reader: Reader,
    private val writer: Writer,
    private val clock: Clock,
    private val repository: CatRepository,
    private val breedSearcher: BreedSearcher
) {
    private lateinit var breedList: List<String>
    fun create(): Cat {
        runBlocking {
            loadBreeds()
        }
        val id = Id.from(obtainInput("Please enter an id for your cat"))
        val name = Name.from(obtainInput("Please enter the name of your cat"))
        val origin = Cat.Origin.from(obtainInput("Please enter where your cat came from"))
        val vaccinated = Cat.Vaccinated.from(obtainInput("Is your cat vaccinated?"))
        val color = Cat.Color.from(obtainInput("What is the color of your cat?"))
        val birthDate = Cat.BirthDate.from(obtainInput("When did your cat birth <yyyy-MM-dd>?"))
        val breed = obtainBreed()
        return Cat.from(
            id,
            name,
            origin,
            birthDate,
            color,
            vaccinated,
            clock.now(),
            breed,
        ).apply {
            repository.save(this)
        }.also {
            writer.write("Your cat has been successfully created $it")
        }
    }

    private suspend fun loadBreeds() = coroutineScope {
        launch {
            breedList = breedSearcher.search().map { it.value }
            writer.write(">>>>>>All breeds loaded!<<<<<<")
        }
        writer.write("Loading breeds,we'll let you know when it's done")
    }

    private fun obtainBreed(): Cat.Breed {
        val breed = obtainInput("What is your cat breed? The supported options are: $breedList")
        if (breed.isNullOrEmpty() || !breedList.contains(breed.lowercase())) {
            throw InvalidBreed(breed)
        }

        return Cat.Breed(breed)
    }

    private fun obtainInput(message: String) = writer.write(message).run { reader.read() }
}
