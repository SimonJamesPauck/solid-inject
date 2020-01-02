package solid.inject

@PublishedApi
internal inline fun <reified K> typeKey(): String
{
  return K::class.qualifiedName!!
}
