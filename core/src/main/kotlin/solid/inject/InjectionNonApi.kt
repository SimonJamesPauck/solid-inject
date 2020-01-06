package solid.inject

@PublishedApi
internal inline fun <reified K> typeKey(): String
{
  if (K::class.typeParameters.isNotEmpty())
  {
    throw IllegalArgumentException("Injecting generic types is not supported")
  }

  return K::class.qualifiedName!!
}
