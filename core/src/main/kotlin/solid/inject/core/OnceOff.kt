package solid.inject.core

internal class OnceOff(initialProvider: Provider) : Provider
{
  private var provider: Provider

  init
  {
    provider = { context ->
      val instance = initialProvider.invoke(context)
      provider = { instance }
      instance
    }
  }

  override fun invoke(registry: ScopedRegistry) = provider.invoke(registry)
}