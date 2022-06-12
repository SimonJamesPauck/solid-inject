package solid.inject.core

class CoreProviderRegistry(
  private val registrations: MutableMap<String, Provider> = LinkedHashMap(),
  private val scopes: MutableMap<String, MutableSet<String>> = LinkedHashMap()) : ProviderRegistry
{

  override fun bind(
    abstractionId: String,
    implementerId: String)
  {
    registrations[abstractionId] = BindingProvider(implementerId)
  }

  override fun register(
    id: String,
    provider: Provider)
  {
    registrations[id] = provider
  }

  override fun scope(
    toThat: String,
    scopeThis: String)
  {
    scopes
      .getOrPut(toThat, ::mutableSetOf)
      .add(scopeThis)
  }

  override fun gimme(id: String): Provider?
  {
    // Potential for optional call here
    return ScopedProvider.from(
      scopes[id] ?: emptySet(),
      registrations[id])
  }

  override fun fork(): ProviderRegistry
  {
    return CoreProviderRegistry(
      LinkedHashMap(registrations),
      LinkedHashMap(scopes))
  }
}
