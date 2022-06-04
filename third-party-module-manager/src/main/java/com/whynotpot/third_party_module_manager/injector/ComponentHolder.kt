package com.whynotpot.third_party_module_manager.injector

// Базовый интерфейс зависимостей компонента//todo delete?
interface BaseFeatureDependencies {
    // Что такое dependencyHolder будет описано ниже в разделе Dependency Holder.
    // Пока можно не заострять на этом внимание.
    val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
}

// Базовый интерфейс для API.
// Просто маркер для FeatureAPI модуля.
interface BaseFeatureAPI

// Базовый интерфейс ComponentHolder.
interface ComponentHolder<A : BaseFeatureAPI, D : BaseFeatureDependencies> {
    var dependencyProvider: (() -> D)?
    fun get(): A
}