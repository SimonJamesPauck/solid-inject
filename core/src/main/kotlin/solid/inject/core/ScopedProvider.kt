package solid.inject.core

/*
 * Private constructor here forces using the companion
 * builder function.
 */
internal class ScopedProvider private constructor(
  private val scopedToThis: MutableSet<String>,
  private val wrap: Provider?) : Provider
{
  companion object
  {
    /**
     * Only wraps with scoping if it is needed.
     */
    fun from(
      scopedToThis: MutableSet<String>,
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
