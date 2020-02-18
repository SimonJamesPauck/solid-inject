package solid.inject.debug

import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.jupiter.api.Test
import solid.inject.Injection
import solid.inject.register

class DebugOutputTest
{
  /**
   * This test constructs a complex scoped scenario that
   * was most difficult to accurately capture with the
   * first implementation.
   */
  @Test
  fun `Debug output accurately represents scoped injected dependencies`()
  {
    val debug = TestDebug()
    val inject = Injection.debuggable()
    inject.register(DebugOutputTest::A)
    inject.register(DebugOutputTest::B)
    inject.register(DebugOutputTest::C)
    inject.scope<A, B>()
    inject.scope<B, C>()
    inject.scope<A, C>()

    val a = inject.gimme<A>(debug)

    assertThat(a.c).isSameAs(a.b.c)
    val infoA = debug.injectionInfo
    val parent = this::class.java.name
    assertThat(infoA.type).isEqualTo("$parent\$A")
    val infoB = infoA.dependencyOfType("$parent\$B")!!
    assertThat(infoA.dependencyOfType("$parent\$C")!!)
      .isEqualTo(infoB.dependencyOfType("$parent\$C")!!)
    assertThat(infoA.dependencyOfType("$parent\$C")!!.dependencies).isEmpty()
  }

  private fun InjectionInfo.dependencyOfType(typeId: String) = this
    .dependencies
    .firstOrNull { it.type == typeId }

  class TestDebug : InjectionDebug
  {
    lateinit var injectionInfo: InjectionInfo

    override fun onGimme(info: InjectionInfo)
    {
      this.injectionInfo = info
      print(JSONObject(info).toString(2))
    }
  }

  class A(val c: C, val b: B)

  class B(val c: C)

  class C
}