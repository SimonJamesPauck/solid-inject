package solid.inject.debug

import solid.inject.core.Provider
import solid.inject.core.ProviderRegistry

internal class DebugOutputProvider(
  private val provider: Provider,
  private val debug: InjectionDebug) : Provider
{
  override fun invoke(registry: ProviderRegistry): Any?
  {
    val context = ContextualRegistry.from(registry)
    val instance = provider.invoke(context)
    context.output(debug)

    return instance
  }
}
