package solid.inject.core

typealias Provider<T> = (ProviderRegistry) -> T?