package solid.inject.core

class OverrideProviderRegistry(
  private val wrapped: ProviderRegistry,
  private val overrides: ProviderRegistry) : ProviderRegistry by overrides
{
  override fun gimme(
    id: String): (Provider)?
  {
    return overrides.gimme(id) ?: wrapped.gimme(id)
  }
}