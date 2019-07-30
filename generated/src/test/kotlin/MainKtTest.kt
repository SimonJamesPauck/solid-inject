import com.squareup.kotlinpoet.FileSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MainKtTest
{
  @Test
  fun `generate 'register' method with no constructor paramaters`()
  {
    val expected =
      """
      inline fun <reified K> Injection.register(noinline constr: () -> K) {
        provider { constr() }
      }
      """.trimIndent()

    val result = StringBuilder()
    FileSpec.builder("", "")
      .addFunction(generateRegisterFunc(0))
      .build()
      .writeTo(result)

    assertThat(result.toString().trimIndent()).isEqualTo(expected)
  }

  @Test
  fun `generate 'register' method with two constructor paramaters`()
  {
    val expected =
      """
      inline fun <reified K, reified A, reified B> Injection.register(noinline constr: (A, B) -> K) {
        provider { constr(gimme(), gimme()) }
      }
      """.trimIndent()

    val result = StringBuilder()
    FileSpec.builder("", "")
      .addFunction(generateRegisterFunc(2))
      .build()
      .writeTo(result)

    assertThat(result.toString().trimIndent()).isEqualTo(expected)
  }
}