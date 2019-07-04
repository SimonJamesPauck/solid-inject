package solid.inject.core

class CoreScopedRegistry(
  private val providerRegistry: ProviderRegistry)
  : ScopedRegistry, ProviderRegistry by providerRegistry
{
  private val scopes = mutableMapOf<String, MutableSet<String>>()

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
    // Potential for optional call here
    return scopedProvider(scopesFor(id), providerRegistry.gimme(id))
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
        val scopedInject = CoreScopedRegistry(
          OverrideProviderRegistry(
            context,
            CoreProviderRegistry()))

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