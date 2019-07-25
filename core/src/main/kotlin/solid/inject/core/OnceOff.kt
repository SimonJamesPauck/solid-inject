package solid.inject.core

internal class OnceOff(initialProvider: Provider<*>) : Provider<Any?>
{
  private var provider: Provider<*>

  init
  {
    provider = { context ->
      val instance = initialProvider.invoke(context)
      provider = { instance }
      instance
    }
  }

  override fun invoke(registry: ProviderRegistry) = provider.invoke(registry)
}