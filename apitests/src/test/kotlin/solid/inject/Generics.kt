package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Generics
{
  @Test
  fun `gene`()
  {
    val inject = Injection()
    inject.registerGeneric<List<*>>(object : Injection.GenProv
    {
      override fun <T> give(injection: Injection): MutableList<T> = ArrayList()
    })

    val instance = inject.gimmeGeneric<MutableList<Scoping.C>, Any>()

    instance.add(Scoping.C())
    assertThat(instance).isNotNull
  }

}