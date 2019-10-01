package solid.inject.core

class CoreProviderRegistry(
  private val bindings: MutableMap<String, String> = LinkedHashMap(),
  private val registrations: MutableMap<String, Provider> = LinkedHashMap(),
  private val scopes: MutableMap<String, MutableSet<String>> = LinkedHashMap()) : ProviderRegistry
{

  override fun bind(
    abstractionId: String,
    implementerId: String)
  {
    bindings[abstractionId] = implementerId
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
    scopesFor(toThat).add(scopeThis)
  }

  private fun scopesFor(
    id: String): MutableSet<String>
  {
    return scopes.getOrPut(id, ::mutableSetOf)
  }

  override fun gimme(id: String): Provider?
  {
    val providerId = bindings[id] ?: id
    val provider = registrations[providerId]

    // Potential for optional call here
    return ScopedProvider.from(scopesFor(id), provider)
  }

  override fun fork(): ProviderRegistry
  {
    return CoreProviderRegistry(
      LinkedHashMap(bindings),
      LinkedHashMap(registrations),
      LinkedHashMap(scopes))
  }
}