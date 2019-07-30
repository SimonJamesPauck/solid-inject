import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeVariableName

fun main()
{
  FileSpec
    .builder("solid.inject", "")
    .addRegisterMethods(8)
    .build()
    .writeTo(System.out)
}

internal fun FileSpec.Builder.addRegisterMethods(
  numberOfMethods: Int): FileSpec.Builder
{
  for (numberOfParameters in 0..numberOfMethods)
  {
    this.addFunction(generateRegisterFunc(numberOfParameters))
  }

  return this
}

internal fun generateRegisterFunc(numberOfParameters: Int): FunSpec
{
  val typeVariable = TypeVariableName("K").copy(reified = true)
  val parameterName = "constr"

  val args = Array(numberOfParameters) { "gimme()" }.joinToString()

  val typeParameters = (0 until numberOfParameters)
    .map { i -> ('A' + i).toString() }
    .map { name -> TypeVariableName(name) }
    .map { it.copy(reified = true) }

  val registerMethod = FunSpec.builder("register")
    .receiver(TypeVariableName("Injection"))
    .addModifiers(KModifier.INLINE)
    .addTypeVariable(typeVariable.copy(reified = true))
    .addTypeVariables(typeParameters)
    .addParameter(
      parameterName,
      LambdaTypeName.get(
        parameters = typeParameters.map { ParameterSpec.unnamed(it) },
        returnType = typeVariable),
      KModifier.NOINLINE)
    .addStatement("provider { $parameterName($args) }")

  return registerMethod.build()
}