package solid.inject.debug

import solid.inject.core.Provider
import solid.inject.core.ProviderRegistry

internal class DebugContextProvider(
  private val typeId: String,
  private val provider: Provider,
  private val debug: InjectionDebug) : Provider
{
  override fun invoke(registry: ProviderRegistry): Any?
  {
    val context = if (registry is ContextualRegistry)
    {
      registry.newChild()
    }
    else
    {
      ContextualRegistry(
        registry,
        linkedMapOf())
    }

    val instance = provider.invoke(context)

    val dependant = context.compute(typeId, instanceId(instance!!))

    if (registry is ContextualRegistry)
    {
      registry.dependencies.add(dependant)
    }
    else
    {
      debug.onGimme(dependant)
    }

    return instance
  }

  private fun instanceId(instance: Any): String
  {
    return instance
      .toString()
      .substringAfterLast('@')
  }
}
