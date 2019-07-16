package solid.inject.core

interface ProviderRegistry
{
  fun bind(
    abstractionId: String,
    implementerId: String)

  fun register(
    id: String,
    provider: Provider)

  fun scope(
    toThat: String,
    scopeThis: String)

  fun gimme(
    id: String): Provider?

  fun fork(): ProviderRegistry
}
