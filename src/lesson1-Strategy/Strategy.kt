package `lesson1-Strategy`

open class Duck(f: FlyB, q: QuackB)
{
    private var flyB: FlyB = f
    private var quackB: QuackB = q
    open fun BehaviorFly()
    {
        flyB.fly()
    }
    open fun BehaviorQuack()
    {
        quackB.quack()
    }
    fun setFlyB(f: FlyB)
    {
        flyB = f
    }
    fun setQuackB(q: QuackB)
    {
        quackB = q
    }

    fun swim()
    {
        println("SWIM")
    }
    open fun display(n: String)
    {
        println(n)
    }
}
interface FlyB
{
    fun fly()
}
class FlyYES: FlyB
{
    override fun fly() {
        println("I CAN FLY")
    }
}
class FlyNO: FlyB
{
    override fun fly() {
        println("I CANNOT FLY")
    }
}
interface QuackB
{
    fun quack()
}
class QuackYES: QuackB
{
    override fun quack() {
        println("QUACK!")
    }
}
class QuackNO: QuackB
{
    override fun quack() {
        println("...")
    }
}


fun main()
{
    var BlackDuck = Duck(FlyYES(), QuackYES())

    BlackDuck.BehaviorFly()
    BlackDuck.setFlyB(FlyYES())
    BlackDuck.BehaviorFly()

    BlackDuck.BehaviorQuack()
    BlackDuck.setQuackB(QuackNO())
    BlackDuck.BehaviorQuack()

    BlackDuck.display("BlackDuck")

}