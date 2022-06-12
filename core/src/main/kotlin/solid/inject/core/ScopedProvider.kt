package solid.inject.core

internal class ScopedProvider
/*
 * Private constructor here forces using the companion
 * builder function.
 */
private constructor(
  private val scopedToThis: Set<String>,
  private val wrap: Provider?) : Provider
{
  companion object
  {
    /**
     * Only wraps with scoping if it is needed.
     */
    fun from(
      scopedToThis: Set<String>,
      wrap: Provider?): Provider?
    {
      return if (scopedToThis.isEmpty()) wrap
      else ScopedProvider(scopedToThis, wrap)
    }
  }

  override fun invoke(context: ProviderRegistry): Any?
  {
    val scopedInject = context.fork()

    for (typeId in scopedToThis)
    {
      scopedInject.register(
        typeId,
        OnceOff(context.gimme(typeId)!!))
    }
    // Potential for optional call here
    return wrap!!.invoke(scopedInject)
  }
}
