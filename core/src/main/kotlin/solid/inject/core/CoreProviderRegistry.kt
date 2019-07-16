package solid.inject.core

class CoreProviderRegistry : ProviderRegistry
{
  private val bindings = HashMap<String, String>()
  private val registrations = HashMap<String, Provider>()
  private val scopes = mutableMapOf<String, MutableSet<String>>()

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
    return scopedProvider(scopesFor(id), provider)
  }

  private fun scopedProvider(
    theseScopes: MutableSet<String>,
    wrap: Provider?): Provider?
  {
    return if (theseScopes.isEmpty())
    {
      wrap
    }
    else
    {
      { context ->
        val scopedInject = OverrideProviderRegistry(
          context,
          CoreProviderRegistry())

        for (instanceId in theseScopes)
        {
          scopedInject.register(
            instanceId,
            OnceOff(gimme(instanceId)!!))
        }
        // Potential for optional call here
        wrap!!.invoke(scopedInject)
      }
    }
  }
}