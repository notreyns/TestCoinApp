package com.example.rei.data.mappers

abstract class SimpleEntityDataMapper<TDestination, TSource> {

    abstract fun transform(entity: TSource): TDestination

    fun transformCollection(entities: Collection<TSource>): Collection<TDestination> {

        val domainModels: MutableCollection<TDestination> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transform(entity)
            domainModels.add(domainModel)
        }
        return domainModels
    }

}