package solid.inject.core

interface ScopedRegistry : ProviderRegistry
{
  fun scope(
    toThat: String,
    scopeThis: String)
}