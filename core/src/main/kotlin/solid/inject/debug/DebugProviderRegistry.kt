package solid.inject.debug

import solid.inject.core.CoreProviderRegistry
import solid.inject.core.Provider
import solid.inject.core.ProviderRegistry

class DebugProviderRegistry(
  private val registry: ProviderRegistry = CoreProviderRegistry())
  : ProviderRegistry by registry
{
  override fun register(id: String, provider: Provider)
  {
    registry.register(id, DebugContextProvider(id, provider))
  }

  override fun fork(): ProviderRegistry
  {
    return DebugProviderRegistry(registry.fork())
  }

  fun gimme(id: String, debug: InjectionDebug): Provider?
  {
    return DebugOutputProvider(registry.gimme(id)!!, debug)
  }
}