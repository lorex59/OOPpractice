package `lesson4-Factory`

open class Dough;
open class Sauce;
open class Cheese;
open class Pepperoni;
open class Clams;

class ThinCrustDough(): Dough();
class MarinaraSauce(): Sauce();
class ReggianoCheese(): Cheese();
class SlicedPepperoni(): Pepperoni();
class FreshClams(): Clams();


interface PizzaIngredientFactory{
    fun createDough(): Dough;
    fun createSauce(): Sauce;
    fun createCheese(): Cheese;
    fun createClam(): Clams;
    fun createPepperoni(): Pepperoni;
}

class NYPizzaIngredientFactory(): PizzaIngredientFactory{

    override fun createDough():Dough {
        return ThinCrustDough();
    }

    override fun createCheese(): Cheese {
        return ReggianoCheese();
    }

    override fun createClam(): Clams {
        return FreshClams();
    }

    override fun createSauce(): Sauce {
        return MarinaraSauce();
    }
    override fun createPepperoni(): SlicedPepperoni {
        return SlicedPepperoni();
    }
}

abstract class Pizza{
    lateinit var name: String;
    lateinit var dough: Dough;
    lateinit var sauce: Sauce;
    lateinit var pepperoni: Pepperoni;
    lateinit var clams: Clams;
    lateinit var cheese: Cheese;
    abstract fun prepare();
    fun bake(){
        println("Bake for 25 minutes");
    }
    fun cut(){
        println("Cutting the pizzZZZZzzaaaa");
    }
    fun box(){
        println("Pizza in box");
    }
    @JvmName("setName1")
    fun setName(name: String){
        this.name = name;
    }
    @JvmName("getName1")
    fun getName(): String {
        return name;
    }
}
class CheesePizza(var ingredient: PizzaIngredientFactory): Pizza(){
    override fun prepare() {
        println("Making $name");
        dough = ingredient.createDough();
        sauce = ingredient.createSauce();
        cheese = ingredient.createCheese();
    }
}
class ClamPizza(var ingredient: PizzaIngredientFactory): Pizza(){
    override fun prepare() {
        println("Making $name");
        dough = ingredient.createDough();
        sauce = ingredient.createSauce();
        cheese = ingredient.createCheese();
        clams = ingredient.createClam();
    }
}
class PepperoniPizza(var ingredient: PizzaIngredientFactory): Pizza(){
    override fun prepare() {
        println("Making $name");
        dough = ingredient.createDough();
        sauce = ingredient.createSauce();
        pepperoni = ingredient.createPepperoni();
        clams = ingredient.createClam();
    }
}
abstract class PizzaStore() {

    fun OrderPizza(type:String): Pizza? {
        var pizza = createPizza(type);
        pizza?.prepare();
        pizza?.bake();
        pizza?.cut();
        pizza?.box();
        return pizza;
    }
    abstract fun createPizza(type: String): Pizza?;
}

class NYPizzaStore: PizzaStore(){
    override fun createPizza(type: String): Pizza? {
        var ingredientFactory: PizzaIngredientFactory = NYPizzaIngredientFactory();
        var pizza: Pizza? = null;
        when (type){
            "cheese" ->{
                pizza = CheesePizza(ingredientFactory);
                pizza.setName("Very good NY pizza with cheese");
            }
            "clam" ->{
                pizza = ClamPizza(ingredientFactory);
                pizza.setName("VEry GOOD NY pizza with clam")
            }
            "pepperoni" ->{
                pizza = PepperoniPizza(ingredientFactory);
                pizza.setName("VEry GOOD NY pizza with Pepperoni")
            }
        }
        return pizza;
    }
}
class ChicagoPizzaStore: PizzaStore(){
    override fun createPizza(type: String): Pizza? {
        var ingredientFactory: PizzaIngredientFactory = NYPizzaIngredientFactory();
        var pizza: Pizza? = null;
        when (type){
            "cheese" ->{
                pizza = CheesePizza(ingredientFactory);
                pizza.setName("Very good Chicago pizza with cheese");
            }
            "clam" ->{
                pizza = ClamPizza(ingredientFactory);
                pizza.setName("VEry GOOD Chicago pizza with clam")
            }
            "pepperoni" ->{
                pizza = PepperoniPizza(ingredientFactory);
                pizza.setName("VEry GOOD Chicago pizza with Pepperoni")
            }
        }
        return pizza;
    }
}

fun main(){
    var nyPizzaStore = ChicagoPizzaStore();
    nyPizzaStore.OrderPizza("pepperoni");
}