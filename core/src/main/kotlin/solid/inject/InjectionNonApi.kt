package solid.inject

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.lang.reflect.WildcardType

@PublishedApi
internal inline fun <reified K> typeKey(): String
{
  val typeOf = typeOf<K>()
  if (containsTypeVariable(typeOf)) throw IllegalArgumentException()
  return typeOf.typeName
}

@PublishedApi
internal fun containsTypeVariable(typeOf: Type): Boolean
{
  return when (typeOf)
  {
    is ParameterizedType -> typeOf.actualTypeArguments.any(::containsTypeVariable)
    is WildcardType -> typeOf.upperBounds.any(::containsTypeVariable)
      || typeOf.lowerBounds.any(::containsTypeVariable)
    is TypeVariable<*> -> true
    is Class<*> -> false
    else -> throw NotImplementedError()
  }
}

// See: https://stackoverflow.com/questions/36253310/how-to-get-actual-type-arguments-of-a-reified-generic-parameter-in-kotlin
// And: https://gafter.blogspot.com/2006/12/super-type-tokens.html

/**
 * Should be replaced by the stdlib equivalent with the same name
 * when it is no longer experimental
 */
@PublishedApi
internal inline fun <reified V> typeOf(): Type
{
  return object : TypeReference<V>()
  {}.type
}

@PublishedApi
internal abstract class TypeReference<T>
{
  val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}
