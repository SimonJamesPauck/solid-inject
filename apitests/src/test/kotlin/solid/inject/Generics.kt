package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import solid.inject.core.ProviderRegistry

class Generics
{
  @Test
  fun `gene`()
  {
    val inject = Injection()
    inject.registerGeneric(object : Injection.GenProv<Map<*,*>>
    {
      override fun <T> give(injection: ProviderRegistry) = HashMap<T, String>()
    })

    val instance = inject.gimmeGeneric<MutableMap<Scoping.C, String>>()

    instance.put(Scoping.C(), "")
    assertThat(instance).isNotNull
  }

}