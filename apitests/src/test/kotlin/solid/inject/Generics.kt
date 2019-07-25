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
    inject.registerGeneric(object : Injection.GenProv<List<*>>
    {
      override fun <T> give(injection: ProviderRegistry): MutableList<T> = ArrayList()
    })

    val instance = inject.gimmeGeneric<MutableList<Scoping.C>>()

    instance.add(Scoping.C())
    assertThat(instance).isNotNull
  }

}