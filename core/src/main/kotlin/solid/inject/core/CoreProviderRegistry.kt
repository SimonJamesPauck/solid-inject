package solid.inject.core

class CoreProviderRegistry : ProviderRegistry
{
  private val bindings = HashMap<String, String>()
  private val registrations = HashMap<String, Provider>()

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

  override fun gimme(
    id: String): Provider?
  {
    val providerId = bindings[id] ?: id
    return registrations[providerId]
  }
}