package solid.inject.debug

import solid.inject.core.CoreProviderRegistry
import solid.inject.core.Provider
import solid.inject.core.ProviderRegistry

internal class DebugProviderRegistry(
  private val registry: ProviderRegistry = CoreProviderRegistry(),
  private val debug: InjectionDebug)
  : ProviderRegistry by registry
{
  override fun register(id: String, provider: Provider)
  {
    registry.register(id, DebugContextProvider(id, provider, debug))
  }

  override fun fork(): ProviderRegistry
  {
    return DebugProviderRegistry(registry.fork(), debug)
  }
}