package solid.inject.core

class VisualProviderRegistry(
  private val registry: ProviderRegistry = CoreProviderRegistry())
  : ProviderRegistry by registry
{
  override fun register(id: String, provider: Provider)
  {
    registry.register(id, PrintingProvider(id, provider))
  }

  override fun fork(): ProviderRegistry
  {
    return VisualProviderRegistry(registry.fork())
  }

  private class PrintingProvider(
    val id: String,
    val provider: Provider) : Provider
  {
    override fun invoke(registry: ProviderRegistry): Any?
    {
      print("""{ "dependencies": [ """)
      val instance = provider.invoke(registry)
      val name = (instance as Any)
        .toString()
        .substringAfterLast('@')
      print(""" ], "id": "$id", "instance": "$name" },""")
      return instance
    }
  }
}