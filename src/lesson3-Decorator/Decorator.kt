package `lesson3-Decorator`

interface Beverage{
    open var description: String;
    fun cost(): Double;
}

interface CondimentDecorator: Beverage{
    override var description: String;
}
class Espresso: Beverage{
    override var description = "Espresso";
    override fun cost():Double{
        return  2.5;
    }
}

class HouseBlend: Beverage{
    override var description = "House Blend Coffee";
    override fun cost(): Double {
        return 2.1;
    }
}

class Mocha(var beverage: Beverage): CondimentDecorator{
    override var description = beverage.description + "-MOCHA";
    override fun cost(): Double {
        return 0.50 + beverage.cost();
    }
}
class Whip(var beverage: Beverage):CondimentDecorator{
    override var description = beverage.description + "-WHIP";
    override fun cost(): Double {
        return 0.40 + beverage.cost();
    }
}

fun main(){
    var drink: Beverage = HouseBlend()
    drink = Mocha(drink)
    drink = Whip(drink)
    drink = Whip(drink)
    println(drink.description + " $" + drink.cost())

}